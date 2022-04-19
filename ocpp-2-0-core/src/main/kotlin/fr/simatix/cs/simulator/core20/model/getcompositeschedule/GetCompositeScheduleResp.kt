package fr.simatix.cs.simulator.core20.model.getcompositeschedule

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.GenericStatusEnumType

data class GetCompositeScheduleResp(
    val status: GenericStatusEnumType,
    val schedule: CompositeScheduleType? = null,
    val statusInfo: StatusInfoType? = null
)