package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusReq
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getcertificatestatus.GetCertificateStatusReq as GetCertificateStatusReqGen
import com.izivia.ocpp.api.model.getcertificatestatus.GetCertificateStatusResp as GetCertificateStatusRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetCertificateStatusMapper {

    fun genToCoreReq(getCertificateStatusReq: GetCertificateStatusReqGen?): GetCertificateStatusReq

    fun coreToGenResp(getCertificateStatusResp: GetCertificateStatusResp?): GetCertificateStatusRespGen

}