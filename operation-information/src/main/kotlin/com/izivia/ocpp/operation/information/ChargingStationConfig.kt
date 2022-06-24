package com.izivia.ocpp.operation.information

data class ChargingStationConfig(
    val acceptConnection: Boolean,
    val soapUrl: String?,
)
