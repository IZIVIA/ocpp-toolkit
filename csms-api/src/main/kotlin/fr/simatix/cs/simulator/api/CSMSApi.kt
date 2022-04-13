package fr.simatix.cs.simulator.api

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitReq
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitResp
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportReq
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportResp
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusReq
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusResp
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationResp

interface CSMSApi {

    fun connect()

    fun close()

    fun heartbeat(meta: RequestMetadata, request: HeartbeatReq): OperationExecution<HeartbeatReq, HeartbeatResp>

    fun authorize(meta: RequestMetadata, request: AuthorizeReq): OperationExecution<AuthorizeReq, AuthorizeResp>

    fun meterValues(meta: RequestMetadata, request: MeterValuesReq): OperationExecution<MeterValuesReq, MeterValuesResp>

    fun dataTransfer(
        meta: RequestMetadata,
        request: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp>

    fun bootNotification(
        meta: RequestMetadata,
        request: BootNotificationReq
    ): OperationExecution<BootNotificationReq, BootNotificationResp>

    fun transactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): OperationExecution<TransactionEventReq, TransactionEventResp>

    fun statusNotification(
        meta: RequestMetadata,
        request: StatusNotificationReq
    ): OperationExecution<StatusNotificationReq, StatusNotificationResp>

    fun notifyReport(
        meta: RequestMetadata,
        request: NotifyReportReq
    ): OperationExecution<NotifyReportReq, NotifyReportResp>

    fun firmwareStatusNotification(
            meta: RequestMetadata,
            request: FirmwareStatusNotificationReq
    ): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp>

    fun clearedChargingLimit(
        meta: RequestMetadata,
        request: ClearedChargingLimitReq
    ): OperationExecution<ClearedChargingLimitReq, ClearedChargingLimitResp>

    fun getCertificateStatus(
        meta: RequestMetadata,
        request: GetCertificateStatusReq
    ): OperationExecution<GetCertificateStatusReq, GetCertificateStatusResp>

    fun notifyCustomerInformation(
        meta: RequestMetadata,
        request: NotifyCustomerInformationReq
    ): OperationExecution<NotifyCustomerInformationReq, NotifyCustomerInformationResp>

}

@Suppress("UNCHECKED_CAST")
fun CSMSApi.send(
    meta: RequestMetadata,
    request: Request
): OperationExecution<Request, Response> =
    when (request) {
        is HeartbeatReq -> heartbeat(meta, request)
        is AuthorizeReq -> authorize(meta, request)
        is MeterValuesReq -> meterValues(meta, request)
        is DataTransferReq -> dataTransfer(meta, request)
        is BootNotificationReq -> bootNotification(meta, request)
        is TransactionEventReq -> transactionEvent(meta, request)
        is StatusNotificationReq -> statusNotification(meta, request)
        is NotifyReportReq -> notifyReport(meta, request)
        is FirmwareStatusNotificationReq -> firmwareStatusNotification(meta, request)
        is ClearedChargingLimitReq -> clearedChargingLimit(meta, request)
        is GetCertificateStatusReq -> getCertificateStatus(meta, request)
        is NotifyCustomerInformationReq -> notifyCustomerInformation(meta, request)
        else -> throw IllegalStateException()
    } as OperationExecution<Request, Response>