package com.izivia.ocpp.security16

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
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareReq
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareResp
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.CSCallbacks
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.security16.impl.RealSecurityCSMSOperations
import com.izivia.ocpp.transport.ServerTransport

interface SecurityCSMSOperations : CSCallbacks {
    companion object {
        fun newSecurityCSMSOperations(
            servers: Set<ServerTransport>,
            acceptConnection: (String) -> ChargingStationConfig,
            chargePointOperations: SecurityChargePointOperations
        ): SecurityCSMSOperations =
            RealSecurityCSMSOperations(servers, acceptConnection, chargePointOperations)
    }

    fun certificateSigned(
        meta: RequestMetadata,
        req: CertificateSignedReq
    ): OperationExecution<CertificateSignedReq, CertificateSignedResp>

    fun deleteCertificate(
        meta: RequestMetadata,
        req: DeleteCertificateReq
    ): OperationExecution<DeleteCertificateReq, DeleteCertificateResp>

    fun extendedTriggerMessage(
        meta: RequestMetadata,
        req: ExtendedTriggerMessageReq
    ): OperationExecution<ExtendedTriggerMessageReq, ExtendedTriggerMessageResp>

    fun getInstalledCertificateIds(
        meta: RequestMetadata,
        req: GetInstalledCertificateIdsReq
    ): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp>

    fun getLog(meta: RequestMetadata, req: GetLogReq): OperationExecution<GetLogReq, GetLogResp>

    fun installCertificate(
        meta: RequestMetadata,
        req: InstallCertificateReq
    ): OperationExecution<InstallCertificateReq, InstallCertificateResp>

    fun signedUpdateFirmware(
        meta: RequestMetadata,
        req: SignedUpdateFirmwareReq
    ): OperationExecution<SignedUpdateFirmwareReq, SignedUpdateFirmwareResp>
}
