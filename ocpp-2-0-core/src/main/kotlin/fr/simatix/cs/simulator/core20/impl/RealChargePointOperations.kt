package fr.simatix.cs.simulator.core20.impl

import fr.simatix.cs.simulator.core20.CSMSOperations
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core20.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import fr.simatix.cs.simulator.core20.model.clearedcharginglimit.ClearedChargingLimitResp
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import fr.simatix.cs.simulator.core20.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.core20.model.getcertificatestatus.GetCertificateStatusReq
import fr.simatix.cs.simulator.core20.model.getcertificatestatus.GetCertificateStatusResp
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core20.model.getreport.GetReportReq
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core20.model.logstatusnotification.LogStatusNotificationReq
import fr.simatix.cs.simulator.core20.model.logstatusnotification.LogStatusNotificationResp
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core20.model.notifycustomerinformation.NotifyCustomerInformationReq
import fr.simatix.cs.simulator.core20.model.notifycustomerinformation.NotifyCustomerInformationResp
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
import fr.simatix.cs.simulator.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import fr.simatix.cs.simulator.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import fr.simatix.cs.simulator.core20.model.notifyevent.NotifyEventReq
import fr.simatix.cs.simulator.core20.model.notifyevent.NotifyEventResp
import fr.simatix.cs.simulator.core20.model.notifycharginglimit.NotifyChargingLimitReq
import fr.simatix.cs.simulator.core20.model.notifycharginglimit.NotifyChargingLimitResp
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import fr.simatix.cs.simulator.core20.model.notifymonitoringreport.NotifyMonitoringReportReq
import fr.simatix.cs.simulator.core20.model.notifymonitoringreport.NotifyMonitoringReportResp
import fr.simatix.cs.simulator.core20.model.notifyreport.NotifyReportReq
import fr.simatix.cs.simulator.core20.model.notifyreport.NotifyReportResp
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.core20.model.reservationstatusupdate.ReservationStatusUpdateReq
import fr.simatix.cs.simulator.core20.model.reservationstatusupdate.ReservationStatusUpdateResp
import fr.simatix.cs.simulator.core20.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.securityeventnotification.SecurityEventNotificationReq
import fr.simatix.cs.simulator.core20.model.securityeventnotification.SecurityEventNotificationResp
import fr.simatix.cs.simulator.core20.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.core20.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.core20.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core20.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.receiveMessage
import fr.simatix.cs.simulator.transport.sendMessage
import kotlinx.datetime.Clock
import java.net.ConnectException

