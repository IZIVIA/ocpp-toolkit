package com.izivia.ocpp.core16.model.changeconfiguration

import com.izivia.ocpp.core16.model.changeconfiguration.enumeration.ConfigurationStatus

data class ChangeConfigurationResp(
    val status: ConfigurationStatus
)