package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core16.model.bootnotification.enumeration.RegistrationStatus
import fr.simatix.cs.simulator.core16.model.common.IdTagInfo
import fr.simatix.cs.simulator.core16.model.common.MeterValue
import fr.simatix.cs.simulator.core16.model.common.SampledValue
import fr.simatix.cs.simulator.core16.model.common.enumeration.AuthorizationStatus
import fr.simatix.cs.simulator.core16.model.common.enumeration.Phase
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core16.model.datatransfer.enumeration.DataTransferStatus
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionReq
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionResp
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointErrorCode
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointStatus
import fr.simatix.cs.simulator.core16.model.stoptransaction.StopTransactionReq
import fr.simatix.cs.simulator.core16.model.stoptransaction.StopTransactionResp
import fr.simatix.cs.simulator.core16.model.stoptransaction.enumeration.Reason
import fr.simatix.cs.simulator.utils.JsonSchemaValidator
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class JsonSchemaTest {

    @Test
    fun `heartbeat request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(HeartbeatReq(), "HeartbeatRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `authorize request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            AuthorizeReq(
                idTag = "Tag1"
            ), "AuthorizeRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `meterValues request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV4(
            MeterValuesReq(
                connectorId = 1,
                meterValue = listOf(MeterValue(listOf(SampledValue("0")), Instant.parse("2022-02-15T00:00:00.000Z")))
            ), "MeterValuesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV4(
            MeterValuesReq(
                connectorId = 1,
                meterValue = listOf(
                    MeterValue(
                        listOf(SampledValue(value = "0", phase = Phase.L1L2)),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                ),
                transactionId = 1
            ), "MeterValuesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `startTransaction request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV4(
            StartTransactionReq(
                connectorId = 1,
                idTag = "Tag1",
                meterStart = 100,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z")
            ), "StartTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV4(
            StartTransactionReq(
                connectorId = 1,
                idTag = "Tag1",
                meterStart = 100,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                reservationId = 110
            ), "StartTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `stopTransaction request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV4(
            StopTransactionReq(
                meterStop = 200,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                transactionId = 12
            ),
            "StopTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV4(
            StopTransactionReq(
                meterStop = 200,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                transactionId = 12,
                idTag = "Tag1",
                reason = Reason.EVDisconnected,
                transactionData = listOf(
                    MeterValue(
                        listOf(SampledValue(value = "0", phase = Phase.L1L2)),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            ),
            "StopTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `statusNotification request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV4(
            StatusNotificationReq(
                connectorId = 1, errorCode = ChargePointErrorCode.NoError, status = ChargePointStatus.Charging
            ), "StatusNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV4(
            StatusNotificationReq(
                connectorId = 1,
                errorCode = ChargePointErrorCode.NoError,
                status = ChargePointStatus.Charging,
                info = "Charging",
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                vendorErrorCode = "Error",
                vendorId = "vendor1"
            ), "StatusNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

    }

    @Test
    fun `dataTransfer request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV4(
            DataTransferReq("vendor1"),
            "DataTransferRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV4(
            DataTransferReq(vendorId = "vendor1", messageId = "message1", data = "data1"),
            "DataTransferRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `bootNotification request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV4(
            BootNotificationReq(chargePointModel = "model1", chargePointVendor = "vendor1"),
            "BootNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV4(
            BootNotificationReq(
                chargePointModel = "model1",
                chargePointVendor = "vendor1",
                chargePointSerialNumber = "SR-100",
                chargeBoxSerialNumber = "BOX-SR-100",
                firmwareVersion = "2.0",
                iccid = "0",
                imsi = "0",
                meterSerialNumber = "SR",
                meterType = "kW"
            ),
            "BootNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `heartbeat response format`() {
        val heartbeatResp = HeartbeatResp(
            currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
        )
        val errors = JsonSchemaValidator.isValidObjectV4(heartbeatResp, "HeartbeatResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `authorize response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV4(
            AuthorizeResp(
                idTagInfo = IdTagInfo(AuthorizationStatus.Accepted)
            ), "AuthorizeResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV4(
            AuthorizeResp(
                idTagInfo = IdTagInfo(
                    status = AuthorizationStatus.Accepted,
                    expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                    parentIdTag = "Parent"
                )
            ), "AuthorizeResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `meterValues response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            MeterValuesResp(), "MeterValuesResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `startTransaction response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV4(
            StartTransactionResp(IdTagInfo(AuthorizationStatus.Accepted), 12),
            "StartTransactionResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV4(
            StartTransactionResp(
                idTagInfo = IdTagInfo(
                    status = AuthorizationStatus.Accepted,
                    expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                    parentIdTag = "Tag1"
                ), transactionId = 12
            ),
            "StartTransactionResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `stopTransaction response format`() {
        var errors = JsonSchemaValidator.isValidObjectV4(
            StopTransactionResp(), "StopTransactionResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV4(
            StopTransactionResp(
                IdTagInfo(
                    status = AuthorizationStatus.Accepted,
                    expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                    parentIdTag = "Tag1"
                )
            ), "StopTransactionResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `statusNotification response format`() {
        val errors =
            JsonSchemaValidator.isValidObjectV4(StatusNotificationResp(), "StatusNotificationResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `dataTransfer response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV4(
            DataTransferResp(DataTransferStatus.Accepted),
            "DataTransferResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV4(
            DataTransferResp(DataTransferStatus.Accepted, "data1"),
            "DataTransferResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `bootNotification response format`() {
        /* Required field only */
        val errors = JsonSchemaValidator.isValidObjectV4(
            BootNotificationResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                interval = 10,
                status = RegistrationStatus.Accepted
            ),
            "BootNotificationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }
}