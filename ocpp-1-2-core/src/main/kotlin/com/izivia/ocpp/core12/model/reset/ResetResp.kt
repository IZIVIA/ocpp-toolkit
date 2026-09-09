package com.izivia.ocpp.core12.model.reset

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.reset.enumeration.ResetStatus

data class ResetResp(
    val status: ResetStatus
): Response
