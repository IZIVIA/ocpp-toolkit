package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq as StatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationResp as StatusNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface StatusNotificationMapper {

    fun genToCoreReq(statusReq: StatusNotificationReqGen?): StatusNotificationReq

    fun coreToGenResp(statusResp: StatusNotificationResp?): StatusNotificationRespGen
}