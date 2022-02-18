package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.model.*
import fr.simatix.cs.simulator.core20.model.enumeration.AuthorizationStatusEnumType
import fr.simatix.cs.simulator.core20.model.enumeration.AuthorizeCertificateStatusEnumType
import fr.simatix.cs.simulator.core20.model.enumeration.HashAlgorithmEnumType
import fr.simatix.cs.simulator.core20.model.enumeration.IdTokenEnumType
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

}