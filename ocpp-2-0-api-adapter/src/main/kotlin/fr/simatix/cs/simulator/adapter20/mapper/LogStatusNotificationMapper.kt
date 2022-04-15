package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.logstatusnotification.LogStatusNotificationReq
import fr.simatix.cs.simulator.core20.model.logstatusnotification.LogStatusNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationReq as LogStatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationResp as LogStatusNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface LogStatusNotificationMapper {

    fun genToCoreReq(logStatusNotificationReq: LogStatusNotificationReqGen?): LogStatusNotificationReq

    fun coreToGenResp(logStatusNotificationResp: LogStatusNotificationResp?): LogStatusNotificationRespGen
}