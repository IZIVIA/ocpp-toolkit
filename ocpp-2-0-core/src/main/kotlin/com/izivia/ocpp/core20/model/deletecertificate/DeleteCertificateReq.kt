package com.izivia.ocpp.core20.model.deletecertificate

import com.izivia.ocpp.core20.model.common.CertificateHashDataType

data class DeleteCertificateReq (
    val certificateHashData : CertificateHashDataType
)