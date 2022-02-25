package fr.simatix.cs.simulator.core20.model.common

data class SignedMeterValueType(
    val signedMeterData: String,
    val signingMethod: String,
    val encodingMethod: String,
    val publicKey: String
)
