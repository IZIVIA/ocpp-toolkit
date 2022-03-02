package fr.simatix.cs.simulator

import fr.simatix.cs.simulator.adapter16.Ocpp16Adapter
import fr.simatix.cs.simulator.api.model.bootnotification.ChargingStationType
import fr.simatix.cs.simulator.api.model.bootnotification.ModemType
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.BootReasonEnumType
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.RegistrationStatusEnumType
import fr.simatix.cs.simulator.api.model.common.*
import fr.simatix.cs.simulator.api.model.common.enumeration.*
import fr.simatix.cs.simulator.api.model.datatransfer.enumeration.DataTransferStatusEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.EVSEType
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.ReasonEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TransactionEventEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TriggerReasonEnumType
import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core16.model.bootnotification.enumeration.RegistrationStatus
import fr.simatix.cs.simulator.core16.model.common.IdTagInfo
import fr.simatix.cs.simulator.core16.model.common.enumeration.AuthorizationStatus
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core16.model.datatransfer.enumeration.DataTransferStatus
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionReq
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionResp
import fr.simatix.cs.simulator.core16.model.stoptransaction.StopTransactionReq
import fr.simatix.cs.simulator.core16.model.stoptransaction.StopTransactionResp
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import fr.simatix.cs.simulator.transport.Transport
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq as BootNotificationReqGen
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq as DataTransferReqGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq as MeterValuesReqGen


class AdapterTest {
    private lateinit var transport: Transport
    private lateinit var chargePointOperations: RealChargePointOperations


    @BeforeEach
    fun init() {
        transport = mockk()
        chargePointOperations = mockk()

        mockkObject(ChargePointOperations.Companion)
        every { ChargePointOperations.Companion.newChargePointOperations(any()) } returns chargePointOperations
    }

    @Test
    fun `heartbeat request`() {
        val requestMetadata = RequestMetadata("")

        every { chargePointOperations.heartbeat(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            HeartbeatReq(),
            HeartbeatResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
            )
        )

        val operations = Ocpp16Adapter(transport)
        val request = HeartbeatReqGen()
        val response = operations.heartbeat(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }

    @Test
    fun `authorize request`() {
        val requestMetadata = RequestMetadata("")

        every { chargePointOperations.authorize(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            AuthorizeReq(""),
            AuthorizeResp(
                idTagInfo = IdTagInfo(
                    AuthorizationStatus.ConcurrentTx,
                    expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                    parentIdTag = "Tag0"
                )
            )
        )

        val operations = Ocpp16Adapter(transport)
        val request = AuthorizeReqGen(idToken = IdTokenType("Tag1", IdTokenEnumType.Central))
        val response = operations.authorize(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.response.idTokenInfo }.isEqualTo(
                    IdTokenInfoType(
                        status = AuthorizationStatusEnumType.ConcurrentTx,
                        cacheExpiryDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                        groupIdToken = IdTokenType("Tag0", IdTokenEnumType.Central)
                    )
                )
            }
    }

    @Test
    fun `meterValues request`() {
        val requestMetadata = RequestMetadata("")

        every { chargePointOperations.meterValues(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            MeterValuesReq(0, listOf()),
            MeterValuesResp()
        )

        val operations = Ocpp16Adapter(transport)
        val request = MeterValuesReqGen(
            1, listOf(
                MeterValueType(
                    listOf(
                        SampledValueType(0.6), SampledValueType(
                            2.4,
                            ReadingContextEnumType.SampleClock,
                            MeasurandEnumType.EnergyActiveNet,
                            PhaseEnumType.L1,
                            LocationEnumType.Body,
                            signedMeterValue = SignedMeterValueType("Hello", "Bye", "Welcome", "Nice"),
                            UnitOfMeasure("kWh")
                        )
                    ),
                    Instant.parse("2022-02-15T00:00:00.000Z"),
                )
            )
        )
        val response = operations.meterValues(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.NOT_SEND) }
    }

    @Test
    fun `dataTransfer request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.dataTransfer(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            DataTransferReq(""),
            DataTransferResp(
                status = DataTransferStatus.Accepted,
                data = "Hello",
            )
        )

        val operations = Ocpp16Adapter(transport)
        val request = DataTransferReqGen(
            vendorId = "vendor1",
            messageId = "ID100",
            data = "Bye"
        )
        val response = operations.dataTransfer(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
            .and { get { this.response.data }.isEqualTo("Hello") }
            .and { get { this.response.status }.isEqualTo(DataTransferStatusEnumType.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(null) }
    }

    @Test
    fun `bootNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.bootNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            BootNotificationReq("", ""),
            BootNotificationResp(
                Instant.parse("2022-02-15T00:00:00.000Z"),
                10,
                RegistrationStatus.Accepted
            )
        )

