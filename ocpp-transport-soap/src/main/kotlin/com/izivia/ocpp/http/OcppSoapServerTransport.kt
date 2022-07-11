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
import org.http4k.server.Http4kServer
import org.http4k.server.Undertow
import org.http4k.server.asServer
import org.slf4j.LoggerFactory
import java.util.*
import kotlin.reflect.KClass

class OcppSoapServerTransport private constructor(
    private val ocppVersion: OcppVersion,
    private val port: Int,
    private val path: String?,
    private val ocppSoapParser: OcppSoapParser,
    private val newMessageId: () -> String = { UUID.randomUUID().toString() },
    private var server: Http4kServer? = null
) : ServerTransport {

    companion object {
        private val logger = LoggerFactory.getLogger(OcppSoapServerTransport::class.java)

        fun createServer(
            ocppVersion: OcppVersion,
            port: Int,
            path: String,
            ocppSoapParser: OcppSoapParser,
            newMessageId: () -> String = { UUID.randomUUID().toString() }
        ) = OcppSoapServerTransport(ocppVersion, port, path, ocppSoapParser, newMessageId)

        fun createServer(
            ocppVersion: OcppVersion,
            ocppSoapParser: OcppSoapParser,
            newMessageId: () -> String = { UUID.randomUUID().toString() },
            server: Http4kServer
        ) = OcppSoapServerTransport(ocppVersion, server.port(), null, ocppSoapParser, newMessageId, server)
    }

    private val handlers = mutableListOf<OcppHttpServerHandler>()

    override fun start() {
        if (server == null) {
            val route = path!! / Path.of("action") / Path.of("ocppId") bindContract POST to ::routeHandler
            val app = contract {
                routes += route
            }
            server = app.asServer(Undertow(port = port))
        }
        server!!.start()
        logger.info("starting http server on port $port")
    }

    private fun routeHandler(action: String, ocppId: String): HttpHandler = { request: Request ->
        val message = HttpMessage(
            ocppId = ocppId,
            action = action,
            payload = request.bodyString()
        )
        handlers
            .asSequence()
            .filter { it.accept(ocppId) }
            .map { it.onAction(message) }
            .firstOrNull()
            ?.let { Response(OK).body(it.payload) }
            ?: Response(NOT_FOUND).also { logger.warn("no action handler found for $message") }
    }

    override fun stop() {
        server!!.stop()
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
                    if (this@OcppSoapServerTransport.ocppVersion == ocppVersion && msg.action?.lowercase() == action.lowercase()) {
                        val message = ocppSoapParser.parseRequestFromSoap(msg.payload, clazz)
                        val response = onAction(RequestMetadata(message.messageId), message.payload)
                        val payload = ocppSoapParser.mapResponseToSoap(
                            ResponseSoapMessage(
                                messageId = "urn:uuid:${newMessageId()}",
                                relatesTo = message.messageId,
                                action = message.action,
                                payload = response
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
