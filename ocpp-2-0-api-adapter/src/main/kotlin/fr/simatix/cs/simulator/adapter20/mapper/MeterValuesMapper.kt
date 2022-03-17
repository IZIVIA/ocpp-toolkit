package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq as MeterValuesReqGen
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesResp as MeterValuesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
interface MeterValuesMapper {

    @Mapping(target = "meterValue", source = "meterValue", qualifiedByName = ["meterValue"])
    fun genToCoreReq(meterValuesReq: MeterValuesReqGen?): MeterValuesReq

    fun coreToGenResp(meterValuesResp: MeterValuesResp?): MeterValuesRespGen
}