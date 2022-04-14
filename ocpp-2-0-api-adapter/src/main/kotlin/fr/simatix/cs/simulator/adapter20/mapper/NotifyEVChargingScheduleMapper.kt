package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import fr.simatix.cs.simulator.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq as NotifyEVChargingScheduleReqGen
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleResp as NotifyEVChargingScheduleRespGen


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyEVChargingScheduleMapper {

    fun genToCoreReq(notifyEVChargingScheduleReq: NotifyEVChargingScheduleReqGen?): NotifyEVChargingScheduleReq

    fun coreToGenResp(notifyEVChargingScheduleResp: NotifyEVChargingScheduleResp?): NotifyEVChargingScheduleRespGen
}