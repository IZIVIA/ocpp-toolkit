package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingLimitSourceEnumType
import fr.simatix.cs.simulator.core20.model.notifycharginglimit.ChargingLimitType
import fr.simatix.cs.simulator.core20.model.notifycharginglimit.NotifyChargingLimitReq
import fr.simatix.cs.simulator.core20.model.notifycharginglimit.NotifyChargingLimitResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingLimitSourceEnumType as ChargingLimitSourceEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifycharginglimit.ChargingLimitType as ChargingLimitTypeGen
import fr.simatix.cs.simulator.api.model.notifycharginglimit.NotifyChargingLimitReq as NotifyChargingLimitReqGen
import fr.simatix.cs.simulator.api.model.notifycharginglimit.NotifyChargingLimitResp as NotifyChargingLimitRespGen

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