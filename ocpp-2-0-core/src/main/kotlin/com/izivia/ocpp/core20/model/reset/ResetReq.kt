package com.izivia.ocpp.core20.model.reset

import com.izivia.ocpp.core20.model.reset.enumeration.ResetEnumType

data class ResetReq(
    val type: ResetEnumType,
    val evseId: Int? = null
)