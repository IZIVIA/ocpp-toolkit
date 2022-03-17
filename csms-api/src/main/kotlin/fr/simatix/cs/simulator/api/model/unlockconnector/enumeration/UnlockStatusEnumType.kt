package fr.simatix.cs.simulator.api.model.unlockconnector.enumeration

enum class UnlockStatusEnumType(val value: String) {
    Unlocked("Unlocked"),

    UnlockFailed("UnlockFailed"),

    OngoingAuthorizedTransaction("OngoingAuthorizedTransaction"),

    UnknownConnector("UnknownConnector");
}