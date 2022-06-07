package com.izivia.ocpp.core20.model.common

data class SignedMeterValueType(
    val signedMeterData: String,
    val signingMethod: String,
    val encodingMethod: String,
    val publicKey: String
)
