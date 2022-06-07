package com.izivia.ocpp.api.model.setvariables

import com.izivia.ocpp.api.model.Response

data class SetVariablesResp(
    val setVariableResult: List<SetVariableResultType>
): Response