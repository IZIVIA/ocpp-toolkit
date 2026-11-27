package com.izivia.ocpp.websocket

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.utils.TimestampZoneContext
import com.izivia.ocpp.utils.escapedForLog
import com.izivia.ocpp.utils.notifySafely
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.ConnectionAuthorizer
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import mu.KotlinLogging
import org.http4k.server.Http4kServer
import java.time.ZoneId
import java.util.*
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.reflect.KClass
import com.izivia.ocpp.OcppVersion as OcppVersionWamp

private val logger = KotlinLogging.logger {}

// answers a call of its version and action, null for any other call
private typealias Route = (WampMessageMeta, WampMessage) -> WampMessage?

/**
 * The acceptance of a charging station, the `accept` function given with each action received, is evaluated once
 * per distinct `accept` function instance per handshake: the actions registered with the same instance share one
 * evaluation, while two different instances, even with the same code, mean two evaluations. Hoist a single instance
 * to evaluate it once.
 *
 * @param timestampZoneFallback zone in which to read the timestamps without timezone sent by a charging station,
 * in its calls and in the results of the calls sent to it. Called at most once per message, and only when such a
 * timestamp is met; timestamps with `Z` or an offset never use it. Returning `null` refuses them: an inbound call
 * gets a CallError, and [sendMessageClass] throws. An exception thrown by the resolver has the same effect.
 * It runs on the thread handling the message, the calls executor or the [sendMessageClass] caller, possibly
 * concurrently: it must be thread-safe, and a slow lookup holds up that message.
 */
class WebsocketServer(
    ocppVersions: Set<OcppVersion>,
    path: String,
    val newMessageId: () -> String = { UUID.randomUUID().toString() },
    settings: OcppWampServerSettings = OcppWampServerSettings(),
    listeners: EventsListeners = EventsListeners(),
    private val errorListener: OcppErrorListener? = null,
    /** Decides from the upgrade request whether a charging station may connect, see [ConnectionAuthorizer]. */
    authorizer: ConnectionAuthorizer = ConnectionAuthorizer.ALLOW_ALL,
    private val timestampZoneFallback: (CSOcppId) -> ZoneId? = { ZoneId.of("UTC") },
) : ServerTransport {

    internal val server: OcppWampServer =
        OcppWampServer.newServer(
            ocppVersions = ocppVersions.map { OcppVersionWamp.valueOf(it.name) }.toSet(),
            path = path,
            settings = settings,
            listeners = listeners,
            errorListener = errorListener,
            authorizer = authorizer
        )
    val serverConfig = server.config()

    private val routesByAccept = IdentityHashMap<(CSOcppId) -> ChargingStationConfig, MutableList<Route>>()

    override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, csOcppId: String, action: String, message: T): P =
        try {
            val msgId: String = newMessageId()
            val version = server.getChargingStationOcppVersion(csOcppId)
            val parser = getJsonMapper(version)
            val response =
                server.sendBlocking(csOcppId, WampMessage.Call(msgId, action, parser.mapPayloadToString(message)))
            response.resultOf(msgId, action) { parser.parsePayloadFromJson(it, clazz, timestampZone(csOcppId)) }
        } catch (e: Exception) {
            throw e
        }

    override fun <T : Any, P> receiveMessageClass(
        clazz: KClass<T>, action: String, ocppVersion: OcppVersion,
        onAction: (RequestMetadata, T) -> P, accept: (CSOcppId) -> ChargingStationConfig
    ) {
        routesAcceptedBy(accept).add { meta, msg ->
                if (meta.ocppVersion == OcppVersionWamp.valueOf(ocppVersion.name) && msg.action == action) {
                    val parser = getJsonMapper(OcppVersionWamp.valueOf(ocppVersion.name))
                    var failureCategory = OcppErrorCategory.PROTOCOL_ERROR
                    try {
                        val request = parser.parsePayloadFromJson(msg.payload, clazz, timestampZone(meta.ocppId))
                        failureCategory = OcppErrorCategory.HANDLER_ERROR
                        val response = onAction(RequestMetadata(meta.ocppId, msg.msgId), request)
                        failureCategory = OcppErrorCategory.PROTOCOL_ERROR
                        val payload = parser.mapPayloadToString(response)
                        WampMessage.CallResult(msg.msgId, payload)
                    } catch (e: Exception) { // TODO Better mapping of exceptions https://izivia.atlassian.net/browse/IDEV-497
                        // the exception message may quote the payload: only its class is logged
                        logger.error(
                            "${e.javaClass.name} while handling ${msg.action?.escapedForLog()} " +
                                "[msgId=${msg.msgId.escapedForLog()}]"
                        )
                        errorListener?.notifySafely(
                            OcppErrorEvent(
                                failureCategory,
                                meta.ocppId,
                                meta.ocppVersion.subprotocol,
                                msg.action,
                                msg.msgId,
                                MessageErrorCode.INTERNAL_ERROR,
                                e
                            )
                        )
                        internalErrorFor(msg.msgId)
                    }
                } else {
                    null
                }
        }
    }

    /**
     * The routes of the actions registered with [accept], served by a single handler, so that a handshake evaluates
     * [accept] once whatever the number of actions registered with it. Grouped by identity: the same function
     * instance, as the operations classes pass for all their actions.
     */
    private fun routesAcceptedBy(accept: (CSOcppId) -> ChargingStationConfig): MutableList<Route> =
        synchronized(routesByAccept) {
            routesByAccept.getOrPut(accept) {
                // a route may be added while the handler serves calls
                CopyOnWriteArrayList<Route>().also { routes ->
                    server.register(handler = object : OcppWampServerHandler {
                        override fun accept(ocppId: CSOcppId): Boolean = accept(ocppId).acceptConnection

                        // the first route matching the version and the action answers, in registration order
                        override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? =
                            routes.firstNotNullOfOrNull { route -> route(meta, msg) }
                    })
                }
            }
        }

    private fun timestampZone(ocppId: CSOcppId) = TimestampZoneContext(ocppId, timestampZoneFallback)

    override fun canSendToChargingStation(chargingStationConfig: ChargingStationConfig): Boolean =
        chargingStationConfig.acceptConnection

    override fun shutdown() = server.shutdown()
}

fun WebsocketServer.asServer(port: Int = 8000): Http4kServer = server.asServer(port)
