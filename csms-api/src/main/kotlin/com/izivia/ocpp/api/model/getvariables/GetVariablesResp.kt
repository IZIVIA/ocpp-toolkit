package com.izivia.ocpp.api.model.getvariables

import com.izivia.ocpp.api.model.Response

data class GetVariablesResp(
    val getVariableResult: List<GetVariableResultType>
): Response