package fr.simatix.cs.simulator.core20.model.publishfirmwarestatusnotification.enumeration

enum class PublishFirmwareStatusEnumType(val value: String) {
    Idle("Idle"),

    DownloadScheduled("DownloadScheduled"),

    Downloading("Downloading"),

    Downloaded("Downloaded"),

    Published("Published"),

    DownloadFailed("DownloadFailed"),

    DownloadPaused("DownloadPaused"),

    InvalidChecksum("InvalidChecksum"),

    ChecksumVerified("ChecksumVerified"),

    PublishFailed("PublishFailed");
}