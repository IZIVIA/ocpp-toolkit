package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesReq as NotifyDisplayMessagesReqGen
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesResp as NotifyDisplayMessagesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyDisplayMessagesMapper {

    fun genToCoreReq(notifyDisplayMessagesReq: NotifyDisplayMessagesReqGen?): NotifyDisplayMessagesReq

    fun coreToGenResp(notifyDisplayMessagesResp: NotifyDisplayMessagesResp?): NotifyDisplayMessagesRespGen
}
