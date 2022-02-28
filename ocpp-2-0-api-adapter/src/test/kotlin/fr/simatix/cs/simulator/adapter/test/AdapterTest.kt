package fr.simatix.cs.simulator.adapter.test

import fr.simatix.cs.simulator.adapter20.Ocpp20Adapter
import fr.simatix.cs.simulator.api.model.ExecutionMetadata
import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.api.model.RequestStatus
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.api.model.bootnotification.ChargingStationType
import fr.simatix.cs.simulator.api.model.bootnotification.ModemType
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.BootReasonEnumType
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.RegistrationStatusEnumType
import fr.simatix.cs.simulator.api.model.common.*
import fr.simatix.cs.simulator.api.model.common.enumeration.*
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core20.model.CoreExecution
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType as StatusInfoType20
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core20.model.datatransfer.enumeration.DataTransferStatusEnumType
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
import fr.simatix.cs.simulator.core20.model.common.IdTokenInfoType as IdTokenInfoType20
import fr.simatix.cs.simulator.core20.model.common.IdTokenType as IdTokenType20
import fr.simatix.cs.simulator.core20.model.common.enumeration.AuthorizationStatusEnumType as AuthorizationStatusEnumType20
import fr.simatix.cs.simulator.core20.model.common.enumeration.IdTokenEnumType as IdTokenEnumType20
import fr.simatix.cs.simulator.core20.model.bootnotification.enumeration.RegistrationStatusEnumType as RegistrationStatusEnumType20


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
        every { chargePointOperations.heartbeat(any(), any()) } returns CoreExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            HeartbeatResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
            )
        )

        val operations = Ocpp20Adapter(transport)
        val request = HeartbeatReq()
        val response = operations.heartbeat(requestMetadata, request)
        expectThat(response)
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }

    @Test
    fun `authorize request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.authorize(any(), any()) } returns CoreExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            AuthorizeResp(
                idTokenInfo = IdTokenInfoType20(
                    status = AuthorizationStatusEnumType20.NotAllowedTypeEVSE,
                    groupIdToken = IdTokenType20("Tag3", IdTokenEnumType20.ISO14443)
                )
            )
        )

        val operations = Ocpp20Adapter(transport)
        val request = AuthorizeReqGen(idToken = IdTokenType("Tag1", IdTokenEnumType.Central))
        val response = operations.authorize(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.response.idTokenInfo }.isEqualTo(
                    IdTokenInfoType(
                        status = AuthorizationStatusEnumType.NotAllowedTypeEVSE,
                        groupIdToken = IdTokenType("Tag3", IdTokenEnumType.ISO14443)
                    )
                )
            }
    }

    @Test
    fun `meterValues request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.meterValues(any(), any()) } returns CoreExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
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
        every { chargePointOperations.dataTransfer(any(), any()) } returns CoreExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            DataTransferResp(
                status = DataTransferStatusEnumType.Accepted,
                data = "Hello",
                statusInfo = StatusInfoType20("reason","additional")
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
            .and { get { this.response.statusInfo }.isEqualTo(StatusInfoType("reason","additional")) }
    }

    @Test
    fun `bootNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.bootNotification(any(), any()) } returns CoreExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            BootNotificationResp(
                Instant.parse("2022-02-15T00:00:00.000Z"),
                10,
                RegistrationStatusEnumType20.Accepted
            )
        )

        val operations = Ocpp20Adapter(transport)
        val request =
            BootNotificationReq(ChargingStationType("model", "vendor", "firmware", ModemType("a","b")), BootReasonEnumType.ApplicationReset)
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
}