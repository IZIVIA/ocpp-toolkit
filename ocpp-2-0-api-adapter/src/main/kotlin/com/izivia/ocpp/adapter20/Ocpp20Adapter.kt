package com.izivia.ocpp.adapter20

import com.izivia.ocpp.adapter20.mapper.*
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.CSMSApi
import com.izivia.ocpp.api.model.authorize.AuthorizeReq
import com.izivia.ocpp.api.model.authorize.AuthorizeResp
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
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitReq
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitResp
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationResp
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesResp
import com.izivia.ocpp.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import com.izivia.ocpp.api.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import com.izivia.ocpp.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import com.izivia.ocpp.api.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import com.izivia.ocpp.api.model.notifyevent.NotifyEventReq
import com.izivia.ocpp.api.model.notifyevent.NotifyEventResp
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
import com.izivia.ocpp.core20.ChargePointOperations
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.Transport
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import java.net.ConnectException
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import com.izivia.ocpp.api.model.heartbeat.HeartbeatResp as HeartbeatRespGen

class Ocpp20Adapter(chargingStationId: String, private val transport: Transport, csApi: CSApi) : CSMSApi {

    companion object {
        private val logger = LoggerFactory.getLogger(Ocpp20Adapter::class.java)
    }

    private val operations = ChargePointOperations.newChargePointOperations(chargingStationId, transport, Ocpp20CSApiAdapter(csApi))

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
        request: AuthorizeReq
    ): OperationExecution<AuthorizeReq, AuthorizeResp> {
        val mapper: AuthorizeMapper = Mappers.getMapper(AuthorizeMapper::class.java)
        val response = operations.authorize(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun meterValues(
        meta: RequestMetadata,
        request: MeterValuesReq
    ): OperationExecution<MeterValuesReq, MeterValuesResp> =
        try {
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

    override fun transactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): OperationExecution<TransactionEventReq, TransactionEventResp> {
        val mapper: TransactionEventMapper = Mappers.getMapper(TransactionEventMapper::class.java)
        val response = operations.transactionEvent(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
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
        val mapper: NotifyReportMapper = Mappers.getMapper(NotifyReportMapper::class.java)
        val response = operations.notifyReport(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
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
        val mapper: ClearedChargingLimitMapper = Mappers.getMapper(ClearedChargingLimitMapper::class.java)
        val response = operations.clearedChargingLimit(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun getCertificateStatus(
        meta: RequestMetadata,
        request: GetCertificateStatusReq
    ): OperationExecution<GetCertificateStatusReq, GetCertificateStatusResp> {
        val mapper: GetCertificateStatusMapper = Mappers.getMapper(GetCertificateStatusMapper::class.java)
        val response = operations.getCertificateStatus(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun notifyCustomerInformation(
        meta: RequestMetadata,
        request: NotifyCustomerInformationReq
    ): OperationExecution<NotifyCustomerInformationReq, NotifyCustomerInformationResp> {
        val mapper: NotifyCustomerInformationMapper = Mappers.getMapper(NotifyCustomerInformationMapper::class.java)
        val response = operations.notifyCustomerInformation(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun notifyEvent(
        meta: RequestMetadata,
        request: NotifyEventReq
    ): OperationExecution<NotifyEventReq, NotifyEventResp> {
        val mapper: NotifyEventMapper = Mappers.getMapper(NotifyEventMapper::class.java)
        val response = operations.notifyEvent(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun notifyEVChargingSchedule(
        meta: RequestMetadata,
        request: NotifyEVChargingScheduleReq
    ): OperationExecution<NotifyEVChargingScheduleReq, NotifyEVChargingScheduleResp> {
        val mapper: NotifyEVChargingScheduleMapper = Mappers.getMapper(NotifyEVChargingScheduleMapper::class.java)
        val response = operations.notifyEVChargingSchedule(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun notifyChargingLimit(
        meta: RequestMetadata,
        request: NotifyChargingLimitReq
    ): OperationExecution<NotifyChargingLimitReq, NotifyChargingLimitResp> {
        val mapper: NotifyChargingLimitMapper = Mappers.getMapper(NotifyChargingLimitMapper::class.java)
        val response = operations.notifyChargingLimit(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun notifyDisplayMessages(
        meta: RequestMetadata,
        request: NotifyDisplayMessagesReq
    ): OperationExecution<NotifyDisplayMessagesReq, NotifyDisplayMessagesResp> {
        val mapper: NotifyDisplayMessagesMapper = Mappers.getMapper(NotifyDisplayMessagesMapper::class.java)
        val response = operations.notifyDisplayMessages(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun notifyEVChargingNeeds(
        meta: RequestMetadata,
        request: NotifyEVChargingNeedsReq
    ): OperationExecution<NotifyEVChargingNeedsReq, NotifyEVChargingNeedsResp> {
        val stateOfCharge = request.chargingNeeds.dcChargingParameters?.stateOfCharge
        val fullSoC = request.chargingNeeds.dcChargingParameters?.fullSoC
        val bulkSoC = request.chargingNeeds.dcChargingParameters?.bulkSoC
        if ((stateOfCharge == null || stateOfCharge in 0..100) &&
            (fullSoC == null || fullSoC in 0..100) &&
            (bulkSoC == null || bulkSoC in 0..100)
        ) {
            val mapper: NotifyEVChargingNeedsMapper = Mappers.getMapper(NotifyEVChargingNeedsMapper::class.java)
            val response = operations.notifyEVChargingNeeds(meta, mapper.genToCoreReq(request))
            return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
        } else throw IllegalStateException("State of charge should be contained between 0 and 100")
    }

    override fun logStatusNotification(
        meta: RequestMetadata,
        request: LogStatusNotificationReq
    ): OperationExecution<LogStatusNotificationReq, LogStatusNotificationResp> {
        val mapper: LogStatusNotificationMapper = Mappers.getMapper(LogStatusNotificationMapper::class.java)
        val response = operations.logStatusNotification(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun publishFirmwareStatusNotification(
        meta: RequestMetadata,
        request: PublishFirmwareStatusNotificationReq
    ): OperationExecution<PublishFirmwareStatusNotificationReq, PublishFirmwareStatusNotificationResp> {
        request.checkBusinessPublishFirmwareStatusNotificationRequest()
        val mapper: PublishFirmwareStatusNotificationMapper = Mappers.getMapper(PublishFirmwareStatusNotificationMapper::class.java)
        val response = operations.publishFirmwareStatusNotification(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun notifyMonitoringReport(
        meta: RequestMetadata,
        request: NotifyMonitoringReportReq
    ): OperationExecution<NotifyMonitoringReportReq, NotifyMonitoringReportResp> {
        val severityChecked =
            request.monitor?.all { it.variableMonitoring.all { variableMonitoringType -> variableMonitoringType.severity in 0..9 } }
        if (severityChecked == null || severityChecked) {
            val mapper: NotifyMonitoringReportMapper = Mappers.getMapper(NotifyMonitoringReportMapper::class.java)
            val response = operations.notifyMonitoringReport(meta, mapper.genToCoreReq(request))
            return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
        } else throw IllegalStateException("Severity should be contained between 0 and 9")
    }

    override fun reservationStatusUpdate(
        meta: RequestMetadata,
        request: ReservationStatusUpdateReq
    ): OperationExecution<ReservationStatusUpdateReq, ReservationStatusUpdateResp> {
        val mapper: ReservationStatusUpdateMapper = Mappers.getMapper(ReservationStatusUpdateMapper::class.java)
        val response = operations.reservationStatusUpdate(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun securityEventNotification(
        meta: RequestMetadata,
        request: SecurityEventNotificationReq
    ): OperationExecution<SecurityEventNotificationReq, SecurityEventNotificationResp> {
        val mapper: SecurityEventNotificationMapper = Mappers.getMapper(SecurityEventNotificationMapper::class.java)
        val response = operations.securityEventNotification(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun signCertificate(
        meta: RequestMetadata,
        request: SignCertificateReq
    ): OperationExecution<SignCertificateReq, SignCertificateResp> {
        val mapper: SignCertificateMapper = Mappers.getMapper(SignCertificateMapper::class.java)
        val response = operations.signCertificate(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }

    override fun reportChargingProfiles(
            meta: RequestMetadata,
            request: ReportChargingProfilesReq
    ): OperationExecution<ReportChargingProfilesReq, ReportChargingProfilesResp> {
        val mapper: ReportChargingProfilesMapper = Mappers.getMapper(ReportChargingProfilesMapper::class.java)
        val response = operations.reportChargingProfiles(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))
    }
}

private fun PublishFirmwareStatusNotificationReq.checkBusinessPublishFirmwareStatusNotificationRequest(): Boolean {
    if (status == com.izivia.ocpp.api.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType.Published && location.isNullOrEmpty()) {
        throw IllegalStateException("Location is required if status is Published")
    }
    return true
}
