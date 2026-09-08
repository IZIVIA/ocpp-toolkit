package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.common.CertificateHashDataType as GenCertificateHashDataType
import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType
import com.izivia.ocpp.api.model.updatefirmware.FirmwareType as GenFirmwareType
import com.izivia.ocpp.core16.model.deletecertificate.CertificateHashDataType
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedReq as GenCertificateSignedReq
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedResp as GenCertificateSignedResp
import com.izivia.ocpp.api.model.certificateSigned.enumeration.CertificateSignedStatusEnumType as GenCertificateSignedStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.GenericStatusEnumType as GenGenericStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.HashAlgorithmEnumType as GenHashAlgorithmEnumType
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateReq as GenDeleteCertificateReq
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateResp as GenDeleteCertificateResp
import com.izivia.ocpp.api.model.deletecertificate.enumerations.DeleteCertificateStatusEnumType as GenDeleteCertificateStatusEnumType
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsReq as GenGetInstalledCertificateIdsReq
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsResp as GenGetInstalledCertificateIdsResp
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType as GenGetInstalledCertificateStatusEnumType
import com.izivia.ocpp.api.model.getlog.GetLogReq as GenGetLogReq
import com.izivia.ocpp.api.model.getlog.GetLogResp as GenGetLogResp
import com.izivia.ocpp.api.model.getlog.LogParametersType as GenLogParametersType
import com.izivia.ocpp.api.model.getlog.enumeration.LogEnumType as GenLogEnumType
import com.izivia.ocpp.api.model.getlog.enumeration.LogStatusEnumType as GenLogStatusEnumType
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateReq as GenInstallCertificateReq
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateResp as GenInstallCertificateResp
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateStatusEnumType as GenInstallCertificateStatusEnumType
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateUseEnumType as GenInstallCertificateUseEnumType
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationReq as GenLogStatusNotificationReq
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationResp as GenLogStatusNotificationResp
import com.izivia.ocpp.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType as GenUploadLogStatusEnumType
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationReq as GenSecurityEventNotificationReq
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationResp as GenSecurityEventNotificationResp
import com.izivia.ocpp.api.model.signcertificate.SignCertificateReq as GenSignCertificateReq
import com.izivia.ocpp.api.model.signcertificate.SignCertificateResp as GenSignCertificateResp
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageReq as GenTriggerMessageReq
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageResp as GenTriggerMessageResp
import com.izivia.ocpp.api.model.triggermessage.enumeration.MessageTriggerEnumType as GenMessageTriggerEnumType
import com.izivia.ocpp.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType as GenTriggerMessageStatusEnumType
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareReq as GenUpdateFirmwareReq
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareResp as GenUpdateFirmwareResp
import com.izivia.ocpp.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType as GenUpdateFirmwareStatusEnumType
import com.izivia.ocpp.core16.model.certificatesigned.CertificateSignedReq as CoreCertificateSignedReq
import com.izivia.ocpp.core16.model.certificatesigned.CertificateSignedResp as CoreCertificateSignedResp
import com.izivia.ocpp.core16.model.certificatesigned.enumeration.CertificateSignedStatusEnumType as CoreCertificateSignedStatusEnumType
import com.izivia.ocpp.core16.model.common.enumeration.GenericStatusEnumType as CoreGenericStatusEnumType
import com.izivia.ocpp.core16.model.deletecertificate.DeleteCertificateReq as CoreDeleteCertificateReq
import com.izivia.ocpp.core16.model.deletecertificate.DeleteCertificateResp as CoreDeleteCertificateResp
import com.izivia.ocpp.core16.model.deletecertificate.enumeration.DeleteCertificateStatusEnumType as CoreDeleteCertificateStatusEnumType
import com.izivia.ocpp.core16.model.deletecertificate.enumeration.HashAlgorithmEnumType as CoreHashAlgorithmEnumType
import com.izivia.ocpp.core16.model.extendedtriggermessage.ExtendedTriggerMessageReq as CoreExtendedTriggerMessageReq
import com.izivia.ocpp.core16.model.extendedtriggermessage.ExtendedTriggerMessageResp as CoreExtendedTriggerMessageResp
import com.izivia.ocpp.core16.model.extendedtriggermessage.enumeration.ExtendedMessageTriggerEnumType as CoreExtendedMessageTriggerEnumType
import com.izivia.ocpp.core16.model.getinstalledcertificateids.GetInstalledCertificateIdsReq as CoreGetInstalledCertificateIdsReq
import com.izivia.ocpp.core16.model.getinstalledcertificateids.GetInstalledCertificateIdsResp as CoreGetInstalledCertificateIdsResp
import com.izivia.ocpp.core16.model.getinstalledcertificateids.enumeration.CertificateUseEnumType as CoreCertificateUseEnumType
import com.izivia.ocpp.core16.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType as CoreGetInstalledCertificateStatusEnumType
import com.izivia.ocpp.core16.model.getlog.GetLogReq as CoreGetLogReq
import com.izivia.ocpp.core16.model.getlog.GetLogResp as CoreGetLogResp
import com.izivia.ocpp.core16.model.getlog.LogParametersType as CoreLogParametersType
import com.izivia.ocpp.core16.model.getlog.enumeration.LogEnumType as CoreLogEnumType
import com.izivia.ocpp.core16.model.getlog.enumeration.LogStatusEnumType as CoreLogStatusEnumType
import com.izivia.ocpp.core16.model.installcertificate.InstallCertificateReq as CoreInstallCertificateReq
import com.izivia.ocpp.core16.model.installcertificate.InstallCertificateResp as CoreInstallCertificateResp
import com.izivia.ocpp.core16.model.installcertificate.enumeration.CertificateStatusEnumType as CoreCertificateStatusEnumType
import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationReq as CoreLogStatusNotificationReq
import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationResp as CoreLogStatusNotificationResp
import com.izivia.ocpp.core16.model.logstatusnotification.enumeration.UpdateLogStatusEnumType as CoreUploadLogStatusEnumType
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationReq as CoreSecurityEventNotificationReq
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationResp as CoreSecurityEventNotificationResp
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateReq as CoreSignCertificateReq
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateResp as CoreSignCertificateResp
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareReq as CoreSignedUpdateFirmwareReq
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareResp as CoreSignedUpdateFirmwareResp
import com.izivia.ocpp.core16.model.signedupdatefirmware.enumeration.UpdateFirmwareStatusEnumType as CoreUpdateFirmwareStatusEnumType
import com.izivia.ocpp.core16.model.triggermessage.enumeration.TriggerMessageStatus as CoreTriggerMessageStatus

