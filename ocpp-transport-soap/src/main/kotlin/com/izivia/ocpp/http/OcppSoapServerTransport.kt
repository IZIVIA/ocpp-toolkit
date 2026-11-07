package com.izivia.ocpp.http

import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.soap.OcppSoapParser
import com.izivia.ocpp.soap.RequestSoapMessage
import com.izivia.ocpp.soap.ResponseSoapMessage
import com.izivia.ocpp.soap.parseRequestFromSoap
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.utils.notifySafely
import org.http4k.contract.contract
import org.http4k.contract.div
import org.http4k.core.HttpHandler
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.Path
import org.http4k.routing.RoutingHttpHandler
import org.slf4j.LoggerFactory
import java.util.*
import kotlin.reflect.KClass

/**
 * Server side of the SOAP (OCPP-S) transport.
 *
 * This transport owns no server and no threads: it only provides the HTTP handler exposed by
 * [serverConfig]. Each inbound request is handled synchronously on a worker thread of the HTTP
 * server that hosts this handler, so concurrency is bounded and tuned by that server:
 * - under the toolkit's `CSMS`, the Undertow server built for each port, which is shared with any
 *   WebSocket transport listening on the same port;
 * - with [asServer], http4k's stock Undertow server.
 *
 * Both currently size their worker pool at 32 threads per available processor.
 *
 * There is no call timeout either, because the server side never sends calls to charging stations
 * ([sendMessageClass] is not implemented). That is why this transport has no counterpart to the
 * WebSocket server's `OcppWampServerSettings`.
 *
 * For the same reason it keeps the default no-op [ServerTransport.shutdown]: each SOAP call is a
 * synchronous HTTP exchange owned by the HTTP server, so the transport holds nothing to drain.
 */
class OcppSoapServerTransport private constructor(
    private val ocppVersion: OcppVersion,
    private val ocppSoapParser: OcppSoapParser,
    private val newMessageId: () -> String = { UUID.randomUUID().toString() },
    private val path: String,
    private val errorListener: OcppErrorListener? = null,
) : ServerTransport {

    companion object {
        private val logger = LoggerFactory.getLogger(OcppSoapServerTransport::class.java)

        fun create(
            ocppVersion: OcppVersion,
            path: String,
            ocppSoapParser: OcppSoapParser,
            newMessageId: () -> String = { UUID.randomUUID().toString() },
            errorListener: OcppErrorListener? = null
        ) = OcppSoapServerTransport(
            ocppVersion,
            ocppSoapParser,
            newMessageId,
            path = path,
            errorListener = errorListener
        )
    }

    private val handler: RoutingHttpHandler
    private val handlers = mutableListOf<OcppHttpServerHandler>()

    init {
        this.handler = createHandler(path)
    }

    val serverConfig = object : ServerConfig {
        override val handler: RoutingHttpHandler
            get() = this@OcppSoapServerTransport.handler
    }

    private fun createHandler(path: String): RoutingHttpHandler {
        val route = path / Path.of("action") / Path.of("ocppId") bindContract POST to ::routeHandler
        return contract {
            routes += route
        }
    }

    private fun routeHandler(action: String, ocppId: String): HttpHandler = { request: Request ->
        val message = HttpMessage(
            ocppId = ocppId,
            action = action,
            payload = request.bodyString(),
            headers = request.headers
        )
        val acceptingHandler = handlers.firstOrNull { it.accept(ocppId) }
        acceptingHandler?.onAction(message)
            ?.let { Response(OK).body(it.payload) }
            ?: Response(NOT_FOUND).also {
                logger.warn("no action handler found for $message")
                // anyone can reach this route: a refused charging station must not produce events about its chosen id
                if (acceptingHandler != null) {
                    errorListener?.notifySafely(
                        OcppErrorEvent(OcppErrorCategory.UNHANDLED_ACTION, ocppId, ocppVersion.subprotocol, action)
                    )
                }
            }
    }

    override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, csOcppId: String, action: String, message: T): P {
        TODO("Not yet implemented")
    }

    override fun <T : Any, P> receiveMessageClass(
        clazz: KClass<T>,
        action: String,
        ocppVersion: OcppVersion,
        onAction: (RequestMetadata, T) -> P,
        accept: (String) -> ChargingStationConfig
    ) {
        handlers.add(
            object : OcppHttpServerHandler {
                override fun accept(ocppId: String): Boolean = accept(ocppId).acceptConnection

                override fun onAction(msg: HttpMessage): HttpMessage? =
                    if (this@OcppSoapServerTransport.ocppVersion == ocppVersion &&
                        msg.action?.lowercase() == action.lowercase()
                    ) {
                        var parsed: RequestSoapMessage<T>? = null
                        var failureCategory = OcppErrorCategory.PROTOCOL_ERROR
                        try {
                            val message = ocppSoapParser.parseRequestFromSoap(msg.payload, clazz)
                            parsed = message
                            failureCategory = OcppErrorCategory.HANDLER_ERROR
                            val response =
                                onAction(RequestMetadata(message.chargingStationId, message.messageId), message.payload)
                            failureCategory = OcppErrorCategory.PROTOCOL_ERROR
                            val payload = ocppSoapParser.mapResponseToSoap(
                                ResponseSoapMessage(
                                    messageId = "urn:uuid:${newMessageId()}",
                                    relatesTo = message.messageId,
                                    action = message.action,
                                    payload = response,
                                    from = message.to,
                                    to = message.from
                                )
                            )
                            HttpMessage(msg.ocppId, action, payload)
                        } catch (e: Exception) {
                            errorListener?.notifySafely(
                                OcppErrorEvent(
                                    failureCategory,
                                    parsed?.chargingStationId ?: msg.ocppId,
                                    ocppVersion.subprotocol,
                                    msg.action,
                                    parsed?.messageId,
                                    cause = e
                                )
                            )
                            throw e
                        }
                    } else {
                        null
                    }
            }
        )
    }

    override fun canSendToChargingStation(chargingStationConfig: ChargingStationConfig): Boolean =
        chargingStationConfig.acceptConnection
}
