package com.izivia.ocpp.core15.model.getdiagnostics

import com.izivia.ocpp.core15.model.Response

data class GetDiagnosticsResp(
    val fileName: String? = null
): Response
