package com.izivia.ocpp.api.model.common

import com.izivia.ocpp.api.model.common.enumeration.HashAlgorithmEnumType

data class OCSPRequestDataType(
    val hashAlgorithm: HashAlgorithmEnumType,
    val issuerNameHash: String,
    val issuerKeyHash: String,
    val serialNumber: String,
    val responderURL: String
)
