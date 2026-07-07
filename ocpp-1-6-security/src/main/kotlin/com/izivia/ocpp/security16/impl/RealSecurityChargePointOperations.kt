package com.izivia.ocpp.security16.impl

import com.izivia.ocpp.core16.model.certificatesigned.CertificateSignedReq
import com.izivia.ocpp.core16.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.core16.model.extendedtriggermessage.ExtendedTriggerMessageReq
import com.izivia.ocpp.core16.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.core16.model.getlog.GetLogReq
import com.izivia.ocpp.core16.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.core16.model.signedfirmwarestatusnotification.SignedFirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.signedfirmwarestatusnotification.SignedFirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareReq
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
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.security16.SecurityCSMSOperations
import com.izivia.ocpp.security16.SecurityChargePointOperations
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import kotlin.time.Clock

class RealSecurityChargePointOperations(
    private val chargingStationId: String,
    private val client: ClientTransport,
    private val csmsOperations: SecurityCSMSOperations
) : SecurityChargePointOperations {

    init {
        client.receiveMessage(CERTIFICATE_SIGNED.value) { req: CertificateSignedReq ->
            csmsOperations.certificateSigned(RequestMetadata(chargingStationId), req).response
        }
        client.receiveMessage(DELETE_CERTIFICATE.value) { req: DeleteCertificateReq ->
            csmsOperations.deleteCertificate(RequestMetadata(chargingStationId), req).response
        }
        client.receiveMessage(EXTENDED_TRIGGER_MESSAGE.value) { req: ExtendedTriggerMessageReq ->
            csmsOperations.extendedTriggerMessage(RequestMetadata(chargingStationId), req).response
        }
        client.receiveMessage(GET_INSTALLED_CERTIFICATE_IDS.value) { req: GetInstalledCertificateIdsReq ->
            csmsOperations.getInstalledCertificateIds(RequestMetadata(chargingStationId), req).response
        }
        client.receiveMessage(GET_LOG.value) { req: GetLogReq ->
            csmsOperations.getLog(RequestMetadata(chargingStationId), req).response
        }
        client.receiveMessage(INSTALL_CERTIFICATE.value) { req: InstallCertificateReq ->
            csmsOperations.installCertificate(RequestMetadata(chargingStationId), req).response
        }
        client.receiveMessage(SIGNED_UPDATE_FIRMWARE.value) { req: SignedUpdateFirmwareReq ->
            csmsOperations.signedUpdateFirmware(RequestMetadata(chargingStationId), req).response
        }
    }

    override fun connect() {
        client.connect()
    }

    override fun close() {
        client.close()
    }

    override fun logStatusNotification(
        meta: RequestMetadata,
        req: LogStatusNotificationReq
    ): OperationExecution<LogStatusNotificationReq, LogStatusNotificationResp> =
        sendMessage(meta, LOG_STATUS_NOTIFICATION.value, req)

    override fun securityEventNotification(
        meta: RequestMetadata,
        req: SecurityEventNotificationReq
    ): OperationExecution<SecurityEventNotificationReq, SecurityEventNotificationResp> =
        sendMessage(meta, SECURITY_EVENT_NOTIFICATION.value, req)

    override fun signCertificate(
        meta: RequestMetadata,
        req: SignCertificateReq
    ): OperationExecution<SignCertificateReq, SignCertificateResp> =
        sendMessage(meta, SIGN_CERTIFICATE.value, req)

    override fun signedFirmwareStatusNotification(
        meta: RequestMetadata,
        req: SignedFirmwareStatusNotificationReq
    ): OperationExecution<SignedFirmwareStatusNotificationReq, SignedFirmwareStatusNotificationResp> =
        sendMessage(meta, SIGNED_FIRMWARE_STATUS_NOTIFICATION.value, req)

    private inline fun <T, reified P : Any> sendMessage(
        meta: RequestMetadata,
        action: String,
        request: T
    ): OperationExecution<T, P> {
        val requestTime = Clock.System.now()
        val response: P = client.sendMessage(action, request)
        val responseTime = Clock.System.now()
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, requestTime, responseTime),
            request,
            response
        )
    }
}
