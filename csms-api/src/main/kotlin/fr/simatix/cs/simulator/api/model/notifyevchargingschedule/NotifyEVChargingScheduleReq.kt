package fr.simatix.cs.simulator.api.model.notifyevchargingschedule

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.ChargingScheduleType
import kotlinx.datetime.Instant

data class NotifyEVChargingScheduleReq(
    val timeBase: Instant,
    val evseId: Int,
    val chargingSchedule: ChargingScheduleType
): Request