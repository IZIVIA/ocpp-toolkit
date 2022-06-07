package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReqGen
import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationResp as FirmwareStatusNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface FirmwareStatusNotificationMapper {

    fun genToCoreReq(statusReq: FirmwareStatusNotificationReqGen?): FirmwareStatusNotificationReq

    fun coreToGenResp(statusResp: FirmwareStatusNotificationResp?): FirmwareStatusNotificationRespGen
}