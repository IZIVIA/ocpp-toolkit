package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListReq as SendLocalListReqGen
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListResp as SendLocalListRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SendLocalListMapper {

    fun genToCoreResp(sendLocalListResp: SendLocalListRespGen?): SendLocalListResp

    fun coreToGenReq(sendLocalListReq: SendLocalListReq): SendLocalListReqGen

}