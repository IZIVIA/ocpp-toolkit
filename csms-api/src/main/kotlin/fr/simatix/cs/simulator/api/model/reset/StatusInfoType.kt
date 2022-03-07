package fr.simatix.cs.simulator.api.model.reset

data class StatusInfoType(
    val reasonCode: String,
    val additionalInfo: String? = null
)