package com.izivia.ocpp.http

import org.http4k.routing.RoutingHttpHandler
import org.http4k.server.Http4kServer
import org.http4k.server.asServer
import java.net.URI
import java.net.URISyntaxException

/**
 * Settings of the HTTP server embedded in [OcppSoapClientTransport], on which the charge point
 * receives the requests initiated by the central system.
 *
 * OCPP-S requires the WS-Addressing `From` header to carry the URL where the charge point listens
 * for incoming SOAP requests; it is the only way for the central system to address it. That URL is
 * built from [scheme], [host], the port the server is actually bound to and [path], unless
 * [advertisedUrl] overrides it, see [callbackUrl]. Invalid settings are rejected at construction.
 */
data class SoapClientSettings(
    /** Port the embedded HTTP server binds to; `0` picks an ephemeral port. */
    val port: Int,
    /** Route the embedded server listens on, also advertised in the `From` header. */
    val path: String = "",
    /**
     * Host advertised in the `From` header, i.e. how the central system reaches this charge point:
     * a hostname or an IP address, without scheme, port or path. IPv6 literals may be bracketed or not.
     */
    val host: String = "localhost",
    /** Scheme advertised in the `From` header: `http`, or `https` behind a TLS terminator. */
    val scheme: String = "http",
    /**
     * Full URL advertised in the `From` header, taking precedence over [scheme], [host], the bound
     * port and [path]. Needed when the central system reaches the charge point through a reverse
     * proxy rewriting the URL, i.e. on another host, port or path than the embedded server binds to.
     */
    val advertisedUrl: String? = null,
) {
    /** [path] normalised with a leading slash and no trailing slash; the root is `/`. */
    val route: String = "/" + path.trim('/')

    // IPv6 literals must be bracketed in an authority; anything else is passed through untouched.
    private val authorityHost = if (':' in host && !host.startsWith('[')) "[$host]" else host

    init {
        require(scheme == "http" || scheme == "https") { "SoapClientSettings.scheme must be http or https, got '$scheme'" }
        val hostMessage = "SoapClientSettings.host must be a hostname or IP address without scheme, port or path, got '$host'"
        require(host.isNotBlank() && '/' !in host) { hostMessage }
        // java.net.URI validates a bracketed IPv6 literal but, unlike its multi-argument constructor,
        // tolerates registry-based names such as cp_1.example.com that are common in internal DNS zones.
        try {
            URI("$scheme://$authorityHost:$port/")
        } catch (e: URISyntaxException) {
            throw IllegalArgumentException(hostMessage, e)
        }
        advertisedUrl?.let { url ->
            val uri = try {
                URI(url)
            } catch (e: URISyntaxException) {
                throw IllegalArgumentException("SoapClientSettings.advertisedUrl must be an absolute URL, got '$url'", e)
            }
            require(uri.isAbsolute && !uri.authority.isNullOrBlank()) {
                "SoapClientSettings.advertisedUrl must be an absolute URL, got '$url'"
            }
        }
    }

    /** URL advertised in the `From` header once the embedded server is bound to [boundPort]. */
    fun callbackUrl(boundPort: Int): String =
        advertisedUrl ?: ("$scheme://$authorityHost:$boundPort" + route.removeSuffix("/"))
}

interface ServerConfig {
    val handler: RoutingHttpHandler
}

fun OcppSoapServerTransport.asServer(port: Int = 8000): Http4kServer {
    return this.serverConfig.handler.asServer(org.http4k.server.Undertow(port))
}
