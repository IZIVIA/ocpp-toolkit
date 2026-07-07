package com.izivia.ocpp.core12.model.statusnotification.enumeration

enum class ChargePointStatus(val value: String) {
    Available("Available"),
    Occupied("Occupied"),
    Faulted("Faulted"),
    Unavailable("Unavailable");
}
