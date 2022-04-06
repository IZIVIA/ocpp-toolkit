package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationResp as FirmwareStatusNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface FirmwareStatusNotificationMapper {

    fun genToCoreReq(statusReq: FirmwareStatusNotificationReqGen?): FirmwareStatusNotificationReq

    fun coreToGenResp(statusResp: FirmwareStatusNotificationResp?): FirmwareStatusNotificationRespGen
}