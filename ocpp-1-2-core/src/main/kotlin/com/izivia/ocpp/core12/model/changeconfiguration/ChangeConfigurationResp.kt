package com.izivia.ocpp.core12.model.changeconfiguration

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.changeconfiguration.enumeration.ConfigurationStatus

data class ChangeConfigurationResp(
    val status: ConfigurationStatus
): Response