        val operations = Ocpp16Adapter(transport)
        val request =
            BootNotificationReqGen(
                ChargingStationType("model", "vendor", "firmware", ModemType("a", "b")),
                BootReasonEnumType.ApplicationReset
            )
        val response = operations.bootNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
            .and { get { this.response.interval }.isEqualTo(10) }
            .and { get { this.response.status }.isEqualTo(RegistrationStatusEnumType.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(null) }
    }

    @Test
    fun `startTransaction request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.startTransaction(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            StartTransactionReq(1, "Tag1", 100, Instant.parse("2022-02-15T00:00:00.000Z")),
            StartTransactionResp(
                IdTagInfo(AuthorizationStatus.Accepted, Instant.parse("2022-02-15T00:00:00.000Z"), "Tag2"), 10,
            )
        )

        val operations = Ocpp16Adapter(transport)
        val request =
            TransactionEventReq(
                eventType = TransactionEventEnumType.Started,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                triggerReason = TriggerReasonEnumType.EVDetected,
                seqNo = 100,
                transactionInfo = TransactionType("T1"),
                evse = EVSEType(1),
                meterValue = listOf(
                    MeterValueType(
                        listOf(SampledValueType(10.0,ReadingContextEnumType.TransactionBegin)),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                ),
                idToken = IdTokenType("Tag1", IdTokenEnumType.Central),
                reservationId = 10
            )
        val response = operations.transactionEvent(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
            .and {
                get { this.response.idTokenInfo }.isEqualTo(
                    IdTokenInfoType(
                        status = AuthorizationStatusEnumType.Accepted,
                        cacheExpiryDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                        groupIdToken = IdTokenType("Tag2", IdTokenEnumType.Central)
                    )
                )
            }
    }

    @Test
    fun `stopTransaction request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.stopTransaction(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            StopTransactionReq(200,  Instant.parse("2022-02-15T00:00:00.000Z"),12),
            StopTransactionResp(
                IdTagInfo(AuthorizationStatus.Accepted, Instant.parse("2022-02-15T00:00:00.000Z"), "Tag2")
            )
        )

        val operations = Ocpp16Adapter(transport)
        val request =
            TransactionEventReq(
                eventType = TransactionEventEnumType.Ended,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                triggerReason = TriggerReasonEnumType.Deauthorized,
                seqNo = 100,
                transactionInfo = TransactionType("T1", stoppedReason = ReasonEnumType.Timeout),
                evse = EVSEType(1),
                meterValue = listOf(
                    MeterValueType(
                        listOf(SampledValueType(10.0,ReadingContextEnumType.TransactionEnd),
                            SampledValueType(20.0, ReadingContextEnumType.TransactionEnd, MeasurandEnumType.CurrentExport)),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    ),
                    MeterValueType(
                        listOf(SampledValueType(10.0,ReadingContextEnumType.TransactionEnd, MeasurandEnumType.EnergyActiveExportRegister),
                            SampledValueType(20.0, ReadingContextEnumType.TransactionBegin)),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                ),
                idToken = IdTokenType("Tag1", IdTokenEnumType.Central),
                reservationId = 10
            )
        val response = operations.transactionEvent(requestMetadata, request)
       println(response.response)
    }
}