package com.izivia.ocpp.api.model.getdiagnostics

import com.izivia.ocpp.api.model.Response

data class GetDiagnosticsResp(
    val fileName: String? = null
) : Response
