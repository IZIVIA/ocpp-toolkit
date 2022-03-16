package fr.simatix.cs.simulator.adapter16.mapper


import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq as BootNotificationReqGen
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationResp as BootNotificationRespGen

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

    abstract fun coreToGenResp(bootNotificationResp: BootNotificationResp?): BootNotificationRespGen

}
