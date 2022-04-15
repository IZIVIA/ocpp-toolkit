package fr.simatix.cs.simulator.core20.model.logstatusnotification.enumeration

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