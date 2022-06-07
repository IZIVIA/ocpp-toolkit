package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedReq
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedReq as CertificateSignedReqGen
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedResp as CertificateSignedRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CertificateSignedMapper {

    fun genToCoreResp(certificateSignedResp: CertificateSignedRespGen): CertificateSignedResp

    fun coreToGenReq(certificateSignedReq: CertificateSignedReq): CertificateSignedReqGen

}