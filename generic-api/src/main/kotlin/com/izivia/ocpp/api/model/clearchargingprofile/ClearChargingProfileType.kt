package com.izivia.ocpp.api.model.clearchargingprofile

import com.izivia.ocpp.api.model.common.enumeration.ChargingProfilePurposeEnumType

data class ClearChargingProfileType(
    val evseId: Int? = null,
    val chargingProfilePurpose: ChargingProfilePurposeEnumType? = null,
    val stackLevel: Int? = null
)
