package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.common.enumeration.ChargingLimitSourceEnumType
import com.izivia.ocpp.core20.model.notifycharginglimit.ChargingLimitType
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitReq
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.common.enumeration.ChargingLimitSourceEnumType as ChargingLimitSourceEnumTypeGen
import com.izivia.ocpp.api.model.notifycharginglimit.ChargingLimitType as ChargingLimitTypeGen
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitReq as NotifyChargingLimitReqGen
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitResp as NotifyChargingLimitRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class NotifyChargingLimitMapper {

    @Named("convertChargingLimitSource")
    fun convertChargingLimitSource(chargingLimitSource: ChargingLimitSourceEnumTypeGen): ChargingLimitSourceEnumType =
        ChargingLimitSourceEnumType.valueOf(chargingLimitSource.value)

    @Named("convertChargingLimit")
    fun convertChargingLimit(chargingLimit: ChargingLimitTypeGen): ChargingLimitType =
        ChargingLimitType(convertChargingLimitSource(chargingLimit.chargingLimitSource), chargingLimit.isGridCritical)

    @Mapping(target = "chargingLimit", source = "chargingLimit", qualifiedByName = ["convertChargingLimit"])
    abstract fun genToCoreReq(notifyChargingLimitReq: NotifyChargingLimitReqGen?): NotifyChargingLimitReq

    abstract fun coreToGenResp(notifyChargingLimitResp: NotifyChargingLimitResp?): NotifyChargingLimitRespGen

}