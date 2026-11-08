package com.izivia.ocpp.integration.test

import com.izivia.ocpp.integration.ApiFactory.Companion.csmsOcppServer
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.utils.OcppErrorCategory
import com.izivia.ocpp.utils.OcppErrorEvent
import com.izivia.ocpp.utils.OcppErrorListener
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import org.http4k.client.JavaHttpClient
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import strikt.api.expectThat
import strikt.assertions.containsExactly
import java.net.ServerSocket
import java.util.concurrent.CopyOnWriteArrayList
import com.izivia.ocpp.OcppVersion as OcppVersionWamp
import com.izivia.ocpp.core16.ChargePointOperations as ChargePointOperations16

class ServerSettingErrorListenerTest {

    private val events = CopyOnWriteArrayList<OcppErrorEvent>()
    private val unhandled = OcppErrorEvent(
        category = OcppErrorCategory.UNHANDLED_ACTION,
        ocppId = "CS1",
        ocppVersion = "ocpp1.6",
        action = "Unknown"
    )

    @Test
    fun `listener given to ServerSetting observes the websocket server`() {
        val port = freePort()
        val csms = csms(port, TransportEnum.WEBSOCKET, "ws")
        csms.start()
        val client = OcppWampClient.newClient(
            Uri.of("ws://localhost:$port/ws"),
            "CS1",
            OcppVersionWamp.OCPP_1_6,
            autoReconnect = false
        )
        try {
            client.connect()
            client.sendBlocking(WampMessage.Call("msg-1", "Unknown", "{}"))
        } finally {
            client.close()
            csms.stop()
        }

        expectThat(events.map { it.copy(errorCode = null, messageId = null) }).containsExactly(unhandled)
    }

    @Test
    fun `listener given to ServerSetting observes the soap server`() {
        val port = freePort()
        val csms = csms(port, TransportEnum.SOAP, "/ocpp/soap/")
        csms.start()
        try {
            JavaHttpClient()(Request(Method.POST, "http://127.0.0.1:$port/ocpp/soap/Unknown/CS1").body(""))
        } finally {
            csms.stop()
        }

        expectThat(events).containsExactly(unhandled)
    }

    private fun csms(port: Int, transportType: TransportEnum, path: String) = csmsOcppServer(
        csmsSettings = CSMSSettings(
            listOf(
                ServerSetting(
                    port = port,
                    path = path,
                    ocppVersion = setOf(OcppVersion.OCPP_1_6),
                    transportType = transportType,
                    errorListener = OcppErrorListener { events.add(it) }
                )
            )
        ),
        csmsApiCallbacks = listOf(mock<ChargePointOperations16>()),
        fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
    )

    private fun freePort() = ServerSocket(0).use { it.localPort }
}
