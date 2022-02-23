package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.AuthorizeReq
import fr.simatix.cs.simulator.core20.model.AuthorizeResp
import org.mapstruct.Mapper
import fr.simatix.cs.simulator.api.model.AuthorizeReq as AuthorizeReqGen
import fr.simatix.cs.simulator.api.model.AuthorizeResp as AuthorizeRespGen

@Mapper
interface AuthorizeMapper {

    fun genToCoreReq(authorizeReq: AuthorizeReqGen?): AuthorizeReq

    fun coreToGenResp(authorizeResp: AuthorizeResp?): AuthorizeRespGen
}