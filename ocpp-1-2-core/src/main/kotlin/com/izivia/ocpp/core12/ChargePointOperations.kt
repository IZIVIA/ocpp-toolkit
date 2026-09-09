package com.izivia.ocpp.core12

import com.izivia.ocpp.core12.impl.RealChargePointOperations
import com.izivia.ocpp.core12.model.authorize.AuthorizeReq
import com.izivia.ocpp.core12.model.authorize.AuthorizeResp
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core12.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core12.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.operation.information.CSMSCallbacks
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.transport.ClientTransport
import java.net.ConnectException

interface ChargePointOperations : CSMSCallbacks {
    companion object {
        fun newChargePointOperations(
            chargingStationId: String,
            transport: ClientTransport,
            csmsOperations: CSMSOperations
        ) = RealChargePointOperations(chargingStationId, transport, csmsOperations)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    fun connect()

    @Throws(IllegalStateException::class, ConnectException::class)
    fun close()

    @Throws(IllegalStateException::class, ConnectException::class)
    fun heartbeat(meta: RequestMetadata, request: HeartbeatReq): OperationExecution<HeartbeatReq, HeartbeatResp>

    @Throws(IllegalStateException::class, ConnectException::class)
    fun authorize(meta: RequestMetadata, request: AuthorizeReq): OperationExecution<AuthorizeReq, AuthorizeResp>

    @Throws(IllegalStateException::class, ConnectException::class)
    fun meterValues(meta: RequestMetadata, request: MeterValuesReq): OperationExecution<MeterValuesReq, MeterValuesResp>

    @Throws(IllegalStateException::class, ConnectException::class)
    fun startTransaction(
        meta: RequestMetadata,
        request: StartTransactionReq
    ): OperationExecution<StartTransactionReq, StartTransactionResp>

    @Throws(IllegalStateException::class, ConnectException::class)
    fun stopTransaction(
        meta: RequestMetadata,
        request: StopTransactionReq
    ): OperationExecution<StopTransactionReq, StopTransactionResp>

    @Throws(IllegalStateException::class, ConnectException::class)
    fun statusNotification(
        meta: RequestMetadata,
        request: StatusNotificationReq
    ): OperationExecution<StatusNotificationReq, StatusNotificationResp>

    @Throws(IllegalStateException::class, ConnectException::class)
    fun bootNotification(
        meta: RequestMetadata,
        request: BootNotificationReq
    ): OperationExecution<BootNotificationReq, BootNotificationResp>

    @Throws(IllegalStateException::class, ConnectException::class)
    fun firmwareStatusNotification(
        meta: RequestMetadata,
        request: FirmwareStatusNotificationReq
    ): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp>

    fun diagnosticsStatusNotification(
        meta: RequestMetadata,
        request: DiagnosticsStatusNotificationReq
    ): OperationExecution<DiagnosticsStatusNotificationReq, DiagnosticsStatusNotificationResp>
}
