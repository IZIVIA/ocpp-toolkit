package fr.simatix.cs.simulator.core20.model.deletecertificate

import fr.simatix.cs.simulator.core20.model.common.CertificateHashDataType

data class DeleteCertificateReq (
    val certificateHashData : CertificateHashDataType
)