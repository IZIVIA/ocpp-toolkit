package com.izivia.ocpp.core20.model.customerinformation

import com.izivia.ocpp.core20.model.common.CertificateHashDataType
import com.izivia.ocpp.core20.model.common.IdTokenType

data class CustomerInformationReq(
        val requestId : Int,
        val report : Boolean,
        val clear : Boolean,
        val customerIdentifier : String?=null,
        val idToken : IdTokenType?=null,
        val customerCertificate : CertificateHashDataType?=null
)
