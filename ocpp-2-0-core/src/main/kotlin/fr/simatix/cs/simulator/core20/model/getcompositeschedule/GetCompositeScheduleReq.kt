package fr.simatix.cs.simulator.core20.model.getcompositeschedule

import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingRateUnitEnumType

data class GetCompositeScheduleReq(
    val evseId: Int,
    val duration: Int,
    val chargingRateUnit: ChargingRateUnitEnumType? = null
)
