package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.core16.model.common.MeterValue
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.metervalues.MeterValuesReq as MeterValuesReqGen
import com.izivia.ocpp.api.model.metervalues.MeterValuesResp as MeterValuesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class MeterValuesMapper {

    @Throws(IllegalStateException::class)
    fun genToCoreReq(meterValuesReq: MeterValuesReqGen): MeterValuesReq {
        val connectorId: Int = meterValuesReq.evseId
        val meterValue = meterValuesReq.meterValue
        val meterValueList = meterValue.map { (s, t) ->
            MeterValue(sampledValue = s.map { CommonMapper.convertSampledValue(it) }, timestamp = t)
        }
        return MeterValuesReq(connectorId = connectorId, meterValue = meterValueList)
    }

    abstract fun coreToGenResp(meterValuesResp: MeterValuesResp?): MeterValuesRespGen
}