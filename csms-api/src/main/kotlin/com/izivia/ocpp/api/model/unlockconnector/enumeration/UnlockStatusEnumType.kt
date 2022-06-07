package com.izivia.ocpp.api.model.unlockconnector.enumeration

enum class UnlockStatusEnumType(val value: String) {
    Unlocked("Unlocked"),

    UnlockFailed("UnlockFailed"),

    OngoingAuthorizedTransaction("OngoingAuthorizedTransaction"),

    UnknownConnector("UnknownConnector");
}