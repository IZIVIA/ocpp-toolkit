package com.izivia.ocpp.integration.test

import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.http.SoapClientSettings
import com.izivia.ocpp.integration.ApiFactory.Companion.getCSMSApi
import com.izivia.ocpp.integration.ApiFactory.Companion.ocpp16ConnectionToCSMS
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import org.http4k.client.JavaHttpClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import strikt.api.expectThat
import strikt.assertions.containsExactly
import java.net.ServerSocket
import java.util.concurrent.CopyOnWriteArrayList
import com.izivia.ocpp.OcppVersion as OcppVersionWamp
import com.izivia.ocpp.api16.OcppCSCallbacks as OcppCSCallbacks16

class SettingsErrorListenerTest {

    private val events = CopyOnWriteArrayList<OcppErrorEvent>()
    private val listener = OcppErrorListener { events.add(it) }
    private val unhandled = OcppErrorEvent(
        category = OcppErrorCategory.UNHANDLED_ACTION,
        ocppId = "CS1",
        ocppVersion = "ocpp1.6",
        action = "Unknown"
    )

    @Test
    fun `listener given to Settings observes the websocket client`() {
        expectUnhandledCallReported { port ->
            val api = getCSMSApi(
                Settings(
                    OcppVersion.OCPP_1_6,
                    TransportEnum.WEBSOCKET,
                    port = port.toString(),
                    path = "ws",
                    errorListener = listener
                ),
                "CS1",
                mock<CSApi>()
            )
            api.connect()
            api::close
        }
    }

    @Test
    fun `listener given to a connection builder observes the websocket client`() {
        expectUnhandledCallReported { port ->
            val connection = ocpp16ConnectionToCSMS(
                chargePointId = "CS1",
                csmsUrl = "ws://localhost:$port/ws",
                transportType = TransportEnum.WEBSOCKET,
                ocppCSCallbacks = mock<OcppCSCallbacks16>(),
                errorListener = listener
            )
            connection.connect()
            connection::close
        }
    }

    @Test
    fun `listener given to Settings observes the soap client`() {
        val port = freePort()
        val api = getCSMSApi(
            Settings(
                OcppVersion.OCPP_1_6,
                TransportEnum.SOAP,
                soapClient = SoapClientSettings(port = port, host = "localhost", path = "/ocpp/soap"),
                errorListener = listener
            ),
            "CS1",
            mock<CSApi>()
        )
        api.connect()
        try {
            JavaHttpClient()(Request(Method.POST, "http://127.0.0.1:$port/ocpp/soap").body(UNKNOWN_ENVELOPE))
        } finally {
            api.close()
        }

        expectThat(events).containsExactly(unhandled)
    }

    /** Has a CSMS send an unknown action to the client that [connect] connects and returns the closing of. */
    private fun expectUnhandledCallReported(connect: (port: Int) -> () -> Unit) {
        val port = freePort()
        val csms = OcppWampServer.newServer(setOf(OcppVersionWamp.OCPP_1_6))
        csms.register(object : OcppWampServerHandler {
            override fun accept(ocppId: String): Boolean = ocppId == "CS1"
            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
        })
        val server = csms.asServer(port)
        server.start()
        try {
            val close = connect(port)
            try {
                awaitConnected { csms.sendBlocking("CS1", WampMessage.Call("msg-1", "Unknown", "{}")) }
            } finally {
                close()
            }
        } finally {
            server.stop()
        }

        expectThat(events.map { it.copy(errorCode = null, messageId = null) }).containsExactly(unhandled)
    }

    /** Retries [action] until the server has registered the connection, which it does asynchronously. */
    private fun <T> awaitConnected(timeoutMs: Long = 5_000, action: () -> T): T {
        val deadline = System.currentTimeMillis() + timeoutMs
        while (true) {
            try {
                return action()
            } catch (e: IllegalStateException) {
                if (System.currentTimeMillis() >= deadline) throw e
                Thread.sleep(50)
            }
        }
    }

    private fun freePort() = ServerSocket(0).use { it.localPort }

    private companion object {
        val UNKNOWN_ENVELOPE =
            """
            <S:Envelope xmlns:S="http://www.w3.org/2003/05/soap-envelope">
                <S:Header>
                    <chargeBoxIdentity xmlns="urn://Ocpp/Cp/2015/10/">CS1</chargeBoxIdentity>
                    <Action xmlns="http://www.w3.org/2005/08/addressing">/Unknown</Action>
                    <MessageID xmlns="http://www.w3.org/2005/08/addressing">uuid:1</MessageID>
                </S:Header>
                <S:Body>
                    <heartbeatRequest xmlns="urn://Ocpp/Cs/2015/10/"/>
                </S:Body>
            </S:Envelope>
            """.trimIndent()
    }
}
