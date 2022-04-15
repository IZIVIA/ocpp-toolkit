package fr.simatix.cs.simulator.adapter20

import fr.simatix.cs.simulator.adapter20.mapper.*
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitReq
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitResp
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportReq
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportResp
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.NotifyDisplayMessagesReq
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.NotifyDisplayMessagesResp
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationResp
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusReq
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusResp
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationReq
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationResp
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventReq
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventResp
import fr.simatix.cs.simulator.api.model.notifycharginglimit.NotifyChargingLimitReq
import fr.simatix.cs.simulator.api.model.notifycharginglimit.NotifyChargingLimitResp
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.NotifyMonitoringReportReq
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.NotifyMonitoringReportResp
import fr.simatix.cs.simulator.api.model.reservationstatusupdate.ReservationStatusUpdateReq
import fr.simatix.cs.simulator.api.model.reservationstatusupdate.ReservationStatusUpdateResp
import fr.simatix.cs.simulator.api.model.securityeventnotification.SecurityEventNotificationReq
import fr.simatix.cs.simulator.api.model.securityeventnotification.SecurityEventNotificationResp
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import fr.simatix.cs.simulator.transport.Transport
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import java.net.ConnectException
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatResp as HeartbeatRespGen

class Ocpp20Adapter(chargingStationId: String,private val transport: Transport, csApi: CSApi) : CSMSApi {

    companion object {
        private val logger = LoggerFactory.getLogger(Ocpp20Adapter::class.java)
    }

    private val operations = ChargePointOperations.newChargePointOperations(chargingStationId,transport,Ocpp20CSApiAdapter(csApi))

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
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))    }

    override fun notifyReport(
        meta: RequestMetadata,
        request: NotifyReportReq
    ): OperationExecution<NotifyReportReq, NotifyReportResp> {
        val mapper: NotifyReportMapper = Mappers.getMapper(NotifyReportMapper::class.java)
        val response = operations.notifyReport(meta, mapper.genToCoreReq(request))
        return OperationExecution(response.executionMeta, request, mapper.coreToGenResp(response.response))     }

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
}