package fr.simatix.cs.simulator.core20.model.notifyevchargingschedule

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.enumeration.GenericStatusEnumType

data class NotifyEVChargingScheduleResp(
    val status: GenericStatusEnumType,
    val statusInfo: StatusInfoType
)