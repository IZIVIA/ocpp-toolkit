package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq as NotifyEVChargingScheduleReqGen
import com.izivia.ocpp.api.model.notifyevchargingschedule.NotifyEVChargingScheduleResp as NotifyEVChargingScheduleRespGen


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyEVChargingScheduleMapper {

    fun genToCoreReq(notifyEVChargingScheduleReq: NotifyEVChargingScheduleReqGen?): NotifyEVChargingScheduleReq

    fun coreToGenResp(notifyEVChargingScheduleResp: NotifyEVChargingScheduleResp?): NotifyEVChargingScheduleRespGen
}