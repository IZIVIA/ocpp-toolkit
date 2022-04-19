package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.signcertificate.SignCertificateReq
import fr.simatix.cs.simulator.core20.model.signcertificate.SignCertificateResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.signcertificate.SignCertificateReq as SignCertificateReqGen
import fr.simatix.cs.simulator.api.model.signcertificate.SignCertificateResp as SignCertificateRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SignCertificateMapper {

    fun genToCoreReq(signCertificateReq: SignCertificateReqGen?): SignCertificateReq

    fun coreToGenResp(signCertificateResp: SignCertificateResp?): SignCertificateRespGen

}