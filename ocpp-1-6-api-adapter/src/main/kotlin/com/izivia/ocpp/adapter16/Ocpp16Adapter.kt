package com.izivia.ocpp.adapter16

import com.izivia.ocpp.adapter16.mapper.*
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.CSMSApi
import com.izivia.ocpp.api.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.api.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.api.model.clearedcharginglimit.ClearedChargingLimitReq
import com.izivia.ocpp.api.model.clearedcharginglimit.ClearedChargingLimitResp
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq
import com.izivia.ocpp.api.model.datatransfer.DataTransferResp
import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.api.model.getcertificatestatus.GetCertificateStatusReq
import com.izivia.ocpp.api.model.getcertificatestatus.GetCertificateStatusResp
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.api.model.metervalues.MeterValuesReq
import com.izivia.ocpp.api.model.metervalues.MeterValuesResp
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesResp
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationResp
import com.izivia.ocpp.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import com.izivia.ocpp.api.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import com.izivia.ocpp.api.model.notifyevent.NotifyEventReq
import com.izivia.ocpp.api.model.notifyevent.NotifyEventResp
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitReq
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitResp
import com.izivia.ocpp.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import com.izivia.ocpp.api.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import com.izivia.ocpp.api.model.notifymonitoringreport.NotifyMonitoringReportReq
import com.izivia.ocpp.api.model.notifymonitoringreport.NotifyMonitoringReportResp
import com.izivia.ocpp.api.model.notifyreport.NotifyReportReq
import com.izivia.ocpp.api.model.notifyreport.NotifyReportResp
import com.izivia.ocpp.api.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import com.izivia.ocpp.api.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationResp
import com.izivia.ocpp.api.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.api.model.reportchargingprofiles.ReportChargingProfilesResp
import com.izivia.ocpp.api.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.api.model.reservationstatusupdate.ReservationStatusUpdateResp
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.api.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.api.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.api.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.api.model.transactionevent.TransactionEventResp
import com.izivia.ocpp.api.model.transactionevent.enumeration.TransactionEventEnumType
import com.izivia.ocpp.core16.ChargePointOperations
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.ClientTransport
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import java.net.ConnectException
import com.izivia.ocpp.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import com.izivia.ocpp.api.model.authorize.AuthorizeResp as AuthorizeRespGen
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import com.izivia.ocpp.api.model.heartbeat.HeartbeatResp as HeartbeatRespGen

