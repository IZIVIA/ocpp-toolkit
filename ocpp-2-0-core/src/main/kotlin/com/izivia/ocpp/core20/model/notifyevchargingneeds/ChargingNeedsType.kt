package com.izivia.ocpp.core20.model.notifyevchargingneeds

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core20.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class ChargingNeedsType(
    val requestedEnergyTransfer: EnergyTransferModeEnumType,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val departureTime: Instant? = null,
    val acChargingParameters: ACChargingParametersType? = null,
    val dcChargingParameters: DCChargingParametersType? = null
)
