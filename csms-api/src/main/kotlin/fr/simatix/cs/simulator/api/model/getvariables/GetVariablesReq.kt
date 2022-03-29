package fr.simatix.cs.simulator.api.model.getvariables

import fr.simatix.cs.simulator.api.model.Request

data class GetVariablesReq(
    val getVariableData: List<GetVariableDataType>
): Request