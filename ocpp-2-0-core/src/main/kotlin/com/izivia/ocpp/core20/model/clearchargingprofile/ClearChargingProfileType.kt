package com.izivia.ocpp.core20.model.clearchargingprofile

import com.izivia.ocpp.core20.model.common.enumeration.ChargingProfilePurposeEnumType

data class ClearChargingProfileType(
    val evseId: Int? = null,
    val chargingProfilePurpose: ChargingProfilePurposeEnumType? = null,
    val stackLevel: Int? = null
)
