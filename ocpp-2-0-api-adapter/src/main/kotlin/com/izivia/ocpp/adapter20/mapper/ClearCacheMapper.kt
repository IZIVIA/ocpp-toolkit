package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core20.model.clearcache.ClearCacheResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.clearcache.ClearCacheReq as ClearCacheReqGen
import com.izivia.ocpp.api.model.clearcache.ClearCacheResp as ClearCacheRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ClearCacheMapper {

    fun genToCoreResp(clearCacheResp: ClearCacheRespGen?): ClearCacheResp

    fun coreToGenReq(clearCacheReq: ClearCacheReq): ClearCacheReqGen

}