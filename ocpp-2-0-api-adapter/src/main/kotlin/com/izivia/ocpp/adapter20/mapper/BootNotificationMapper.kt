package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.api.model.bootnotification.ModemType
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core20.model.bootnotification.ModemType as ModemType20
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.bootnotification.BootNotificationReq as BootNotificationReqGen
import com.izivia.ocpp.api.model.bootnotification.BootNotificationResp as BootNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface BootNotificationMapper {

    @Mapping(target="chargingStation.modem", source = "chargingStation.modem", qualifiedByName = ["modemType"])
    fun genToCoreReq(bootNotificationReq: BootNotificationReqGen?): BootNotificationReq

    @Named("modemType")
    fun modemType(modemType: ModemType): ModemType20
    
    fun coreToGenResp(bootNotificationResp: BootNotificationResp?): BootNotificationRespGen
}