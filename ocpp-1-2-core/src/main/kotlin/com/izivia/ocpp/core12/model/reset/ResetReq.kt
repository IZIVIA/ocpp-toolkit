package com.izivia.ocpp.core12.model.reset

import com.izivia.ocpp.core12.model.Request
import com.izivia.ocpp.core12.model.reset.enumeration.ResetType

data class ResetReq(
    val type: ResetType
): Request
