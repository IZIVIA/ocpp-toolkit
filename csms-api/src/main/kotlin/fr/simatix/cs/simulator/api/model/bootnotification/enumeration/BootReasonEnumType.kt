package fr.simatix.cs.simulator.api.model.bootnotification.enumeration

enum class BootReasonEnumType(val value: String) {
    ApplicationReset("ApplicationReset"),

    FirmwareUpdate("FirmwareUpdate"),

    LocalReset("LocalReset"),

    PowerUp("PowerUp"),

    RemoteReset("RemoteReset"),

    ScheduledReset("ScheduledReset"),

    Triggered("Triggered"),

    Unknown("Unknown"),

    Watchdog("Watchdog");
}