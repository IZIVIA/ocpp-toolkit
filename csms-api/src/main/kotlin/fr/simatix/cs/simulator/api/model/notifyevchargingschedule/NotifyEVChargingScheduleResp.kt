package fr.simatix.cs.simulator.api.model.notifyevchargingschedule

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.getcompositeschedule.enumeration.GenericStatusEnumType

data class NotifyEVChargingScheduleResp(
    val status: GenericStatusEnumType,
    val statusInfo: StatusInfoType
): Response