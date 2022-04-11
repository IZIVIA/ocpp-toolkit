package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.core16.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core16.model.getlocallistversion.GetLocalListVersionResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionReq as GetLocalListVersionReqGen
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionResp as GetLocalListVersionRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetLocalListVersionMapper {

    @Mapping(target = "listVersion", source = "versionNumber")
    abstract fun genToCoreResp(getLocalListVersionResp: GetLocalListVersionRespGen?): GetLocalListVersionResp

    abstract fun coreToGenReq(getLocalListVersionReq: GetLocalListVersionReq): GetLocalListVersionReqGen

}