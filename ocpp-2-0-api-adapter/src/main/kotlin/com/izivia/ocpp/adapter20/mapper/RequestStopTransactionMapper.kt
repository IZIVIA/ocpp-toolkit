package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionReq as RequestStopTransactionReqGen
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionResp as RequestStopTransactionRespGen
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RequestStopTransactionMapper {

    fun genToCoreResp(remoteStopResp: RequestStopTransactionRespGen?): RequestStopTransactionResp

    fun coreToGenReq(remoteStopReq: RequestStopTransactionReq): RequestStopTransactionReqGen

}