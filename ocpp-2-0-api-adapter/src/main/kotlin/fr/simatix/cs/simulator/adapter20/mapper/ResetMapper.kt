package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.reset.ResetReq as ResetReqGen
import fr.simatix.cs.simulator.api.model.reset.ResetResp as ResetRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class ResetMapper {

    abstract fun genToCoreResp(resetResp: ResetRespGen): ResetResp

    abstract fun coreToGenReq(resetReq: ResetReq): ResetReqGen

}
