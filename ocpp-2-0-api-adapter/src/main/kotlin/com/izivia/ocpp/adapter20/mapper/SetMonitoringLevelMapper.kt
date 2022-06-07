package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.api.model.setmonitoringlevel.SetMonitoringLevelReq as SetMonitoringLevelReqGen
import com.izivia.ocpp.api.model.setmonitoringlevel.SetMonitoringLevelResp as SetMonitoringLevelRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetMonitoringLevelMapper {
    fun genToCoreResp(getLogResp: SetMonitoringLevelRespGen?): SetMonitoringLevelResp

    fun coreToGenReq(getLogReq: SetMonitoringLevelReq?): SetMonitoringLevelReqGen
}