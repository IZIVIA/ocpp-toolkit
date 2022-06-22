package com.izivia.ocpp.core20

import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitResp
import com.izivia.ocpp.core20.impl.RealChargePointOperations
import com.izivia.ocpp.core20.model.authorize.AuthorizeReq
import com.izivia.ocpp.core20.model.authorize.AuthorizeResp
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core20.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core20.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusReq
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusResp
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.core20.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core20.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationResp
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventReq
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventResp
import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitReq
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitResp
import com.izivia.ocpp.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import com.izivia.ocpp.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportReq
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportResp
import com.izivia.ocpp.core20.model.notifyreport.NotifyReportReq
import com.izivia.ocpp.core20.model.notifyreport.NotifyReportResp
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationResp
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesResp
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateResp
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventResp
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.transport.ClientTransport
import java.net.ConnectException

/**
 * Operations initiated by Charge Point
 */
interface ChargePointOperations {
    companion object {
        fun newChargePointOperations(chargingStationId: String, transport: ClientTransport, csmsOperations: CSMSOperations) =
                RealChargePointOperations(chargingStationId, transport, csmsOperations)
    }

    /**
     * Sends a Heartbeat request to let the Central System know the Charge Point is still connected
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun heartbeat(meta: RequestMetadata, request: HeartbeatReq): OperationExecution<HeartbeatReq, HeartbeatResp>

    /**
     * Sends an Authorize request to the Central System if the identifier is authorize to start/stop
     * to charge.
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun authorize(meta: RequestMetadata, request: AuthorizeReq): OperationExecution<AuthorizeReq, AuthorizeResp>

    /**
     * Sends periodic, possibly clock-aligned MeterValues
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun meterValues(meta: RequestMetadata, request: MeterValuesReq): OperationExecution<MeterValuesReq, MeterValuesResp>

    /**
     * Sends information about a transaction event (started, updated, ended)
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun transactionEvent(meta: RequestMetadata, request: TransactionEventReq): OperationExecution<TransactionEventReq, TransactionEventResp>

    /**
     * Sends a notification to the Central System about a status change or an error within a connector
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun statusNotification(meta: RequestMetadata, request: StatusNotificationReq): OperationExecution<StatusNotificationReq, StatusNotificationResp>

    /**
     * Sends information to the Central System for a function not supported by OCPP
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun dataTransfer(meta: RequestMetadata, request: DataTransferReq): OperationExecution<DataTransferReq, DataTransferResp>

    /**
     * Sends a request to the Central System with information about its configuration
     * each time it boots or reboots
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun bootNotification(meta: RequestMetadata, request: BootNotificationReq): OperationExecution<BootNotificationReq, BootNotificationResp>

    /**
     * Sends a message to the CSMS after receiving a GetBaseReport or GetReport request
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun notifyReport(meta: RequestMetadata, request: NotifyReportReq): OperationExecution<NotifyReportReq, NotifyReportResp>

    /**
     * Sends notifications to inform the Central System about the progress of the firmware update
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun firmwareStatusNotification(meta: RequestMetadata, request: FirmwareStatusNotificationReq): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp>

    /**
     * Sends a message to the CSMS to notify it that the charge limit has been changed
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun clearedChargingLimit(meta: RequestMetadata, request: ClearedChargingLimitReq): OperationExecution<ClearedChargingLimitReq, ClearedChargingLimitResp>

    /**
     * Sends a request to the Central System to obtain the certificate status information
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun getCertificateStatus(meta: RequestMetadata, request: GetCertificateStatusReq): OperationExecution<GetCertificateStatusReq, GetCertificateStatusResp>

    /**
     * Sends a message to the CSMS to transfer customer informations
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun notifyCustomerInformation(meta: RequestMetadata, request: NotifyCustomerInformationReq): OperationExecution<NotifyCustomerInformationReq, NotifyCustomerInformationResp>

    /**
     * NotifyEventRequest reports every Component/Variable for which a VariableMonitoring setting was triggered.
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun notifyEvent(meta: RequestMetadata, request: NotifyEventReq): OperationExecution<NotifyEventReq, NotifyEventResp>

    /**
     * Sends a message to the CSMS to notify the charging schedule as calculated by the EV
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun notifyEVChargingSchedule(meta: RequestMetadata, request: NotifyEVChargingScheduleReq): OperationExecution<NotifyEVChargingScheduleReq, NotifyEVChargingScheduleResp>

    /**
     * Sends a message to the CSMS to notify the charging limit
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun notifyChargingLimit(meta: RequestMetadata, request: NotifyChargingLimitReq): OperationExecution<NotifyChargingLimitReq, NotifyChargingLimitResp>

    /**
     * Sends display messages to the CSMS
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun notifyDisplayMessages(meta: RequestMetadata, request: NotifyDisplayMessagesReq): OperationExecution<NotifyDisplayMessagesReq, NotifyDisplayMessagesResp>

    /**
     * Sends information to the Central System about the ev charging needs
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun notifyEVChargingNeeds(meta: RequestMetadata, request: NotifyEVChargingNeedsReq): OperationExecution<NotifyEVChargingNeedsReq, NotifyEVChargingNeedsResp>

    /**
     * Sends log information to the CSMS
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun logStatusNotification(meta: RequestMetadata, request: LogStatusNotificationReq): OperationExecution<LogStatusNotificationReq, LogStatusNotificationResp>

    /**
     * Sends a message to the CSMS to notify the progress status of the publishfirmware installation
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun publishFirmwareStatusNotification(meta: RequestMetadata, request: PublishFirmwareStatusNotificationReq): OperationExecution<PublishFirmwareStatusNotificationReq, PublishFirmwareStatusNotificationResp>

    /**
     * Sends a message to the CSMS to notify the monitoring report
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun notifyMonitoringReport(meta: RequestMetadata, request: NotifyMonitoringReportReq): OperationExecution<NotifyMonitoringReportReq, NotifyMonitoringReportResp>

    /**
     * Sends the field definition of a reservation status update to the CSMS
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun reservationStatusUpdate(meta: RequestMetadata, request: ReservationStatusUpdateReq): OperationExecution<ReservationStatusUpdateReq, ReservationStatusUpdateResp>

    /**
     * Sends message to the CSMS in case of a security event
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun securityEventNotification(meta: RequestMetadata, request: SecurityEventNotificationReq): OperationExecution<SecurityEventNotificationReq, SecurityEventNotificationResp>

    /**
     * Sends message to the CSMS to request that the Certificate Authority signs the public key into a certificate
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun signCertificate(meta: RequestMetadata, request: SignCertificateReq): OperationExecution<SignCertificateReq, SignCertificateResp>


    @Throws(IllegalStateException::class, ConnectException::class)
    fun reportChargingProfiles(meta: RequestMetadata, request: ReportChargingProfilesReq): OperationExecution<ReportChargingProfilesReq, ReportChargingProfilesResp>
    fun connect()

    fun close()
}