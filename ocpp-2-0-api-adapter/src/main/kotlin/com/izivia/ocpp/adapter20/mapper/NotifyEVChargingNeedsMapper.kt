package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import com.izivia.ocpp.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq as NotifyEVChargingNeedsReqGen
import com.izivia.ocpp.api.model.notifyevchargingneeds.NotifyEVChargingNeedsResp as NotifyEVChargingNeedsRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyEVChargingNeedsMapper {

    fun genToCoreReq(notifyEVChargingNeedsReq: NotifyEVChargingNeedsReqGen?): NotifyEVChargingNeedsReq

    fun coreToGenResp(notifyEVChargingNeedsResp: NotifyEVChargingNeedsResp?): NotifyEVChargingNeedsRespGen

}