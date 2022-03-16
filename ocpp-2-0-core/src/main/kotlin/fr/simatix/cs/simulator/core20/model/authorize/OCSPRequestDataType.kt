package fr.simatix.cs.simulator.core20.model.authorize

import fr.simatix.cs.simulator.core20.model.authorize.enumeration.HashAlgorithmEnumType

data class OCSPRequestDataType(
    val hashAlgorithm: HashAlgorithmEnumType,
    val issuerNameHash: String,
    val issuerKeyHash: String,
    val serialNumber: String,
    val responderURL: String
)
