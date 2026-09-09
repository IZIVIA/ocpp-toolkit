package com.izivia.ocpp.core12.model.common.enumeration

import com.izivia.ocpp.core12.model.authorize.AuthorizeReq
import com.izivia.ocpp.core12.model.authorize.AuthorizeResp
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core12.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core12.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationResp
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
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.utils.IActions
import com.izivia.ocpp.utils.OcppInitiator

enum class Actions(
    override val value: String,
    override val classRequest: Class<*>,
    override val classResponse: Class<*>,
    override val initiatedBy: OcppInitiator
) : IActions {
    AUTHORIZE("authorize", AuthorizeReq::class.java, AuthorizeResp::class.java, OcppInitiator.CHARGING_STATION),
    BOOTNOTIFICATION(
        "bootNotification",
        BootNotificationReq::class.java,
        BootNotificationResp::class.java,
        OcppInitiator.CHARGING_STATION
    ),
    CHANGEAVAILABILITY(
        "changeAvailability",
        ChangeAvailabilityReq::class.java,
        ChangeAvailabilityResp::class.java,
        OcppInitiator.CENTRAL_SYSTEM
    ),
    CHANGECONFIGURATION(
        "changeConfiguration",
        ChangeConfigurationReq::class.java,
        ChangeConfigurationResp::class.java,
        OcppInitiator.CENTRAL_SYSTEM
    ),
    CLEARCACHE("clearCache", ClearCacheReq::class.java, ClearCacheResp::class.java, OcppInitiator.CENTRAL_SYSTEM),
    DIAGNOSTICSSTATUSNOTIFICATION(
        "diagnosticsStatusNotification",
        DiagnosticsStatusNotificationReq::class.java,
        DiagnosticsStatusNotificationResp::class.java,
        OcppInitiator.CHARGING_STATION
    ),
    FIRMWARESTATUSNOTIFICATION(
        "firmwareStatusNotification",
        FirmwareStatusNotificationReq::class.java,
        FirmwareStatusNotificationResp::class.java,
        OcppInitiator.CHARGING_STATION
    ),
    GETDIAGNOSTICS(
        "getDiagnostics",
        GetDiagnosticsReq::class.java,
        GetDiagnosticsResp::class.java,
        OcppInitiator.CENTRAL_SYSTEM
    ),
    HEARTBEAT("heartbeat", HeartbeatReq::class.java, HeartbeatResp::class.java, OcppInitiator.CHARGING_STATION),
    METERVALUES("meterValues", MeterValuesReq::class.java, MeterValuesResp::class.java, OcppInitiator.CHARGING_STATION),
    REMOTESTARTTRANSACTION(
        "remoteStartTransaction",
        RemoteStartTransactionReq::class.java,
        RemoteStartTransactionResp::class.java,
        OcppInitiator.CENTRAL_SYSTEM
    ),
    REMOTESTOPTRANSACTION(
        "remoteStopTransaction",
        RemoteStopTransactionReq::class.java,
        RemoteStopTransactionResp::class.java,
        OcppInitiator.CENTRAL_SYSTEM
    ),
    RESET("reset", ResetReq::class.java, ResetResp::class.java, OcppInitiator.CENTRAL_SYSTEM),
    STARTTRANSACTION(
        "startTransaction",
        StartTransactionReq::class.java,
        StartTransactionResp::class.java,
        OcppInitiator.CHARGING_STATION
    ),
    STATUSNOTIFICATION(
        "statusNotification",
        StatusNotificationReq::class.java,
        StatusNotificationResp::class.java,
        OcppInitiator.CHARGING_STATION
    ),
    STOPTRANSACTION(
        "stopTransaction",
        StopTransactionReq::class.java,
        StopTransactionResp::class.java,
        OcppInitiator.CHARGING_STATION
    ),
    UNLOCKCONNECTOR(
        "unlockConnector",
        UnlockConnectorReq::class.java,
        UnlockConnectorResp::class.java,
        OcppInitiator.CENTRAL_SYSTEM
    ),
    UPDATEFIRMWARE(
        "updateFirmware",
        UpdateFirmwareReq::class.java,
        UpdateFirmwareResp::class.java,
        OcppInitiator.CENTRAL_SYSTEM
    );

    fun lowercase() = value.lowercase()

    fun camelCase() = value.replaceFirstChar { it.uppercase() }

    fun camelCaseRequest() = "${camelCase()}Req"
}
