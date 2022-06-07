package com.izivia.ocpp.core16.model.reset

import com.izivia.ocpp.core16.model.reset.enumeration.ResetStatus

data class ResetResp(
    val status: ResetStatus
)