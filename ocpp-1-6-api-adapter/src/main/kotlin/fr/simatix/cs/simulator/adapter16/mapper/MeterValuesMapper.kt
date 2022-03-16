package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.core16.model.common.MeterValue
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq as MeterValuesReqGen
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesResp as MeterValuesRespGen

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