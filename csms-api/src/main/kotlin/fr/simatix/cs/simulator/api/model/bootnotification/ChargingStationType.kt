package fr.simatix.cs.simulator.api.model.bootnotification

data class ChargingStationType(
    val model: String,
    val vendorName: String,
    val firmwareVersion: String? = null,
    val modem: ModemType? = null,
    val serialNumber: String? = null,
)