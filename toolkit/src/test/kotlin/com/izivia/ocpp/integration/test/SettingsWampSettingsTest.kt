package com.izivia.ocpp.integration.test

import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.integration.ApiFactory.Companion.getCSMSApi
import com.izivia.ocpp.integration.ApiFactory.Companion.ocpp12ConnectionToCSMS
import com.izivia.ocpp.integration.ApiFactory.Companion.ocpp15ConnectionToCSMS
import com.izivia.ocpp.integration.ApiFactory.Companion.ocpp16ConnectionToCSMS
import com.izivia.ocpp.integration.ApiFactory.Companion.ocpp20ConnectionToCSMS
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.client.OcppWampClientSettings
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.izivia.ocpp.api12.OcppCSCallbacks as OcppCSCallbacks12
import com.izivia.ocpp.api15.OcppCSCallbacks as OcppCSCallbacks15
import com.izivia.ocpp.api16.OcppCSCallbacks as OcppCSCallbacks16
import com.izivia.ocpp.api20.OcppCSCallbacks as OcppCSCallbacks20

class SettingsWampSettingsTest {

    private val wampSettings = OcppWampClientSettings(timeoutInMs = 1234, autoReconnect = false)

    @BeforeEach
    fun init() {
        mockkObject(OcppWampClient.Companion)
        every {
            OcppWampClient.Companion.newClient(any(), any(), any(), any<OcppWampClientSettings>(), any())
        } returns mockk(relaxed = true)
    }

    @AfterEach
    fun destroy() {
        unmockkAll()
    }

    private fun verifyClientBuiltWithWampSettings() {
        verify { OcppWampClient.Companion.newClient(any(), any(), any(), wampSettings, any()) }
    }

    @Test
    fun `wamp settings given to Settings reach the websocket client`() {
        getCSMSApi(
            Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, wampSettings = wampSettings),
            "CS1",
            mockk<CSApi>(relaxed = true)
        )

        verifyClientBuiltWithWampSettings()
    }

    @Test
    fun `wamp settings given to ocpp20ConnectionToCSMS reach the websocket client`() {
        ocpp20ConnectionToCSMS(
            "CS1", "ws://localhost:8080/ws", TransportEnum.WEBSOCKET,
            ocppCSCallbacks = mockk<OcppCSCallbacks20>(relaxed = true), wampSettings = wampSettings
        )

        verifyClientBuiltWithWampSettings()
    }

    @Test
    fun `wamp settings given to ocpp16ConnectionToCSMS reach the websocket client`() {
        ocpp16ConnectionToCSMS(
            "CS1", "ws://localhost:8080/ws", TransportEnum.WEBSOCKET,
            ocppCSCallbacks = mockk<OcppCSCallbacks16>(relaxed = true), wampSettings = wampSettings
        )

        verifyClientBuiltWithWampSettings()
    }

    @Test
    fun `wamp settings given to ocpp15ConnectionToCSMS reach the websocket client`() {
        ocpp15ConnectionToCSMS(
            "CS1", "ws://localhost:8080/ws", TransportEnum.WEBSOCKET,
            ocppCSCallbacks = mockk<OcppCSCallbacks15>(relaxed = true), wampSettings = wampSettings
        )

        verifyClientBuiltWithWampSettings()
    }

    @Test
    fun `wamp settings given to ocpp12ConnectionToCSMS reach the websocket client`() {
        ocpp12ConnectionToCSMS(
            "CS1", "ws://localhost:8080/ws", TransportEnum.WEBSOCKET,
            ocppCSCallbacks = mockk<OcppCSCallbacks12>(relaxed = true), wampSettings = wampSettings
        )

        verifyClientBuiltWithWampSettings()
    }
}
