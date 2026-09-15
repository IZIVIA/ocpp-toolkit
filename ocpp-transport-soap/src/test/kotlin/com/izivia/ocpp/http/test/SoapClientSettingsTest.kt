package com.izivia.ocpp.http.test

import com.izivia.ocpp.http.SoapClientSettings
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.contains
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.message

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
        expectThat(SoapClientSettings(port = 8081, path = "/cp", host = "[2001:db8::1]").callbackUrl(8081))
            .isEqualTo("http://[2001:db8::1]:8081/cp")
    }

    @Test
    fun `accepts hostnames that are not RFC 2396 compliant`() {
        expectThat(SoapClientSettings(port = 8081, path = "/cp", host = "cp_1.example.com").callbackUrl(8081))
            .isEqualTo("http://cp_1.example.com:8081/cp")
    }

    @ParameterizedTest
    @ValueSource(strings = ["http://192.168.0.3", "192.168.0.3:8081", "", " ", "example.com/cp", "2001:db8:::1"])
    fun `rejects a host that is not a bare hostname or ip address`(host: String) {
        expectThrows<IllegalArgumentException> { SoapClientSettings(port = 8081, host = host) }
            .message.isNotNull().contains("SoapClientSettings.host")
    }

    @Test
    fun `uses the configured scheme in the callback url`() {
        expectThat(SoapClientSettings(port = 8443, path = "/cp", host = "cp.example.com", scheme = "https").callbackUrl(8443))
            .isEqualTo("https://cp.example.com:8443/cp")
        expectThrows<IllegalArgumentException> { SoapClientSettings(port = 8081, scheme = "ftp") }
            .message.isNotNull().contains("SoapClientSettings.scheme")
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

    @ParameterizedTest
    @ValueSource(strings = ["cp.example.com/ocpp/cp", "/ocpp/cp", "https://", "not a url"])
    fun `rejects an advertised url that is not absolute`(advertisedUrl: String) {
        expectThrows<IllegalArgumentException> { SoapClientSettings(port = 8081, advertisedUrl = advertisedUrl) }
            .message.isNotNull().contains("SoapClientSettings.advertisedUrl")
    }
}
