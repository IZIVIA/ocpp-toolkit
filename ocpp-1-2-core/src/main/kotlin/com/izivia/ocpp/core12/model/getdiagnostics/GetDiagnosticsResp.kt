package com.izivia.ocpp.core12.model.getdiagnostics

import com.izivia.ocpp.core12.model.Response

data class GetDiagnosticsResp(
    val fileName: String? = null
): Response
