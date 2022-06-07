package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.clearedcharginglimit.ClearedChargingLimitResp as ClearedChargingLimitRespGen
import com.izivia.ocpp.api.model.clearedcharginglimit.ClearedChargingLimitReq as ClearedChargingLimitReqGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ClearedChargingLimitMapper {

    fun coreToGenResp(clearedChargingLimitResp: ClearedChargingLimitResp?): ClearedChargingLimitRespGen

    fun genToCoreReq(clearedChargingLimitReq: ClearedChargingLimitReqGen): ClearedChargingLimitReq
}
