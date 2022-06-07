package com.izivia.ocpp.api.model.deletecertificate

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.CertificateHashDataType

data class DeleteCertificateReq (
    val certificateHashData : CertificateHashDataType
) : Request