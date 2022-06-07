package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationReq as LogStatusNotificationReqGen
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationResp as LogStatusNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface LogStatusNotificationMapper {

    fun genToCoreReq(logStatusNotificationReq: LogStatusNotificationReqGen?): LogStatusNotificationReq

    fun coreToGenResp(logStatusNotificationResp: LogStatusNotificationResp?): LogStatusNotificationRespGen
}