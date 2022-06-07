package com.izivia.ocpp.core20.model.getcompositeschedule

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.common.enumeration.GenericStatusEnumType

data class GetCompositeScheduleResp(
    val status: GenericStatusEnumType,
    val schedule: CompositeScheduleType? = null,
    val statusInfo: StatusInfoType? = null
)