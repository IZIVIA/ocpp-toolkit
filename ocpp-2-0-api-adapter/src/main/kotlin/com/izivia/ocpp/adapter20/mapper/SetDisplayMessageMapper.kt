package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageResp
import com.izivia.ocpp.api.model.setdisplaymessage.SetDisplayMessageResp as SetDisplayMessageRespGen

import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageReq
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.setdisplaymessage.SetDisplayMessageReq as SetDisplayMessageReqGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetDisplayMessageMapper {
    fun genToCoreResp(setDisplayMessageResp: SetDisplayMessageRespGen?): SetDisplayMessageResp

    fun coreToGenReq(setDisplayMessageReq: SetDisplayMessageReq?): SetDisplayMessageReqGen
}