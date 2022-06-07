package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import com.izivia.ocpp.api.model.heartbeat.HeartbeatResp as HeartbeatRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface HeartbeatMapper {

    fun genToCoreReq(heartbeatReq: HeartbeatReqGen?): HeartbeatReq

    fun coreToGenResp(heartbeatResp: HeartbeatResp?): HeartbeatRespGen
}