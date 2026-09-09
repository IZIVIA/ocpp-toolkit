package com.izivia.ocpp.adapter12.mapper

import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RemoteStopTransactionMapper {

    fun genToCoreResp(remoteStopResp: RequestStopTransactionResp?): RemoteStopTransactionResp

    @Mapping(target = "transactionId", source = "transactionId")
    fun coreToGenReq(remoteStopReq: RemoteStopTransactionReq, transactionId: String): RequestStopTransactionReq
}
