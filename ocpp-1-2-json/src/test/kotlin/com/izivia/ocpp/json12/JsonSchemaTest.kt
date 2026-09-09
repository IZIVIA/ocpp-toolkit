package com.izivia.ocpp.json12

import com.izivia.ocpp.core12.model.authorize.AuthorizeReq
import com.izivia.ocpp.core12.model.authorize.AuthorizeResp
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core12.model.bootnotification.enumeration.RegistrationStatus
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core12.model.changeavailability.enumeration.AvailabilityStatus
import com.izivia.ocpp.core12.model.changeavailability.enumeration.AvailabilityType
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core12.model.changeconfiguration.enumeration.ConfigurationStatus
import com.izivia.ocpp.core12.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core12.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core12.model.clearcache.enumeration.ClearCacheStatus
import com.izivia.ocpp.core12.model.common.IdTagInfo
import com.izivia.ocpp.core12.model.common.MeterValue
import com.izivia.ocpp.core12.model.common.enumeration.AuthorizationStatus
import com.izivia.ocpp.core12.model.common.enumeration.RemoteStartStopStatus
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core12.model.firmwarestatusnotification.enumeration.FirmwareStatus
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core12.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core12.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core12.model.reset.ResetReq
import com.izivia.ocpp.core12.model.reset.ResetResp
import com.izivia.ocpp.core12.model.reset.enumeration.ResetStatus
import com.izivia.ocpp.core12.model.reset.enumeration.ResetType
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core12.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core12.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core12.model.unlockconnector.enumeration.UnlockStatus
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.json.JsonMessage
import com.izivia.ocpp.json.JsonMessageType
import kotlin.time.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA

class JsonSchemaTest {

    companion object {
        val parser = Ocpp12JsonParser()
        val TIMESTAMP: Instant = Instant.parse("2022-02-15T00:00:00.000Z")
    }

    @Test
    fun `authorize request format`() {
        validateObject(AuthorizeReq(idTag = "Tag1"))
    }

    @Test
    fun `authorize response format`() {
        /* Required field only */
        validateObject(AuthorizeResp(idTagInfo = IdTagInfo(status = AuthorizationStatus.Accepted)))

        /* Every field */
        validateObject(
            AuthorizeResp(
                idTagInfo = IdTagInfo(
                    status = AuthorizationStatus.ConcurrentTx,
                    expiryDate = TIMESTAMP,
                    parentIdTag = "ParentTag1"
                )
            )
        )
    }

