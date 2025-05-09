package com.izivia.ocpp.http

import org.http4k.routing.RoutingHttpHandler
import org.http4k.server.Http4kServer
import org.http4k.server.asServer

data class SoapClientSettings(
    val path: String,
    val port: Int,
)

interface ServerConfig {
    val handler: RoutingHttpHandler
}

fun OcppSoapServerTransport.asServer(port: Int = 8000): Http4kServer {
    return this.serverConfig.handler.asServer(org.http4k.server.Undertow(port))
}
