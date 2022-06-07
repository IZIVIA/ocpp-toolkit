package com.izivia.ocpp.api.model.common

import com.izivia.ocpp.api.model.common.enumeration.HashAlgorithmEnumType

data class CertificateHashDataType(
    val hashAlgorithm : HashAlgorithmEnumType,
    val issuerNameHash : String,
    val issuerKeyHash : String,
    val serialNumber : String
)
