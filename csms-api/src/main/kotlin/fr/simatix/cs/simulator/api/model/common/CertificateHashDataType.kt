package fr.simatix.cs.simulator.api.model.common

import fr.simatix.cs.simulator.api.model.common.enumeration.HashAlgorithmEnumType

data class CertificateHashDataType(
        val hashAlgorithm : HashAlgorithmEnumType,
        val issuerNameHash : String,
        val issuerKeyHash : String,
        val serialNumber : String
)
