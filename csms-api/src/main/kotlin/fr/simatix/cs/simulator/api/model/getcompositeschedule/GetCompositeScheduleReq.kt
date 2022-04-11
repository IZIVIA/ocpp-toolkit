package fr.simatix.cs.simulator.api.model.getcompositeschedule

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingRateUnitEnumType

data class GetCompositeScheduleReq(
    val evseId: Int,
    val duration: Int,
    val chargingRateUnit: ChargingRateUnitEnumType? = null
): Request
