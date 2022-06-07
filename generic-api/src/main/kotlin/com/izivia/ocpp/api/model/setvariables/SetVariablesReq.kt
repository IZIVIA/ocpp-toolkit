package com.izivia.ocpp.api.model.setvariables

import com.izivia.ocpp.api.model.Request

data class SetVariablesReq(
    val setVariableData: List<SetVariableDataType>
): Request