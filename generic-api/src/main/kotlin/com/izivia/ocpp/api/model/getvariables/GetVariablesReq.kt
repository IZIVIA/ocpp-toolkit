package com.izivia.ocpp.api.model.getvariables

import com.izivia.ocpp.api.model.Request

data class GetVariablesReq(
    val getVariableData: List<GetVariableDataType>
): Request