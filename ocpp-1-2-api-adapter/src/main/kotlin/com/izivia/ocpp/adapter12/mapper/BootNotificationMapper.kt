package com.izivia.ocpp.adapter12.mapper


import com.izivia.ocpp.core12.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.bootnotification.BootNotificationReq as BootNotificationReqGen
import com.izivia.ocpp.api.model.bootnotification.BootNotificationResp as BootNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class BootNotificationMapper {

    fun genToCoreReq(bootNotificationReq: BootNotificationReqGen): BootNotificationReq =
        BootNotificationReq(
            chargePointModel = bootNotificationReq.chargingStation.model,
            chargePointVendor = bootNotificationReq.chargingStation.vendorName,
            chargePointSerialNumber = bootNotificationReq.chargingStation.serialNumber,
            firmwareVersion = bootNotificationReq.chargingStation.firmwareVersion,
            iccid = bootNotificationReq.chargingStation.modem?.iccid,
            imsi = bootNotificationReq.chargingStation.modem?.imsi
        )

    @Mapping(source = "heartbeatInterval", target = "interval")
    abstract fun coreToGenResp(bootNotificationResp: BootNotificationResp?): BootNotificationRespGen

}
