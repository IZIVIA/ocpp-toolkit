package com.izivia.ocpp.core20.model.getcertificatestatus

import com.izivia.ocpp.core20.model.common.OCSPRequestDataType

data class GetCertificateStatusReq(
    val ocspRequestData: OCSPRequestDataType
)