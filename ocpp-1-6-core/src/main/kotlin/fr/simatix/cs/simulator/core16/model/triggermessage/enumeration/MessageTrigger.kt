package fr.simatix.cs.simulator.core16.model.triggermessage.enumeration

enum class MessageTrigger(val value: String) {
    BootNotification("BootNotification"),

    DiagnosticsStatusNotification("DiagnosticsStatusNotification"),

    FirmwareStatusNotification("FirmwareStatusNotification"),

    Heartbeat("Heartbeat"),

    MeterValues("MeterValues"),

    StatusNotification("StatusNotification");
}
