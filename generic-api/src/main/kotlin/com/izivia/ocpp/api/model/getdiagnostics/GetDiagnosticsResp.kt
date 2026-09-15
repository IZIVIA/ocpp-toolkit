package com.izivia.ocpp.api.model.getdiagnostics

import com.izivia.ocpp.api.model.Response

/**
 * GetDiagnostics.conf, an OCPP 1.x message. It carries no status: [fileName] names the file the
 * charging station will upload, and is absent when it has no diagnostic information to upload.
 */
data class GetDiagnosticsResp(
    val fileName: String? = null
) : Response
