package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileReq
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileResp
import org.mapstruct.Mapper
import com.izivia.ocpp.api.model.setnetworkprofile.SetNetworkProfileReq as SetNetworkProfileReqGen
import com.izivia.ocpp.api.model.setnetworkprofile.SetNetworkProfileResp as SetNetworkProfileRespGen
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetNetworkProfileMapper
{
    fun genToCoreResp(getLogResp: SetNetworkProfileRespGen?): SetNetworkProfileResp

    fun coreToGenReq(getLogReq: SetNetworkProfileReq?): SetNetworkProfileReqGen
}