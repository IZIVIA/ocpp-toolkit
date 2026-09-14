package com.izivia.ocpp.http

import org.http4k.routing.RoutingHttpHandler
import org.http4k.server.Http4kServer
import org.http4k.server.asServer
import java.net.URI

/**
 * Settings of the HTTP server embedded in [OcppSoapClientTransport], on which the charge point
 * receives the requests initiated by the central system.
 *
 * OCPP-S requires the WS-Addressing `From` header to carry the URL where the charge point listens
 * for incoming SOAP requests; it is the only way for the central system to address it. That URL is
 * built from [host], the port the server is actually bound to and [path], unless [advertisedUrl]
 * overrides it, see [callbackUrl].
 */
data class SoapClientSettings(
    /** Port the embedded HTTP server binds to; `0` picks an ephemeral port. */
    val port: Int,
    /** Route the embedded server listens on, also advertised in the `From` header. */
    val path: String = "",
    /** Host advertised in the `From` header, i.e. how the central system reaches this charge point. */
    val host: String = "localhost",
    /**
     * Full URL advertised in the `From` header, taking precedence over [host], the bound port and
     * [path]. Needed when the central system reaches the charge point through a reverse proxy or a
     * TLS terminator, i.e. on another scheme, host or port than the embedded server binds to.
     */
    val advertisedUrl: String? = null,
) {
    /** [path] normalised with a leading slash and no trailing slash; the root is `/`. */
    val route: String = "/" + path.trim('/')

    /** URL advertised in the `From` header once the embedded server is bound to [boundPort]. */
    fun callbackUrl(boundPort: Int): String =
        // The multi-argument constructor brackets IPv6 hosts; an empty path keeps the root URL bare.
        advertisedUrl ?: URI("http", null, host, boundPort, route.removeSuffix("/"), null, null).toString()
}

interface ServerConfig {
    val handler: RoutingHttpHandler
}

fun OcppSoapServerTransport.asServer(port: Int = 8000): Http4kServer {
    return this.serverConfig.handler.asServer(org.http4k.server.Undertow(port))
}
