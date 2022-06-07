package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringResp
import com.izivia.ocpp.api.model.setvariablemonitoring.SetVariableMonitoringReq as SetVariableMonitoringReqGen
import com.izivia.ocpp.api.model.setvariablemonitoring.SetVariableMonitoringResp as SetVariableMonitoringRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetVariableMonitoringMapper {
    fun genToCoreResp(setVariableMonitoringResp: SetVariableMonitoringRespGen?): SetVariableMonitoringResp

    fun coreToGenReq(setVariableMonitoringReq: SetVariableMonitoringReq?): SetVariableMonitoringReqGen
}