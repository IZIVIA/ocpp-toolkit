package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.setnetworkprofile.SetNetworkProfileReq
import fr.simatix.cs.simulator.core20.model.setnetworkprofile.SetNetworkProfileResp
import org.mapstruct.Mapper
import fr.simatix.cs.simulator.api.model.setnetworkprofile.SetNetworkProfileReq as SetNetworkProfileReqGen
import fr.simatix.cs.simulator.api.model.setnetworkprofile.SetNetworkProfileResp as SetNetworkProfileRespGen
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetNetworkProfileMapper
{
    fun genToCoreResp(getLogResp: SetNetworkProfileRespGen?): SetNetworkProfileResp

    fun coreToGenReq(getLogReq: SetNetworkProfileReq?): SetNetworkProfileReqGen
}