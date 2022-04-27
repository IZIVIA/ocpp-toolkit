package fr.simatix.cs.simulator.api.model.common

import fr.simatix.cs.simulator.api.model.common.enumeration.HashAlgorithmEnumType

data class OCSPRequestDataType(
        val hashAlgorithm: HashAlgorithmEnumType,
        val issuerNameHash: String,
        val issuerKeyHash: String,
        val serialNumber: String,
        val responderURL: String
)
