package com.izivia.ocpp.api.model.common.enumeration

enum class AuthorizationStatusEnumType(val value: String) {
    Accepted("Accepted"),
    Blocked("Blocked"),
    ConcurrentTx("ConcurrentTx"),
    Expired("Expired"),
    Invalid("Invalid"),
    NoCredit("NoCredit"),
    NotAllowedTypeEVSE("NotAllowedTypeEVSE"),
    NotAtThisLocation("NotAtThisLocation"),
    NotAtThisTime("NotAtThisTime"),
    Unknown("Unknown")
}
