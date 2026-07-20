package com.izivia.ocpp.security16.impl

import com.izivia.ocpp.core16.model.certificatesigned.CertificateSignedReq
import com.izivia.ocpp.core16.model.certificatesigned.CertificateSignedResp
import com.izivia.ocpp.core16.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.core16.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.core16.model.extendedtriggermessage.ExtendedTriggerMessageReq
import com.izivia.ocpp.core16.model.extendedtriggermessage.ExtendedTriggerMessageResp
import com.izivia.ocpp.core16.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.core16.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.core16.model.getlog.GetLogReq
import com.izivia.ocpp.core16.model.getlog.GetLogResp
import com.izivia.ocpp.core16.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core16.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core16.model.signedfirmwarestatusnotification.SignedFirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareReq
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareResp
import com.izivia.ocpp.operation.information.ActionOcpp.CERTIFICATE_SIGNED
import com.izivia.ocpp.operation.information.ActionOcpp.DELETE_CERTIFICATE
import com.izivia.ocpp.operation.information.ActionOcpp.EXTENDED_TRIGGER_MESSAGE
import com.izivia.ocpp.operation.information.ActionOcpp.GET_INSTALLED_CERTIFICATE_IDS
import com.izivia.ocpp.operation.information.ActionOcpp.GET_LOG
import com.izivia.ocpp.operation.information.ActionOcpp.INSTALL_CERTIFICATE
import com.izivia.ocpp.operation.information.ActionOcpp.LOG_STATUS_NOTIFICATION
import com.izivia.ocpp.operation.information.ActionOcpp.SECURITY_EVENT_NOTIFICATION
import com.izivia.ocpp.operation.information.ActionOcpp.SIGN_CERTIFICATE
import com.izivia.ocpp.operation.information.ActionOcpp.SIGNED_FIRMWARE_STATUS_NOTIFICATION
import com.izivia.ocpp.operation.information.ActionOcpp.SIGNED_UPDATE_FIRMWARE
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.security16.SecurityCSMSOperations
import com.izivia.ocpp.security16.SecurityChargePointOperations
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import kotlin.time.Clock

class RealSecurityCSMSOperations(
    private val servers: Set<ServerTransport>,
    private val acceptConnection: (String) -> ChargingStationConfig,
    chargePointOperations: SecurityChargePointOperations
) : SecurityCSMSOperations {

    init {
        servers.forEach { server ->
            server.receiveMessage(
                LOG_STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: LogStatusNotificationReq ->
                    chargePointOperations.logStatusNotification(meta, req).response
                },
                acceptConnection
            )
            server.receiveMessage(
                SECURITY_EVENT_NOTIFICATION.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: SecurityEventNotificationReq ->
                    chargePointOperations.securityEventNotification(meta, req).response
                },
                acceptConnection
            )
            server.receiveMessage(
                SIGN_CERTIFICATE.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: SignCertificateReq ->
                    chargePointOperations.signCertificate(meta, req).response
                },
                acceptConnection
            )
            server.receiveMessage(
                SIGNED_FIRMWARE_STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: SignedFirmwareStatusNotificationReq ->
                    chargePointOperations.signedFirmwareStatusNotification(meta, req).response
                },
                acceptConnection
            )
        }
    }

    override fun certificateSigned(
        meta: RequestMetadata,
        req: CertificateSignedReq
    ): OperationExecution<CertificateSignedReq, CertificateSignedResp> =
        sendMessage(meta, meta.chargingStationId, CERTIFICATE_SIGNED.value, req)

    override fun deleteCertificate(
        meta: RequestMetadata,
        req: DeleteCertificateReq
    ): OperationExecution<DeleteCertificateReq, DeleteCertificateResp> =
        sendMessage(meta, meta.chargingStationId, DELETE_CERTIFICATE.value, req)

    override fun extendedTriggerMessage(
        meta: RequestMetadata,
        req: ExtendedTriggerMessageReq
    ): OperationExecution<ExtendedTriggerMessageReq, ExtendedTriggerMessageResp> =
        sendMessage(meta, meta.chargingStationId, EXTENDED_TRIGGER_MESSAGE.value, req)

    override fun getInstalledCertificateIds(
        meta: RequestMetadata,
        req: GetInstalledCertificateIdsReq
    ): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp> =
        sendMessage(meta, meta.chargingStationId, GET_INSTALLED_CERTIFICATE_IDS.value, req)

    override fun getLog(meta: RequestMetadata, req: GetLogReq): OperationExecution<GetLogReq, GetLogResp> =
        sendMessage(meta, meta.chargingStationId, GET_LOG.value, req)

    override fun installCertificate(
        meta: RequestMetadata,
        req: InstallCertificateReq
    ): OperationExecution<InstallCertificateReq, InstallCertificateResp> =
        sendMessage(meta, meta.chargingStationId, INSTALL_CERTIFICATE.value, req)

    override fun signedUpdateFirmware(
        meta: RequestMetadata,
        req: SignedUpdateFirmwareReq
    ): OperationExecution<SignedUpdateFirmwareReq, SignedUpdateFirmwareResp> =
        sendMessage(meta, meta.chargingStationId, SIGNED_UPDATE_FIRMWARE.value, req)

    private inline fun <T, reified P : Any> sendMessage(
        meta: RequestMetadata,
        ocppId: String,
        action: String,
        request: T
    ): OperationExecution<T, P> {
        val transport = getTransport(ocppId)
        val requestTime = Clock.System.now()
        val response: P = transport.sendMessage(ocppId, action, request)
        val responseTime = Clock.System.now()
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, requestTime, responseTime),
            request,
            response
        )
    }

    private fun getTransport(ocppId: String): ServerTransport {
        val config = acceptConnection(ocppId)
        return servers.firstOrNull { it.canSendToChargingStation(config) }
            ?: throw IllegalStateException("No transport to send a message to $ocppId")
    }
}
