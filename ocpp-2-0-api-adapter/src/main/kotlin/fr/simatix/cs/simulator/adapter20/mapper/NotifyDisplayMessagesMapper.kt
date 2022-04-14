package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.NotifyDisplayMessagesReq as NotifyDisplayMessagesReqGen
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.NotifyDisplayMessagesResp as NotifyDisplayMessagesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyDisplayMessagesMapper {

    fun genToCoreReq(notifyDisplayMessagesReq: NotifyDisplayMessagesReqGen?): NotifyDisplayMessagesReq

    fun coreToGenResp(notifyDisplayMessagesResp: NotifyDisplayMessagesResp?): NotifyDisplayMessagesRespGen
}
