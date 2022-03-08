package fr.simatix.cs.simulator.adapter20

import fr.simatix.cs.simulator.adapter20.mapper.ResetMapper
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.core20.CSMSOperations
import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp
import fr.simatix.cs.simulator.transport.Transport
import org.mapstruct.factory.Mappers

class Ocpp20CSApiAdapter(transport: Transport, private val csApi: CSApi) {

    private val operations = CSMSOperations.newCSMSOperations(transport)

    init {
        reset()
    }

    private fun reset() {
        val fn20 : (ResetReq) -> ResetResp = { req: ResetReq ->
            val mapper: ResetMapper = Mappers.getMapper(ResetMapper::class.java)
            mapper.genToCoreResp(csApi.reset(mapper.coreToGenReq(req)))
        }
        operations.reset(fn20)
    }
}