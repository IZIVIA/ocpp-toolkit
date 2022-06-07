package com.izivia.ocpp.core16

import com.izivia.ocpp.core16.impl.RealChargePointOperations
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.transport.Transport
import java.net.ConnectException

/**
 * Operations initiated by Charge Point
 */
interface ChargePointOperations {
    companion object {
        fun newChargePointOperations(chargingStationId: String, transport: Transport, csmsOperations: CSMSOperations) =
            RealChargePointOperations(chargingStationId,transport, csmsOperations)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    fun connect()

    @Throws(IllegalStateException::class, ConnectException::class)
    fun close()

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
     * Sends a StartTransaction request to the Central System to inform about a transaction that has been started.
     * If this transaction ends a reservation the StartTransaction.req MUST contain the reservationId.
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun startTransaction(meta: RequestMetadata, request: StartTransactionReq): OperationExecution<StartTransactionReq, StartTransactionResp>

    /**
     * Sends a StopTransaction request to the Central System to inform about a transaction that has been stopped.
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun stopTransaction(meta: RequestMetadata, request: StopTransactionReq): OperationExecution<StopTransactionReq, StopTransactionResp>

    /**
     * Sends a notification to the Central System about a status change or an error within the Charge Point
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
     * Sends notifications to inform the Central System about the progress of the firmware update
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun firmwareStatusNotification(meta: RequestMetadata, request: FirmwareStatusNotificationReq): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp>


    /**
     * Sends log information to the CSMS
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun diagnosticsStatusNotification(meta: RequestMetadata, request: DiagnosticsStatusNotificationReq): OperationExecution<DiagnosticsStatusNotificationReq, DiagnosticsStatusNotificationResp>
}