package fr.simatix.cs.simulator.api

import fr.simatix.cs.simulator.api.model.HeartbeatRequest
import fr.simatix.cs.simulator.api.model.HeartbeatResponse

interface CSMSApi {

    fun heartbeat(request: HeartbeatRequest): HeartbeatResponse

}
