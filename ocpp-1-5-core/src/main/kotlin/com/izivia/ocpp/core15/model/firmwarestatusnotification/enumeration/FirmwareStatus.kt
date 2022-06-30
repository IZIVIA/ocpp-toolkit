package com.izivia.ocpp.core15.model.firmwarestatusnotification.enumeration

enum class FirmwareStatus(val value: String) {
    Downloaded("Downloaded"),

    DownloadFailed("DownloadFailed"),

    InstallationFailed("InstallationFailed"),

    Installed("Installed");
}