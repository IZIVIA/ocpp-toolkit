package fr.simatix.cs.simulator.api.model.notifyevent.enumeration

enum class EventNotificationEnumType(val value: String) {
    HardWiredNotification("HardWiredNotification"),

    HardWiredMonitor("HardWiredMonitor"),

    PreconfiguredMonitor("PreconfiguredMonitor"),

    CustomMonitor("CustomMonitor");
}