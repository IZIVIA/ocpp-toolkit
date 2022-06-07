package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesReq as GetDisplayMessagesReqGen
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesResp as GetDisplayMessagesRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetDisplayMessagesMapper {
    fun genToCoreResp(getDisplayMessagesResp: GetDisplayMessagesRespGen?): GetDisplayMessagesResp

    fun coreToGenReq(getDisplayMessagesReq: GetDisplayMessagesReq?): GetDisplayMessagesReqGen
}