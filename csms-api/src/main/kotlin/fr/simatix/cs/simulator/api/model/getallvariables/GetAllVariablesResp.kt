package fr.simatix.cs.simulator.api.model.getallvariables

import fr.simatix.cs.simulator.api.model.Response

data class GetAllVariablesResp(
    val configurationKey: List<KeyValue>? = null,
): Response