object SecurityMapper {
    fun genToCoreReq(req: GenSecurityEventNotificationReq) =
        CoreSecurityEventNotificationReq(req.type, req.timestamp, req.techInfo)

    // SecurityEventNotification.conf is empty in OCPP 1.6: there is nothing to read from resp.
    fun coreToGenResp(resp: CoreSecurityEventNotificationResp?) = GenSecurityEventNotificationResp()

    // OCPP 1.6 SignCertificate.req only carries the csr: certificateType has no equivalent and is
    // dropped. The whitepaper knows a single use case, the charge point certificate.
    fun genToCoreReq(req: GenSignCertificateReq) = CoreSignCertificateReq(req.csr)

    fun coreToGenResp(resp: CoreSignCertificateResp) =
        GenSignCertificateResp(
            when (resp.status) {
                CoreGenericStatusEnumType.Accepted -> GenGenericStatusEnumType.Accepted
                CoreGenericStatusEnumType.Rejected -> GenGenericStatusEnumType.Rejected
            }
        )

    fun genToCoreReq(req: GenLogStatusNotificationReq) =
        CoreLogStatusNotificationReq(
            status = when (req.status) {
                // OCPP 1.6 security whitepaper has no canceled status for LogStatusNotification.
                // The closest non-success terminal status is UploadFailure.
                GenUploadLogStatusEnumType.AcceptedCanceled -> CoreUploadLogStatusEnumType.UploadFailure
                GenUploadLogStatusEnumType.BadMessage -> CoreUploadLogStatusEnumType.BadMessage
                GenUploadLogStatusEnumType.Idle -> CoreUploadLogStatusEnumType.Idle
                GenUploadLogStatusEnumType.NotSupportedOperation -> CoreUploadLogStatusEnumType.NotSupportedOperation
                GenUploadLogStatusEnumType.PermissionDenied -> CoreUploadLogStatusEnumType.PermissionDenied
                GenUploadLogStatusEnumType.UploadFailure -> CoreUploadLogStatusEnumType.UploadFailure
                GenUploadLogStatusEnumType.Uploaded -> CoreUploadLogStatusEnumType.Uploaded
                GenUploadLogStatusEnumType.Uploading -> CoreUploadLogStatusEnumType.Uploading
            },
            requestId = req.requestId
        )

    // LogStatusNotification.conf is empty in OCPP 1.6: there is nothing to read from resp.
    fun coreToGenResp(resp: CoreLogStatusNotificationResp?) = GenLogStatusNotificationResp()

    fun coreToGenReq(req: CoreCertificateSignedReq) = GenCertificateSignedReq(req.certificateChain)

