package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.core20.model.triggermessage.TriggerMessageResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.triggermessage.TriggerMessageReq as TriggerMessageReqGen
import fr.simatix.cs.simulator.api.model.triggermessage.TriggerMessageResp as TriggerMessageRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface TriggerMessageMapper {

    fun genToCoreResp(triggerMessageResp: TriggerMessageRespGen?): TriggerMessageResp

    fun coreToGenReq(triggerMessageReq: TriggerMessageReq?): TriggerMessageReqGen
}