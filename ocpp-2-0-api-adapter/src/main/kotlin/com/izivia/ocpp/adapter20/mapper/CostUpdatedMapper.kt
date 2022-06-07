package com.izivia.ocpp.adapter20.mapper

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

import com.izivia.ocpp.core20.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.api.model.costupdated.CostUpdatedReq as CostUpdatedReqGen
import com.izivia.ocpp.api.model.costupdated.CostUpdatedResp as CostUpdatedRespGen
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CostUpdatedMapper {

    fun genToCoreResp(costUpdatedResp: CostUpdatedRespGen): CostUpdatedResp
    fun coreToGenReq(costUpdatedReq: CostUpdatedReq): CostUpdatedReqGen
}
