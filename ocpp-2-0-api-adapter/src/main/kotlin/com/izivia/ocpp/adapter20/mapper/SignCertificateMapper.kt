package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.signcertificate.SignCertificateReq as SignCertificateReqGen
import com.izivia.ocpp.api.model.signcertificate.SignCertificateResp as SignCertificateRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SignCertificateMapper {

    fun genToCoreReq(signCertificateReq: SignCertificateReqGen?): SignCertificateReq

    fun coreToGenResp(signCertificateResp: SignCertificateResp?): SignCertificateRespGen

}