package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorReq as UnlockConnectorReqGen
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorResp as UnlockConnectorRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UnlockConnectorMapper {

    fun genToCoreResp(unlockConnectorResp: UnlockConnectorRespGen): UnlockConnectorResp

    fun coreToGenReq(unlockConnectorReq: UnlockConnectorReq): UnlockConnectorReqGen

}