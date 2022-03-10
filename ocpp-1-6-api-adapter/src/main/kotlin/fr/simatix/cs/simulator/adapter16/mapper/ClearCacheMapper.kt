package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheReq as ClearCacheReqGen
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheResp as ClearCacheRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class ClearCacheMapper {

    abstract fun genToCoreResp(clearCacheResp: ClearCacheRespGen?): ClearCacheResp

    abstract fun coreToGenReq(clearCacheReq: ClearCacheReq): ClearCacheReqGen

}