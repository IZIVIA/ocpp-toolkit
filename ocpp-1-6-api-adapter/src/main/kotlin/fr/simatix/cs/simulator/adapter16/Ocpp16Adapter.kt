package fr.simatix.cs.simulator.adapter16

import fr.simatix.cs.simulator.adapter16.mapper.AuthorizeMapper
import fr.simatix.cs.simulator.adapter16.mapper.HeartbeatMapper
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api.model.OperationExecution
import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.transport.Transport
import org.mapstruct.factory.Mappers
import java.net.ConnectException
import fr.simatix.cs.simulator.api.model.AuthorizeReq as AuthorizeReqGen
import fr.simatix.cs.simulator.api.model.AuthorizeResp as AuthorizeRespGen
import fr.simatix.cs.simulator.api.model.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.HeartbeatResp as HeartbeatRespGen

class Ocpp16Adapter(transport: Transport) : CSMSApi {

    private val operations = ChargePointOperations.newChargePointOperations(transport)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(
        meta: RequestMetadata,
        request: HeartbeatReqGen
    ): OperationExecution<HeartbeatReqGen, HeartbeatRespGen> {
        val mapper: HeartbeatMapper = Mappers.getMapper(HeartbeatMapper::class.java)
        val response = operations.heartbeat(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun authorize(
        meta: RequestMetadata,
        request: AuthorizeReqGen
    ): OperationExecution<AuthorizeReqGen, AuthorizeRespGen> {
        val mapper: AuthorizeMapper = Mappers.getMapper(AuthorizeMapper::class.java)
        val response = operations.authorize(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }
}