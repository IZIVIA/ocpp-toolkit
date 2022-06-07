package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.updatefirmware.FirmwareType
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareReq as UpdateFirmwareReqGen
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareResp as UpdateFirmwareRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class UpdateFirmwareMapper {

    fun coreToGenReq(updateFirmwareReq: UpdateFirmwareReq): UpdateFirmwareReqGen{
        val firmware: FirmwareType = FirmwareType(
            location = updateFirmwareReq.location,
            retrieveDateTime = updateFirmwareReq.retrieveDate
        )

        return UpdateFirmwareReqGen(retries = updateFirmwareReq.retries, retryInterval = updateFirmwareReq.retryInterval,
            requestId = 0, firmware = firmware)
    }

    abstract fun genToCoreResp(updateFirmwareResp: UpdateFirmwareRespGen?): UpdateFirmwareResp
}
