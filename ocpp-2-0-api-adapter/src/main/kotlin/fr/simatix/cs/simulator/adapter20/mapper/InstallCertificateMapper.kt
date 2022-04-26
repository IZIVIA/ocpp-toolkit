package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.installcertificate.InstallCertificateReq
import fr.simatix.cs.simulator.core20.model.installcertificate.InstallCertificateResp
import fr.simatix.cs.simulator.api.model.installcertificate.InstallCertificateReq as InstallCertificateReqGen
import fr.simatix.cs.simulator.api.model.installcertificate.InstallCertificateResp as InstallCertificateRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface InstallCertificateMapper
{
    fun genToCoreResp(installCertificateResp: InstallCertificateRespGen?): InstallCertificateResp

    fun coreToGenReq(installCertificateReq: InstallCertificateReq?): InstallCertificateReqGen
}