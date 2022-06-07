package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareReq
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareResp
import com.izivia.ocpp.api.model.unpublishfirmware.UnpublishFirmwareReq as UnpublishFirmwareReqGen
import com.izivia.ocpp.api.model.unpublishfirmware.UnpublishFirmwareResp as UnpublishFirmwareRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UnpublishFirmwareMapper {
    fun genToCoreResp(getLogResp: UnpublishFirmwareRespGen?): UnpublishFirmwareResp

    fun coreToGenReq(getLogReq: UnpublishFirmwareReq?): UnpublishFirmwareReqGen
}