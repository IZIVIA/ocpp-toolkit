package com.izivia.ocpp.adapter20.mapper


import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateReq as DeleteCertificateReqGen
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateReq

import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateResp as DeleteCertificateRespGen
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateResp

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface DeleteCertificateMapper {
    fun genToCoreResp(deleteCertificateResp:DeleteCertificateRespGen): DeleteCertificateResp

    fun coreToGenReq(deleteCertificateReq: DeleteCertificateReq): DeleteCertificateReqGen
}