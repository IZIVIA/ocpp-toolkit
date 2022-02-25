package fr.simatix.cs.simulator.adapter16

import fr.simatix.cs.simulator.adapter16.mapper.AuthorizeMapper
import fr.simatix.cs.simulator.adapter16.mapper.HeartbeatMapper
import fr.simatix.cs.simulator.adapter16.mapper.MeterValuesMapper
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api.model.*
import fr.simatix.cs.simulator.api.model.RequestStatus
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.transport.Transport
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import java.net.ConnectException
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeResp as AuthorizeRespGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatResp as HeartbeatRespGen

class Ocpp16Adapter(transport: Transport) : CSMSApi {

    companion object {
        private val logger = LoggerFactory.getLogger(Ocpp16Adapter::class.java)
    }

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

    override fun meterValues(
        meta: RequestMetadata,
        request: MeterValuesReq
    ): OperationExecution<MeterValuesReq, MeterValuesResp> {
        val mapper: MeterValuesMapper = Mappers.getMapper(MeterValuesMapper::class.java)
        return try {
            val response = operations.meterValues(meta, mapper.genToCoreReq(request))
            OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
        } catch (e: IllegalStateException){
            logger.warn(e.message)
            OperationExecution(ExecutionMetadata(meta, RequestStatus.NOT_SEND), request, MeterValuesResp())
        }
    }
}