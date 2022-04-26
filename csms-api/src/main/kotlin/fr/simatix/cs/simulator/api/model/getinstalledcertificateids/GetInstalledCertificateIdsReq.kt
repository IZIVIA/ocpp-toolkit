package fr.simatix.cs.simulator.api.model.getinstalledcertificateids

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType

data class GetInstalledCertificateIdsReq(
        val certificateType: List<GetCertificateIdUseEnumType>?=null
) : Request
