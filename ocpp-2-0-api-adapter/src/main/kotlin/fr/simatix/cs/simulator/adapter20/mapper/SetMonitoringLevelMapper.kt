package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import fr.simatix.cs.simulator.core20.model.setmonitoringlevel.SetMonitoringLevelResp
import fr.simatix.cs.simulator.api.model.setmonitoringlevel.SetMonitoringLevelReq as SetMonitoringLevelReqGen
import fr.simatix.cs.simulator.api.model.setmonitoringlevel.SetMonitoringLevelResp as SetMonitoringLevelRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetMonitoringLevelMapper {
    fun genToCoreResp(getLogResp: SetMonitoringLevelRespGen?): SetMonitoringLevelResp

    fun coreToGenReq(getLogReq: SetMonitoringLevelReq?): SetMonitoringLevelReqGen
}