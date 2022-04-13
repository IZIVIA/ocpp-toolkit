package fr.simatix.cs.simulator.core20.model.notifydisplaymessages.enumeration

enum class MessageStateEnumType(val value: String) {
    Charging("Charging"),

    Faulted("Faulted"),

    Idle("Idle"),

    Unavailable("Unavailable");
}