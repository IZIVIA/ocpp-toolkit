package com.izivia.ocpp.api.model.customerinformation

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.CertificateHashDataType
import com.izivia.ocpp.api.model.common.IdTokenType

data class CustomerInformationReq(
    val requestId : Int,
    val report : Boolean,
    val clear : Boolean,
    val customerIdentifier : String?=null,
    val idToken : IdTokenType?=null,
    val customerCertificate : CertificateHashDataType?=null
) : Request
