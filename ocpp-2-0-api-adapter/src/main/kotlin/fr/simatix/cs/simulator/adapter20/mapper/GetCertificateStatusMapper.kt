package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.getcertificatestatus.GetCertificateStatusReq
import fr.simatix.cs.simulator.core20.model.getcertificatestatus.GetCertificateStatusResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusReq as GetCertificateStatusReqGen
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusResp as GetCertificateStatusRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetCertificateStatusMapper {

    fun genToCoreReq(getCertificateStatusReq: GetCertificateStatusReqGen?): GetCertificateStatusReq

    fun coreToGenResp(getCertificateStatusResp: GetCertificateStatusResp?): GetCertificateStatusRespGen

}