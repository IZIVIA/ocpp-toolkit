package com.izivia.ocpp.core16.model.unlockconnector.enumeration

enum class UnlockStatus(val value: String) {
    Unlocked("Unlocked"),

    UnlockFailed("UnlockFailed"),

    NotSupported("NotSupported");
}