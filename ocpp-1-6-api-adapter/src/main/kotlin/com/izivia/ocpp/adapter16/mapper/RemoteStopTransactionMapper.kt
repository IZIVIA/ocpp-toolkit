package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface RemoteStopTransactionMapper {

    fun genToCoreResp(remoteStopResp: RequestStopTransactionResp?): RemoteStopTransactionResp
    
    fun coreToGenReq(remoteStopReq: RemoteStopTransactionReq): RequestStopTransactionReq

}