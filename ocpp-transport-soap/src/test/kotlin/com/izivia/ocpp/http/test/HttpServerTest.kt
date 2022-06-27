package com.izivia.ocpp.http.test

import com.izivia.ocpp.http.HttpServer
import org.http4k.client.JavaHttpClient
import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class HttpServerTest {

    @Test
    fun `should start`() {
        val server = HttpServer(port = 5002, path = "/ocpp16soap/")
        server.start()

        val request = Request(Method.GET, "http://127.0.0.1:5002/")

        val client: HttpHandler = JavaHttpClient()
        val res = client(request)
        expectThat(res) {
            get { body.toString() }.isEqualTo("Hello")
            get { status.code }.isEqualTo(200)
        }
    }
}