package fr.simatix.cs.simulator.api.model.notifyevchargingneeds

import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType
import kotlinx.datetime.Instant

data class ChargingNeedsType(
    val requestedEnergyTransfer: EnergyTransferModeEnumType,
    val departureTime: Instant? = null,
    val acChargingParameters: ACChargingParametersType? = null,
    val dcChargingParameters: DCChargingParametersType? = null
)