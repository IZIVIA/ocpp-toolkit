package fr.simatix.cs.simulator.core20.impl

import fr.simatix.cs.simulator.api.model.ExecutionMetadata
import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.model.CoreExecution
import fr.simatix.cs.simulator.core20.model.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.HeartbeatResp
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.sendMessage
import kotlinx.datetime.Clock
import java.net.ConnectException

class RealChargePointOperations(private val client: Transport) : ChargePointOperations {

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(
        meta: RequestMetadata,
        request: HeartbeatReq
    ): CoreExecution<HeartbeatResp> {
        val requestTime = Clock.System.now()
        val response = client.sendMessage<HeartbeatReq, HeartbeatResp>("Heartbeat", request)
        val responseTime = Clock.System.now()
        return CoreExecution(ExecutionMetadata(meta, requestTime, responseTime), response)
    }
}
