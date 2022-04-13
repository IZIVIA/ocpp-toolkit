package fr.simatix.cs.simulator.api.model.notifycharginglimit

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.ChargingScheduleType

data class NotifyChargingLimitReq(
    val chargingLimit: ChargingLimitType,
    val evseId: Int? = null,
    val chargingSchedule: List<ChargingScheduleType>? = null
): Request
