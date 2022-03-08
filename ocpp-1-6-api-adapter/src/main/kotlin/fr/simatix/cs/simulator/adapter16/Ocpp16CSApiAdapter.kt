package fr.simatix.cs.simulator.adapter16

import fr.simatix.cs.simulator.adapter16.mapper.ResetMapper
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.core16.CSMSOperations
import fr.simatix.cs.simulator.core16.model.reset.ResetReq
import fr.simatix.cs.simulator.core16.model.reset.ResetResp
import fr.simatix.cs.simulator.transport.Transport
import org.mapstruct.factory.Mappers

class Ocpp16CSApiAdapter(transport: Transport, private val csApi: CSApi){

    private val operations = CSMSOperations.newCSMSOperations(transport)

    init {
        reset()
    }

    private fun reset() {
        val fn16 : (ResetReq) -> ResetResp = { req: ResetReq ->
            val mapper: ResetMapper = Mappers.getMapper(ResetMapper::class.java)
            mapper.genToCoreResp(csApi.reset(mapper.coreToGenReq(req)))
        }
        operations.reset(fn16)
    }
}