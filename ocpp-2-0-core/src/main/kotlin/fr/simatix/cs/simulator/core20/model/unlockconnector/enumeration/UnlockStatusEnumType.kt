package fr.simatix.cs.simulator.core20.model.unlockconnector.enumeration

enum class UnlockStatusEnumType(val value: String) {
    Unlocked("Unlocked"),

    UnlockFailed("UnlockFailed"),

    OngoingAuthorizedTransaction("OngoingAuthorizedTransaction"),

    UnknownConnector("UnknownConnector");
}