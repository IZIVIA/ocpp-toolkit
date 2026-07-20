package com.izivia.ocpp.adapter16

import com.izivia.ocpp.adapter16.mapper.SecurityMapper
import com.izivia.ocpp.api.CSApi
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
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.security16.SecurityCSMSOperations

class Ocpp16SecurityCSApiAdapter(private val csApi: CSApi) : SecurityCSMSOperations {
    override fun certificateSigned(
        meta: RequestMetadata,
        req: CertificateSignedReq
    ): OperationExecution<CertificateSignedReq, CertificateSignedResp> {
        val response = csApi.certificateSigned(meta, SecurityMapper.coreToGenReq(req))
        return ok(meta, req, SecurityMapper.genToCoreResp(response.response))
    }

    override fun deleteCertificate(
        meta: RequestMetadata,
        req: DeleteCertificateReq
    ): OperationExecution<DeleteCertificateReq, DeleteCertificateResp> {
        val response = csApi.deleteCertificate(meta, SecurityMapper.coreToGenReq(req))
        return ok(meta, req, SecurityMapper.genToCoreResp(response.response))
    }

    override fun extendedTriggerMessage(
        meta: RequestMetadata,
        req: ExtendedTriggerMessageReq
    ): OperationExecution<ExtendedTriggerMessageReq, ExtendedTriggerMessageResp> {
        val response = csApi.triggerMessage(meta, SecurityMapper.coreToGenReq(req))
        return ok(meta, req, SecurityMapper.genToCoreResp(response.response))
    }

    override fun getInstalledCertificateIds(
        meta: RequestMetadata,
        req: GetInstalledCertificateIdsReq
    ): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp> {
        val response = csApi.getInstalledCertificateIds(meta, SecurityMapper.coreToGenReq(req))
        return ok(meta, req, SecurityMapper.genToCoreResp(response.response))
    }

    override fun getLog(meta: RequestMetadata, req: GetLogReq): OperationExecution<GetLogReq, GetLogResp> {
        val response = csApi.getLog(meta, SecurityMapper.coreToGenReq(req))
        return ok(meta, req, SecurityMapper.genToCoreResp(response.response))
    }

    override fun installCertificate(
        meta: RequestMetadata,
        req: InstallCertificateReq
    ): OperationExecution<InstallCertificateReq, InstallCertificateResp> {
        val response = csApi.installCertificate(meta, SecurityMapper.coreToGenReq(req))
        return ok(meta, req, SecurityMapper.genToCoreResp(response.response))
    }

    override fun signedUpdateFirmware(
        meta: RequestMetadata,
        req: SignedUpdateFirmwareReq
    ): OperationExecution<SignedUpdateFirmwareReq, SignedUpdateFirmwareResp> {
        val response = csApi.updateFirmware(meta, SecurityMapper.coreToGenReq(req))
        return ok(meta, req, SecurityMapper.genToCoreResp(response.response))
    }

    private fun <Q, S> ok(meta: RequestMetadata, req: Q, resp: S) =
        OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, resp)
}