class RealChargePointOperations(
    private val chargeStationId: String,
    private val client: Transport,
    private val csmsOperations: CSMSOperations
) : ChargePointOperations {

    init {
        client.receiveMessage("Reset") { req: ResetReq ->
            csmsOperations.reset(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("ChangeAvailability") { req: ChangeAvailabilityReq ->
            csmsOperations.changeAvailability(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("SetVariables") { req: SetVariablesReq ->
            csmsOperations.setVariables(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("ClearCache") { req: ClearCacheReq ->
            csmsOperations.clearCache(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("RequestStartTransaction") { req: RequestStartTransactionReq ->
            csmsOperations.requestStartTransaction(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("RequestStopTransaction") { req: RequestStopTransactionReq ->
            csmsOperations.requestStopTransaction(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("UnlockConnector") { req: UnlockConnectorReq ->
            csmsOperations.unlockConnector(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("GetVariables") { req: GetVariablesReq ->
            csmsOperations.getVariables(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("GetReport") { req: GetReportReq ->
            csmsOperations.getReport(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("GetBaseReport") { req: GetBaseReportReq ->
            csmsOperations.getBaseReport(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("SetChargingProfile") { req: SetChargingProfileReq ->
            csmsOperations.setChargingProfile(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("TriggerMessage") { req: TriggerMessageReq ->
            csmsOperations.triggerMessage(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("SendLocalList") { req: SendLocalListReq ->
            csmsOperations.sendLocalList(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("GetLocalListVersion") { req: GetLocalListVersionReq ->
            csmsOperations.getLocalListVersion(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("CancelReservation") { req: CancelReservationReq ->
            csmsOperations.cancelReservation(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("ClearChargingProfile") { req: ClearChargingProfileReq ->
            csmsOperations.clearChargingProfile(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("GetCompositeSchedule") { req: GetCompositeScheduleReq ->
            csmsOperations.getCompositeSchedule(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("UpdateFirmware") { req: UpdateFirmwareReq ->
            csmsOperations.updateFirmware(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("ReserveNow") { req: ReserveNowReq ->
            csmsOperations.reserveNow(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("DataTransfer") { req: DataTransferReq ->
            csmsOperations.dataTransfer(
                RequestMetadata(chargeStationId),
                req
            ).response
        }
    }

    private inline fun <T, reified P> sendMessage(
        meta: RequestMetadata,
        action: String,
        request: T
    ): OperationExecution<T, P> {
        val requestTime = Clock.System.now()
        val response: P = client.sendMessage(action, request)
        val responseTime = Clock.System.now()
        return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS, requestTime, responseTime), request, response)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(meta: RequestMetadata, request: HeartbeatReq): OperationExecution<HeartbeatReq, HeartbeatResp> =
        sendMessage(meta, "Heartbeat", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun authorize(meta: RequestMetadata, request: AuthorizeReq): OperationExecution<AuthorizeReq, AuthorizeResp> =
        sendMessage(meta, "Authorize", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun meterValues(meta: RequestMetadata, request: MeterValuesReq): OperationExecution<MeterValuesReq, MeterValuesResp> =
        sendMessage(meta, "MeterValues", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun transactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): OperationExecution<TransactionEventReq, TransactionEventResp> =
        sendMessage(meta, "TransactionEvent", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun statusNotification(
        meta: RequestMetadata,
        request: StatusNotificationReq
    ): OperationExecution<StatusNotificationReq, StatusNotificationResp> =
        sendMessage(meta, "StatusNotification", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun dataTransfer(meta: RequestMetadata, request: DataTransferReq): OperationExecution<DataTransferReq, DataTransferResp> =
        sendMessage(meta, "DataTransfer", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun bootNotification(
        meta: RequestMetadata,
        request: BootNotificationReq
    ): OperationExecution<BootNotificationReq, BootNotificationResp> =
        sendMessage(meta, "BootNotification", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun notifyReport(
        meta: RequestMetadata,
        request: NotifyReportReq
    ): OperationExecution<NotifyReportReq, NotifyReportResp> =
        sendMessage(meta, "NotifyReport", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun firmwareStatusNotification(
        meta: RequestMetadata,
        request: FirmwareStatusNotificationReq
    ): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp> =
            sendMessage(meta, "FirmwareStatusNotification", request)

    override fun clearedChargingLimit(
        meta: RequestMetadata,
        request: ClearedChargingLimitReq
    ): OperationExecution<ClearedChargingLimitReq, ClearedChargingLimitResp> =
        sendMessage(meta, "ClearedChargingLimit", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun getCertificateStatus(
        meta: RequestMetadata,
        request: GetCertificateStatusReq
    ): OperationExecution<GetCertificateStatusReq, GetCertificateStatusResp> =
        sendMessage(meta, "GetCertificateStatus", request)

    override fun notifyCustomerInformation(
        meta: RequestMetadata,
        request: NotifyCustomerInformationReq
    ): OperationExecution<NotifyCustomerInformationReq, NotifyCustomerInformationResp> =
        sendMessage(meta, "NotifyCustomerInformation", request)

    override fun notifyEvent(
        meta: RequestMetadata,
        request: NotifyEventReq
    ): OperationExecution<NotifyEventReq, NotifyEventResp> =
        sendMessage(meta, "NotifyEvent", request)

    override fun notifyEVChargingSchedule(
        meta: RequestMetadata,
        request: NotifyEVChargingScheduleReq
    ): OperationExecution<NotifyEVChargingScheduleReq, NotifyEVChargingScheduleResp> =
        sendMessage(meta, "NotifyEVChargingSchedule", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun notifyChargingLimit(
        meta: RequestMetadata,
        request: NotifyChargingLimitReq
    ): OperationExecution<NotifyChargingLimitReq, NotifyChargingLimitResp> =
        sendMessage(meta, "NotifyChargingLimit", request)

    override fun notifyDisplayMessages(
        meta: RequestMetadata,
        request: NotifyDisplayMessagesReq
    ): OperationExecution<NotifyDisplayMessagesReq, NotifyDisplayMessagesResp> =
        sendMessage(meta, "NotifyDisplayMessages", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun notifyEVChargingNeeds(
        meta: RequestMetadata,
        request: NotifyEVChargingNeedsReq
    ): OperationExecution<NotifyEVChargingNeedsReq, NotifyEVChargingNeedsResp> =
        sendMessage(meta, "NotifyEVChargingNeeds", request)

    override fun logStatusNotification(
        meta: RequestMetadata,
        request: LogStatusNotificationReq
    ): OperationExecution<LogStatusNotificationReq, LogStatusNotificationResp> =
        sendMessage(meta, "LogStatusNotification", request)

    override fun notifyMonitoringReport(
        meta: RequestMetadata,
        request: NotifyMonitoringReportReq
    ): OperationExecution<NotifyMonitoringReportReq, NotifyMonitoringReportResp> =
        sendMessage(meta, "NotifyMonitoringReport", request)
    override fun reservationStatusUpdate(
        meta: RequestMetadata,
        request: ReservationStatusUpdateReq
    ): OperationExecution<ReservationStatusUpdateReq, ReservationStatusUpdateResp> =
        sendMessage(meta, "ReservationStatusUpdate", request)
    override fun securityEventNotification(
        meta: RequestMetadata,
        request: SecurityEventNotificationReq
    ): OperationExecution<SecurityEventNotificationReq, SecurityEventNotificationResp> =
        sendMessage(meta, "SecurityEventNotification", request)
}
