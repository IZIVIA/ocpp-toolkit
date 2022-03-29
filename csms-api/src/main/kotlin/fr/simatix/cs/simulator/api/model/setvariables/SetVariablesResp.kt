package fr.simatix.cs.simulator.api.model.setvariables

import fr.simatix.cs.simulator.api.model.Response

data class SetVariablesResp(
    val setVariableResult: List<SetVariableResultType>
): Response