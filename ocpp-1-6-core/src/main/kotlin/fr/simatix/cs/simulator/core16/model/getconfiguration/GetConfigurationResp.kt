package fr.simatix.cs.simulator.core16.model.getconfiguration

data class GetConfigurationResp(
    val configurationKey: List<KeyValue>? = null,
    val unknownKey: List<String>? = null
)