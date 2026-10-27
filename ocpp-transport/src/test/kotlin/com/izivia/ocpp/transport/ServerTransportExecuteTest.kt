package com.izivia.ocpp.transport

import com.izivia.ocpp.operation.information.ChargingStationConfig
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

class ServerTransportExecuteTest {

    data class ResetReq(val value: String)
    data class ResetResp(val value: String)

    private class RecordingServerTransport(private val answer: (Any?) -> Any) : ServerTransport {
        var clazz: KClass<*>? = null
        var csOcppId: String? = null
        var action: String? = null
        var message: Any? = null
        var callTime: Instant? = null

        @Suppress("UNCHECKED_CAST")
        override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, csOcppId: String, action: String, message: T): P {
            this.clazz = clazz
            this.csOcppId = csOcppId
            this.action = action
            this.message = message
            callTime = clockTick()
            return answer(message) as P
        }

        override fun <T : Any, P> receiveMessageClass(
            clazz: KClass<T>,
            action: String,
            ocppVersion: OcppVersion,
            onAction: (RequestMetadata, T) -> P,
            accept: (String) -> ChargingStationConfig
        ) = throw UnsupportedOperationException()

        override fun canSendToChargingStation(chargingStationConfig: ChargingStationConfig) = true
    }

    private val meta = RequestMetadata("cs-1", "msg-1")

    @Test
    fun `execute forwards the charging station, action and request with the reified response class`() {
        val request = ResetReq("hard")
        val transport = RecordingServerTransport { ResetResp("accepted") }

        transport.execute<ResetReq, ResetResp>(meta, "cs-1", "Reset", request)

        expectThat(transport) {
            get { clazz }.isEqualTo(ResetResp::class)
            get { csOcppId }.isEqualTo("cs-1")
            get { action }.isEqualTo("Reset")
            get { message }.isSameInstanceAs(request)
        }
    }

    @Test
    fun `execute wraps the transport answer in a successful OperationExecution timed around the call`() {
        val request = ResetReq("hard")
        val response = ResetResp("accepted")
        val transport = RecordingServerTransport { response }

        val execution = transport.execute<ResetReq, ResetResp>(meta, "cs-1", "Reset", request)

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
        val failure = IllegalStateException("charging station not connected")
        val transport = RecordingServerTransport { throw failure }

        expectThrows<IllegalStateException> {
            transport.execute<ResetReq, ResetResp>(meta, "cs-1", "Reset", ResetReq("hard"))
        }.isSameInstanceAs(failure)
    }
}
