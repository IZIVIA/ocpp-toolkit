package fr.simatix.cs.simulator.core20.model.deletecertificate

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.deletecertificate.enumeration.DeleteCertificateStatusEnumType

data class DeleteCertificateResp (
        val status : DeleteCertificateStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
