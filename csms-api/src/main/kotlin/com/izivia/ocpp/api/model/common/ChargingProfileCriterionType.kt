package com.izivia.ocpp.api.model.common

import com.izivia.ocpp.api.model.common.enumeration.ChargingLimitSourceEnumType
import com.izivia.ocpp.api.model.common.enumeration.ChargingProfilePurposeEnumType

data class ChargingProfileCriterionType (
    val chargingProfilePurpose : ChargingProfilePurposeEnumType?=null,
    val stackLevel : Int?=null,
    val chargingProfileId : List<Int>?=null,
    val chargingLimitSource : List<ChargingLimitSourceEnumType>?=null
)