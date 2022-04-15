package fr.simatix.cs.simulator.core16.model.diagnosticsstatusnotification.enumeration

enum class DiagnosticsStatus(val value : String) {

    Idle("Idle"),

    Uploaded("Uploaded"),

    UploadFailed("UploadFailed"),

    Uploading("Uploading");
}