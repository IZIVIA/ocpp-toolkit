package com.izivia.ocpp.core20.model.notifyevchargingneeds

import com.izivia.ocpp.core20.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType
import kotlinx.datetime.Instant

data class ChargingNeedsType(
    val requestedEnergyTransfer: EnergyTransferModeEnumType,
    val departureTime: Instant? = null,
    val acChargingParameters: ACChargingParametersType? = null,
    val dcChargingParameters: DCChargingParametersType? = null
)
