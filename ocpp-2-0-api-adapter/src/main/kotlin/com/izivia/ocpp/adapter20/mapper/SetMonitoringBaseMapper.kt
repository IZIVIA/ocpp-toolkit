package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.setmonitoringbase.SetMonitoringBaseResp as SetMonitoringBaseRespGen
import com.izivia.ocpp.api.model.setmonitoringbase.SetMonitoringBaseReq as SetMonitoringBaseReqGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetMonitoringBaseMapper {
    fun genToCoreResp(setMonitoringResp: SetMonitoringBaseRespGen?): SetMonitoringBaseResp

    fun coreToGenReq(setMonitoringReq: SetMonitoringBaseReq?): SetMonitoringBaseReqGen
}