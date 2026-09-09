package com.izivia.ocpp.api.model.diagnosticsstatusnotification

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatusEnumType

class DiagnosticsStatusNotificationReq(
    val status: DiagnosticsStatusEnumType
): Request
