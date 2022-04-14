package fr.simatix.cs.simulator.adapter16

import fr.simatix.cs.simulator.adapter16.mapper.*
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitReq
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitResp
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusReq
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusResp
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationResp
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventReq
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventResp
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportReq
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportResp
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TransactionEventEnumType
import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import fr.simatix.cs.simulator.transport.Transport
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.plus
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import java.net.ConnectException
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeResp as AuthorizeRespGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatResp as HeartbeatRespGen

class Ocpp16Adapter(
    chargingStationId: String,
    private val transport: Transport,
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
}