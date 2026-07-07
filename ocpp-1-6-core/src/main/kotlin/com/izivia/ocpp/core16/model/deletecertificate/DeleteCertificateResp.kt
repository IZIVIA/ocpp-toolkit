package com.izivia.ocpp.core16.model.deletecertificate

import com.izivia.ocpp.core16.model.Response
import com.izivia.ocpp.core16.model.deletecertificate.enumeration.DeleteCertificateStatusEnumType

data class DeleteCertificateResp(
    val status: DeleteCertificateStatusEnumType
) : Response
