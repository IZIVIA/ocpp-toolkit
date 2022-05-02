package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.setmonitoringbase.SetMonitoringBaseReq
import fr.simatix.cs.simulator.core20.model.setmonitoringbase.SetMonitoringBaseResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.setmonitoringbase.SetMonitoringBaseResp as SetMonitoringBaseRespGen
import fr.simatix.cs.simulator.api.model.setmonitoringbase.SetMonitoringBaseReq as SetMonitoringBaseReqGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetMonitoringBaseMapper {
    fun genToCoreResp(setMonitoringResp: SetMonitoringBaseRespGen?): SetMonitoringBaseResp

    fun coreToGenReq(setMonitoringReq: SetMonitoringBaseReq?): SetMonitoringBaseReqGen
}