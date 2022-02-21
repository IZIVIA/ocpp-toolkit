package fr.simatix.cs.simulator.adapter

import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.model.HeartbeatReq
import fr.simatix.cs.simulator.transport.Transport
import java.net.ConnectException
import fr.simatix.cs.simulator.api.model.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.HeartbeatResp as HeartbeatRespGen

class Ocpp20Adapter(transport: Transport) : CSMSApi {

    private val operations = ChargePointOperations.newChargePointOperations(transport)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(request: HeartbeatReqGen): HeartbeatRespGen {
        val time = operations.heartbeat(HeartbeatReq())
        return HeartbeatRespGen(time.currentTime)
    }
}