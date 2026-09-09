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

    /**
     * Transient states with no OCPP 1.5 equivalent; kept in sync with the throw branch of convertFirmwareStatus.
     */
    private val unsupportedStatuses = setOf(
        FirmwareStatusEnumType.Downloading,
        FirmwareStatusEnumType.DownloadScheduled,
        FirmwareStatusEnumType.DownloadPaused,
        FirmwareStatusEnumType.Idle,
        FirmwareStatusEnumType.Installing
    )

    /**
     * OCPP 1.5 only models terminal firmware states; transient states are filtered out by the adapter
     * (logged and not forwarded).
     */
    fun isSupported(status: FirmwareStatusEnumType): Boolean =
        status !in unsupportedStatuses

    @Named("convertFirmwareStatus")
    fun convertFirmwareStatus(status: FirmwareStatusEnumType): FirmwareStatus =
        when (status) {
            FirmwareStatusEnumType.Downloaded,
            FirmwareStatusEnumType.InstallScheduled,
            FirmwareStatusEnumType.InstallRebooting,
            FirmwareStatusEnumType.SignatureVerified -> FirmwareStatus.Downloaded

            FirmwareStatusEnumType.DownloadFailed -> FirmwareStatus.DownloadFailed

            FirmwareStatusEnumType.InstallationFailed,
            FirmwareStatusEnumType.InstallVerificationFailed,
            FirmwareStatusEnumType.InvalidSignature -> FirmwareStatus.InstallationFailed

            FirmwareStatusEnumType.Installed -> FirmwareStatus.Installed

            // Transient states have no OCPP 1.5 equivalent; the adapter filters them out (isSupported) before mapping.
            FirmwareStatusEnumType.Downloading,
            FirmwareStatusEnumType.DownloadScheduled,
            FirmwareStatusEnumType.DownloadPaused,
            FirmwareStatusEnumType.Idle,
            FirmwareStatusEnumType.Installing ->
                throw IllegalArgumentException("FirmwareStatus $status has no OCPP 1.5 equivalent and must be filtered before mapping")
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertFirmwareStatus"])
    abstract fun genToCoreReq(statusReq: FirmwareStatusNotificationReqGen?): FirmwareStatusNotificationReq

    abstract fun coreToGenResp(statusResp: FirmwareStatusNotificationResp?): FirmwareStatusNotificationRespGen
}
