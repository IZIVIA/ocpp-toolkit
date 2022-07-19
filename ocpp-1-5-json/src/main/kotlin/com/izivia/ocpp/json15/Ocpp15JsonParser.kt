package com.izivia.ocpp.json15

import com.izivia.ocpp.core15.model.authorize.AuthorizeReq
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core15.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core15.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core15.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core15.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core15.model.reset.ResetReq
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.json.OcppJsonParser

class Ocpp15JsonParser : OcppJsonParser(Ocpp15JsonObjectMapper) {

    override fun getRequestPayloadClass(action: String, context: String) = when (action.lowercase()) {
        "authorize" -> AuthorizeReq::class.java
        "bootnotification" -> BootNotificationReq::class.java
        "cancelreservation" -> CancelReservationReq::class.java
        "changeavailability" -> ChangeAvailabilityReq::class.java
        "changeconfiguration" -> ChangeConfigurationReq::class.java
        "clearcache" -> ClearCacheReq::class.java
        "datatransfer" -> DataTransferReq::class.java
        "diagnosticsstatusnotification" -> DiagnosticsStatusNotificationReq::class.java
        "firmwarestatusnotification" -> FirmwareStatusNotificationReq::class.java
        "getconfiguration" -> GetConfigurationReq::class.java
        "getdiagnostics" -> GetDiagnosticsReq::class.java
        "getlocallistversion" -> GetLocalListVersionReq::class.java
        "heartbeat" -> HeartbeatReq::class.java
        "metervalues" -> MeterValuesReq::class.java
        "remotestarttransaction" -> RemoteStartTransactionReq::class.java
        "remotestoptransaction" -> RemoteStopTransactionReq::class.java
        "reservenow" -> ReserveNowReq::class.java
        "reset" -> ResetReq::class.java
        "sendlocallist" -> SendLocalListReq::class.java
        "starttransaction" -> StartTransactionReq::class.java
        "statusnotification" -> StatusNotificationReq::class.java
        "stoptransaction" -> StopTransactionReq::class.java
        "unlockconnector" -> UnlockConnectorReq::class.java
        "updatefirmware" -> UpdateFirmwareReq::class.java
        else -> throw IllegalArgumentException("Action not recognized. action = ${action}. message = $context")
    }
}
