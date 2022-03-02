package fr.simatix.cs.simulator.api.model.transactionevent

data class EVSEType(
    val id: Int,
    val connectorId: Int? = null
)
