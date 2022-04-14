package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.notifyevent.NotifyEventReq
import fr.simatix.cs.simulator.core20.model.notifyevent.NotifyEventResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventReq as NotifyEventReqGen
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventResp as NotifyEventRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyEventMapper {

    fun genToCoreReq(notifyEventReq: NotifyEventReqGen?): NotifyEventReq

    fun coreToGenResp(notifyEventResp: NotifyEventResp): NotifyEventRespGen

}