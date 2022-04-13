package fr.simatix.cs.simulator.core20.model.notifycharginglimit

import fr.simatix.cs.simulator.core20.model.common.ChargingScheduleType

data class NotifyChargingLimitReq(
    val chargingLimit: ChargingLimitType,
    val evseId: Int? = null,
    val chargingSchedule: List<ChargingScheduleType>? = null
)