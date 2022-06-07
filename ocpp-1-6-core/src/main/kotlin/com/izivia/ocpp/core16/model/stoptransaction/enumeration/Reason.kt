package com.izivia.ocpp.core16.model.stoptransaction.enumeration

enum class Reason(val value: String) {
    EmergencyStop("EmergencyStop"),

    EVDisconnected("EVDisconnected"),

    HardReset("HardReset"),

    Local("Local"),

    Other("Other"),

    PowerLoss("PowerLoss"),

    Reboot("Reboot"),

    Remote("Remote"),

    SoftReset("SoftReset"),

    UnlockCommand("UnlockCommand"),

    DeAuthorized("DeAuthorized");
}