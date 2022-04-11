package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionReq as GetLocalListVersionReqGen
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionResp as GetLocalListVersionRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface GetLocalListVersionMapper {

    fun genToCoreResp(getLocalListVersionResp: GetLocalListVersionRespGen?): GetLocalListVersionResp

    fun coreToGenReq(getLocalListVersionReq: GetLocalListVersionReq): GetLocalListVersionReqGen

}