package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageReq as ClearDisplayMessageReqGen
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageResp as ClearDisplayMessageRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ClearDisplayMessageMapper {

    fun genToCoreResp(clearDisplayMessageResp: ClearDisplayMessageRespGen): ClearDisplayMessageResp

    fun coreToGenReq(clearDisplayMessageReq: ClearDisplayMessageReq): ClearDisplayMessageReqGen

}