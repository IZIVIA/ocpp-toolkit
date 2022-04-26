package fr.simatix.cs.simulator.core20.model.installcertificate

import fr.simatix.cs.simulator.core20.model.installcertificate.enumeration.InstallCertificateUseEnumType

data class InstallCertificateReq(
        val certificateType : InstallCertificateUseEnumType,
        val certificate : String
)
