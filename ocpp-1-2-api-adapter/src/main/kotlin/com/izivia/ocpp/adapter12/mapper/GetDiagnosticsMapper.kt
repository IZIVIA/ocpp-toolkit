package com.izivia.ocpp.adapter12.mapper

import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getdiagnostics.GetDiagnosticsReq as GetDiagnosticsReqGen
import com.izivia.ocpp.api.model.getdiagnostics.GetDiagnosticsResp as GetDiagnosticsRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetDiagnosticsMapper {

    // Hand-written: every target field is optional, so MapStruct would use the no-arg constructor.
    fun genToCoreResp(getDiagnosticsResp: GetDiagnosticsRespGen?): GetDiagnosticsResp =
        GetDiagnosticsResp(getDiagnosticsResp?.fileName)

    abstract fun coreToGenReq(getDiagnosticsReq: GetDiagnosticsReq): GetDiagnosticsReqGen
}
