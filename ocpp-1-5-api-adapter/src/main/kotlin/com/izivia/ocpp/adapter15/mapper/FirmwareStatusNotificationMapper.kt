package com.izivia.ocpp.adapter15.mapper

import com.izivia.ocpp.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core15.model.firmwarestatusnotification.enumeration.FirmwareStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReqGen
import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationResp as FirmwareStatusNotificationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class FirmwareStatusNotificationMapper {

    @Named("convertFirmwareStatus")
    fun convertFirmwareStatus(status: FirmwareStatusEnumType): FirmwareStatus =
        when(status){

            FirmwareStatusEnumType.InstallScheduled -> FirmwareStatus.Idle

            FirmwareStatusEnumType.InstallRebooting,
            FirmwareStatusEnumType.SignatureVerified -> FirmwareStatus.Installing

            FirmwareStatusEnumType.InstallVerificationFailed,
            FirmwareStatusEnumType.InvalidSignature -> FirmwareStatus.InstallationFailed

            else -> FirmwareStatus.valueOf(status.name)
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertFirmwareStatus"])
    abstract fun genToCoreReq(statusReq: FirmwareStatusNotificationReqGen?): FirmwareStatusNotificationReq

    abstract fun coreToGenResp(statusResp: FirmwareStatusNotificationResp?): FirmwareStatusNotificationRespGen
}
