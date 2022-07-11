package com.izivia.ocpp.http

import com.izivia.ocpp.soap.*
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.transport.OcppCallErrorException
import org.http4k.client.JavaHttpClient
import org.http4k.contract.contract
import org.http4k.contract.div
import org.http4k.core.*
import org.http4k.lens.Path
import org.http4k.server.Http4kServer
import org.http4k.server.Undertow
import org.http4k.server.asServer
import org.slf4j.LoggerFactory
import java.util.*
import kotlin.reflect.KClass

class OcppSoapClientTransport(
    private val clientSettings: SoapClientSettings,
    private val ocppId: String,
    private val target: String,
    private val ocppSoapParser: OcppSoapParser,
    private val newMessageId: () -> String = { UUID.randomUUID().toString() },
) : ClientTransport {

    companion object {
        private val logger = LoggerFactory.getLogger(OcppSoapClientTransport::class.java)
    }

    private val server: Http4kServer?
    private val client = JavaHttpClient()

    private val handlers = mutableListOf<(HttpMessage) -> HttpMessage?>()

    init {

        val route =
            clientSettings.path / Path.of("action") bindContract Method.POST to ::routeHandler
        val app = contract {
            routes += route
        }
        server = app.asServer(Undertow(port = clientSettings.port))
    }

    private fun routeHandler(action: String): HttpHandler = { request: Request ->
        val message = HttpMessage(
            ocppId = ocppId,
            action = action,
            payload = request.bodyString()
        )
        handlers
            .asSequence()
            .map { it(message) }
            .firstOrNull()
            ?.let { Response(Status.OK).body(it.payload) }
            ?: Response(Status.NOT_FOUND).also { logger.warn("no action handler found for $message") }
    }

    override fun connect() {
        server?.start()
            ?.also { logger.info("starting http server on port ${clientSettings.port}") }
    }

    override fun close() {
        server?.close()
    }

    override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, action: String, message: T): P {
        val request = Request(Method.POST, target)
            .body(
                ocppSoapParser.mapRequestToSoap(
                    RequestSoapMessage(
                        messageId = newMessageId(),
                        chargingStationId = ocppId,
                        action = action,
                        from = clientSettings.path,
                        to = target,
                        payload = message
                    )
                )
            )
        val response = client(request)
        return if (response.status == Status.OK)
            ocppSoapParser.parseResponseFromSoap(response.bodyString(), clazz).payload
        else {
            logger.warn("an error occurred. response = $response")
            throw OcppCallErrorException(response.bodyString())
        }
    }

    override fun <T : Any, P> receiveMessageClass(clazz: KClass<T>, action: String, fn: (T) -> P) {
        handlers.add { msg ->
            if (msg.action?.lowercase() == action.lowercase()) {
                val message = ocppSoapParser.parseRequestFromSoap(msg.payload, clazz)
                val response = fn(message.payload)
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
    }
}
