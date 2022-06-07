package com.izivia.ocpp.core20.model.common

import com.izivia.ocpp.core20.model.common.enumeration.HashAlgorithmEnumType

data class OCSPRequestDataType(
        val hashAlgorithm: HashAlgorithmEnumType,
        val issuerNameHash: String,
        val issuerKeyHash: String,
        val serialNumber: String,
        val responderURL: String
)
