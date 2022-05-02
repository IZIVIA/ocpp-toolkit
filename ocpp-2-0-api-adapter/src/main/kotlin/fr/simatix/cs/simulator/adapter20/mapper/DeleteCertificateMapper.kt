package fr.simatix.cs.simulator.adapter20.mapper


import fr.simatix.cs.simulator.api.model.deletecertificate.DeleteCertificateReq as DeleteCertificateReqGen
import fr.simatix.cs.simulator.core20.model.deletecertificate.DeleteCertificateReq

import fr.simatix.cs.simulator.api.model.deletecertificate.DeleteCertificateResp as DeleteCertificateRespGen
import fr.simatix.cs.simulator.core20.model.deletecertificate.DeleteCertificateResp

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface DeleteCertificateMapper {
    fun genToCoreResp(deleteCertificateResp:DeleteCertificateRespGen): DeleteCertificateResp

    fun coreToGenReq(deleteCertificateReq: DeleteCertificateReq): DeleteCertificateReqGen
}