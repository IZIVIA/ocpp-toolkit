package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationReq as SecurityEventNotificationReqGen
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationResp as SecurityEventNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SecurityEventNotificationMapper {

    fun genToCoreReq(securityEventNotificationReq: SecurityEventNotificationReqGen?): SecurityEventNotificationReq

    fun coreToGenResp(securityEventNotificationResp: SecurityEventNotificationResp?): SecurityEventNotificationRespGen

}