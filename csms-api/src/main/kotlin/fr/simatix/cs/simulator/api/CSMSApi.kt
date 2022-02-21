package fr.simatix.cs.simulator.api

import fr.simatix.cs.simulator.api.model.HeartbeatReq
import fr.simatix.cs.simulator.api.model.HeartbeatResp

interface CSMSApi {

    fun heartbeat(request: HeartbeatReq): HeartbeatResp

}
