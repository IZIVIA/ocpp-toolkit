package fr.simatix.cs.simulator.core20.model.getinstalledcertificateids

import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType

data class GetInstalledCertificateIdsReq(
        val certificateType: GetCertificateIdUseEnumType?=null
)
