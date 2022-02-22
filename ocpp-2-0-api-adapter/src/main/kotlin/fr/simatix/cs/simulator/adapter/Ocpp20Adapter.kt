package fr.simatix.cs.simulator.adapter

import fr.simatix.cs.simulator.adapter.mapper.HeartbeatMapper
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.transport.Transport
import org.mapstruct.factory.Mappers
import java.net.ConnectException
import fr.simatix.cs.simulator.api.model.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.HeartbeatResp as HeartbeatRespGen

class Ocpp20Adapter(transport: Transport) : CSMSApi {

    private val operations = ChargePointOperations.newChargePointOperations(transport)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(request: HeartbeatReqGen): HeartbeatRespGen {
        val mapper: HeartbeatMapper = Mappers.getMapper(HeartbeatMapper::class.java)
        val response = operations.heartbeat(mapper.genToCoreReq(request))
        return mapper.coreToGenResp(response)
    }
}