package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeResp as AuthorizeRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class AuthorizeMapper {

    @Mapping(source = "idToken.idToken", target = "idTag")
    abstract fun genToCoreReq(authorizeReq: AuthorizeReqGen?): AuthorizeReq

    fun coreToGenResp(authorizeResp: AuthorizeResp): AuthorizeRespGen =
        AuthorizeRespGen(CommonMapper.convertIdTagInfo(authorizeResp.idTagInfo))

}