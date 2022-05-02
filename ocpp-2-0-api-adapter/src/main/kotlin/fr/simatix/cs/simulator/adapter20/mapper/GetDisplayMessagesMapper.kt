package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.getdisplaymessages.GetDisplayMessagesReq
import fr.simatix.cs.simulator.core20.model.getdisplaymessages.GetDisplayMessagesResp
import fr.simatix.cs.simulator.api.model.getdisplaymessages.GetDisplayMessagesReq as GetDisplayMessagesReqGen
import fr.simatix.cs.simulator.api.model.getdisplaymessages.GetDisplayMessagesResp as GetDisplayMessagesRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetDisplayMessagesMapper {
    fun genToCoreResp(getDisplayMessagesResp: GetDisplayMessagesRespGen?): GetDisplayMessagesResp

    fun coreToGenReq(getDisplayMessagesReq: GetDisplayMessagesReq?): GetDisplayMessagesReqGen
}