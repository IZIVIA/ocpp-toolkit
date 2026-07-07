package com.izivia.ocpp.core12.model.bootnotification

import com.izivia.ocpp.core12.model.Request

data class BootNotificationReq(
    val chargeBoxSerialNumber: String? = null,
    val chargePointModel: String,
    val chargePointSerialNumber: String? = null,
    val chargePointVendor: String,
    val firmwareVersion: String? = null,
    val iccid: String? = null,
    val imsi: String? = null,
    val meterSerialNumber: String? = null,
    val meterType: String? = null
): Request
