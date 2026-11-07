package com.izivia.ocpp.adapter15.mapper

import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getdiagnostics.GetDiagnosticsReq as GetDiagnosticsReqGen
import com.izivia.ocpp.api.model.getdiagnostics.GetDiagnosticsResp as GetDiagnosticsRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetDiagnosticsMapper {

    // Hand-written: every target field is optional, so MapStruct would use the no-arg constructor.
    fun genToCoreResp(getDiagnosticsResp: GetDiagnosticsRespGen?): GetDiagnosticsResp =
        GetDiagnosticsResp(getDiagnosticsResp?.fileName)

    // Safe to generate only while the generic GetDiagnosticsReq.location has no default value: that is
    // what keeps MapStruct on the full constructor, and no reporting policy would catch a switch to
    // the no-arg one. The MapperTest is the safety net.
    abstract fun coreToGenReq(getDiagnosticsReq: GetDiagnosticsReq): GetDiagnosticsReqGen
}
