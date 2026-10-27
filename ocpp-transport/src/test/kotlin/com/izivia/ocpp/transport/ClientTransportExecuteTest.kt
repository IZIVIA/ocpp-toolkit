package com.izivia.ocpp.transport

import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import strikt.assertions.isGreaterThan
import strikt.assertions.isLessThan
import strikt.assertions.isSameInstanceAs
import kotlin.reflect.KClass
import kotlin.time.Clock
import kotlin.time.Instant

// Sleeps around the reading so the execution timestamps cannot coincide with it.
private fun clockTick(): Instant {
    Thread.sleep(2)
    return Clock.System.now().also { Thread.sleep(2) }
}

class ClientTransportExecuteTest {

    data class PingReq(val value: String)
    data class PingResp(val value: String)

    private class RecordingClientTransport(private val answer: (Any?) -> Any) : ClientTransport {
        var clazz: KClass<*>? = null
        var action: String? = null
        var message: Any? = null
        var callTime: Instant? = null

        override fun connect() = Unit

        override fun close() = Unit

        @Suppress("UNCHECKED_CAST")
        override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, action: String, message: T): P {
            this.clazz = clazz
            this.action = action
            this.message = message
            callTime = clockTick()
            return answer(message) as P
        }

        override fun <T : Any, P> receiveMessageClass(clazz: KClass<T>, action: String, fn: (T) -> P) =
            throw UnsupportedOperationException()
    }

    private val meta = RequestMetadata("cs-1", "msg-1")

    @Test
    fun `execute forwards the action and request with the reified response class`() {
        val request = PingReq("ping")
        val transport = RecordingClientTransport { PingResp("pong") }

        transport.execute<PingReq, PingResp>(meta, "Ping", request)

        expectThat(transport) {
            get { clazz }.isEqualTo(PingResp::class)
            get { action }.isEqualTo("Ping")
            get { message }.isSameInstanceAs(request)
        }
    }

    @Test
    fun `execute wraps the transport answer in a successful OperationExecution timed around the call`() {
        val request = PingReq("ping")
        val response = PingResp("pong")
        val transport = RecordingClientTransport { response }

        val execution = transport.execute<PingReq, PingResp>(meta, "Ping", request)

        expectThat(execution) {
            get { this.request }.isSameInstanceAs(request)
            get { this.response }.isSameInstanceAs(response)
            get { executionMeta.reqMeta }.isEqualTo(meta)
            get { executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
        val callTime = requireNotNull(transport.callTime)
        expectThat(execution.executionMeta.requestTime).isLessThan(callTime)
        expectThat(execution.executionMeta.responseTime).isGreaterThan(callTime)
    }

    @Test
    fun `execute lets a transport failure propagate`() {
        val failure = IllegalStateException("not connected")
        val transport = RecordingClientTransport { throw failure }

        expectThrows<IllegalStateException> {
            transport.execute<PingReq, PingResp>(meta, "Ping", PingReq("ping"))
        }.isSameInstanceAs(failure)
    }
}
