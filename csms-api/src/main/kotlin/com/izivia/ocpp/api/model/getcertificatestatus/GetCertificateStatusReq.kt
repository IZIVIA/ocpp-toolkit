package com.izivia.ocpp.api.model.getcertificatestatus

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.OCSPRequestDataType

data class GetCertificateStatusReq(
    val ocspRequestData: OCSPRequestDataType
): Request