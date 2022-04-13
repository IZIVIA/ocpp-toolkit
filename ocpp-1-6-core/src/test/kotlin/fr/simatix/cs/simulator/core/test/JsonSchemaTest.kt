package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core16.model.bootnotification.enumeration.RegistrationStatus
import fr.simatix.cs.simulator.core16.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core16.model.cancelreservation.CancelReservationResp
import fr.simatix.cs.simulator.core16.model.cancelreservation.enumeration.CancelReservationStatus
import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.core16.model.changeavailability.enumeration.AvailabilityStatus
import fr.simatix.cs.simulator.core16.model.changeavailability.enumeration.AvailabilityType
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationReq
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationResp
import fr.simatix.cs.simulator.core16.model.changeconfiguration.enumeration.ConfigurationStatus
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.core16.model.clearcache.enumeration.ClearCacheStatus
import fr.simatix.cs.simulator.core16.model.common.ChargingSchedule
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.enumeration.ClearChargingProfileStatus
import fr.simatix.cs.simulator.core16.model.common.ChargingProfile
import fr.simatix.cs.simulator.core16.model.common.IdTagInfo
import fr.simatix.cs.simulator.core16.model.common.MeterValue
import fr.simatix.cs.simulator.core16.model.common.SampledValue
import fr.simatix.cs.simulator.core16.model.common.enumeration.AuthorizationStatus
import fr.simatix.cs.simulator.core16.model.common.enumeration.Phase
import fr.simatix.cs.simulator.core16.model.common.enumeration.RemoteStartStopStatus
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core16.model.datatransfer.enumeration.DataTransferStatus
import fr.simatix.cs.simulator.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import fr.simatix.cs.simulator.core16.model.firmwarestatusnotification.enumeration.FirmwareStatus
import fr.simatix.cs.simulator.core16.model.getconfiguration.GetConfigurationReq
import fr.simatix.cs.simulator.core16.model.getconfiguration.GetConfigurationResp
import fr.simatix.cs.simulator.core16.model.getconfiguration.KeyValue
import fr.simatix.cs.simulator.core16.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core16.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core16.model.remotestart.*
import fr.simatix.cs.simulator.core16.model.remotestart.enumeration.ChargingProfileKindType
import fr.simatix.cs.simulator.core16.model.common.enumeration.ChargingProfilePurposeType
import fr.simatix.cs.simulator.core16.model.common.enumeration.ChargingRateUnitType
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.enumeration.GetCompositeScheduleStatus
import fr.simatix.cs.simulator.core16.model.remotestart.enumeration.RecurrencyKindType
import fr.simatix.cs.simulator.core16.model.remotestop.RemoteStopTransactionReq
import fr.simatix.cs.simulator.core16.model.remotestop.RemoteStopTransactionResp
import fr.simatix.cs.simulator.core16.model.sendlocallist.AuthorizationData
import fr.simatix.cs.simulator.core16.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.core16.model.sendlocallist.SendLocalListResp
import fr.simatix.cs.simulator.core16.model.sendlocallist.enumeration.UpdateStatus
import fr.simatix.cs.simulator.core16.model.sendlocallist.enumeration.UpdateType
import fr.simatix.cs.simulator.core16.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core16.model.setchargingprofile.SetChargingProfileResp
import fr.simatix.cs.simulator.core16.model.setchargingprofile.enumeration.ChargingProfileStatus
import fr.simatix.cs.simulator.core16.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.core16.model.reservenow.ReserveNowResp
import fr.simatix.cs.simulator.core16.model.reservenow.enumeration.ReservationStatus
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionReq
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionResp
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointErrorCode
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointStatus
import fr.simatix.cs.simulator.core16.model.stoptransaction.StopTransactionReq
import fr.simatix.cs.simulator.core16.model.stoptransaction.StopTransactionResp
import fr.simatix.cs.simulator.core16.model.stoptransaction.enumeration.Reason
import fr.simatix.cs.simulator.core16.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.core16.model.triggermessage.TriggerMessageResp
import fr.simatix.cs.simulator.core16.model.triggermessage.enumeration.MessageTrigger
import fr.simatix.cs.simulator.core16.model.triggermessage.enumeration.TriggerMessageStatus
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.core16.model.unlockconnector.enumeration.UnlockStatus
import fr.simatix.cs.simulator.core16.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.core16.model.updatefirmware.UpdateFirmwareResp
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
    fun `changeAvailability request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            ChangeAvailabilityReq(1, AvailabilityType.Operative), "ChangeAvailabilityRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearCache request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            ClearCacheReq(), "ClearCacheRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `unlockConnector request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            UnlockConnectorReq(1), "UnlockConnectorRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `remoteStartTransaction request format`() {
        var errors = JsonSchemaValidator.isValidObjectV4(
            RemoteStartTransactionReq("Tag1"), "RemoteStartTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV4(
            RemoteStartTransactionReq(
                idTag = "Tag1", connectorId = 1, chargingProfile = ChargingProfile(
                    chargingProfileId = 1,
                    stackLevel = 1,
                    chargingProfilePurpose = ChargingProfilePurposeType.ChargePointMaxProfile,
                    chargingProfileKind = ChargingProfileKindType.Absolute,
                    chargingSchedule = ChargingSchedule(
                        chargingRateUnit = ChargingRateUnitType.A,
                        chargingSchedulePeriod = listOf(ChargingSchedulePeriod(0, 0.1, 2)),
                        duration = 100,
                        startSchedule = Instant.parse("2022-02-15T00:00:00.000Z"),
                        minChargingRate = 0.2
                    ),
                    recurrencyKind = RecurrencyKindType.Weekly,
                    transactionId = 10,
                    validFrom = Instant.parse("2022-02-15T00:00:00.000Z"),
                    validTo = Instant.parse("2022-02-15T00:00:00.000Z")
                )
            ), "RemoteStartTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `remoteStopTransaction request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            RemoteStopTransactionReq(1), "RemoteStopTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getConfiguration request format`() {
        var errors = JsonSchemaValidator.isValidObjectV4(
            GetConfigurationReq(), "GetConfigurationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV4(
            GetConfigurationReq(listOf("key")), "GetConfigurationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `changeConfiguration request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            ChangeConfigurationReq("key", "value"), "ChangeConfigurationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setChargingProfile request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            SetChargingProfileReq(
                1, ChargingProfile(
                    chargingProfileId = 1,
                    stackLevel = 1,
                    chargingProfilePurpose = ChargingProfilePurposeType.ChargePointMaxProfile,
                    chargingProfileKind = ChargingProfileKindType.Absolute,
                    chargingSchedule = ChargingSchedule(
                        chargingRateUnit = ChargingRateUnitType.A,
                        chargingSchedulePeriod = listOf(ChargingSchedulePeriod(0, 0.1, 2)),
                        duration = 100,
                        startSchedule = Instant.parse("2022-02-15T00:00:00.000Z"),
                        minChargingRate = 0.2
                    ),
                    recurrencyKind = RecurrencyKindType.Weekly,
                    transactionId = 10,
                    validFrom = Instant.parse("2022-02-15T00:00:00.000Z"),
                    validTo = Instant.parse("2022-02-15T00:00:00.000Z")
                )
            ),
            "SetChargingProfileRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getLocalListVersion request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(GetLocalListVersionReq(), "GetLocalListVersionRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `cancelReservation request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            CancelReservationReq(3), "CancelReservationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `firmwareStatusNotification request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            FirmwareStatusNotificationReq(FirmwareStatus.Downloaded),
            "FirmwareStatusNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearChargingProfile request format`() {
        var errors = JsonSchemaValidator.isValidObjectV4(
            ClearChargingProfileReq(), "ClearChargingProfileRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV4(
            ClearChargingProfileReq(
                1, 1, ChargingProfilePurposeType.ChargePointMaxProfile, 1
            ), "ClearChargingProfileRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getCompositeSchedule request format`() {
        var errors =
            JsonSchemaValidator.isValidObjectV4(GetCompositeScheduleReq(1, 3), "GetCompositeScheduleRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV4(
            GetCompositeScheduleReq(1, 3, ChargingRateUnitType.A),
            "GetCompositeScheduleRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `updateFirmware request format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            UpdateFirmwareReq(
                location = "http://www.ietf.org/rfc/rfc2396.txt", // URI
                retries = 2,
                retrieveDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                retryInterval = 3
            ), "UpdateFirmwareRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `sendLocalList request format`() {
        var errors = JsonSchemaValidator.isValidObjectV4(
            SendLocalListReq(1, UpdateType.Differential), "SendLocalListRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV4(
            SendLocalListReq(1, UpdateType.Differential, listOf(AuthorizationData(""))), "SendLocalListRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }


    @Test
    fun `triggerMessage request format`() {
        var errors = JsonSchemaValidator.isValidObjectV4(
            TriggerMessageReq(
                MessageTrigger.BootNotification
            ),
            "TriggerMessageRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObjectV4(
            TriggerMessageReq(
                MessageTrigger.Heartbeat,
                1
            ),
            "TriggerMessageRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `reserveNow request format`() {
        var errors = JsonSchemaValidator.isValidObjectV4(
            ReserveNowReq(
                connectorId = 1,
                expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                idTag = "idTag",
                reservationId = 2
            ), "ReserveNowRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObjectV4(
            ReserveNowReq(
                connectorId = 1,
                expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                idTag = "idTag",
                reservationId = 2,
                parentIdTag = "partentIdTag"
            ), "ReserveNowRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
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

    @Test
    fun `changeAvailability response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            ChangeAvailabilityResp(AvailabilityStatus.Accepted), "ChangeAvailabilityResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearCache response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            ClearCacheResp(ClearCacheStatus.Accepted), "ClearCacheResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `unlockConnector response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            UnlockConnectorResp(UnlockStatus.Unlocked), "UnlockConnectorResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `remoteStartTransaction response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            RemoteStartTransactionResp(RemoteStartStopStatus.Accepted), "RemoteStartTransactionResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `remoteStopTransaction response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            RemoteStopTransactionResp(RemoteStartStopStatus.Accepted), "RemoteStopTransactionResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getConfiguration response format`() {
        var errors = JsonSchemaValidator.isValidObjectV4(
            GetConfigurationResp(), "GetConfigurationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV4(
            GetConfigurationResp(listOf(KeyValue("key", true)), listOf("unknown key")), "GetConfigurationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV4(
            GetConfigurationResp(listOf(KeyValue("key", true, "value"))), "GetConfigurationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `changeConfiguration response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            ChangeConfigurationResp(ConfigurationStatus.Accepted), "ChangeConfigurationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setChargingProfile response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            SetChargingProfileResp(ChargingProfileStatus.Accepted), "SetChargingProfileResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getLocalListVersion response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(GetLocalListVersionResp(1), "GetLocalListVersionResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `firmwareStatusNotification response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(FirmwareStatusNotificationResp(), "FirmwareStatusNotificationResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `cancelReservation response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            CancelReservationResp(CancelReservationStatus.Rejected), "CancelReservationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearChargingProfile response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            ClearChargingProfileResp(ClearChargingProfileStatus.Accepted), "ClearChargingProfileResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getCompositeSchedule response format`() {
        var errors = JsonSchemaValidator.isValidObjectV4(
            GetCompositeScheduleResp(GetCompositeScheduleStatus.Accepted), "GetCompositeScheduleResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV4(
            GetCompositeScheduleResp(
                GetCompositeScheduleStatus.Accepted,
                1,
                Instant.parse("2022-02-15T00:00:00.000Z"),
                ChargingSchedule(ChargingRateUnitType.A, listOf(ChargingSchedulePeriod(1, 10.0)))
            ),
            "GetCompositeScheduleResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }
    @Test
    fun `updateFirmware response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            UpdateFirmwareResp(), "UpdateFirmwareResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `sendLocalList response format`() {
        val errors =
            JsonSchemaValidator.isValidObjectV4(SendLocalListResp(UpdateStatus.Accepted), "SendLocalListResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }


    @Test
    fun `triggerMessage response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            TriggerMessageResp(
                TriggerMessageStatus.Accepted
            ),
            "TriggerMessageResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `reserveNow response format`() {
        val errors = JsonSchemaValidator.isValidObjectV4(
            ReserveNowResp(ReservationStatus.Accepted), "ReserveNowResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

}