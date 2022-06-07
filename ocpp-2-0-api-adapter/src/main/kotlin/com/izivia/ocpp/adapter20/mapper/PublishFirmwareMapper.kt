package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareResp as PublishFirmwareRespGen
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareReq
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareReq as PublishFirmwareReqGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PublishFirmwareMapper
{
    fun genToCoreResp(publishFirmwareResp: PublishFirmwareRespGen?): PublishFirmwareResp

    fun coreToGenReq(publishFirmwareReq: PublishFirmwareReq?): PublishFirmwareReqGen

}
