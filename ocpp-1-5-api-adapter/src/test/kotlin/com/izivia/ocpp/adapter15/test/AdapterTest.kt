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
import com.izivia.ocpp.api.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.api.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq
import com.izivia.ocpp.api.model.datatransfer.enumeration.DataTransferStatusEnumType
import com.izivia.ocpp.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import com.izivia.ocpp.api.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatusEnumType
import com.izivia.ocpp.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType
import com.izivia.ocpp.api.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.api.model.statusnotification.enumeration.ConnectorStatusEnumType
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
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import com.izivia.ocpp.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import com.izivia.ocpp.api.model.bootnotification.BootNotificationReq as BootNotificationReqGen
import com.izivia.ocpp.api.model.bootnotification.ChargingStationType as ChargingStationTypeGen
import com.izivia.ocpp.api.model.bootnotification.enumeration.BootReasonEnumType as BootReasonEnumTypeGen
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import com.izivia.ocpp.api.model.metervalues.MeterValuesReq as MeterValuesReqGen
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationReq as StatusNotificationReqGen
import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReqGen
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationReq as LogStatusNotificationReqGen
import com.izivia.ocpp.api.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq as DiagnosticsStatusNotificationReqGen
import com.izivia.ocpp.core15.model.authorize.AuthorizeReq as AuthorizeReqCore
import com.izivia.ocpp.core15.model.authorize.AuthorizeResp as AuthorizeRespCore
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationReq as BootNotificationReqCore
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationResp as BootNotificationRespCore
import com.izivia.ocpp.core15.model.bootnotification.enumeration.RegistrationStatus as RegistrationStatusCore
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq as DataTransferReqCore
import com.izivia.ocpp.core15.model.datatransfer.DataTransferResp as DataTransferRespCore
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq as DiagnosticsStatusNotificationReqCore
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp as DiagnosticsStatusNotificationRespCore
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReqCore
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationResp as FirmwareStatusNotificationRespCore
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatReq as HeartbeatReqCore
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatResp as HeartbeatRespCore
import com.izivia.ocpp.core15.model.metervalues.MeterValuesReq as MeterValuesReqCore
import com.izivia.ocpp.core15.model.metervalues.MeterValuesResp as MeterValuesRespCore
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionReq as StartTransactionReqCore
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionResp as StartTransactionRespCore
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationReq as StatusNotificationReqCore
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationResp as StatusNotificationRespCore
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
    fun `meter values request`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.meterValues(any(), any()) } returns success(
            requestMetadata,
            MeterValuesReqCore(1),
            MeterValuesRespCore()
        )

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = MeterValuesReqGen(
            connectorId = 1,
            evseId = 1,
            meterValue = listOf(MeterValueType(listOf(SampledValueType(10.0)), timestamp)),
            transactionId = null
        )
        val response = adapter.meterValues(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
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
    fun `boot notification request`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.bootNotification(any(), any()) } returns success(
            requestMetadata,
            BootNotificationReqCore(chargePointModel = "model", chargePointVendor = "vendor"),
            BootNotificationRespCore(timestamp, 30, RegistrationStatusCore.Accepted)
        )

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = BootNotificationReqGen(
            ChargingStationTypeGen("model", "vendor"),
            BootReasonEnumTypeGen.PowerUp
        )
        val response = adapter.bootNotification(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.currentTime }.isEqualTo(timestamp)
            get { this.response.interval }.isEqualTo(30)
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

    @Test
    fun `status notification request`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.statusNotification(any(), any()) } returns success(
            requestMetadata,
            StatusNotificationReqCore(
                connectorId = 1,
                errorCode = com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointErrorCode.NoError,
                status = com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointStatus.Available
            ),
            StatusNotificationRespCore()
        )

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = StatusNotificationReqGen(
            connectorId = 1,
            evseId = 1,
            connectorStatus = ConnectorStatusEnumType.Available,
            errorCode = ChargePointErrorCode.NoError,
            timestamp = timestamp
        )
        val response = adapter.statusNotification(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
    }

    @Test
    fun `firmware status notification request`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.firmwareStatusNotification(any(), any()) } returns success(
            requestMetadata,
            FirmwareStatusNotificationReqCore(com.izivia.ocpp.core15.model.firmwarestatusnotification.enumeration.FirmwareStatus.Downloaded),
            FirmwareStatusNotificationRespCore()
        )

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = FirmwareStatusNotificationReqGen(FirmwareStatusEnumType.Downloaded)
        val response = adapter.firmwareStatusNotification(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
    }

    @Test
    fun `diagnostics status notification request`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.diagnosticsStatusNotification(any(), any()) } returns success(
            requestMetadata,
            DiagnosticsStatusNotificationReqCore(com.izivia.ocpp.core15.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus.Uploaded),
            DiagnosticsStatusNotificationRespCore()
        )

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = DiagnosticsStatusNotificationReqGen(DiagnosticsStatusEnumType.Uploaded)
        val response = adapter.diagnosticsStatusNotification(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
    }

    @Test
    fun `transient firmware status is ignored and not forwarded in OCPP 1_5`() {
        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = FirmwareStatusNotificationReqGen(FirmwareStatusEnumType.Installing)

        val response = adapter.firmwareStatusNotification(RequestMetadata("CP001"), request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.executionMeta.status }.isEqualTo(RequestStatus.NOT_SEND)
        }
        verify(exactly = 0) { chargePointOperations.firmwareStatusNotification(any(), any()) }
    }

    @Test
    fun `transient diagnostics status is ignored and not forwarded in OCPP 1_5`() {
        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = DiagnosticsStatusNotificationReqGen(DiagnosticsStatusEnumType.Uploading)

        val response = adapter.diagnosticsStatusNotification(RequestMetadata("CP001"), request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.executionMeta.status }.isEqualTo(RequestStatus.NOT_SEND)
        }
        verify(exactly = 0) { chargePointOperations.diagnosticsStatusNotification(any(), any()) }
    }

    @Test
    fun `unsupported OCPP 1_5 generic requests are rejected`() {
        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val meta = RequestMetadata("CP001")

        assertUnsupported { adapter.notifyReport(meta, mockk()) }
        assertUnsupported { adapter.clearedChargingLimit(meta, mockk()) }
        assertUnsupported { adapter.getCertificateStatus(meta, mockk()) }
        assertUnsupported { adapter.notifyCustomerInformation(meta, mockk()) }
        assertUnsupported { adapter.notifyEvent(meta, mockk()) }
        assertUnsupported { adapter.notifyEVChargingSchedule(meta, mockk()) }
        assertUnsupported { adapter.notifyChargingLimit(meta, mockk()) }
        assertUnsupported { adapter.notifyDisplayMessages(meta, mockk()) }
        assertUnsupported { adapter.notifyEVChargingNeeds(meta, mockk()) }
        assertUnsupported { adapter.publishFirmwareStatusNotification(meta, mockk()) }
        assertUnsupported { adapter.notifyMonitoringReport(meta, mockk()) }
        assertUnsupported { adapter.reservationStatusUpdate(meta, mockk()) }
        assertUnsupported { adapter.securityEventNotification(meta, mockk()) }
        assertUnsupported { adapter.signCertificate(meta, mockk()) }
        assertUnsupported { adapter.reportChargingProfiles(meta, mockk()) }
    }

    @Test
    fun `meter values with a measurand absent from OCPP 1_5 are not sent`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.meterValues(any(), any()) } returns success(
            requestMetadata,
            MeterValuesReqCore(1),
            MeterValuesRespCore()
        )

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = MeterValuesReqGen(
            connectorId = 1,
            evseId = 1,
            meterValue = listOf(
                MeterValueType(
                    listOf(SampledValueType(80.0, measurand = MeasurandEnumType.SoC)),
                    timestamp
                )
            ),
            transactionId = null
        )
        val response = adapter.meterValues(requestMetadata, request)

        expectThat(response.executionMeta.status).isEqualTo(RequestStatus.NOT_SEND)
        verify(exactly = 0) { chargePointOperations.meterValues(any(), any()) }
    }

    @Test
    fun `meter values transport failures are not reported as not sent`() {
        val requestMetadata = RequestMetadata("CP001")
        every { chargePointOperations.meterValues(any(), any()) } throws IllegalStateException("not connected")

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = MeterValuesReqGen(
            connectorId = 1,
            evseId = 1,
            meterValue = listOf(MeterValueType(listOf(SampledValueType(10.0)), timestamp)),
            transactionId = null
        )

        assertThrows(IllegalStateException::class.java) { adapter.meterValues(requestMetadata, request) }
    }

    @Test
    fun `stopping a transaction releases its id mapping`() {
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

        val transactionIds = RealTransactionRepository()
        val adapter = Ocpp15Adapter("CP001", transport, csApi, transactionIds)
        adapter.transactionEvent(requestMetadata, transactionEvent(TransactionEventEnumType.Started, 10.0))
        expectThat(transactionIds.getTransactionIdsByLocalId("T1").csmsId).isEqualTo(123)

        adapter.transactionEvent(requestMetadata, transactionEvent(TransactionEventEnumType.Ended, 20.0))

        assertThrows(IllegalStateException::class.java) { transactionIds.getTransactionIdsByLocalId("T1") }
    }

    @Test
    fun `transaction event updated without a charging state is not sent`() {
        val requestMetadata = RequestMetadata("CP001")

        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())
        val request = transactionEvent(TransactionEventEnumType.Updated, 10.0)
        val response = adapter.transactionEvent(requestMetadata, request)

        expectThat(response.executionMeta.status).isEqualTo(RequestStatus.NOT_SEND)
        verify(exactly = 0) { chargePointOperations.statusNotification(any(), any()) }
    }

    @Test
    fun `log status notification is rejected in OCPP 1_5`() {
        val adapter = Ocpp15Adapter("CP001", transport, csApi, RealTransactionRepository())

        assertUnsupported {
            adapter.logStatusNotification(
                RequestMetadata("CP001"),
                LogStatusNotificationReqGen(UploadLogStatusEnumType.Uploaded, requestId = 1)
            )
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

    private fun assertUnsupported(call: () -> Unit) {
        assertThrows(IllegalStateException::class.java, call)
    }

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
