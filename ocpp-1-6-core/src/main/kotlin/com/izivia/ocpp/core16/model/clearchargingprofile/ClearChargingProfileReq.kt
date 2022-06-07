package com.izivia.ocpp.core16.model.clearchargingprofile

import com.izivia.ocpp.core16.model.common.enumeration.ChargingProfilePurposeType

data class ClearChargingProfileReq(
    val id: Int? = null,
    val connectorId: Int? = null,
    val chargingProfilePurpose: ChargingProfilePurposeType? = null,
    val stackLevel: Int? = null
)