package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.unpublishfirmware.UnpublishFirmwareReq
import fr.simatix.cs.simulator.core20.model.unpublishfirmware.UnpublishFirmwareResp
import fr.simatix.cs.simulator.api.model.unpublishfirmware.UnpublishFirmwareReq as UnpublishFirmwareReqGen
import fr.simatix.cs.simulator.api.model.unpublishfirmware.UnpublishFirmwareResp as UnpublishFirmwareRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UnpublishFirmwareMapper {
    fun genToCoreResp(getLogResp: UnpublishFirmwareRespGen?): UnpublishFirmwareResp

    fun coreToGenReq(getLogReq: UnpublishFirmwareReq?): UnpublishFirmwareReqGen
}