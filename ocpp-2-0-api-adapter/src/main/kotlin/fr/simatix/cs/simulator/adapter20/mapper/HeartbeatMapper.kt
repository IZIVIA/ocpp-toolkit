package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.HeartbeatResp
import org.mapstruct.Mapper
import fr.simatix.cs.simulator.api.model.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.HeartbeatResp as HeartbeatRespGen

@Mapper
interface HeartbeatMapper {

    fun genToCoreReq(heartbeatReq: HeartbeatReqGen?): HeartbeatReq

    fun coreToGenResp(heartbeatResp: HeartbeatResp?): HeartbeatRespGen
}