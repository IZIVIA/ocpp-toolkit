package fr.simatix.cs.simulator.api.model.getallvariables

data class KeyValue(
    val key: String,
    val readonly: Boolean,
    val value: String? = null
)