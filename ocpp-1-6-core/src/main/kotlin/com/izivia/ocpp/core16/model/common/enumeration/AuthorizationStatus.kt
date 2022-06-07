package com.izivia.ocpp.core16.model.common.enumeration

enum class AuthorizationStatus(val value: String) {
    Accepted("Accepted"),

    Blocked("Blocked"),

    Expired("Expired"),

    Invalid("Invalid"),

    ConcurrentTx("ConcurrentTx");
}