package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageReq as TriggerMessageReqGen
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageResp as TriggerMessageRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface TriggerMessageMapper {

    fun genToCoreResp(triggerMessageResp: TriggerMessageRespGen?): TriggerMessageResp

    fun coreToGenReq(triggerMessageReq: TriggerMessageReq?): TriggerMessageReqGen
}