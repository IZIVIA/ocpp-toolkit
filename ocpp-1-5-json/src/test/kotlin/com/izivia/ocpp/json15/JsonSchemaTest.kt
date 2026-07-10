package com.izivia.ocpp.json15

import com.izivia.ocpp.core15.model.authorize.AuthorizeReq
import com.izivia.ocpp.core15.model.authorize.AuthorizeResp
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core15.model.bootnotification.enumeration.RegistrationStatus
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core15.model.cancelreservation.enumeration.CancelReservationStatus
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core15.model.changeavailability.enumeration.AvailabilityStatus
import com.izivia.ocpp.core15.model.changeavailability.enumeration.AvailabilityType
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core15.model.changeconfiguration.enumeration.ConfigurationStatus
import com.izivia.ocpp.core15.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core15.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core15.model.clearcache.enumeration.ClearCacheStatus
import com.izivia.ocpp.core15.model.common.IdTagInfo
import com.izivia.ocpp.core15.model.common.MeterValue
import com.izivia.ocpp.core15.model.common.SampledValue
import com.izivia.ocpp.core15.model.common.enumeration.*
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core15.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core15.model.datatransfer.enumeration.DataTransferStatus
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core15.model.firmwarestatusnotification.enumeration.FirmwareStatus
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core15.model.getconfiguration.KeyValue
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core15.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core15.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core15.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core15.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core15.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core15.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core15.model.reservenow.enumeration.ReservationStatus
import com.izivia.ocpp.core15.model.reset.ResetReq
import com.izivia.ocpp.core15.model.reset.ResetResp
import com.izivia.ocpp.core15.model.reset.enumeration.ResetStatus
import com.izivia.ocpp.core15.model.reset.enumeration.ResetType
import com.izivia.ocpp.core15.model.sendlocallist.AuthorisationData
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core15.model.sendlocallist.enumeration.UpdateStatus
import com.izivia.ocpp.core15.model.sendlocallist.enumeration.UpdateType
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core15.model.stoptransaction.TransactionData
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core15.model.unlockconnector.enumeration.UnlockStatus
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.json.JsonMessage
import com.izivia.ocpp.json.JsonMessageType
import kotlin.time.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA

class JsonSchemaTest {

    companion object {
        val parser = Ocpp15JsonParser()
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
                values = listOf(
                    MeterValue(
                        timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                        value = listOf(
                            SampledValue(
                                value = "123456789",
                                context = ReadingContext.SamplePeriodic,
                                format = ValueFormat.Raw,
                                measurand = Measurand.EnergyActiveImportRegister,
                                location = Location.Outlet,
                                unit = UnitOfMeasure.Wh
                            )
                        )
                    )
                )
            )
        )

        /* Every field */
        validateObject(
            MeterValuesReq(
                connectorId = 1,
                values = listOf(
                    MeterValue(
                        value = listOf(
                            SampledValue(
                                value = "123456789",
                                context = ReadingContext.SamplePeriodic,
                                format = ValueFormat.Raw,
                                measurand = Measurand.EnergyActiveImportRegister,
                                location = Location.Outlet,
                                unit = UnitOfMeasure.Wh
                            )
                        ),
                        timestamp = Instant.parse("2022-02-15T00:00:00.000Z")
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
                transactionData = listOf(
                    TransactionData(
                        values = listOf(
                            MeterValue(
                                value = listOf(
                                    SampledValue(
                                        value = "123456789",
                                        context = ReadingContext.SamplePeriodic,
                                        format = ValueFormat.Raw,
                                        measurand = Measurand.EnergyActiveImportRegister,
                                        location = Location.Outlet,
                                        unit = UnitOfMeasure.Wh
                                    )
                                ),
                                timestamp = Instant.parse("2022-02-15T00:00:00.000Z")
                            )
                        )
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
                status = ChargePointStatus.Occupied
            )
        )

        /* Every field */
        validateObject(
            StatusNotificationReq(
                connectorId = 1,
                errorCode = ChargePointErrorCode.NoError,
                status = ChargePointStatus.Occupied,
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
            RemoteStartTransactionReq(idTag = "Tag1")
        )

        validateObject(
            RemoteStartTransactionReq(
                idTag = "Tag1",
                connectorId = 1
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
            SendLocalListReq(listVersion = 1, updateType = UpdateType.Differential)
        )

        validateObject(
            SendLocalListReq(
                listVersion = 1,
                updateType = UpdateType.Differential,
                localAuthorizationList = listOf(AuthorisationData("idTag"))
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
        validateObject(
            GetDiagnosticsReq("http://www.ietf.org/rfc/rfc2396.txt")
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
                idTagInfo = IdTagInfo(status = AuthorizationStatus.Accepted)
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
            StartTransactionResp(IdTagInfo(status = AuthorizationStatus.Accepted), 12)
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
                heartbeatInterval = 10,
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
            UnlockConnectorResp(UnlockStatus.Accepted)
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
    fun `updateFirmware response format`() {
        validateObject(
            UpdateFirmwareResp()
        )
    }

    @Test
    fun `sendLocalList response format`() {
        validateObject(SendLocalListResp(status = UpdateStatus.Accepted))
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
