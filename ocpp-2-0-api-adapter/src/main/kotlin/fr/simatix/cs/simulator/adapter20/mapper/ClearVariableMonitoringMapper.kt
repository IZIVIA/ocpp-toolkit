package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.clearvariablemonitoring.ClearVariableMonitoringReq
import fr.simatix.cs.simulator.core20.model.clearvariablemonitoring.ClearVariableMonitoringResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.clearvariablemonitoring.ClearVariableMonitoringReq as ClearVariableMonitoringReqGen
import fr.simatix.cs.simulator.api.model.clearvariablemonitoring.ClearVariableMonitoringResp as ClearVariableMonitoringRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ClearVariableMonitoringMapper {

    fun genToCoreResp(clearVariableMonitoringResp: ClearVariableMonitoringRespGen?): ClearVariableMonitoringResp

    fun coreToGenReq(clearVariableMonitoringReq: ClearVariableMonitoringReq?): ClearVariableMonitoringReqGen
}