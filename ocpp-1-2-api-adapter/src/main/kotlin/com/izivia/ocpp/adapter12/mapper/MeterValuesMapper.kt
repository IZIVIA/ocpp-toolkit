package com.izivia.ocpp.adapter12.mapper

import com.izivia.ocpp.core12.model.common.MeterValue
import com.izivia.ocpp.core12.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core12.model.metervalues.MeterValuesResp
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
        val meterValueList = meterValue.map { (s, t) ->
            MeterValue(
                value = CommonMapper.singleEnergyRegister(s)
                    ?: throw IllegalArgumentException("A meter value MUST have one EnergyActiveImportRegister sampled value in OCPP 1.2"),
                timestamp = t
            )
        }
        return MeterValuesReq(connectorId = connectorId, values = meterValueList)
    }

    abstract fun coreToGenResp(meterValuesResp: MeterValuesResp?): MeterValuesRespGen
}
