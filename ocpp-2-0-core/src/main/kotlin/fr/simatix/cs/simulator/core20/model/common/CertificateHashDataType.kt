package fr.simatix.cs.simulator.core20.model.common

import fr.simatix.cs.simulator.core20.model.authorize.enumeration.HashAlgorithmEnumType

data class CertificateHashDataType(
        val hashAlgorithm : HashAlgorithmEnumType,
        val issuerNameHash : String,
        val issuerKeyHash : String,
        val serialNumber : String
)
