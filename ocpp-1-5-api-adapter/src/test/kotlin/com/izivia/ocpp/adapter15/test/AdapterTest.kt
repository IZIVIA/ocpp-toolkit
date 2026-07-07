package com.izivia.ocpp.adapter15.test

import com.izivia.ocpp.adapter15.Ocpp15Adapter
import com.izivia.ocpp.adapter15.impl.RealTransactionRepository
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.api.model.common.IdTokenInfoType
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.common.SampledValueType
import com.izivia.ocpp.api.model.common.enumeration.AuthorizationStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.IdTokenEnumType
import com.izivia.ocpp.api.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq
import com.izivia.ocpp.api.model.datatransfer.enumeration.DataTransferStatusEnumType
import com.izivia.ocpp.api.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.api.model.transactionevent.TransactionType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TransactionEventEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TriggerReasonEnumType
import com.izivia.ocpp.core15.ChargePointOperations
import com.izivia.ocpp.core15.impl.RealChargePointOperations
import com.izivia.ocpp.core15.model.common.IdTagInfo
import com.izivia.ocpp.core15.model.common.enumeration.AuthorizationStatus
import com.izivia.ocpp.core15.model.datatransfer.enumeration.DataTransferStatus
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.ClientTransport
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import com.izivia.ocpp.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import com.izivia.ocpp.core15.model.authorize.AuthorizeReq as AuthorizeReqCore
import com.izivia.ocpp.core15.model.authorize.AuthorizeResp as AuthorizeRespCore
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq as DataTransferReqCore
import com.izivia.ocpp.core15.model.datatransfer.DataTransferResp as DataTransferRespCore
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatReq as HeartbeatReqCore
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatResp as HeartbeatRespCore
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionReq as StartTransactionReqCore
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionResp as StartTransactionRespCore
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionReq as StopTransactionReqCore
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionResp as StopTransactionRespCore

class AdapterTest {
    private lateinit var transport: ClientTransport
    private lateinit var csApi: CSApi
    private lateinit var chargePointOperations: RealChargePointOperations
    private val timestamp = Instant.parse("2022-02-15T00:00:00.000Z")

    @BeforeEach
    fun init() {
        transport = mockk()
        csApi = mockk()
        chargePointOperations = mockk()

        mockkObject(ChargePointOperations)
        every {
            ChargePointOperations.newChargePointOperations(any(), any(), any())
        } returns chargePointOperations
    }

    @AfterEach
    fun destroy() {
        unmockkAll()
    }

    @Test
    fun `heartbeat request`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.heartbeat(any(), any()) } returns success(
            requestMetadata,
            HeartbeatReqCore(),
            HeartbeatRespCore(timestamp)
        )

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = HeartbeatReqGen()
        val response = adapter.heartbeat(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.currentTime }.isEqualTo(timestamp)
        }
    }

    @Test
    fun `authorize request`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.authorize(any(), any()) } returns success(
            requestMetadata,
            AuthorizeReqCore("Tag1"),
            AuthorizeRespCore(IdTagInfo(timestamp, "Tag2", AuthorizationStatus.ConcurrentTx))
        )

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = AuthorizeReqGen(IdTokenType("Tag1", IdTokenEnumType.Central))
        val response = adapter.authorize(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.idTokenInfo }.isEqualTo(
                IdTokenInfoType(
                    status = AuthorizationStatusEnumType.ConcurrentTx,
                    cacheExpiryDateTime = timestamp,
                    groupIdToken = IdTokenType("Tag2", IdTokenEnumType.Central)
                )
            )
        }
    }

    @Test
    fun `data transfer request`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.dataTransfer(any(), any()) } returns success(
            requestMetadata,
            DataTransferReqCore("vendor"),
            DataTransferRespCore(DataTransferStatus.Accepted, "payload")
        )

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = DataTransferReq(vendorId = "vendor", messageId = "message", data = "request")
        val response = adapter.dataTransfer(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(DataTransferStatusEnumType.Accepted)
            get { this.response.data }.isEqualTo("payload")
        }
    }

    @Test
    fun `transaction event starts and stops OCPP 1_5 transaction`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.startTransaction(any(), any()) } returns success(
            requestMetadata,
            StartTransactionReqCore(1, "Tag1", 10, timestamp = timestamp),
            StartTransactionRespCore(IdTagInfo(status = AuthorizationStatus.Accepted), 123)
        )
        every { chargePointOperations.stopTransaction(any(), any()) } returns success(
            requestMetadata,
            StopTransactionReqCore("Tag1", 20, timestamp, 123),
            StopTransactionRespCore(IdTagInfo(status = AuthorizationStatus.Accepted))
        )

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val start = transactionEvent(TransactionEventEnumType.Started, 10.0)
        val startResponse = adapter.transactionEvent(requestMetadata, start)
        val stop = transactionEvent(TransactionEventEnumType.Ended, 20.0)
        val stopResponse = adapter.transactionEvent(requestMetadata, stop)

        expectThat(startResponse.response.idTokenInfo?.status).isEqualTo(AuthorizationStatusEnumType.Accepted)
        expectThat(stopResponse.response.idTokenInfo?.status).isEqualTo(AuthorizationStatusEnumType.Accepted)
        verify {
            chargePointOperations.stopTransaction(any(), match { it.transactionId == 123 })
        }
    }

    private fun transactionEvent(eventType: TransactionEventEnumType, meterValue: Double) =
        TransactionEventReq(
            eventType = eventType,
            timestamp = timestamp,
            triggerReason = TriggerReasonEnumType.Authorized,
            seqNo = 1,
            transactionInfo = TransactionType("T1"),
            evse = EVSEType(1, 1),
            idToken = IdTokenType("Tag1", IdTokenEnumType.Central),
            meterValue = listOf(
                MeterValueType(
                    listOf(SampledValueType(meterValue, contextFor(eventType))),
                    timestamp
                )
            )
        )

    private fun contextFor(eventType: TransactionEventEnumType) =
        when (eventType) {
            TransactionEventEnumType.Started -> ReadingContextEnumType.TransactionBegin
            TransactionEventEnumType.Updated,
            TransactionEventEnumType.Ended -> ReadingContextEnumType.TransactionEnd
        }

    private fun <REQ, RESP> success(
        requestMetadata: RequestMetadata,
        request: REQ,
        response: RESP
    ) = OperationExecution(
        ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
        request,
        response
    )
}
