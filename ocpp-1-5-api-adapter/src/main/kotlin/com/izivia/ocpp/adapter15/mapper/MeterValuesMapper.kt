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

    @Throws(IllegalStateException::class)
    fun genToCoreReq(meterValuesReq: MeterValuesReqGen): MeterValuesReq {
        val meterValueList = mutableListOf<MeterValue>()
        val connectorId: Int = meterValuesReq.evseId
        val meterValueTypes = meterValuesReq.meterValue
        meterValueTypes.forEach { (sampledValue, timestamp) ->
            sampledValue.map { meterValueList.add(CommonMapper.convertToMeterValue(it, timestamp)) }
        }
        return MeterValuesReq(connectorId = connectorId, values = meterValueList)
    }

    abstract fun coreToGenResp(meterValuesResp: MeterValuesResp?): MeterValuesRespGen
}
