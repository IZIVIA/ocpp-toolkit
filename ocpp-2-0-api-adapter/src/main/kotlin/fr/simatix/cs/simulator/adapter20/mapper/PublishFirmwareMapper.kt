package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.publishfirmware.PublishFirmwareResp
import fr.simatix.cs.simulator.api.model.publishfirmware.PublishFirmwareResp as PublishFirmwareRespGen
import fr.simatix.cs.simulator.core20.model.publishfirmware.PublishFirmwareReq
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.publishfirmware.PublishFirmwareReq as PublishFirmwareReqGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PublishFirmwareMapper
{
    fun genToCoreResp(publishFirmwareResp: PublishFirmwareRespGen?): PublishFirmwareResp

    fun coreToGenReq(publishFirmwareReq: PublishFirmwareReq?): PublishFirmwareReqGen

}
