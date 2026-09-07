package com.izivia.ocpp.adapter15.mapper

import com.izivia.ocpp.core15.model.common.MeterValue
import com.izivia.ocpp.core15.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core15.model.metervalues.MeterValuesResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.metervalues.MeterValuesReq as MeterValuesReqGen
import com.izivia.ocpp.api.model.metervalues.MeterValuesResp as MeterValuesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class MeterValuesMapper {

    @Throws(IllegalArgumentException::class)
    fun genToCoreReq(meterValuesReq: MeterValuesReqGen): MeterValuesReq {
        val connectorId: Int = meterValuesReq.connectorId ?: meterValuesReq.evseId
        val meterValue = meterValuesReq.meterValue
        val transactionId = meterValuesReq.transactionId?.toInt()
        val meterValueList = meterValue.map { (s, t) ->
            MeterValue(value = s.map { CommonMapper.convertSampledValue(it) }, timestamp = t)
        }
        return MeterValuesReq(connectorId = connectorId, values = meterValueList, transactionId = transactionId)
    }

    abstract fun coreToGenResp(meterValuesResp: MeterValuesResp?): MeterValuesRespGen
}
