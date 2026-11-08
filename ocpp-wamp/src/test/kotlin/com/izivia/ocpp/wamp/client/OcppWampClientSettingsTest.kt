package com.izivia.ocpp.wamp.client

import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.client.autoreconnect.AutoReconnectConfig
import com.izivia.ocpp.wamp.client.autoreconnect.AutoReconnectOcppWampClient
import com.izivia.ocpp.wamp.client.impl.OkHttpOcppWampClient
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import strikt.assertions.isSuccess
import kotlin.time.Duration.Companion.milliseconds

class OcppWampClientSettingsTest {

    private val target = Uri.of("ws://localhost:1/ws")

    @Test
    fun `a non-positive timeout is rejected`() {
        expectCatching { OcppWampClientSettings(timeoutInMs = 0) }
            .isFailure()
            .isA<IllegalArgumentException>()
    }

    @Test
    fun `a negative auto reconnect delay is rejected`() {
        expectCatching { OcppWampClientSettings(baseAutoReconnectDelayInMs = -1) }
            .isFailure()
            .isA<IllegalArgumentException>()
    }

    @Test
    fun `a zero auto reconnect delay is accepted`() {
        expectCatching { OcppWampClientSettings(baseAutoReconnectDelayInMs = 0) }.isSuccess()
    }

    @Test
    fun `newClient without auto reconnect applies the timeout to the websocket client`() {
        val client = OcppWampClient.newClient(
            target, "CS1", OCPP_1_6,
            OcppWampClientSettings(timeoutInMs = 1234, autoReconnect = false)
        )

        expectThat(client).isA<OkHttpOcppWampClient>()
            .get { timeoutInMs }.isEqualTo(1234)
    }

    @Test
    fun `newClient with auto reconnect applies the timeout and the reconnect delay`() {
        val client = OcppWampClient.newClient(
            target, "CS1", OCPP_1_6,
            OcppWampClientSettings(timeoutInMs = 1234, autoReconnect = true, baseAutoReconnectDelayInMs = 42)
        )

        expectThat(client).isA<AutoReconnectOcppWampClient>().and {
            get { config }.isEqualTo(AutoReconnectConfig(1234.milliseconds, 42.milliseconds))
            get { this.client }.isA<OkHttpOcppWampClient>().get { timeoutInMs }.isEqualTo(1234)
        }
    }

    @Test
    fun `newClient with individual parameters builds the same client as with settings`() {
        val client = OcppWampClient.newClient(
            target, "CS1", OCPP_1_6,
            timeoutInMs = 1234, autoReconnect = true, baseAutoReconnectDelayInMs = 42
        )

        expectThat(client).isA<AutoReconnectOcppWampClient>()
            .get { config }.isEqualTo(AutoReconnectConfig(1234.milliseconds, 42.milliseconds))
    }
}
