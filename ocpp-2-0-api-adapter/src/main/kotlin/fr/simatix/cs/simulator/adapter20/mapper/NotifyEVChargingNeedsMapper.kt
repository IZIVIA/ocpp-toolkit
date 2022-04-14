package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq as NotifyEVChargingNeedsReqGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.NotifyEVChargingNeedsResp as NotifyEVChargingNeedsRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyEVChargingNeedsMapper {

    fun genToCoreReq(notifyEVChargingNeedsReq: NotifyEVChargingNeedsReqGen?): NotifyEVChargingNeedsReq

    fun coreToGenResp(notifyEVChargingNeedsResp: NotifyEVChargingNeedsResp?): NotifyEVChargingNeedsRespGen

}