package fr.simatix.cs.simulator.adapter

import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.model.HeartbeatReq
import fr.simatix.cs.simulator.transport.Transport
import java.net.ConnectException
import fr.simatix.cs.simulator.api.model.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.HeartbeatResp as HeartbeatRespGen

class Ocpp16Adapter(transport: Transport) : CSMSApi {

    private val operations = ChargePointOperations.newChargePointOperations(transport)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(request: HeartbeatReqGen): HeartbeatRespGen {
        return HeartbeatRespGen(operations.heartbeat(HeartbeatReq()).currentTime)
    }
}