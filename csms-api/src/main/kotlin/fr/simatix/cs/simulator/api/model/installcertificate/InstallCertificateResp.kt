package fr.simatix.cs.simulator.api.model.installcertificate

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.installcertificate.enumeration.InstallCertificateStatusEnumType

data class InstallCertificateResp(
        val status : InstallCertificateStatusEnumType,
        val statusInfo : StatusInfoType?=null
) : Response
