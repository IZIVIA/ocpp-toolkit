package fr.simatix.cs.simulator.api.model.triggermessage.enumeration

enum class MessageTriggerEnumType(val value: String) {
    BootNotification("BootNotification"),

    LogStatusNotification("LogStatusNotification"),

    FirmwareStatusNotification("FirmwareStatusNotification"),

    Heartbeat("Heartbeat"),

    MeterValues("MeterValues"),

    SignChargingStationCertificate("SignChargingStationCertificate"),

    SignV2GCertificate("SignV2GCertificate"),

    StatusNotification("StatusNotification"),

    TransactionEvent("TransactionEvent"),

    SignCombinedCertificate("SignCombinedCertificate"),

    PublishFirmwareStatusNotification("PublishFirmwareStatusNotification");
}
