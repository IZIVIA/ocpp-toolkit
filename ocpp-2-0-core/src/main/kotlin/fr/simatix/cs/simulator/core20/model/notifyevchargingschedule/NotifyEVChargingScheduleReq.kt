package fr.simatix.cs.simulator.core20.model.notifyevchargingschedule

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.core20.model.common.ChargingScheduleType
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class NotifyEVChargingScheduleReq(
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val timeBase: Instant,
    val evseId: Int,
    val chargingSchedule: ChargingScheduleType
)