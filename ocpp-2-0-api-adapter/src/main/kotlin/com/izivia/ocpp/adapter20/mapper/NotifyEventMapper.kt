package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.notifyevent.NotifyEventReq
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.notifyevent.NotifyEventReq as NotifyEventReqGen
import com.izivia.ocpp.api.model.notifyevent.NotifyEventResp as NotifyEventRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyEventMapper {

    fun genToCoreReq(notifyEventReq: NotifyEventReqGen?): NotifyEventReq

    fun coreToGenResp(notifyEventResp: NotifyEventResp): NotifyEventRespGen

}