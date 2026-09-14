package com.izivia.ocpp.http.test

import com.izivia.ocpp.http.SoapClientSettings
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class SoapClientSettingsTest {

    @Test
    fun `normalises the route with a leading slash and no trailing slash`() {
        expectThat(SoapClientSettings(port = 8081, path = "").route).isEqualTo("/")
        expectThat(SoapClientSettings(port = 8081, path = "/").route).isEqualTo("/")
        expectThat(SoapClientSettings(port = 8081, path = "cp").route).isEqualTo("/cp")
        expectThat(SoapClientSettings(port = 8081, path = "/cp/").route).isEqualTo("/cp")
        expectThat(SoapClientSettings(port = 8081, path = "/ocpp/soap").route).isEqualTo("/ocpp/soap")
    }

    @Test
    fun `builds the callback url from the host, the bound port and the route`() {
        expectThat(SoapClientSettings(port = 8081, path = "/cp", host = "192.168.0.3").callbackUrl(8081))
            .isEqualTo("http://192.168.0.3:8081/cp")
        expectThat(SoapClientSettings(port = 0, path = "cp/").callbackUrl(51234))
            .isEqualTo("http://localhost:51234/cp")
        expectThat(SoapClientSettings(port = 8081).callbackUrl(8081))
            .isEqualTo("http://localhost:8081")
    }

    @Test
    fun `brackets an IPv6 host in the callback url`() {
        expectThat(SoapClientSettings(port = 8081, path = "/cp", host = "2001:db8::1").callbackUrl(8081))
            .isEqualTo("http://[2001:db8::1]:8081/cp")
        expectThat(SoapClientSettings(port = 8081, host = "2001:db8::1").callbackUrl(8081))
            .isEqualTo("http://[2001:db8::1]:8081")
    }

    @Test
    fun `advertises the configured url instead of the bound one when provided`() {
        val settings = SoapClientSettings(
            port = 8081,
            path = "/cp",
            advertisedUrl = "https://cp.example.com/ocpp/cp"
        )

        expectThat(settings.route).isEqualTo("/cp")
        expectThat(settings.callbackUrl(8081)).isEqualTo("https://cp.example.com/ocpp/cp")
    }
}
