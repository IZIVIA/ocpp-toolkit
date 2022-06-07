package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import com.izivia.ocpp.api.model.authorize.AuthorizeResp as AuthorizeRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class AuthorizeMapper {

    @Mapping(source = "idToken.idToken", target = "idTag")
    abstract fun genToCoreReq(authorizeReq: AuthorizeReqGen?): AuthorizeReq

    fun coreToGenResp(authorizeResp: AuthorizeResp): AuthorizeRespGen =
        AuthorizeRespGen(CommonMapper.convertIdTagInfo(authorizeResp.idTagInfo))

}