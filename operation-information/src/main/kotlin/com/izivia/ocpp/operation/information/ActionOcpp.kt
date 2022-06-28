package com.izivia.ocpp.operation.information

enum class ActionOcpp(val value: String) {
    START_TRANSACTION("StartTransaction"),
    STOP_TRANSACTION("StopTransaction"),
    DIAGNOSTICS_STATUS_NOTIFICATION("DiagnosticsStatusNotification"),
    CHANGE_CONFIGURATION("ChangeConfiguration"),
    REMOTE_START_TRANSACTION("RemoteStartTransaction"),
    REMOTE_STOP_TRANSACTION("RemoteStopTransaction"),
    GET_CONFIGURATION("GetConfiguration"),

    RESET("Reset"),
    SET_VARIABLES("SetVariables"),
    CHANGE_AVAILABILITY("ChangeAvailability"),
    CLEAR_CACHE("ClearCache"),
    REQUEST_START_TRANSACTION("RequestStartTransaction"),
    REQUEST_STOP_TRANSACTION("RequestStopTransaction"),
    UNLOCK_CONNECTOR("UnlockConnector"),
    GET_REPORT("GetReport"),
    GET_BASE_REPORT("GetBaseReport"),
    GET_VARIABLES("GetVariables"),
    CANCEL_RESERVATION("CancelReservation"),
    GET_DIAGNOSTICS("GetDiagnostics"),
    CLEAR_CHARGING_PROFILE("ClearChargingProfile"),
    GET_COMPOSITE_SCHEDULE("GetCompositeSchedule"),
    GET_LOCAL_LIST_VERSION("GetLocalListVersion"),
    UPDATE_FIRMWARE("UpdateFirmware"),
    SEND_LOCAL_LIST("SendLocalList"),
    TRIGGER_MESSAGE("TriggerMessage"),
    SET_CHARGING_PROFILE("SetChargingProfile"),
    RESERVE_NOW("ReserveNow"),
    DATA_TRANSFER("DataTransfer"),
    CERTIFICATE_SIGNED("CertificateSigned"),
    GET_LOG("GetLog"),
    CLEAR_DISPLAY_MESSAGE("ClearDisplayMessage"),
    GET_CHARGING_PROFILES("GetChargingProfiles"),
    GET_INSTALLED_CERTIFICATE_IDS("GetInstalledCertificateIds"),
    INSTALL_CERTIFICATE("InstallCertificate"),
    CUSTOMER_INFORMATION("CustomerInformation"),
    UNPUBLISH_FIRMWARE("UnpublishFirmware"),
    SET_VARIABLE_MONITORING("SetVariableMonitoring"),
    SET_MONITORING_LEVEL("SetMonitoringLevel"),
    PUBLISH_FIRMWARE("PublishFirmware"),
    SET_NETWORK_PROFILE("SetNetworkProfile"),
    GET_TRANSACTION_STATUS("GetTransactionStatus"),
    SET_MONITORING_BASE("SetMonitoringBase"),
    GET_DISPLAY_MESSAGES("GetDisplayMessages"),
    COST_UPDATED("CostUpdated"),
    SET_DISPLAY_MESSAGE("SetDisplayMessage"),
    DELETE_CERTIFICATE("DeleteCertificate"),
    GET_MONITORING_REPORT("GetMonitoringReport"),
    CLEAR_VARIABLE_MONITORING("ClearVariableMonitoring"),
    HEARTBEAT("Heartbeat"),
    AUTHORIZE("Authorize"),
    METER_VALUES("MeterValues"),
    TRANSACTION_EVENT("TransactionEvent"),
    STATUS_NOTIFICATION("StatusNotification"),
    BOOT_NOTIFICATION("BootNotification"),
    NOTIFY_REPORT("NotifyReport"),
    FIRMWARE_STATUS_NOTIFICATION("FirmwareStatusNotification"),
    CLEARED_CHARGING_LIMIT("ClearedChargingLimit"),
    GET_CERTIFICATE_STATUS("GetCertificateStatus"),
    NOTIFY_CUSTOMER_INFORMATION("NotifyCustomerInformation"),
    NOTIFY_EVENT("NotifyEvent"),
    NOTIFY_EV_CHARGING_SCHEDULE("NotifyEVChargingSchedule"),
    NOTIFY_CHARGING_LIMIT("NotifyChargingLimit"),
    NOTIFY_DISPLAY_MESSAGES("NotifyDisplayMessages"),
    NOTIFY_EV_CHARGING_NEEDS("NotifyEVChargingNeeds"),
    LOG_STATUS_NOTIFICATION("LogStatusNotification"),
    PUBLISH_FIRMWARE_STATUS_NOTIFICATION("PublishFirmwareStatusNotification"),
    NOTIFY_MONITORING_REPORT("NotifyMonitoringReport"),
    RESERVATION_STATUS_UPDATE("ReservationStatusUpdate"),
    SECURITY_EVENT_NOTIFICATION("SecurityEventNotification"),
    SIGN_CERTIFICATE("SignCertificate"),
    REPORT_CHARGING_PROFILES("ReportChargingProfiles")
}

