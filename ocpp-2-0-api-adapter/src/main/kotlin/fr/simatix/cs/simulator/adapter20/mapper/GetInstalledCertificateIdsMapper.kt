package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType as GetCertificateIdUseEnumTypeGen
import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType
import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.GetInstalledCertificateIdsResp as GetInstalledCertificateIdsRespGen
import org.mapstruct.Mapper
import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import org.mapstruct.Named
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.GetInstalledCertificateIdsReq as GetInstalledCertificateIdsReqGen
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetInstalledCertificateIdsMapper {


    private fun getInstalledCertificateIdsCertificateType(certificates: List<GetCertificateIdUseEnumType>) : List<GetCertificateIdUseEnumTypeGen> =
        certificates.map { GetCertificateIdUseEnumTypeGen.valueOf(it.value) }

    abstract fun genToCoreResp(getInstalledCertificateIdsResp: GetInstalledCertificateIdsRespGen): GetInstalledCertificateIdsResp

    fun coreToGenReq(getInstalledCertificateIdsReq: GetInstalledCertificateIdsReq) : GetInstalledCertificateIdsReqGen =
        GetInstalledCertificateIdsReqGen(getInstalledCertificateIdsReq.certificateType?.let { getInstalledCertificateIdsCertificateType(it) })
}