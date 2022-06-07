package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateReq as InstallCertificateReqGen
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateResp as InstallCertificateRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface InstallCertificateMapper
{
    fun genToCoreResp(installCertificateResp: InstallCertificateRespGen?): InstallCertificateResp

    fun coreToGenReq(installCertificateReq: InstallCertificateReq?): InstallCertificateReqGen
}