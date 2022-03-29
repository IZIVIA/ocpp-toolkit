package fr.simatix.cs.simulator.api.model.getvariables

import fr.simatix.cs.simulator.api.model.Response

data class GetVariablesResp(
    val getVariableResult: List<GetVariableResultType>
): Response