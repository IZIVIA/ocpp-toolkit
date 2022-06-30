package com.izivia.ocpp.core15.model.statusnotification.enumeration

enum class ChargePointStatus(val value: String) {
    Available("Available"),

    Occupied("Occupied"),

    Reserved("Reserved"),

    Unavailable("Unavailable"),

    Faulted("Faulted");
}