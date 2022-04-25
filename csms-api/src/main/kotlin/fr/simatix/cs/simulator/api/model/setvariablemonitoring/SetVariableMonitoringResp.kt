package fr.simatix.cs.simulator.api.model.setvariablemonitoring

import fr.simatix.cs.simulator.api.model.Response

data class SetVariableMonitoringResp(
    val setMonitoringResult : List<SetMonitoringResultType>
) : Response
