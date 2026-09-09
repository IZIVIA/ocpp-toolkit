package com.izivia.ocpp.core12.model.firmwarestatusnotification.enumeration

enum class FirmwareStatus(val value: String) {
    Downloaded("Downloaded"),

    DownloadFailed("DownloadFailed"),

    InstallationFailed("InstallationFailed"),

    Installed("Installed");
}