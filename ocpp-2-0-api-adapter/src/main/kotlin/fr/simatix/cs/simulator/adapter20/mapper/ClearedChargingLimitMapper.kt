package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import fr.simatix.cs.simulator.core20.model.clearedcharginglimit.ClearedChargingLimitResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitResp as ClearedChargingLimitRespGen
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitReq as ClearedChargingLimitReqGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ClearedChargingLimitMapper {

    fun coreToGenResp(clearedChargingLimitResp: ClearedChargingLimitResp?): ClearedChargingLimitRespGen

    fun genToCoreReq(clearedChargingLimitReq: ClearedChargingLimitReqGen): ClearedChargingLimitReq
}
