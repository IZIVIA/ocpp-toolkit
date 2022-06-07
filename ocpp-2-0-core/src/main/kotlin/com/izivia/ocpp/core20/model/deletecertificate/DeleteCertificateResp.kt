package com.izivia.ocpp.core20.model.deletecertificate

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.deletecertificate.enumeration.DeleteCertificateStatusEnumType

data class DeleteCertificateResp (
        val status : DeleteCertificateStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
