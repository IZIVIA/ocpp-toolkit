package com.izivia.ocpp.core20.model.updatefirmware.enumeration

enum class UpdateFirmwareStatusEnumType(val value: String) {
    Accepted("Accepted"),

    Rejected("Rejected"),

    AcceptedCanceled("AcceptedCanceled"),

    InvalidCertificate("InvalidCertificate"),

    RevokedCertificate("RevokedCertificate");
}