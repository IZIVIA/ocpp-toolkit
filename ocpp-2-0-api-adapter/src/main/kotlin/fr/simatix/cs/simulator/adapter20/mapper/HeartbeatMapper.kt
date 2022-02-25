package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatResp
import org.mapstruct.Mapper
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatResp as HeartbeatRespGen

@Mapper
interface HeartbeatMapper {

    fun genToCoreReq(heartbeatReq: HeartbeatReqGen?): HeartbeatReq

    fun coreToGenResp(heartbeatResp: HeartbeatResp?): HeartbeatRespGen
}