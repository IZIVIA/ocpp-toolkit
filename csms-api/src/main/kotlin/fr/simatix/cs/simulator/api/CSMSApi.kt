package fr.simatix.cs.simulator.api

import fr.simatix.cs.simulator.api.model.HeartbeatReq
import fr.simatix.cs.simulator.api.model.HeartbeatResp
import fr.simatix.cs.simulator.api.model.OperationExecution
import fr.simatix.cs.simulator.api.model.RequestMetadata

interface CSMSApi {

    fun heartbeat(meta: RequestMetadata, request: HeartbeatReq): OperationExecution<HeartbeatReq, HeartbeatResp>

}
