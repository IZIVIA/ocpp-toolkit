package fr.simatix.cs.simulator.api.model.clearvariablemonitoring

import fr.simatix.cs.simulator.api.model.Request

data class ClearVariableMonitoringReq(
    val ids: List<Int>
): Request