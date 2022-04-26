package fr.simatix.cs.simulator.core20.model.installcertificate

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.installcertificate.enumeration.InstallCertificateStatusEnumType

data class InstallCertificateResp(
    val status : InstallCertificateStatusEnumType,
    val statusInfo : StatusInfoType?=null
)
