package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType as GetCertificateIdUseEnumTypeGen
import com.izivia.ocpp.core20.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsResp as GetInstalledCertificateIdsRespGen
import org.mapstruct.Mapper
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsReq as GetInstalledCertificateIdsReqGen
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetInstalledCertificateIdsMapper {


    private fun getInstalledCertificateIdsCertificateType(certificates: List<GetCertificateIdUseEnumType>) : List<GetCertificateIdUseEnumTypeGen> =
        certificates.map { GetCertificateIdUseEnumTypeGen.valueOf(it.value) }

    abstract fun genToCoreResp(getInstalledCertificateIdsResp: GetInstalledCertificateIdsRespGen): GetInstalledCertificateIdsResp

    fun coreToGenReq(getInstalledCertificateIdsReq: GetInstalledCertificateIdsReq) : GetInstalledCertificateIdsReqGen =
        GetInstalledCertificateIdsReqGen(getInstalledCertificateIdsReq.certificateType?.let { getInstalledCertificateIdsCertificateType(it) })
}