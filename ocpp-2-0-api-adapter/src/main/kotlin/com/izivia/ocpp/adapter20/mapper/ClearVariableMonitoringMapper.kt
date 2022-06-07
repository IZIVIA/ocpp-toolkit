package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringReq as ClearVariableMonitoringReqGen
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringResp as ClearVariableMonitoringRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ClearVariableMonitoringMapper {

    fun genToCoreResp(clearVariableMonitoringResp: ClearVariableMonitoringRespGen?): ClearVariableMonitoringResp

    fun coreToGenReq(clearVariableMonitoringReq: ClearVariableMonitoringReq?): ClearVariableMonitoringReqGen
}