    fun genToCoreResp(resp: GenCertificateSignedResp) =
        CoreCertificateSignedResp(
            when (resp.status) {
                GenCertificateSignedStatusEnumType.Accepted -> CoreCertificateSignedStatusEnumType.Accepted
                GenCertificateSignedStatusEnumType.Rejected -> CoreCertificateSignedStatusEnumType.Rejected
            }
        )

    fun coreToGenReq(req: CoreDeleteCertificateReq) =
        GenDeleteCertificateReq(coreToGen(req.certificateHashData))

    fun genToCoreResp(resp: GenDeleteCertificateResp) =
        CoreDeleteCertificateResp(
            when (resp.status) {
                GenDeleteCertificateStatusEnumType.Accepted -> CoreDeleteCertificateStatusEnumType.Accepted
                GenDeleteCertificateStatusEnumType.Failed -> CoreDeleteCertificateStatusEnumType.Failed
                GenDeleteCertificateStatusEnumType.NotFound -> CoreDeleteCertificateStatusEnumType.NotFound
            }
        )

    fun coreToGenReq(req: CoreGetInstalledCertificateIdsReq) =
        GenGetInstalledCertificateIdsReq(listOf(coreToGen(req.certificateType)))

    fun genToCoreResp(resp: GenGetInstalledCertificateIdsResp) =
        CoreGetInstalledCertificateIdsResp(
            status = when (resp.status) {
                GenGetInstalledCertificateStatusEnumType.Accepted -> CoreGetInstalledCertificateStatusEnumType.Accepted
                GenGetInstalledCertificateStatusEnumType.NotFound -> CoreGetInstalledCertificateStatusEnumType.NotFound
            },
            // OCPP 1.6 only knows a flat list of hashes: the chain structure is lost, so
            // childCertificateHashData and certificateType of each link are dropped.
            certificateHashData = resp.certificateHashDataChain?.map { genToCore(it.certificateHashData) }
        )

    fun coreToGenReq(req: CoreGetLogReq) =
        GenGetLogReq(
            requestId = req.requestId,
            logType = when (req.logType) {
                CoreLogEnumType.DiagnosticsLog -> GenLogEnumType.DiagnosticsLog
                CoreLogEnumType.SecurityLog -> GenLogEnumType.SecurityLog
            },
            log = GenLogParametersType(
                remoteLocation = req.log.remoteLocation,
                oldestTimestamp = req.log.oldestTimestamp,
                latestTimestamp = req.log.latestTimestamp
            ),
            retries = req.retries,
            retryInterval = req.retryInterval
        )

    fun genToCoreResp(resp: GenGetLogResp) =
        CoreGetLogResp(
            when (resp.status) {
                GenLogStatusEnumType.Accepted -> CoreLogStatusEnumType.Accepted
                GenLogStatusEnumType.Rejected -> CoreLogStatusEnumType.Rejected
                GenLogStatusEnumType.AcceptedCanceled -> CoreLogStatusEnumType.AcceptedCanceled
            },
            resp.filename
        )

    fun coreToGenReq(req: CoreInstallCertificateReq) =
        GenInstallCertificateReq(coreToGenInstall(req.certificateType), req.certificate)

    fun genToCoreResp(resp: GenInstallCertificateResp) =
        CoreInstallCertificateResp(
            when (resp.status) {
                GenInstallCertificateStatusEnumType.Accepted -> CoreCertificateStatusEnumType.Accepted
                GenInstallCertificateStatusEnumType.Failed -> CoreCertificateStatusEnumType.Failed
                GenInstallCertificateStatusEnumType.Rejected -> CoreCertificateStatusEnumType.Rejected
            }
        )

    fun coreToGenReq(req: CoreSignedUpdateFirmwareReq) =
        GenUpdateFirmwareReq(
            retries = req.retries,
            retryInterval = req.retryInterval,
            requestId = req.requestId,
            firmware = GenFirmwareType(
                location = req.firmware.location,
                retrieveDateTime = req.firmware.retrieveDateTime,
                installDateTime = req.firmware.installDateTime,
                signingCertificate = req.firmware.signingCertificate,
                signature = req.firmware.signature
            )
        )

    fun genToCoreResp(resp: GenUpdateFirmwareResp) =
        CoreSignedUpdateFirmwareResp(
            when (resp.status) {
                GenUpdateFirmwareStatusEnumType.Accepted -> CoreUpdateFirmwareStatusEnumType.Accepted
                GenUpdateFirmwareStatusEnumType.Rejected -> CoreUpdateFirmwareStatusEnumType.Rejected
                GenUpdateFirmwareStatusEnumType.AcceptedCanceled -> CoreUpdateFirmwareStatusEnumType.AcceptedCanceled
                GenUpdateFirmwareStatusEnumType.InvalidCertificate -> CoreUpdateFirmwareStatusEnumType.InvalidCertificate
                GenUpdateFirmwareStatusEnumType.RevokedCertificate -> CoreUpdateFirmwareStatusEnumType.RevokedCertificate
            }
        )

