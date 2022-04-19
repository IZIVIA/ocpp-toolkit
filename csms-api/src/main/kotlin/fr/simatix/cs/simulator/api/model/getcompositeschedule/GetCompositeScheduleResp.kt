package fr.simatix.cs.simulator.api.model.getcompositeschedule

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.common.enumeration.GenericStatusEnumType

data class GetCompositeScheduleResp(
    val status: GenericStatusEnumType,
    val schedule: CompositeScheduleType? = null,
    val statusInfo: StatusInfoType? = null
): Response
