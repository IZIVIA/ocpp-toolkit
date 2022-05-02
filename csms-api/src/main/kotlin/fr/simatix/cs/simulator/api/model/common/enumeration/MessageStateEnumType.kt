package fr.simatix.cs.simulator.api.model.common.enumeration

enum class MessageStateEnumType(val value: String) {
    Charging("Charging"),

    Faulted("Faulted"),

    Idle("Idle"),

    Unavailable("Unavailable");
}