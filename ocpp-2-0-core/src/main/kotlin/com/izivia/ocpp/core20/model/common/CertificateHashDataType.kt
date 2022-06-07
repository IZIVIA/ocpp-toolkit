package com.izivia.ocpp.core20.model.common

import com.izivia.ocpp.core20.model.common.enumeration.HashAlgorithmEnumType

data class CertificateHashDataType(
    val hashAlgorithm : HashAlgorithmEnumType,
    val issuerNameHash : String,
    val issuerKeyHash : String,
    val serialNumber : String
)
