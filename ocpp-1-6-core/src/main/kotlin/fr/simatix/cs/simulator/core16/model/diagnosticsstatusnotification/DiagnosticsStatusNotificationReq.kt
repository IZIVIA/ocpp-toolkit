package fr.simatix.cs.simulator.core16.model.diagnosticsstatusnotification

import fr.simatix.cs.simulator.core16.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus

data class DiagnosticsStatusNotificationReq(
        val status: DiagnosticsStatus
)