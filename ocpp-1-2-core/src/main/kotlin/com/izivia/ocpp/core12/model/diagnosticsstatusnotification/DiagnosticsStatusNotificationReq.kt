package com.izivia.ocpp.core12.model.diagnosticsstatusnotification

import com.izivia.ocpp.core12.model.Request
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus

data class DiagnosticsStatusNotificationReq(
    val status: DiagnosticsStatus
): Request
