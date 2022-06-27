package com.izivia.ocpp.http

import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Http4kServer
import org.http4k.server.asServer
import org.http4k.server.Undertow

import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport

import kotlin.reflect.KClass


class HttpServer(
    private val port: Int,
    private val path: String,
    private var server: Http4kServer?= null) : ServerTransport
{
    override fun start() {
        // TODO("Implement app")
        val app = { request: Request -> Response(OK).body("Hello") }
        if(server == null) {
            server = app.asServer(Undertow(port = port)).start()
        }
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
        onAction: (com.izivia.ocpp.operation.information.RequestMetadata, T) -> P,
        accept: (String) -> com.izivia.ocpp.operation.information.ChargingStationConfig
    ) {
        TODO("Not yet implemented")
    }

    override fun canSendToChargingStation(chargingStationConfig: com.izivia.ocpp.operation.information.ChargingStationConfig): Boolean {
        TODO("Not yet implemented")
    }
}