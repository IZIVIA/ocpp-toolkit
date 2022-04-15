package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.securityeventnotification.SecurityEventNotificationReq
import fr.simatix.cs.simulator.core20.model.securityeventnotification.SecurityEventNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.securityeventnotification.SecurityEventNotificationReq as SecurityEventNotificationReqGen
import fr.simatix.cs.simulator.api.model.securityeventnotification.SecurityEventNotificationResp as SecurityEventNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SecurityEventNotificationMapper {

    fun genToCoreReq(securityEventNotificationReq: SecurityEventNotificationReqGen?): SecurityEventNotificationReq

    fun coreToGenResp(securityEventNotificationResp: SecurityEventNotificationResp?): SecurityEventNotificationRespGen

}