package fr.simatix.cs.simulator.api.model.common

data class StatusInfoType(
    val reasonCode: String,
    val additionalInfo: String? = null
)