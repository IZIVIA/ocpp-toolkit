package com.izivia.ocpp.adapter12.mapper


import com.izivia.ocpp.core12.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.slf4j.LoggerFactory
import kotlin.time.Clock
import com.izivia.ocpp.api.model.bootnotification.BootNotificationReq as BootNotificationReqGen
import com.izivia.ocpp.api.model.bootnotification.BootNotificationResp as BootNotificationRespGen
import com.izivia.ocpp.api.model.bootnotification.enumeration.RegistrationStatusEnumType

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class BootNotificationMapper {
    private val logger = LoggerFactory.getLogger(BootNotificationMapper::class.java)

    fun genToCoreReq(bootNotificationReq: BootNotificationReqGen): BootNotificationReq =
        BootNotificationReq(
            chargePointModel = bootNotificationReq.chargingStation.model,
            chargePointVendor = bootNotificationReq.chargingStation.vendorName,
            chargePointSerialNumber = bootNotificationReq.chargingStation.serialNumber,
            firmwareVersion = bootNotificationReq.chargingStation.firmwareVersion,
            iccid = bootNotificationReq.chargingStation.modem?.iccid,
            imsi = bootNotificationReq.chargingStation.modem?.imsi
        )

    /**
     * OCPP 1.2 declares currentTime and heartbeatInterval with minOccurs="0" -- unlike 1.5 and 1.6 --
     * so a bare `<status>Rejected</status>` is legal and is the usual shape of a refusal. The generic
     * model requires both, so they are defaulted rather than letting the caller lose the rejection.
     * A missing value on an Accepted response is a central-system fault, hence the warning.
     */
    fun coreToGenResp(bootNotificationResp: BootNotificationResp): BootNotificationRespGen {
        val status = RegistrationStatusEnumType.valueOf(bootNotificationResp.status.name)
        val currentTime = bootNotificationResp.currentTime
        val interval = bootNotificationResp.heartbeatInterval
        if (status == RegistrationStatusEnumType.Accepted && (currentTime == null || interval == null)) {
            logger.warn("Accepted BootNotification without currentTime or heartbeatInterval in OCPP 1.2")
        }
        return BootNotificationRespGen(
            currentTime = currentTime ?: Clock.System.now(),
            interval = interval ?: 0,
            status = status
        )
    }

}
