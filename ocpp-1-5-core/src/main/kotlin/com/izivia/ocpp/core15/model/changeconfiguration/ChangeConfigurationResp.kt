package com.izivia.ocpp.core15.model.changeconfiguration

import com.izivia.ocpp.core15.model.changeconfiguration.enumeration.ConfigurationStatus

data class ChangeConfigurationResp(
    val status: ConfigurationStatus
)