class Ocpp16Adapter(
    chargingStationId: String,
    private val transport: ClientTransport,
    csApi: CSApi,
    private val transactionIds: TransactionRepository
) : CSMSApi {

    companion object {
        private val logger = LoggerFactory.getLogger(Ocpp16Adapter::class.java)
    }

    private val operations: ChargePointOperations =
        ChargePointOperations.newChargePointOperations(chargingStationId, transport, Ocpp16CSApiAdapter(csApi))

    override fun connect() {
        transport.connect()
    }

    override fun close() {
        transport.close()
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(
        meta: RequestMetadata,
        request: HeartbeatReqGen
    ): OperationExecution<HeartbeatReqGen, HeartbeatRespGen> {
        val mapper: HeartbeatMapper = Mappers.getMapper(HeartbeatMapper::class.java)
        val response = operations.heartbeat(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun authorize(
        meta: RequestMetadata,
        request: AuthorizeReqGen
    ): OperationExecution<AuthorizeReqGen, AuthorizeRespGen> {
        val mapper: AuthorizeMapper = Mappers.getMapper(AuthorizeMapper::class.java)
        val response = operations.authorize(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun meterValues(
        meta: RequestMetadata,
        request: MeterValuesReq
    ): OperationExecution<MeterValuesReq, MeterValuesResp> = try {
        val mapper: MeterValuesMapper = Mappers.getMapper(MeterValuesMapper::class.java)
        val response = operations.meterValues(meta, mapper.genToCoreReq(request))
        OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    } catch (e: IllegalStateException) {
        logger.warn(e.message)
        OperationExecution(ExecutionMetadata(meta, RequestStatus.NOT_SEND), request, MeterValuesResp())
    }

    override fun dataTransfer(
        meta: RequestMetadata,
        request: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp> {
        val mapper: DataTransferMapper = Mappers.getMapper(DataTransferMapper::class.java)
        val response = operations.dataTransfer(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun bootNotification(
        meta: RequestMetadata,
        request: BootNotificationReq
    ): OperationExecution<BootNotificationReq, BootNotificationResp> {
        val mapper: BootNotificationMapper = Mappers.getMapper(BootNotificationMapper::class.java)
        val response = operations.bootNotification(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    private fun updateTransactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): OperationExecution<TransactionEventReq, TransactionEventResp> {
        val mapper: StatusNotificationMapper = Mappers.getMapper(StatusNotificationMapper::class.java)
        val response = operations.statusNotification(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenRespTransac(response.response))
    }

    private fun updateStatusEvent(
        meta: RequestMetadata,
        request: TransactionEventReq,
        responseMeta: ExecutionMetadata
    ): ExecutionMetadata {
        var executionMetadata = responseMeta
        if (request.transactionInfo.chargingState != null) {
            // Add 1ms to the timestamp so that the statusNotification request timestamp
            // is the latest one compare to the previous request timestamp
            request.timestamp = request.timestamp.plus(1, DateTimeUnit.MILLISECOND)
            val updateResponse = updateTransactionEvent(meta, request)
            executionMetadata = executionMetadata.combine(updateResponse.executionMeta)
        }
        return executionMetadata
    }

    private fun stopTransactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): OperationExecution<TransactionEventReq, TransactionEventResp> {
        val mapper: StopTransactionMapper = Mappers.getMapper(StopTransactionMapper::class.java)
        val transactionId =
            transactionIds.getTransactionIdsByLocalId(request.transactionInfo.transactionId).csmsId
        val response = operations.stopTransaction(meta, mapper.genToCoreReq(request, transactionId))
        val executionMetadata = updateStatusEvent(meta, request, response.executionMeta)
        return OperationExecution(executionMetadata, request, mapper.coreToGenResp(response.response))
    }


    private fun startTransactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): OperationExecution<TransactionEventReq, TransactionEventResp> {
        val mapper: StartTransactionMapper = Mappers.getMapper(StartTransactionMapper::class.java)
        val response = operations.startTransaction(meta, mapper.genToCoreReq(request))
        transactionIds.saveTransactionIds(
            Ocpp16TransactionIds(
                request.transactionInfo.transactionId,
                response.response.transactionId
            )
        )
        val executionMetadata = updateStatusEvent(meta, request, response.executionMeta)
        return OperationExecution(executionMetadata, request, mapper.coreToGenResp(response.response))
    }

    override fun transactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): OperationExecution<TransactionEventReq, TransactionEventResp> =
        when (request.eventType) {
            TransactionEventEnumType.Started -> {
                startTransactionEvent(meta, request)
            }
            TransactionEventEnumType.Ended -> {
                stopTransactionEvent(meta, request)
            }
            TransactionEventEnumType.Updated -> {
                updateTransactionEvent(meta, request)
            }
        }

    override fun statusNotification(
        meta: RequestMetadata,
        request: StatusNotificationReq
    ): OperationExecution<StatusNotificationReq, StatusNotificationResp> {
        val mapper: StatusNotificationMapper = Mappers.getMapper(StatusNotificationMapper::class.java)
        val response = operations.statusNotification(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun notifyReport(
        meta: RequestMetadata,
        request: NotifyReportReq
    ): OperationExecution<NotifyReportReq, NotifyReportResp> {
        throw IllegalStateException("NotifyReport can't be call in OCPP 1.6")
    }

    override fun firmwareStatusNotification(
        meta: RequestMetadata,
        request: FirmwareStatusNotificationReq
    ): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp> {
        val mapper: FirmwareStatusNotificationMapper = Mappers.getMapper(FirmwareStatusNotificationMapper::class.java)
        val response = operations.firmwareStatusNotification(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun clearedChargingLimit(
        meta: RequestMetadata,
        request: ClearedChargingLimitReq
    ): OperationExecution<ClearedChargingLimitReq, ClearedChargingLimitResp> {
        throw IllegalStateException("clearedChargingLimit can't be call in OCPP 1.6")
    }

    override fun getCertificateStatus(
        meta: RequestMetadata,
        request: GetCertificateStatusReq
    ): OperationExecution<GetCertificateStatusReq, GetCertificateStatusResp> {
        throw IllegalStateException("GetCertificateStatus can't be call in OCPP 1.6")
    }

    override fun notifyCustomerInformation(
        meta: RequestMetadata,
        request: NotifyCustomerInformationReq
    ): OperationExecution<NotifyCustomerInformationReq, NotifyCustomerInformationResp> {
        throw IllegalStateException("notifyCustomerInformation can't be call in OCPP 1.6")
    }

    override fun notifyEvent(
        meta: RequestMetadata,
        request: NotifyEventReq
    ): OperationExecution<NotifyEventReq, NotifyEventResp> {
        throw IllegalStateException("NotifyEvent can't be call in OCPP 1.6")
    }

    override fun notifyEVChargingSchedule(
        meta: RequestMetadata,
        request: NotifyEVChargingScheduleReq
    ): OperationExecution<NotifyEVChargingScheduleReq, NotifyEVChargingScheduleResp> {
        throw IllegalStateException("notifyCustomerInformation can't be called in OCPP 1.6")
    }

    override fun notifyChargingLimit(
        meta: RequestMetadata,
        request: NotifyChargingLimitReq
    ): OperationExecution<NotifyChargingLimitReq, NotifyChargingLimitResp> {
        throw IllegalStateException("NotifyChargingLimit can't be call in OCPP 1.6")
    }

    override fun notifyDisplayMessages(
        meta: RequestMetadata,
        request: NotifyDisplayMessagesReq
    ): OperationExecution<NotifyDisplayMessagesReq, NotifyDisplayMessagesResp> {
        throw IllegalStateException("notifyDisplayMessages can't be call in OCPP 1.6")
    }

    override fun notifyEVChargingNeeds(
        meta: RequestMetadata,
        request: NotifyEVChargingNeedsReq
    ): OperationExecution<NotifyEVChargingNeedsReq, NotifyEVChargingNeedsResp> {
        throw IllegalStateException("NotifyEVChargingNeeds can't be call in OCPP 1.6")
    }

    override fun logStatusNotification(
        meta: RequestMetadata,
        request: LogStatusNotificationReq
    ): OperationExecution<LogStatusNotificationReq, LogStatusNotificationResp> {
        val mapper: DiagnosticsStatusNotificationMapper = Mappers.getMapper(DiagnosticsStatusNotificationMapper::class.java)
        val response = operations.diagnosticsStatusNotification(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun publishFirmwareStatusNotification(
        meta: RequestMetadata,
        request: PublishFirmwareStatusNotificationReq
    ): OperationExecution<PublishFirmwareStatusNotificationReq, PublishFirmwareStatusNotificationResp> {
        throw IllegalStateException("PublishFirmwareStatusNotification can't be call in OCPP 1.6")
    }

    override fun notifyMonitoringReport(
        meta: RequestMetadata,
        request: NotifyMonitoringReportReq
    ): OperationExecution<NotifyMonitoringReportReq, NotifyMonitoringReportResp> {
        throw IllegalStateException("NotifyMonitoringReport can't be call in OCPP 1.6")
    }

    override fun reservationStatusUpdate(
        meta: RequestMetadata,
        request: ReservationStatusUpdateReq
    ): OperationExecution<ReservationStatusUpdateReq, ReservationStatusUpdateResp> {
        throw IllegalStateException("ReservationStatusUpdate can't be call in OCPP 1.6")
    }

    override fun securityEventNotification(
        meta: RequestMetadata,
        request: SecurityEventNotificationReq
    ): OperationExecution<SecurityEventNotificationReq, SecurityEventNotificationResp> {
        throw IllegalStateException("SecurityEventNotification can't be call in OCPP 1.6")
    }

    override fun signCertificate(
        meta: RequestMetadata,
        request: SignCertificateReq
    ): OperationExecution<SignCertificateReq, SignCertificateResp> {
        throw IllegalStateException("SignCertificate can't be call in OCPP 1.6")
    }

    override fun reportChargingProfiles(
            meta: RequestMetadata,
            request: ReportChargingProfilesReq
    ): OperationExecution<ReportChargingProfilesReq, ReportChargingProfilesResp> {
        throw IllegalStateException("ReportChargingProfiles can't be call in OCPP 1.6")
    }
}