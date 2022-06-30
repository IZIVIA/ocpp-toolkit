package com.izivia.ocpp.core15.model.reset

import com.izivia.ocpp.core15.model.reset.enumeration.ResetStatus

data class ResetResp(
    val status: ResetStatus
)