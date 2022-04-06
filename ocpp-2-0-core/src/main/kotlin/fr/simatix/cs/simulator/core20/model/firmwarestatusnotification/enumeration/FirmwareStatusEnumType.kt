package fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.enumeration

enum class FirmwareStatusEnumType(val value : String) {
    Downloaded("Downloaded"),
    DownloadFailed("DownloadFailed"),
    Downloading("Downloading"),
    DownloadScheduled("DownloadScheduled"),
    DownloadPaused("DownloadPaused"),
    Idle("Idle"),
    InstallationFailed("InstallationFailed"),
    Installing("Installing"),
    Installed("Installed"),
    InstallRebooting("InstallRebooting"),
    InstallScheduled("InstallScheduled"),
    InstallVerificationFailed("InstallVerificationFailed"),
    InvalidSignature("InvalidSignature"),
    SignatureVerified("SignatureVerified");
}