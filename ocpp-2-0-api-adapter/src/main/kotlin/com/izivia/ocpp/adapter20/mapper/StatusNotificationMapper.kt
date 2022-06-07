package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationReq as StatusNotificationReqGen
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationResp as StatusNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface StatusNotificationMapper {

    fun genToCoreReq(statusReq: StatusNotificationReqGen?): StatusNotificationReq

    fun coreToGenResp(statusResp: StatusNotificationResp?): StatusNotificationRespGen
}