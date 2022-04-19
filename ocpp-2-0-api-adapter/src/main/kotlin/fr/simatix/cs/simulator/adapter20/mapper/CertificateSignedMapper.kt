package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.certificateSigned.CertificateSignedReq
import fr.simatix.cs.simulator.core20.model.certificateSigned.CertificateSignedResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.certificateSigned.CertificateSignedReq as CertificateSignedReqGen
import fr.simatix.cs.simulator.api.model.certificateSigned.CertificateSignedResp as CertificateSignedRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CertificateSignedMapper {

    fun genToCoreResp(certificateSignedResp: CertificateSignedRespGen): CertificateSignedResp

    fun coreToGenReq(certificateSignedReq: CertificateSignedReq): CertificateSignedReqGen

}