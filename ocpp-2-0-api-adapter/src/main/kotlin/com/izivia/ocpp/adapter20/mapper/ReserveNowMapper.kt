package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core20.model.reservenow.ReserveNowResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.reservenow.ReserveNowReq as ReserveNowReqGen
import com.izivia.ocpp.api.model.reservenow.ReserveNowResp as ReserveNowRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ReserveNowMapper {

    fun genToCoreResp(reserveNowResp: ReserveNowRespGen): ReserveNowResp

    fun coreToGenReq(reserveNowReq: ReserveNowReq): ReserveNowReqGen

}