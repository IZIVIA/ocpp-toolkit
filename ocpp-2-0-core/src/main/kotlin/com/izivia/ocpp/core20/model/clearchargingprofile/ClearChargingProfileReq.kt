package com.izivia.ocpp.core20.model.clearchargingprofile

data class ClearChargingProfileReq(
    val chargingProfileId: Int? = null,
    val chargingProfileCriteria: ClearChargingProfileType? = null
)