package fr.simatix.cs.simulator.api.model.clearvariablemonitoring

import fr.simatix.cs.simulator.api.model.Response

data class ClearVariableMonitoringResp(
    val clearMonitoringResults : List<ClearMonitoringResultType>
): Response