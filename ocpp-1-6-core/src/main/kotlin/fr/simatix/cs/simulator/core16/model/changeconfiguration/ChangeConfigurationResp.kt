package fr.simatix.cs.simulator.core16.model.changeconfiguration

import fr.simatix.cs.simulator.core16.model.changeconfiguration.enumeration.ConfigurationStatus

data class ChangeConfigurationResp(
    val status: ConfigurationStatus
)