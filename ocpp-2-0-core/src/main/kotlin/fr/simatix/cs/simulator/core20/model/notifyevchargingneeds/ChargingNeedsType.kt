package fr.simatix.cs.simulator.core20.model.notifyevchargingneeds

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class ChargingNeedsType(
    val requestedEnergyTransfer: EnergyTransferModeEnumType,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val departureTime: Instant? = null,
    val acChargingParameters: ACChargingParametersType? = null,
    val dcChargingParameters: DCChargingParametersType? = null
)
