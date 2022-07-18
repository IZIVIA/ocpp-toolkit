package com.izivia.ocpp.core15.model.sendlocallist.enumeration

enum class UpdateStatus(val value: String) {
    Accepted("Accepted"),

    Failed("Failed"),

    HashError("HashError"),

    NotSupported("NotSupported"),

    VersionMismatch("VersionMismatch")
}
