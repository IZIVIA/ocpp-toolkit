package com.izivia.ocpp.wamp

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.util.Base64
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

/** The outcome of a WebSocket upgrade: 101 when it succeeds, the HTTP error status otherwise. */
data class Handshake(val status: Int?, val wwwAuthenticate: String?)

/**
 * Attempts one OCPP 1.6 upgrade on `/ws/<ocppId>` with raw OkHttp, so that a refused handshake is reported once
 * instead of being retried by a reconnecting client.
 */
fun handshake(port: Int, ocppId: CSOcppId, vararg headers: Pair<String, String>): Handshake {
    val result = CompletableFuture<Handshake>()
    val request = Request.Builder()
        .url("ws://localhost:$port/ws/$ocppId")
        .header("Sec-WebSocket-Protocol", OCPP_1_6.subprotocol)
        .apply { headers.forEach { (name, value) -> header(name, value) } }
        .build()
    val webSocket = OkHttpClient().newWebSocket(
        request,
        object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                result.complete(Handshake(response.code, response.header("WWW-Authenticate")))
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                result.complete(Handshake(response?.code, response?.header("WWW-Authenticate")))
            }
        }
    )
    return try {
        result.get(5, TimeUnit.SECONDS)
    } finally {
        webSocket.cancel()
    }
}

/** An `Authorization` header of HTTP Basic authentication. */
fun basicAuthorization(user: String, password: String) = basicAuthorization(user, password.toByteArray())

/** An `Authorization` header of HTTP Basic authentication, with a binary password such as an OCPP 1.6 key. */
fun basicAuthorization(user: String, password: ByteArray) =
    "Authorization" to "Basic " + Base64.getEncoder().encodeToString("$user:".toByteArray() + password)
