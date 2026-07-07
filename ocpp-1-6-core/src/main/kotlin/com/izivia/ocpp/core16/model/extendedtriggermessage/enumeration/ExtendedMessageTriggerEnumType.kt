package com.izivia.ocpp.core16.model.extendedtriggermessage.enumeration

enum class ExtendedMessageTriggerEnumType(val value: String) {
    BootNotification("BootNotification"),

    LogStatusNotification("LogStatusNotification"),

    FirmwareStatusNotification("FirmwareStatusNotification"),

    Heartbeat("Heartbeat"),

    MeterValues("MeterValues"),

    SignChargePointCertificate("SignChargePointCertificate"),

    StatusNotification("StatusNotification");
}
