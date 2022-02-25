package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatResp as HeartbeatRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface HeartbeatMapper {

    fun genToCoreReq(heartbeatReq: HeartbeatReqGen?): HeartbeatReq

    fun coreToGenResp(heartbeatResp: HeartbeatResp?): HeartbeatRespGen
}