package com.izivia.ocpp.core20.model.getinstalledcertificateids

import com.izivia.ocpp.core20.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType

data class GetInstalledCertificateIdsReq(
        val certificateType: List<GetCertificateIdUseEnumType>?=null
)
