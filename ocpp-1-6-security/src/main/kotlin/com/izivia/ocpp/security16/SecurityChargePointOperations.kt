package com.izivia.ocpp.security16

import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.core16.model.signedfirmwarestatusnotification.SignedFirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.signedfirmwarestatusnotification.SignedFirmwareStatusNotificationResp
import com.izivia.ocpp.operation.information.CSMSCallbacks
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.security16.impl.RealSecurityChargePointOperations
import com.izivia.ocpp.transport.ClientTransport

interface SecurityChargePointOperations : CSMSCallbacks {
    companion object {
        fun newSecurityChargePointOperations(
            chargingStationId: String,
            transport: ClientTransport,
            csmsOperations: SecurityCSMSOperations
        ): SecurityChargePointOperations =
            RealSecurityChargePointOperations(chargingStationId, transport, csmsOperations)
    }

    fun logStatusNotification(
        meta: RequestMetadata,
        req: LogStatusNotificationReq
    ): OperationExecution<LogStatusNotificationReq, LogStatusNotificationResp>

    fun securityEventNotification(
        meta: RequestMetadata,
        req: SecurityEventNotificationReq
    ): OperationExecution<SecurityEventNotificationReq, SecurityEventNotificationResp>

    fun signCertificate(
        meta: RequestMetadata,
        req: SignCertificateReq
    ): OperationExecution<SignCertificateReq, SignCertificateResp>

    fun signedFirmwareStatusNotification(
        meta: RequestMetadata,
        req: SignedFirmwareStatusNotificationReq
    ): OperationExecution<SignedFirmwareStatusNotificationReq, SignedFirmwareStatusNotificationResp>
}
