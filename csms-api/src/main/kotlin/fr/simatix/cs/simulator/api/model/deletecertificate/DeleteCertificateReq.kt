package fr.simatix.cs.simulator.api.model.deletecertificate

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.CertificateHashDataType

data class DeleteCertificateReq (
    val certificateHashData : CertificateHashDataType
) : Request