    fun coreToGenReq(req: CoreExtendedTriggerMessageReq) =
        GenTriggerMessageReq(
            requestedMessage = when (req.requestedMessage) {
                CoreExtendedMessageTriggerEnumType.BootNotification -> GenMessageTriggerEnumType.BootNotification
                CoreExtendedMessageTriggerEnumType.LogStatusNotification -> GenMessageTriggerEnumType.LogStatusNotification
                CoreExtendedMessageTriggerEnumType.FirmwareStatusNotification -> GenMessageTriggerEnumType.FirmwareStatusNotification
                CoreExtendedMessageTriggerEnumType.Heartbeat -> GenMessageTriggerEnumType.Heartbeat
                CoreExtendedMessageTriggerEnumType.MeterValues -> GenMessageTriggerEnumType.MeterValues
                CoreExtendedMessageTriggerEnumType.SignChargePointCertificate -> GenMessageTriggerEnumType.SignChargingStationCertificate
                CoreExtendedMessageTriggerEnumType.StatusNotification -> GenMessageTriggerEnumType.StatusNotification
            },
            // OCPP 1.6 is a 2-tier model (Charge Point + Connectors) and has no EVSE tier, so the
            // EVSE id has to be synthesised. OCA Application Note "Multiple Connectors per EVSE in
            // OCPP 1.x implementations" (v1.2, 2024-03-25), section 4: "The TWG's advice is to
            // implement each connector as if it has its own EVSE." One EVSE per connector therefore
            // maps connectorId onto both tiers.
            evse = req.connectorId?.let { EVSEType(it, it) }
        )

    fun genToCoreResp(resp: GenTriggerMessageResp) =
        CoreExtendedTriggerMessageResp(
            when (resp.status) {
                GenTriggerMessageStatusEnumType.Accepted -> CoreTriggerMessageStatus.Accepted
                GenTriggerMessageStatusEnumType.Rejected -> CoreTriggerMessageStatus.Rejected
                GenTriggerMessageStatusEnumType.NotImplemented -> CoreTriggerMessageStatus.NotImplemented
            }
        )

    private fun genToCore(hash: GenCertificateHashDataType) =
        CertificateHashDataType(
            hashAlgorithm = when (hash.hashAlgorithm) {
                GenHashAlgorithmEnumType.SHA256 -> CoreHashAlgorithmEnumType.SHA256
                GenHashAlgorithmEnumType.SHA384 -> CoreHashAlgorithmEnumType.SHA384
                GenHashAlgorithmEnumType.SHA512 -> CoreHashAlgorithmEnumType.SHA512
            },
            issuerNameHash = hash.issuerNameHash,
            issuerKeyHash = hash.issuerKeyHash,
            serialNumber = hash.serialNumber
        )

    private fun coreToGen(hash: CertificateHashDataType) =
        GenCertificateHashDataType(
            hashAlgorithm = when (hash.hashAlgorithm) {
                CoreHashAlgorithmEnumType.SHA256 -> GenHashAlgorithmEnumType.SHA256
                CoreHashAlgorithmEnumType.SHA384 -> GenHashAlgorithmEnumType.SHA384
                CoreHashAlgorithmEnumType.SHA512 -> GenHashAlgorithmEnumType.SHA512
            },
            issuerNameHash = hash.issuerNameHash,
            issuerKeyHash = hash.issuerKeyHash,
            serialNumber = hash.serialNumber
        )

    private fun coreToGen(type: CoreCertificateUseEnumType): GetCertificateIdUseEnumType =
        when (type) {
            CoreCertificateUseEnumType.CentralSystemRootCertificate -> GetCertificateIdUseEnumType.CSMSRootCertificate
            CoreCertificateUseEnumType.ManufacturerRootCertificate -> GetCertificateIdUseEnumType.ManufacturerRootCertificate
        }

    private fun coreToGenInstall(type: CoreCertificateUseEnumType): GenInstallCertificateUseEnumType =
        when (type) {
            CoreCertificateUseEnumType.CentralSystemRootCertificate -> GenInstallCertificateUseEnumType.CSMSRootCertificate
            CoreCertificateUseEnumType.ManufacturerRootCertificate -> GenInstallCertificateUseEnumType.ManufacturerRootCertificate
        }
}
