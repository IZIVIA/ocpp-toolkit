package com.izivia.ocpp.core16.model.sendlocallist.enumeration

enum class UpdateStatus(val value: String) {
    Accepted("Accepted"),

    Failed("Failed"),

    NotSupported("NotSupported"),

    VersionMismatch("VersionMismatch")
}
