package com.izivia.ocpp.http

import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.soap.OcppSoapParser
import com.izivia.ocpp.soap.ResponseSoapMessage
import com.izivia.ocpp.soap.parseRequestFromSoap
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
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

class OcppSoapServerTransport private constructor(
    private val ocppVersion: OcppVersion,
    private val ocppSoapParser: OcppSoapParser,
    private val newMessageId: () -> String = { UUID.randomUUID().toString() },
    private val path: String,
) : ServerTransport {

    companion object {
        private val logger = LoggerFactory.getLogger(OcppSoapServerTransport::class.java)

        fun create(
            ocppVersion: OcppVersion,
            path: String,
            ocppSoapParser: OcppSoapParser,
            newMessageId: () -> String = { UUID.randomUUID().toString() }
        ) = OcppSoapServerTransport(ocppVersion, ocppSoapParser, newMessageId, path = path)
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
        handlers
            .asSequence()
            .filter { it.accept(ocppId) }
            .map { it.onAction(message) }
            .firstOrNull()
            ?.let { Response(OK).body(it.payload) }
            ?: Response(NOT_FOUND).also { logger.warn("no action handler found for $message") }
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
                        val message = ocppSoapParser.parseRequestFromSoap(msg.payload, clazz)
                        val response = onAction(RequestMetadata(message.chargingStationId, message.messageId), message.payload)
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
                    } else {
                        null
                    }
            }
        )
    }

    override fun canSendToChargingStation(chargingStationConfig: ChargingStationConfig): Boolean =
        chargingStationConfig.acceptConnection
}
