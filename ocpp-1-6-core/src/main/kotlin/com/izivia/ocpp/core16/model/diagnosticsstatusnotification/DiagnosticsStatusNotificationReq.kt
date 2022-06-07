package com.izivia.ocpp.core16.model.diagnosticsstatusnotification

import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus

data class DiagnosticsStatusNotificationReq(
        val status: DiagnosticsStatus
)