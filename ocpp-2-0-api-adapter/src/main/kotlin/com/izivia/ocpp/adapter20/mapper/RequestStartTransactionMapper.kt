package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionReq as RequestStartTransactionReqGen
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionResp as RequestStartTransactionRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RequestStartTransactionMapper {

    fun genToCoreResp(reqStartResp: RequestStartTransactionRespGen?): RequestStartTransactionResp

    fun coreToGenReq(reqStartReq: RequestStartTransactionReq): RequestStartTransactionReqGen

}