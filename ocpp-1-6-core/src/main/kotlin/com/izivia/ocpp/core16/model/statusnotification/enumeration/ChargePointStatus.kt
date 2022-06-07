package com.izivia.ocpp.core16.model.statusnotification.enumeration

enum class ChargePointStatus(val value: String) {
    Available("Available"),

    Preparing("Preparing"),

    Charging("Charging"),

    SuspendedEVSE("SuspendedEVSE"),

    SuspendedEV("SuspendedEV"),

    Finishing("Finishing"),

    Reserved("Reserved"),

    Unavailable("Unavailable"),

    Faulted("Faulted");
}