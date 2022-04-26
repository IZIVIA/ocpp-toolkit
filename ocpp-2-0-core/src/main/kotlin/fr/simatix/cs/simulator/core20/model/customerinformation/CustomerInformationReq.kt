package fr.simatix.cs.simulator.core20.model.customerinformation

import fr.simatix.cs.simulator.core20.model.common.CertificateHashDataType
import fr.simatix.cs.simulator.core20.model.common.IdTokenType

data class CustomerInformationReq(
        val requestId : Int,
        val report : Boolean,
        val clear : Boolean,
        val customerIdentifier : String?=null,
        val idToken : IdTokenType?=null,
        val customerCertificate : CertificateHashDataType?=null
)
