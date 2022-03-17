package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheReq as ClearCacheReqGen
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheResp as ClearCacheRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ClearCacheMapper {

    fun genToCoreResp(clearCacheResp: ClearCacheRespGen?): ClearCacheResp

    fun coreToGenReq(clearCacheReq: ClearCacheReq): ClearCacheReqGen

}