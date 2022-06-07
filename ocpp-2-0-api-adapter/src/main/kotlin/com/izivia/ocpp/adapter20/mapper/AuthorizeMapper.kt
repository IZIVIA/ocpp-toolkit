package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.authorize.AuthorizeReq
import com.izivia.ocpp.core20.model.authorize.AuthorizeResp
import org.mapstruct.Mapper
import com.izivia.ocpp.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import com.izivia.ocpp.api.model.authorize.AuthorizeResp as AuthorizeRespGen

@Mapper
interface AuthorizeMapper {

    fun genToCoreReq(authorizeReq: AuthorizeReqGen?): AuthorizeReq

    fun coreToGenResp(authorizeResp: AuthorizeResp?): AuthorizeRespGen
}