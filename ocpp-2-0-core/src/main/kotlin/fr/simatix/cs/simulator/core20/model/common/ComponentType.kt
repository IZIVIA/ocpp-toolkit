package fr.simatix.cs.simulator.core20.model.common

data class ComponentType(
    val name: String,
    val instance: String? = null,
    val evse: EVSEType? = null
)