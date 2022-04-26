package fr.simatix.cs.simulator.api.model.installcertificate

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.installcertificate.enumeration.InstallCertificateUseEnumType

data class InstallCertificateReq(
        val certificateType : InstallCertificateUseEnumType,
        val certificate : String
) : Request
