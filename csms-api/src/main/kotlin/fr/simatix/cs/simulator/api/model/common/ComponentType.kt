package fr.simatix.cs.simulator.api.model.common

data class ComponentType(
    val name: String,
    val instance: String? = null,
    val evse: EVSEType? = null
)