    @Test
    fun `bootNotification request format`() {
        /* Required field only */
        validateObject(BootNotificationReq(chargePointModel = "model1", chargePointVendor = "vendor1"))

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
    fun `bootNotification response format`() {
        /* Required field only */
        validateObject(BootNotificationResp(status = RegistrationStatus.Accepted))

        /* Every field */
        validateObject(
            BootNotificationResp(
                status = RegistrationStatus.Rejected,
                currentTime = TIMESTAMP,
                heartbeatInterval = 1800
            )
        )
    }

    @Test
    fun `changeAvailability request format`() {
        validateObject(ChangeAvailabilityReq(connectorId = 1, type = AvailabilityType.Operative))
    }

    @Test
    fun `changeAvailability response format`() {
        validateObject(ChangeAvailabilityResp(status = AvailabilityStatus.Scheduled))
    }

    @Test
    fun `changeConfiguration request format`() {
        validateObject(ChangeConfigurationReq(key = "HeartBeatInterval", value = "1800"))
    }

    @Test
    fun `changeConfiguration response format`() {
        validateObject(ChangeConfigurationResp(status = ConfigurationStatus.NotSupported))
    }

    @Test
    fun `clearCache request format`() {
        validateObject(ClearCacheReq())
    }

    @Test
    fun `clearCache response format`() {
        validateObject(ClearCacheResp(status = ClearCacheStatus.Accepted))
    }

    @Test
    fun `diagnosticsStatusNotification request format`() {
        validateObject(DiagnosticsStatusNotificationReq(status = DiagnosticsStatus.Uploaded))
    }

    @Test
    fun `diagnosticsStatusNotification response format`() {
        validateObject(DiagnosticsStatusNotificationResp())
    }

    @Test
    fun `firmwareStatusNotification request format`() {
        validateObject(FirmwareStatusNotificationReq(status = FirmwareStatus.Installed))
    }

    @Test
    fun `firmwareStatusNotification response format`() {
        validateObject(FirmwareStatusNotificationResp())
    }

    @Test
    fun `getDiagnostics request format`() {
        /* Required field only */
        validateObject(GetDiagnosticsReq(location = "ftp://root:root@localhost/diagnostics"))

        /* Every field */
        validateObject(
            GetDiagnosticsReq(
                location = "ftp://root:root@localhost/diagnostics",
                startTime = TIMESTAMP,
                stopTime = TIMESTAMP,
                retries = 3,
                retryInterval = 60
            )
        )
    }

    @Test
    fun `getDiagnostics response format`() {
        /* Required field only */
        validateObject(GetDiagnosticsResp())

        /* Every field */
        validateObject(GetDiagnosticsResp(fileName = "diagnostics.log"))
    }

    @Test
    fun `heartbeat request format`() {
        validateObject(HeartbeatReq())
    }

    @Test
    fun `heartbeat response format`() {
        validateObject(HeartbeatResp(currentTime = TIMESTAMP))
    }

    @Test
    fun `meterValues request format`() {
        /* Required field only */
        validateObject(MeterValuesReq(connectorId = 1))

        /* Every field */
        validateObject(
            MeterValuesReq(
                connectorId = 1,
                values = listOf(MeterValue(timestamp = TIMESTAMP, value = 123456789))
            )
        )
    }

    @Test
    fun `meterValues response format`() {
        validateObject(MeterValuesResp())
    }

    @Test
    fun `remoteStartTransaction request format`() {
        validateObject(RemoteStartTransactionReq(idTag = "Tag1"))
    }

    @Test
    fun `remoteStartTransaction response format`() {
        validateObject(RemoteStartTransactionResp(status = RemoteStartStopStatus.Accepted))
    }

    @Test
    fun `remoteStopTransaction request format`() {
        validateObject(RemoteStopTransactionReq(transactionId = 1))
    }

    @Test
    fun `remoteStopTransaction response format`() {
        validateObject(RemoteStopTransactionResp(status = RemoteStartStopStatus.Rejected))
    }

    @Test
    fun `reset request format`() {
        validateObject(ResetReq(type = ResetType.Hard))
    }

    @Test
    fun `reset response format`() {
        validateObject(ResetResp(status = ResetStatus.Accepted))
    }

    @Test
    fun `startTransaction request format`() {
        validateObject(
            StartTransactionReq(
                connectorId = 1,
                idTag = "Tag1",
                meterStart = 0,
                timestamp = TIMESTAMP
            )
        )
    }

    @Test
    fun `startTransaction response format`() {
        validateObject(
            StartTransactionResp(
                transactionId = 1,
                idTagInfo = IdTagInfo(status = AuthorizationStatus.Accepted)
            )
        )
    }

    @Test
    fun `statusNotification request format`() {
        validateObject(
            StatusNotificationReq(
                connectorId = 1,
                status = ChargePointStatus.Occupied,
                errorCode = ChargePointErrorCode.NoError
            )
        )
    }

    @Test
    fun `statusNotification response format`() {
        validateObject(StatusNotificationResp())
    }

    @Test
    fun `stopTransaction request format`() {
        /* Required field only */
        validateObject(
            StopTransactionReq(
                transactionId = 1,
                timestamp = TIMESTAMP,
                meterStop = 1000
            )
        )

        /* Every field */
        validateObject(
            StopTransactionReq(
                transactionId = 1,
                idTag = "Tag1",
                timestamp = TIMESTAMP,
                meterStop = 1000
            )
        )
    }

    @Test
    fun `stopTransaction response format`() {
        /* Required field only */
        validateObject(StopTransactionResp())

        /* Every field */
        validateObject(StopTransactionResp(idTagInfo = IdTagInfo(status = AuthorizationStatus.Expired)))
    }

    @Test
    fun `unlockConnector request format`() {
        validateObject(UnlockConnectorReq(connectorId = 1))
    }

    @Test
    fun `unlockConnector response format`() {
        validateObject(UnlockConnectorResp(status = UnlockStatus.Accepted))
    }

    @Test
    fun `updateFirmware request format`() {
        /* Required field only */
        validateObject(
            UpdateFirmwareReq(
                location = "ftp://root:root@localhost/firmware.bin",
                retrieveDate = TIMESTAMP
            )
        )

        /* Every field */
        validateObject(
            UpdateFirmwareReq(
                location = "ftp://root:root@localhost/firmware.bin",
                retrieveDate = TIMESTAMP,
                retries = 3,
                retryInterval = 60
            )
        )
    }

    @Test
    fun `updateFirmware response format`() {
        validateObject(UpdateFirmwareResp())
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
