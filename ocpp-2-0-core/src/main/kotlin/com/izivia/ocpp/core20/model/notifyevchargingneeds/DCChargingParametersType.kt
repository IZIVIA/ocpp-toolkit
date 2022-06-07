package com.izivia.ocpp.core20.model.notifyevchargingneeds

data class DCChargingParametersType(
    val evMaxCurrent: Int,
    val evMaxVoltage: Int,
    val energyAmount: Int? = null,
    val evMaxPower: Int? = null,
    val stateOfCharge: Int? = null,
    val evEnergyCapacity: Int? = null,
    val fullSoC: Int? = null,
    val bulkSoC: Int? = null,
)
