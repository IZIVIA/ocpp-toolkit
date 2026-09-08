package com.izivia.ocpp.operation.information

enum class ActionOcpp(val value: String) {
    // OCPP 1.2 CP -> CSMS actions.
    AUTHORIZE("Authorize"),
    BOOT_NOTIFICATION("BootNotification"),
    DIAGNOSTICS_STATUS_NOTIFICATION("DiagnosticsStatusNotification"),
    FIRMWARE_STATUS_NOTIFICATION("FirmwareStatusNotification"),
    HEARTBEAT("Heartbeat"),
    METER_VALUES("MeterValues"),
    START_TRANSACTION("StartTransaction"),
    STATUS_NOTIFICATION("StatusNotification"),
    STOP_TRANSACTION("StopTransaction"),

    // OCPP 1.2 CSMS -> CP actions.
    CHANGE_AVAILABILITY("ChangeAvailability"),
    CHANGE_CONFIGURATION("ChangeConfiguration"),
    CLEAR_CACHE("ClearCache"),
    GET_CONFIGURATION("GetConfiguration"),
    GET_DIAGNOSTICS("GetDiagnostics"),
    REMOTE_START_TRANSACTION("RemoteStartTransaction"),
    REMOTE_STOP_TRANSACTION("RemoteStopTransaction"),
    RESET("Reset"),
    UNLOCK_CONNECTOR("UnlockConnector"),
    UPDATE_FIRMWARE("UpdateFirmware"),

    // OCPP 1.5 CP <-> CSMS actions.
    DATA_TRANSFER("DataTransfer"),

    // OCPP 1.5 CSMS -> CP actions.
    CANCEL_RESERVATION("CancelReservation"),
    GET_LOCAL_LIST_VERSION("GetLocalListVersion"),
    RESERVE_NOW("ReserveNow"),
    SEND_LOCAL_LIST("SendLocalList"),

    // OCPP 1.6 CSMS -> CP additions.
    CLEAR_CHARGING_PROFILE("ClearChargingProfile"),
    GET_COMPOSITE_SCHEDULE("GetCompositeSchedule"),
    SET_CHARGING_PROFILE("SetChargingProfile"),
    TRIGGER_MESSAGE("TriggerMessage"),

    // OCPP 1.6 security whitepaper CSMS -> CP actions.
    CERTIFICATE_SIGNED("CertificateSigned"),
    DELETE_CERTIFICATE("DeleteCertificate"),
    EXTENDED_TRIGGER_MESSAGE("ExtendedTriggerMessage"),
    GET_INSTALLED_CERTIFICATE_IDS("GetInstalledCertificateIds"),
    GET_LOG("GetLog"),
    INSTALL_CERTIFICATE("InstallCertificate"),
    SIGNED_UPDATE_FIRMWARE("SignedUpdateFirmware"),

    // OCPP 1.6 security whitepaper CP -> CSMS actions.
    LOG_STATUS_NOTIFICATION("LogStatusNotification"),
    SECURITY_EVENT_NOTIFICATION("SecurityEventNotification"),
    SIGN_CERTIFICATE("SignCertificate"),
    SIGNED_FIRMWARE_STATUS_NOTIFICATION("SignedFirmwareStatusNotification"),

    // OCPP 2.x CP -> CSMS additions.
    CLEARED_CHARGING_LIMIT("ClearedChargingLimit"),
    GET_CERTIFICATE_STATUS("GetCertificateStatus"),
    NOTIFY_CHARGING_LIMIT("NotifyChargingLimit"),
    NOTIFY_CUSTOMER_INFORMATION("NotifyCustomerInformation"),
    NOTIFY_DISPLAY_MESSAGES("NotifyDisplayMessages"),
    NOTIFY_EVENT("NotifyEvent"),
    NOTIFY_EV_CHARGING_NEEDS("NotifyEVChargingNeeds"),
    NOTIFY_EV_CHARGING_SCHEDULE("NotifyEVChargingSchedule"),
    NOTIFY_MONITORING_REPORT("NotifyMonitoringReport"),
    NOTIFY_REPORT("NotifyReport"),
    PUBLISH_FIRMWARE_STATUS_NOTIFICATION("PublishFirmwareStatusNotification"),
    REPORT_CHARGING_PROFILES("ReportChargingProfiles"),
    RESERVATION_STATUS_UPDATE("ReservationStatusUpdate"),
    TRANSACTION_EVENT("TransactionEvent"),

    // OCPP 2.x CSMS -> CP additions.
    CLEAR_DISPLAY_MESSAGE("ClearDisplayMessage"),
    CLEAR_VARIABLE_MONITORING("ClearVariableMonitoring"),
    COST_UPDATED("CostUpdated"),
    CUSTOMER_INFORMATION("CustomerInformation"),
    GET_BASE_REPORT("GetBaseReport"),
    GET_CHARGING_PROFILES("GetChargingProfiles"),
    GET_DISPLAY_MESSAGES("GetDisplayMessages"),
    GET_MONITORING_REPORT("GetMonitoringReport"),
    GET_REPORT("GetReport"),
    GET_TRANSACTION_STATUS("GetTransactionStatus"),
    GET_VARIABLES("GetVariables"),
    PUBLISH_FIRMWARE("PublishFirmware"),
    REQUEST_START_TRANSACTION("RequestStartTransaction"),
    REQUEST_STOP_TRANSACTION("RequestStopTransaction"),
    SET_DISPLAY_MESSAGE("SetDisplayMessage"),
    SET_MONITORING_BASE("SetMonitoringBase"),
    SET_MONITORING_LEVEL("SetMonitoringLevel"),
    SET_NETWORK_PROFILE("SetNetworkProfile"),
    SET_VARIABLE_MONITORING("SetVariableMonitoring"),
    SET_VARIABLES("SetVariables"),
    UNPUBLISH_FIRMWARE("UnpublishFirmware")
}
