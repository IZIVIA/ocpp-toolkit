package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.reset.enumeration.ResetEnumType
import com.izivia.ocpp.api.model.reset.enumeration.ResetStatusEnumType
import com.izivia.ocpp.core16.model.reset.ResetReq
import com.izivia.ocpp.core16.model.reset.ResetResp
import com.izivia.ocpp.core16.model.reset.enumeration.ResetStatus
import com.izivia.ocpp.core16.model.reset.enumeration.ResetType
import com.izivia.ocpp.api.model.reset.ResetResp as ResetRespGen
import com.izivia.ocpp.api.model.reset.ResetReq as ResetReqGen
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class ResetMapper {

    @Named("convertResetStatus")
    fun convertResetStatus(status: ResetStatusEnumType): ResetStatus =
        when (status) {
            ResetStatusEnumType.Scheduled -> ResetStatus.Accepted
            else -> ResetStatus.valueOf(status.name)
        }

    @Named("convertResetType")
    fun convertResetType(type: ResetType): ResetEnumType =
        when (type) {
            ResetType.Hard -> ResetEnumType.Immediate
            ResetType.Soft -> ResetEnumType.OnIdle
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertResetStatus"])
    abstract fun genToCoreResp(resetResp: ResetRespGen): ResetResp

    @Mapping(target = "type", source = "type", qualifiedByName = ["convertResetType"])
    abstract fun coreToGenReq(resetReq: ResetReq): ResetReqGen

}
