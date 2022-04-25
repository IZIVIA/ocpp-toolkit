package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import fr.simatix.cs.simulator.core20.model.setvariablemonitoring.SetVariableMonitoringResp
import fr.simatix.cs.simulator.api.model.setvariablemonitoring.SetVariableMonitoringReq as SetVariableMonitoringReqGen
import fr.simatix.cs.simulator.api.model.setvariablemonitoring.SetVariableMonitoringResp as SetVariableMonitoringRespGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetVariableMonitoringMapper {
    fun genToCoreResp(setVariableMonitoringResp: SetVariableMonitoringRespGen?): SetVariableMonitoringResp

    fun coreToGenReq(setVariableMonitoringReq: SetVariableMonitoringReq?): SetVariableMonitoringReqGen
}