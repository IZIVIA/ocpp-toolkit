package com.izivia.ocpp.json16

import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core16.model.bootnotification.enumeration.RegistrationStatus
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core16.model.cancelreservation.enumeration.CancelReservationStatus
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core16.model.changeavailability.enumeration.AvailabilityStatus
import com.izivia.ocpp.core16.model.changeavailability.enumeration.AvailabilityType
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core16.model.changeconfiguration.enumeration.ConfigurationStatus
import com.izivia.ocpp.core16.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core16.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core16.model.clearcache.enumeration.ClearCacheStatus
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core16.model.clearchargingprofile.enumeration.ClearChargingProfileStatus
import com.izivia.ocpp.core16.model.common.*
import com.izivia.ocpp.core16.model.common.enumeration.*
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.datatransfer.enumeration.DataTransferStatus
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.firmwarestatusnotification.enumeration.FirmwareStatus
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core16.model.getcompositeschedule.enumeration.GetCompositeScheduleStatus
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core16.model.getconfiguration.KeyValue
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core16.model.remotestart.ChargingSchedulePeriod
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core16.model.remotestart.enumeration.ChargingProfileKindType
import com.izivia.ocpp.core16.model.remotestart.enumeration.RecurrencyKindType
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core16.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core16.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core16.model.reservenow.enumeration.ReservationStatus
import com.izivia.ocpp.core16.model.reset.ResetReq
import com.izivia.ocpp.core16.model.reset.ResetResp
import com.izivia.ocpp.core16.model.reset.enumeration.ResetStatus
import com.izivia.ocpp.core16.model.reset.enumeration.ResetType
import com.izivia.ocpp.core16.model.sendlocallist.AuthorizationData
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core16.model.sendlocallist.enumeration.UpdateStatus
import com.izivia.ocpp.core16.model.sendlocallist.enumeration.UpdateType
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core16.model.setchargingprofile.enumeration.ChargingProfileStatus
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core16.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core16.model.stoptransaction.enumeration.Reason
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core16.model.triggermessage.enumeration.MessageTrigger
import com.izivia.ocpp.core16.model.triggermessage.enumeration.TriggerMessageStatus
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core16.model.unlockconnector.enumeration.UnlockStatus
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.json.JsonMessage
import com.izivia.ocpp.json.JsonMessageType
import kotlin.time.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class JsonSchemaTest {

    companion object {
        val parser = Ocpp16JsonParser()
    }

    @Test
    fun `heartbeat request format`() {
        val result = parser.parseAnyFromString(
            parser.mapPayloadToString(HeartbeatReq())
        )
        expectThat(result).isA<JsonMessage<HeartbeatReq>>()
    }

    @Test
    fun `authorize request format`() {
        /* Required field only */
        validateObject(
            AuthorizeReq(
                idTag = "Tag1"
            )
        )
    }

    @Test
    fun `meterValues request format`() {
        /* Required field only */
        validateObject(
            MeterValuesReq(
                connectorId = 1,
                meterValue = listOf(
                    MeterValue(
                        listOf(SampledValue("0")),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            )
        )

        /* Every field */
        validateObject(
            MeterValuesReq(
                connectorId = 1,
                meterValue = listOf(
                    MeterValue(
                        listOf(SampledValue(value = "0", phase = Phase.L1L2)),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                ),
                transactionId = 1
            )
        )
    }

    @Test
    fun `startTransaction request format`() {
        /* Required field only */
        validateObject(
            StartTransactionReq(
                connectorId = 1,
                idTag = "Tag1",
                meterStart = 100,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z")
            )
        )

        /* Every field */
        validateObject(
            StartTransactionReq(
                connectorId = 1,
                idTag = "Tag1",
                meterStart = 100,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                reservationId = 110
            )
        )
    }

    @Test
    fun `stopTransaction request format`() {
        /* Required field only */
        validateObject(
            StopTransactionReq(
                meterStop = 200,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                transactionId = 12
            )
        )

        /* Every field */
        validateObject(
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
            )
        )
    }

    @Test
    fun `statusNotification request format`() {
        /* Required field only */
        validateObject(
            StatusNotificationReq(
                connectorId = 1,
                errorCode = ChargePointErrorCode.NoError,
                status = ChargePointStatus.Charging
            )
        )

        /* Every field */
        validateObject(
            StatusNotificationReq(
                connectorId = 1,
                errorCode = ChargePointErrorCode.NoError,
                status = ChargePointStatus.Charging,
                info = "Charging",
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                vendorErrorCode = "Error",
                vendorId = "vendor1"
            )
        )
    }

    @Test
    fun `dataTransfer request format`() {
        /* Required field only */
        validateObject(
            DataTransferReq("vendor1")
        )

        validateObject(
            DataTransferReq(vendorId = "vendor1", messageId = "message1", data = "data1")
        )
    }

    @Test
    fun `bootNotification request format`() {
        /* Required field only */
        validateObject(
            BootNotificationReq(chargePointModel = "model1", chargePointVendor = "vendor1")
        )

        /* Every field */
        validateObject(
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
            )
        )
    }

    @Test
    fun `changeAvailability request format`() {
        validateObject(
            ChangeAvailabilityReq(1, AvailabilityType.Operative)
        )
    }

    @Test
    fun `clearCache request format`() {
        validateObject(
            ClearCacheReq()
        )
    }

    @Test
    fun `unlockConnector request format`() {
        validateObject(
            UnlockConnectorReq(1)
        )
    }

    @Test
    fun `remoteStartTransaction request format`() {
        validateObject(
            RemoteStartTransactionReq("Tag1")
        )

        validateObject(
            RemoteStartTransactionReq(
                idTag = "Tag1",
                connectorId = 1,
                chargingProfile = ChargingProfile(
                    chargingProfileId = 1,
                    stackLevel = 1,
                    chargingProfilePurpose = ChargingProfilePurposeType.ChargePointMaxProfile,
                    chargingProfileKind = ChargingProfileKindType.Absolute,
                    chargingSchedule = ChargingSchedule(
                        chargingRateUnit = ChargingRateUnitType.A,
                        chargingSchedulePeriod = listOf(ChargingSchedulePeriod(0, 1, 2)),
                        duration = 100,
                        startSchedule = Instant.parse("2022-02-15T00:00:00.000Z"),
                        minChargingRate = 0.2
                    ),
                    recurrencyKind = RecurrencyKindType.Weekly,
                    transactionId = 10,
                    validFrom = Instant.parse("2022-02-15T00:00:00.000Z"),
                    validTo = Instant.parse("2022-02-15T00:00:00.000Z")
                )
            )
        )
    }

    @Test
    fun `remoteStopTransaction request format`() {
        validateObject(
            RemoteStopTransactionReq(1)
        )
    }

    @Test
    fun `getConfiguration request format`() {
        validateObject(
            GetConfigurationReq()
        )

        validateObject(
            GetConfigurationReq(listOf("key"))
        )
    }

    @Test
    fun `changeConfiguration request format`() {
        validateObject(
            ChangeConfigurationReq("key", "value")
        )
    }

    @Test
    fun `setChargingProfile request format`() {
        validateObject(
            SetChargingProfileReq(
                1,
                ChargingProfile(
                    chargingProfileId = 1,
                    stackLevel = 1,
                    chargingProfilePurpose = ChargingProfilePurposeType.ChargePointMaxProfile,
                    chargingProfileKind = ChargingProfileKindType.Absolute,
                    chargingSchedule = ChargingSchedule(
                        chargingRateUnit = ChargingRateUnitType.A,
                        chargingSchedulePeriod = listOf(ChargingSchedulePeriod(0, 0, 2)),
                        duration = 100,
                        startSchedule = Instant.parse("2022-02-15T00:00:00.000Z"),
                        minChargingRate = 0.2
                    ),
                    recurrencyKind = RecurrencyKindType.Weekly,
                    transactionId = 10,
                    validFrom = Instant.parse("2022-02-15T00:00:00.000Z"),
                    validTo = Instant.parse("2022-02-15T00:00:00.000Z")
                )
            )
        )
    }

    @Test
    fun `getLocalListVersion request format`() {
        validateObject(
            GetLocalListVersionReq()
        )
    }

    @Test
    fun `cancelReservation request format`() {
        validateObject(
            CancelReservationReq(3)
        )
    }

    @Test
    fun `firmwareStatusNotification request format`() {
        validateObject(
            FirmwareStatusNotificationReq(FirmwareStatus.Downloaded)
        )
    }

    @Test
    fun `clearChargingProfile request format`() {
        validateObject(
            ClearChargingProfileReq()
        )

        validateObject(
            ClearChargingProfileReq(
                1,
                1,
                ChargingProfilePurposeType.ChargePointMaxProfile,
                1
            )
        )
    }

    @Test
    fun `getCompositeSchedule request format`() {
        validateObject(
            GetCompositeScheduleReq(1, 3)
        )

        validateObject(
            GetCompositeScheduleReq(1, 3, ChargingRateUnitType.A)
        )
    }

    @Test
    fun `updateFirmware request format`() {
        validateObject(
            UpdateFirmwareReq(
                location = "http://www.ietf.org/rfc/rfc2396.txt", // URI
                retries = 2,
                retrieveDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                retryInterval = 3
            )
        )
    }

    @Test
    fun `sendLocalList request format`() {
        validateObject(
            SendLocalListReq(1, UpdateType.Differential)
        )

        validateObject(
            SendLocalListReq(2, UpdateType.Differential, listOf(AuthorizationData("idTag")))
        )
    }

    @Test
    fun `triggerMessage request format`() {
        validateObject(
            TriggerMessageReq(
                MessageTrigger.BootNotification
            )
        )

        validateObject(
            TriggerMessageReq(
                MessageTrigger.Heartbeat,
                1
            )
        )
    }

    @Test
    fun `reserveNow request format`() {
        validateObject(
            ReserveNowReq(
                connectorId = 1,
                expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                idTag = "idTag",
                reservationId = 2
            )
        )

        validateObject(
            ReserveNowReq(
                connectorId = 1,
                expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                idTag = "idTag",
                reservationId = 2,
                parentIdTag = "partentIdTag"
            )
        )
    }

    @Test
    fun `diagnosticsStatusNotification request format`() {
        validateObject(
            DiagnosticsStatusNotificationReq(
                status = DiagnosticsStatus.Uploaded
            )
        )
    }

    @Test
    fun `getDiagnostics request format`() {
        val request = GetDiagnosticsReq("http://www.ietf.org/rfc/rfc2396.txt")
        validateObject(request)

        val json = parser.mapPayloadToString(request)

        expectThat(json).isEqualTo(
            """{"location":"http://www.ietf.org/rfc/rfc2396.txt"}""".trimIndent()
        )

        validateObject(
            GetDiagnosticsReq(
                location = "http://www.ietf.org/rfc/rfc2396.txt",
                retries = 3,
                retryInterval = 2,
                startTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                stopTime = Instant.parse("2022-02-15T00:00:00.000Z")
            )
        )
    }

    @Test
    fun `heartbeat response format`() {
        validateObject(
            HeartbeatResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
            )
        )
    }

    @Test
    fun `authorize response format`() {
        /* Required field only */
        validateObject(
            AuthorizeResp(
                idTagInfo = IdTagInfo(AuthorizationStatus.Accepted)
            )
        )

        /* Every field */
        validateObject(
            AuthorizeResp(
                idTagInfo = IdTagInfo(
                    status = AuthorizationStatus.Accepted,
                    expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                    parentIdTag = "Parent"
                )
            )
        )
    }

    @Test
    fun `meterValues response format`() {
        validateObject(
            MeterValuesResp()
        )
    }

    @Test
    fun `startTransaction response format`() {
        /* Required field only */
        validateObject(
            StartTransactionResp(IdTagInfo(AuthorizationStatus.Accepted), 12)
        )

        /* Every field */
        validateObject(
            StartTransactionResp(
                idTagInfo = IdTagInfo(
                    status = AuthorizationStatus.Accepted,
                    expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                    parentIdTag = "Tag1"
                ),
                transactionId = 12
            )
        )
    }

    @Test
    fun `stopTransaction response format`() {
        validateObject(
            StopTransactionResp()
        )

        validateObject(
            StopTransactionResp(
                IdTagInfo(
                    status = AuthorizationStatus.Accepted,
                    expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                    parentIdTag = "Tag1"
                )
            )
        )
    }

    @Test
    fun `statusNotification response format`() {
        validateObject(StatusNotificationResp())
    }

    @Test
    fun `dataTransfer response format`() {
        /* Required field only */
        validateObject(
            DataTransferResp(DataTransferStatus.Accepted)
        )

        /* Every field */
        validateObject(
            DataTransferResp(DataTransferStatus.Accepted, "data1")
        )
    }

    @Test
    fun `bootNotification response format`() {
        /* Required field only */
        validateObject(
            BootNotificationResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                interval = 10,
                status = RegistrationStatus.Accepted
            )
        )
    }

    @Test
    fun `changeAvailability response format`() {
        validateObject(
            ChangeAvailabilityResp(AvailabilityStatus.Accepted)
        )
    }

    @Test
    fun `clearCache response format`() {
        validateObject(
            ClearCacheResp(ClearCacheStatus.Accepted)
        )
    }

    @Test
    fun `unlockConnector response format`() {
        validateObject(
            UnlockConnectorResp(UnlockStatus.Unlocked)
        )
    }

    @Test
    fun `remoteStartTransaction response format`() {
        validateObject(
            RemoteStartTransactionResp(RemoteStartStopStatus.Accepted)
        )
    }

    @Test
    fun `remoteStopTransaction response format`() {
        validateObject(
            RemoteStopTransactionResp(RemoteStartStopStatus.Accepted)
        )
    }

    @Test
    fun `getConfiguration response format`() {
        validateObject(
            GetConfigurationResp()
        )

        validateObject(
            GetConfigurationResp(listOf(KeyValue("key", true)), listOf("unknown key"))
        )

        validateObject(
            GetConfigurationResp(listOf(KeyValue("key", true, "value")))
        )
    }

    @Test
    fun `changeConfiguration response format`() {
        validateObject(
            ChangeConfigurationResp(ConfigurationStatus.Accepted)
        )
    }

    @Test
    fun `setChargingProfile response format`() {
        validateObject(
            SetChargingProfileResp(ChargingProfileStatus.Accepted)
        )
    }

    @Test
    fun `getLocalListVersion response format`() {
        validateObject(GetLocalListVersionResp(1))
    }

    @Test
    fun `firmwareStatusNotification response format`() {
        validateObject(
            FirmwareStatusNotificationResp()
        )
    }

    @Test
    fun `cancelReservation response format`() {
        validateObject(
            CancelReservationResp(CancelReservationStatus.Rejected)
        )
    }

    @Test
    fun `clearChargingProfile response format`() {
        validateObject(
            ClearChargingProfileResp(ClearChargingProfileStatus.Accepted)
        )
    }

    @Test
    fun `getCompositeSchedule response format`() {
        validateObject(
            GetCompositeScheduleResp(GetCompositeScheduleStatus.Accepted)
        )

        validateObject(
            GetCompositeScheduleResp(
                GetCompositeScheduleStatus.Accepted,
                1,
                Instant.parse("2022-02-15T00:00:00.000Z"),
                ChargingSchedule(ChargingRateUnitType.A, listOf(ChargingSchedulePeriod(1, 10)))
            )
        )
    }

    @Test
    fun `updateFirmware response format`() {
        validateObject(
            UpdateFirmwareResp()
        )
    }

    @Test
    fun `sendLocalList response format`() {
        validateObject(SendLocalListResp(UpdateStatus.Accepted))
    }

    @Test
    fun `triggerMessage response format`() {
        validateObject(
            TriggerMessageResp(
                TriggerMessageStatus.Accepted
            )
        )
    }

    @Test
    fun `reserveNow response format`() {
        validateObject(
            ReserveNowResp(ReservationStatus.Accepted)
        )
    }

    @Test
    fun `diagnosticsStatusNotification response format`() {
        validateObject(
            DiagnosticsStatusNotificationResp()
        )
    }

    @Test
    fun `getDiagnostics response format`() {
        validateObject(GetDiagnosticsResp())

        validateObject(GetDiagnosticsResp("fileName"))
    }

    @Test
    fun `reset request format`() {
        validateObject(
            ResetReq(type = ResetType.Hard)
        )

        validateObject(
            ResetReq(type = ResetType.Soft)
        )
    }

    @Test
    fun `reset response format`() {
        validateObject(
            ResetResp(status = ResetStatus.Accepted)
        )

        validateObject(
            ResetResp(status = ResetStatus.Rejected)
        )
    }

    inline fun <reified T : Any> validateObject(instance: T) {
        val instanceClass = instance::class.java.simpleName
        expectThat(
            parser.parseAnyFromJson<T>(
                parser.mapToJson(
                    JsonMessage(
                        msgType = JsonMessageType.CALL.takeIf { instanceClass.endsWith("Req") }
                            ?: JsonMessageType.CALL_RESULT,
                        msgId = "123456",
                        action = instanceClass.replace(Regex("Req$"), "").replace(Regex("Resp$"), ""),
                        payload = instance
                    )
                )
            )
        ).isA<JsonMessage<T>>().get { payload }.isA<T>()
    }
}
