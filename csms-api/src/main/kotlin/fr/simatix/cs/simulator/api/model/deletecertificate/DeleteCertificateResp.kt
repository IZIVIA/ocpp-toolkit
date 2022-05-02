package fr.simatix.cs.simulator.api.model.deletecertificate

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.deletecertificate.enumerations.DeleteCertificateStatusEnumType

data class DeleteCertificateResp (
        val status : DeleteCertificateStatusEnumType,
        val statusInfo : StatusInfoType?=null
) : Response
