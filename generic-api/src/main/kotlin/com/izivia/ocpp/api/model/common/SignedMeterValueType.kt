package com.izivia.ocpp.api.model.common

data class SignedMeterValueType(
    val signedMeterData: String,
    val signingMethod: String,
    val encodingMethod: String,
    val publicKey: String
)
