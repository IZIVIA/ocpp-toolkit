package fr.simatix.cs.simulator.api.model.setvariables

import fr.simatix.cs.simulator.api.model.Request

data class SetVariablesReq(
    val setVariableData: List<SetVariableDataType>
): Request