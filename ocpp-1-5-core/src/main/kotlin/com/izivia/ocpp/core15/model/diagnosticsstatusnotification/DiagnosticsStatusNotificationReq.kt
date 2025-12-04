package com.izivia.ocpp.core15.model.diagnosticsstatusnotification

import com.izivia.ocpp.core15.model.Request
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus

data class DiagnosticsStatusNotificationReq(
    val status: DiagnosticsStatus
): Request
