package com.izivia.ocpp.http

import com.izivia.ocpp.utils.toUriHost
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
    /**
     * Host advertised in the `From` header, i.e. how the central system reaches this charge point:
     * a hostname or an IP address, without scheme, port or path. IPv6 literals may be bracketed or
     * not. Mandatory: a default would let a charge point advertise an address the central system
     * cannot reach, which is the non-compliance this class exists to prevent.
     */
    val host: String,
    /** Route the embedded server listens on, also advertised in the `From` header. */
    val path: String = "",
    /** Scheme advertised in the `From` header: `http`, or `https` behind a TLS terminator. */
    val scheme: String = "http",
    /**
     * Port advertised in the `From` header instead of the bound one, for a TLS terminator or a port
     * mapping in front of the charge point, e.g. `scheme = "https"` with `advertisedPort = 8443`.
     */
    val advertisedPort: Int? = null,
    /**
     * Full URL advertised in the `From` header, taking precedence over [scheme], [host],
     * [advertisedPort], the bound port and [path]. Needed when the central system reaches the charge point through a reverse
     * proxy rewriting the URL, i.e. on another host, port or path than the embedded server binds to.
     */
    val advertisedUrl: String? = null,
) {
    /**
     * [path] normalised with a leading slash, no trailing slash and no empty segment; the root is
     * `/`. Empty segments are collapsed because the embedded server would otherwise not serve the
     * URL this class advertises.
     */
    val route: String = "/" + path.split('/').filter { it.isNotEmpty() }.joinToString("/")

    private val authorityHost = host.toUriHost()

    init {
        require(scheme in SCHEMES) { "SoapClientSettings.scheme must be http or https, got '$scheme'" }
        require(port in 0..65535) { "SoapClientSettings.port must be a TCP port, got $port" }
        advertisedPort?.let {
            require(it in 1..65535) { "SoapClientSettings.advertisedPort must be a TCP port, got $it" }
        }
        val hostMessage = "SoapClientSettings.host must be a hostname or IP address without scheme, port or path, got '$host'"
        // An IPv6 literal goes to java.net.URI, which checks its shape. Everything else is matched
        // against a whitelist: URI falls back to a registry-based authority, where a character no
        // central system can resolve, such as `;` or `%`, would go through unnoticed. `_` is kept,
        // as internal DNS zones commonly use it. A zone id (fe80::1%eth0) is rejected rather than
        // escaped per RFC 6874, since it only names an interface of this charge point.
        if (host.startsWith('[') || ':' in host) {
            require('%' !in host) { hostMessage }
            val uri = requireParsable("$scheme://$authorityHost:$port/", hostMessage)
            require(uri.rawAuthority == "$authorityHost:$port" && uri.host == authorityHost) { hostMessage }
        } else {
            require(HOST_PATTERN.matches(host)) { hostMessage }
        }
        val pathMessage =
            "SoapClientSettings.path must be a URL path without query, fragment or percent-escape, got '$path'"
        require('?' !in path && '#' !in path && '%' !in path) { pathMessage }
        requireParsable("http://localhost$route", pathMessage)
        advertisedUrl?.let { url ->
            val message = "SoapClientSettings.advertisedUrl must be an absolute http or https URL, got '$url'"
            val advertised = requireParsable(url, message)
            // rawAuthority keeps registry-based names that URI.getHost() drops. The port is parsed
            // from the text rather than read from URI.getPort(), which answers -1 both when there
            // is no port and when the authority is registry-based, letting `:80a`, `:` and
            // `cp_1.example.com:99999` through.
            val authority = advertised.rawAuthority.orEmpty().substringAfterLast('@')
            val portText = if (authority.startsWith('[')) {
                authority.substringAfter(']', missingDelimiterValue = ":")
            } else {
                authority.indexOf(':').let { if (it < 0) "" else authority.substring(it) }
            }
            val hostText = authority.removeSuffix(portText)
            require(
                advertised.scheme in SCHEMES && hostText.isNotBlank() &&
                    (portText.isEmpty() || portText.drop(1).toIntOrNull() in 1..65535)
            ) { message }
        }
    }

    /** URL advertised in the `From` header once the embedded server is bound to [boundPort]. */
    fun callbackUrl(boundPort: Int): String =
        advertisedUrl ?: ("$scheme://$authorityHost:${advertisedPort ?: boundPort}" + route.removeSuffix("/"))

    private companion object {
        val SCHEMES = setOf("http", "https")
        val HOST_PATTERN = Regex("[A-Za-z0-9._-]+")

        fun requireParsable(url: String, message: String): URI =
            try {
                URI(url)
            } catch (e: URISyntaxException) {
                throw IllegalArgumentException(message, e)
            }
    }
}

interface ServerConfig {
    val handler: RoutingHttpHandler
}

fun OcppSoapServerTransport.asServer(port: Int = 8000): Http4kServer {
    return this.serverConfig.handler.asServer(org.http4k.server.Undertow(port))
}
