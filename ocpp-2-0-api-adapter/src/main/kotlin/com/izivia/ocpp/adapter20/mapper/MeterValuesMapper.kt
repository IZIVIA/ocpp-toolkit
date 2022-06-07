package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core20.model.metervalues.MeterValuesResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.metervalues.MeterValuesReq as MeterValuesReqGen
import com.izivia.ocpp.api.model.metervalues.MeterValuesResp as MeterValuesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
interface MeterValuesMapper {

    @Mapping(target = "meterValue", source = "meterValue", qualifiedByName = ["meterValue"])
    fun genToCoreReq(meterValuesReq: MeterValuesReqGen?): MeterValuesReq

    fun coreToGenResp(meterValuesResp: MeterValuesResp?): MeterValuesRespGen
}