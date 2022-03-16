package fr.simatix.cs.simulator.core16.model.bootnotification

data class BootNotificationReq(
    val chargePointModel: String,
    val chargePointVendor: String,
    val chargePointSerialNumber: String? = null,
    val chargeBoxSerialNumber: String? = null,
    val firmwareVersion: String? = null,
    val iccid: String? = null,
    val imsi: String? = null,
    val meterSerialNumber: String? = null,
    val meterType: String? = null
)