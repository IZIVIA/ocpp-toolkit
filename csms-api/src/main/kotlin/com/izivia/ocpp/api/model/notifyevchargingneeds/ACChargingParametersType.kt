package com.izivia.ocpp.api.model.notifyevchargingneeds

data class ACChargingParametersType(
    val energyAmount: Int,
    val evMinCurrent: Int,
    val evMaxCurrent: Int,
    val evMaxVoltage: Int
)
