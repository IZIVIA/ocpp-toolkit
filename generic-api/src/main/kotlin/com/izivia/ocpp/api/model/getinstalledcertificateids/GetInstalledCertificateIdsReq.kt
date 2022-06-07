package com.izivia.ocpp.api.model.getinstalledcertificateids

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType

data class GetInstalledCertificateIdsReq(
        val certificateType: List<GetCertificateIdUseEnumType>?=null
) : Request
