package com.izivia.ocpp.core15.model.reset

import com.izivia.ocpp.core15.model.reset.enumeration.ResetType

data class ResetReq(
    val type: ResetType
)