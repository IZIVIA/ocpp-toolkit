package com.izivia.ocpp.api.model.deletecertificate

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.deletecertificate.enumerations.DeleteCertificateStatusEnumType

data class DeleteCertificateResp (
    val status : DeleteCertificateStatusEnumType,
    val statusInfo : StatusInfoType?=null
) : Response
