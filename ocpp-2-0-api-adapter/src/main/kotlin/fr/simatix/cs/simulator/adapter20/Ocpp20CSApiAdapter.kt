package fr.simatix.cs.simulator.adapter20

import fr.simatix.cs.simulator.adapter20.mapper.ResetMapper
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.core20.CSMSOperations
import fr.simatix.cs.simulator.core20.OperationsHandler
import fr.simatix.cs.simulator.core20.impl.RealCSMSOperations
import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp
import fr.simatix.cs.simulator.core20.model.reset.enumeration.ResetEnumType
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.transport.Transport
import org.mapstruct.factory.Mappers

class Ocpp20CSApiAdapter(transport: Transport, val csApi: CSApi) {
    private val handlers: OperationsHandler = object : OperationsHandler {
        override val reset: (ResetReq) -> ResetResp = { req: ResetReq ->
            val meta = RequestMetadata("")
            val mapper: ResetMapper = Mappers.getMapper(ResetMapper::class.java)
            mapper.genToCoreResp(csApi.reset(meta, mapper.coreToGenReq(req)).response)
        }
    }
    private val csmsOperations: CSMSOperations = RealCSMSOperations(transport,handlers)

    init {
        val meta = RequestMetadata("")
        csmsOperations.reset(meta, ResetReq(ResetEnumType.Immediate))
    }

}