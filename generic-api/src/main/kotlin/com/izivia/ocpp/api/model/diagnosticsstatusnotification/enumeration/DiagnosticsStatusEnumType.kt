package com.izivia.ocpp.api.model.diagnosticsstatusnotification.enumeration

/**
 * Status of a diagnostics file upload. Modelled on OCPP 1.6, the richest version to define
 * DiagnosticsStatusNotification; OCPP 1.2 and 1.5 only know the two terminal states.
 */
enum class DiagnosticsStatusEnumType(val value: String) {

    Idle("Idle"),

    Uploaded("Uploaded"),

    UploadFailed("UploadFailed"),

    Uploading("Uploading");
}
