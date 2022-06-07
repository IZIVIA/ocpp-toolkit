package com.izivia.ocpp.api.model.getcompositeschedule

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.enumeration.GenericStatusEnumType

data class GetCompositeScheduleResp(
    val status: GenericStatusEnumType,
    val schedule: CompositeScheduleType? = null,
    val statusInfo: StatusInfoType? = null
): Response
