package com.izivia.ocpp.api.model.logstatusnotification.enumeration

enum class UploadLogStatusEnumType(val value: String) {

    BadMessage("BadMessage"),

    Idle("Idle"),

    NotSupportedOperation("NotSupportedOperation"),

    PermissionDenied("PermissionDenied"),

    Uploaded("Uploaded"),

    UploadFailure("UploadFailure"),

    Uploading("Uploading"),

    AcceptedCanceled("AcceptedCanceled");
}