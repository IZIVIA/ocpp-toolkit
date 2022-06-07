package com.izivia.ocpp.api.model.setdisplaymessage.enumeration

enum class DisplayMessageStatusEnumType(val value: String)
{
    Accepted("Accepted"),
    NotSupportedMessageFormat("NotSupportedMessageFormat"),
    Rejected("Rejected"),
    NotSupportedPriority("NotSupportedPriority"),
    NotSupportedState("NotSupportedState"),
    UnknownTransaction("UnknownTransaction")
}