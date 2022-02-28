package fr.simatix.cs.simulator.adapter.test

import fr.simatix.cs.simulator.adapter20.Ocpp20Adapter
import fr.simatix.cs.simulator.api.model.ExecutionMetadata
import fr.simatix.cs.simulator.api.model.OperationExecution
import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.api.model.RequestStatus
import fr.simatix.cs.simulator.api.model.bootnotification.ModemType
import fr.simatix.cs.simulator.api.model.common.MeterValueType
import fr.simatix.cs.simulator.api.model.common.SampledValueType
import fr.simatix.cs.simulator.api.model.common.SignedMeterValueType
import fr.simatix.cs.simulator.api.model.common.enumeration.LocationEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.MeasurandEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.PhaseEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.ReadingContextEnumType
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core20.model.bootnotification.ChargingStationType
import fr.simatix.cs.simulator.core20.model.bootnotification.enumeration.BootReasonEnumType
import fr.simatix.cs.simulator.core20.model.bootnotification.enumeration.RegistrationStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.core20.model.common.IdTokenType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.AuthorizationStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.IdTokenEnumType
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core20.model.datatransfer.enumeration.DataTransferStatusEnumType
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesResp
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
import fr.simatix.cs.simulator.api.model.bootnotification.ChargingStationType as ChargingStationTypeGen
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.BootReasonEnumType as BootReasonEnumTypeGen
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.RegistrationStatusEnumType as RegistrationStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.IdTokenInfoType as IdTokenInfoTypeGen
import fr.simatix.cs.simulator.api.model.common.IdTokenType as IdTokenTypeGen
import fr.simatix.cs.simulator.api.model.common.StatusInfoType as StatusInfoTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.AuthorizationStatusEnumType as AuthorizationStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.IdTokenEnumType as IdTokenEnumTypeGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen


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

        val operations = Ocpp20Adapter(transport)
        val request = HeartbeatReqGen()
        val response = operations.heartbeat(requestMetadata, request)
        expectThat(response)
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }

    @Test
    fun `authorize request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.authorize(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            AuthorizeReq(IdTokenType("", IdTokenEnumType.Central)),
            AuthorizeResp(
                idTokenInfo = IdTokenInfoType(
                    status = AuthorizationStatusEnumType.NotAllowedTypeEVSE,
                    groupIdToken = IdTokenType("Tag3", IdTokenEnumType.ISO14443)
                )
            )
        )

        val operations = Ocpp20Adapter(transport)
        val request = AuthorizeReqGen(idToken = IdTokenTypeGen("Tag1", IdTokenEnumTypeGen.Central))
        val response = operations.authorize(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.response.idTokenInfo }.isEqualTo(
                    IdTokenInfoTypeGen(
                        status = AuthorizationStatusEnumTypeGen.NotAllowedTypeEVSE,
                        groupIdToken = IdTokenTypeGen("Tag3", IdTokenEnumTypeGen.ISO14443)
                    )
                )
            }
    }

    @Test
    fun `meterValues request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.meterValues(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesReq(1, listOf()),
            MeterValuesResp()
        )

        val operations = Ocpp20Adapter(transport)
        val request = MeterValuesReq(
            1, listOf(
                MeterValueType(
                    listOf(
                        SampledValueType(0.6), SampledValueType(
                            value = 2.4,
                            context = ReadingContextEnumType.SampleClock,
                            measurand = MeasurandEnumType.Temperature,
                            phase = PhaseEnumType.L1,
                            location = LocationEnumType.Body,
                            signedMeterValue = SignedMeterValueType("Hello", "Bye", "Welcome", "Nice")
                        )
                    ),
                    Instant.parse("2022-02-15T00:00:00.000Z"),
                )
            )
        )
        val response = operations.meterValues(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.NOT_SEND)
            }
    }

    @Test
    fun `dataTransfer request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.dataTransfer(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferReq(""),
            DataTransferResp(
                status = DataTransferStatusEnumType.Accepted,
                data = "Hello",
                statusInfo = StatusInfoType("reason", "additional")
            )
        )

        val operations = Ocpp20Adapter(transport)
        val request = DataTransferReq(
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
            .and { get { this.response.status }.isEqualTo(fr.simatix.cs.simulator.api.model.datatransfer.enumeration.DataTransferStatusEnumType.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(StatusInfoTypeGen("reason", "additional")) }
    }

    @Test
    fun `bootNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.bootNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            BootNotificationReq(ChargingStationType("", ""), BootReasonEnumType.ApplicationReset),
            BootNotificationResp(
                Instant.parse("2022-02-15T00:00:00.000Z"),
                10,
                RegistrationStatusEnumType.Accepted
            )
        )

        val operations = Ocpp20Adapter(transport)
        val request =
            BootNotificationReqGen(
                ChargingStationTypeGen("model", "vendor", "firmware", ModemType("a", "b")),
                BootReasonEnumTypeGen.ApplicationReset
            )
        val response = operations.bootNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
            .and { get { this.response.interval }.isEqualTo(10) }
            .and { get { this.response.status }.isEqualTo(RegistrationStatusEnumTypeGen.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(null) }
    }
}