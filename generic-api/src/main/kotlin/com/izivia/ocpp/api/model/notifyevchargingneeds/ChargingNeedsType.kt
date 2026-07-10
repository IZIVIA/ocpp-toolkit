package com.izivia.ocpp.api.model.notifyevchargingneeds

import com.izivia.ocpp.api.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType
import kotlin.time.Instant

data class ChargingNeedsType(
    val requestedEnergyTransfer: EnergyTransferModeEnumType,
    val departureTime: Instant? = null,
    val acChargingParameters: ACChargingParametersType? = null,
    val dcChargingParameters: DCChargingParametersType? = null
)