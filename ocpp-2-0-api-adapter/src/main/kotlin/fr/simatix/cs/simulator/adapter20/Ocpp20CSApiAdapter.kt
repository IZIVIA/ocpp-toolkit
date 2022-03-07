package fr.simatix.cs.simulator.adapter20

import fr.simatix.cs.simulator.adapter20.mapper.ResetMapper
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.core20.CSMSOperations
import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp
import fr.simatix.cs.simulator.transport.Transport
import org.mapstruct.factory.Mappers
import fr.simatix.cs.simulator.api.model.reset.ResetReq as ResetReqGen
import fr.simatix.cs.simulator.api.model.reset.ResetResp as ResetRespGen

class Ocpp20CSApiAdapter(val transport: Transport): CSApi {

    private val operations = CSMSOperations.newCSMSOperations(transport)

    override fun connect() = transport.connect()

    override fun close() = transport.close()

    override fun reset(fn: (ResetReqGen) -> ResetRespGen) {
        val fn20 : (ResetReq) -> ResetResp = { req: ResetReq ->
            val mapper: ResetMapper = Mappers.getMapper(ResetMapper::class.java)
            mapper.genToCoreResp(fn(mapper.coreToGenReq(req)))
        }
        operations.reset(fn20)
    }
}