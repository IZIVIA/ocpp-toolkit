package fr.simatix.cs.simulator.core16.model.getconfiguration

data class KeyValue(
    val key: String,
    val readonly: Boolean,
    val value: String? = null
)