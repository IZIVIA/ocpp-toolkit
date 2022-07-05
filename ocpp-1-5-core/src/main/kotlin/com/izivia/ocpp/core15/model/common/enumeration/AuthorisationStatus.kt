package com.izivia.ocpp.core15.model.common.enumeration

enum class AuthorisationStatus(val value: String) {
    Accepted("Accepted"),

    Blocked("Blocked"),

    Expired("Expired"),

    Invalid("Invalid"),

    ConcurrentTx("ConcurrentTx");
}
