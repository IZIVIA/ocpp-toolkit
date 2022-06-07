package com.izivia.ocpp.api.model.getallvariables

import com.izivia.ocpp.api.model.Response

data class GetAllVariablesResp(
    val configurationKey: List<KeyValue>? = null,
): Response