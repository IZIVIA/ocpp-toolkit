package fr.simatix.cs.simulator.api.model.customerinformation

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.CertificateHashDataType
import fr.simatix.cs.simulator.api.model.common.IdTokenType

data class CustomerInformationReq(
        val requestId : Int,
        val report : Boolean,
        val clear : Boolean,
        val customerIdentifier : String?=null,
        val idToken : IdTokenType?=null,
        val customerCertificate : CertificateHashDataType?=null
) : Request
