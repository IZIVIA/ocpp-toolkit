package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareReq as UpdateFirmwareReqGen
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareResp as UpdateFirmwareRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UpdateFirmwareMapper {

        fun genToCoreResp(updateFirmwareResp: UpdateFirmwareRespGen): UpdateFirmwareResp

        fun coreToGenReq(updateFirmwareReq: UpdateFirmwareReq): UpdateFirmwareReqGen
}
