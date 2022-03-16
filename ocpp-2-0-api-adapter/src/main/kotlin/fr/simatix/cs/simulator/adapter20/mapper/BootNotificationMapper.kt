package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.api.model.bootnotification.ModemType
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core20.model.bootnotification.ModemType as ModemType20
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq as BootNotificationReqGen
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationResp as BootNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BootNotificationMapper {

    @Mapping(target="chargingStation.modem", source = "chargingStation.modem", qualifiedByName = ["modemType"])
    fun genToCoreReq(bootNotificationReq: BootNotificationReqGen?): BootNotificationReq

    @Named("modemType")
    fun modemType(modemType: ModemType): ModemType20
    
    fun coreToGenResp(bootNotificationResp: BootNotificationResp?): BootNotificationRespGen
}