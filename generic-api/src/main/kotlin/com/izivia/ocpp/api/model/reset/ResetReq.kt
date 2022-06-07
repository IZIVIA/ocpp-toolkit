package com.izivia.ocpp.api.model.reset

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.reset.enumeration.ResetEnumType

data class ResetReq(
    val type: ResetEnumType,
    val evseId: Int? = null
): Request