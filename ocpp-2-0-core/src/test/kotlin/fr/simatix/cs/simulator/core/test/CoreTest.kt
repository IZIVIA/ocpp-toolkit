package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core20.model.authorize.OCSPRequestDataType
import fr.simatix.cs.simulator.core20.model.authorize.enumeration.AuthorizeCertificateStatusEnumType
import fr.simatix.cs.simulator.core20.model.authorize.enumeration.HashAlgorithmEnumType
import fr.simatix.cs.simulator.core20.model.common.*
import fr.simatix.cs.simulator.core20.model.common.enumeration.AuthorizationStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.IdTokenEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.MessageFormatEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.PhaseEnumType
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.core20.model.statusnotification.enumeration.ConnectorStatusEnumType
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionType
import fr.simatix.cs.simulator.core20.model.transactionevent.enumeration.TransactionEventEnumType
import fr.simatix.cs.simulator.core20.model.transactionevent.enumeration.TriggerReasonEnumType
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.sendMessage
import fr.simatix.cs.simulator.utils.JsonSchemaValidator
import io.mockk.every
import io.mockk.mockk
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CoreTest {
    @Test
    fun `heartbeat request`() {
        val transport = mockk<Transport>()
        every { transport.sendMessage<HeartbeatReq, HeartbeatResp>(any(), any()) } returns HeartbeatResp(
            currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
        )

        val operations =
            ChargePointOperations.newChargePointOperations(transport)
        val response = operations.heartbeat(RequestMetadata(""), HeartbeatReq())
        expectThat(response)
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }

    @Test
    fun `heartbeat request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(HeartbeatReq(), "HeartbeatRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `authorize request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(
            AuthorizeReq(
                idToken = IdTokenType("Tag1", IdTokenEnumType.Local)
            ), "AuthorizeRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            AuthorizeReq(
                idToken = IdTokenType("Tag1", IdTokenEnumType.Central, listOf(AdditionalInfoType("", ""))),
                certificate = "certificate1",
                iso15118CertificateHashData = listOf(OCSPRequestDataType(HashAlgorithmEnumType.SHA256, "", "", "", ""))
            ), "AuthorizeRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `meterValues request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(
            MeterValuesReq(
                evseId = 1,
                meterValue = listOf(
                    MeterValueType(
                        listOf(SampledValueType(value = 0.9)),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            ), "MeterValuesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            MeterValuesReq(
                evseId = 1,
                meterValue = listOf(
                    MeterValueType(
                        listOf(
                            SampledValueType(
                                value = 0.9,
                                phase = PhaseEnumType.L1,
                                signedMeterValue = SignedMeterValueType("", "", "", ""),
                                unitOfMeasure = UnitOfMeasure("", 2)
                            )
                        ), Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            ), "MeterValuesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `transactionEvent request format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            TransactionEventReq(
                TransactionEventEnumType.Started,
                Instant.parse("2022-02-15T00:00:00.000Z"),
                TriggerReasonEnumType.Authorized,
                1,
                TransactionType("")
            ), "TransactionEventRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            TransactionEventReq(
                TransactionEventEnumType.Started,
                Instant.parse("2022-02-15T00:00:00.000Z"),
                TriggerReasonEnumType.Authorized,
                1,
                TransactionType(""),
                1,
                EVSEType(1, 2),
                IdTokenType(
                    "", IdTokenEnumType.Central,
                    listOf(AdditionalInfoType("", ""))
                ),
                listOf(MeterValueType(listOf(SampledValueType(10.9)), Instant.parse("2022-02-15T00:00:00.000Z"))),
                3,
                true,
                100
            ), "TransactionEventRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `statusNotification request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            StatusNotificationReq(
            connectorId = 2,
            connectorStatus = ConnectorStatusEnumType.Available,
            evseId = 12,
            timestamp = Instant.parse("2022-02-15T00:00:00.000Z")
        ), "StatusNotificationRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `heartbeat response format`() {
        val heartbeatResp = HeartbeatResp(
            currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
        )
        val errors = JsonSchemaValidator.isValidObjectV6(heartbeatResp, "HeartbeatResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }


    @Test
    fun `authorize response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(
            AuthorizeResp(
                idTokenInfo = IdTokenInfoType(AuthorizationStatusEnumType.Accepted)
            ), "AuthorizeResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            AuthorizeResp(
                idTokenInfo = IdTokenInfoType(AuthorizationStatusEnumType.Blocked),
                certificateStatus = AuthorizeCertificateStatusEnumType.CertificateExpired
            ), "AuthorizeResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `meterValues response format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            MeterValuesResp(), "MeterValuesResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `transactionEvent response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(TransactionEventResp(), "TransactionEventResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            TransactionEventResp(
                200.0, 9,
                IdTokenInfoType(
                    AuthorizationStatusEnumType.Accepted,
                    Instant.parse("2022-02-15T00:00:00.000Z"),
                    9,
                    "",
                    listOf(2, 4),
                    "",
                    IdTokenType(
                        "", IdTokenEnumType.Central,
                        listOf(AdditionalInfoType("", ""))
                    ), MessageContentType(MessageFormatEnumType.ASCII, "", "")
                ), MessageContentType(MessageFormatEnumType.ASCII, "", "")
            ), "TransactionEventResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `statusNotification response format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(StatusNotificationResp(), "StatusNotificationResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }
}