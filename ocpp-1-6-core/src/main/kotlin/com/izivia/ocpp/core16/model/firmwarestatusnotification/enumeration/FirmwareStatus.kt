package com.izivia.ocpp.core16.model.firmwarestatusnotification.enumeration

enum class FirmwareStatus(val value : String) {
    Downloaded("Downloaded"),
    DownloadFailed("DownloadFailed"),
    Downloading("Downloading"),
    Idle("Idle"),
    InstallationFailed("InstallationFailed"),
    Installing("Installing"),
    Installed("Installed");
}