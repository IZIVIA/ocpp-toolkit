package fr.simatix.cs.simulator.api.model.setdisplaymessage.enumeration

enum class DisplayMessageStatusEnumType(val value: String)
{
    Accepted("Accepted"),
    NotSupportedMessageFormat("NotSupportedMessageFormat"),
    Rejected("Rejected"),
    NotSupportedPriority("NotSupportedPriority"),
    NotSupportedState("NotSupportedState"),
    UnknownTransaction("UnknownTransaction")
}