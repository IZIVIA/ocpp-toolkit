package com.izivia.ocpp.core12.impl

import com.izivia.ocpp.core12.CSMSOperations
import com.izivia.ocpp.core12.ChargePointOperations
import com.izivia.ocpp.core12.model.authorize.AuthorizeReq
import com.izivia.ocpp.core12.model.authorize.AuthorizeResp
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core12.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core12.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core12.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core12.model.reset.ResetReq
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.operation.information.ActionOcpp
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import kotlinx.datetime.Clock
import java.net.ConnectException

class RealChargePointOperations(
    private val chargeStationId: String,
    private val client: ClientTransport,
    private val csmsOperations: CSMSOperations
) : ChargePointOperations {
    init {
        client.receiveMessage(ActionOcpp.RESET.value) { req: ResetReq ->
            csmsOperations.reset(RequestMetadata(chargeStationId), req).response
        }
        client.receiveMessage(ActionOcpp.CHANGE_AVAILABILITY.value) { req: ChangeAvailabilityReq ->
            csmsOperations.changeAvailability(RequestMetadata(chargeStationId), req).response
        }
        client.receiveMessage(ActionOcpp.CHANGE_CONFIGURATION.value) { req: ChangeConfigurationReq ->
            csmsOperations.changeConfiguration(RequestMetadata(chargeStationId), req).response
        }
        client.receiveMessage(ActionOcpp.CLEAR_CACHE.value) { req: ClearCacheReq ->
            csmsOperations.clearCache(RequestMetadata(chargeStationId), req).response
        }
        client.receiveMessage(ActionOcpp.REMOTE_START_TRANSACTION.value) { req: RemoteStartTransactionReq ->
            csmsOperations.remoteStartTransaction(RequestMetadata(chargeStationId), req).response
        }
        client.receiveMessage(ActionOcpp.REMOTE_STOP_TRANSACTION.value) { req: RemoteStopTransactionReq ->
            csmsOperations.remoteStopTransaction(RequestMetadata(chargeStationId), req).response
        }
        client.receiveMessage(ActionOcpp.UNLOCK_CONNECTOR.value) { req: UnlockConnectorReq ->
            csmsOperations.unlockConnector(RequestMetadata(chargeStationId), req).response
        }
        client.receiveMessage(ActionOcpp.UPDATE_FIRMWARE.value) { req: UpdateFirmwareReq ->
            csmsOperations.updateFirmware(RequestMetadata(chargeStationId), req).response
        }
        client.receiveMessage(ActionOcpp.GET_DIAGNOSTICS.value) { req: GetDiagnosticsReq ->
            csmsOperations.getDiagnostics(RequestMetadata(chargeStationId), req).response
        }
    }

    override fun connect() {
        client.connect()
    }

    override fun close() {
        client.close()
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(
        meta: RequestMetadata,
        request: HeartbeatReq
    ): OperationExecution<HeartbeatReq, HeartbeatResp> =
        sendMessage(meta, getActionFromReq(request), request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun authorize(
        meta: RequestMetadata,
        request: AuthorizeReq
    ): OperationExecution<AuthorizeReq, AuthorizeResp> =
        sendMessage(meta, getActionFromReq(request), request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun meterValues(
        meta: RequestMetadata,
        request: MeterValuesReq
    ): OperationExecution<MeterValuesReq, MeterValuesResp> =
        sendMessage(meta, getActionFromReq(request), request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun startTransaction(
        meta: RequestMetadata,
        request: StartTransactionReq
    ): OperationExecution<StartTransactionReq, StartTransactionResp> =
        sendMessage(meta, getActionFromReq(request), request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun stopTransaction(
        meta: RequestMetadata,
        request: StopTransactionReq
    ): OperationExecution<StopTransactionReq, StopTransactionResp> =
        sendMessage(meta, getActionFromReq(request), request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun statusNotification(
        meta: RequestMetadata,
        request: StatusNotificationReq
    ): OperationExecution<StatusNotificationReq, StatusNotificationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun bootNotification(
        meta: RequestMetadata,
        request: BootNotificationReq
    ): OperationExecution<BootNotificationReq, BootNotificationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun firmwareStatusNotification(
        meta: RequestMetadata,
        request: FirmwareStatusNotificationReq
    ): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    override fun diagnosticsStatusNotification(
        meta: RequestMetadata,
        request: DiagnosticsStatusNotificationReq
    ): OperationExecution<DiagnosticsStatusNotificationReq, DiagnosticsStatusNotificationResp> =
        sendMessage(meta, getActionFromReq(request), request)

    private inline fun <T, reified P> sendMessage(
        meta: RequestMetadata,
        action: String,
        request: T
    ): OperationExecution<T, P> {
        val requestTime = Clock.System.now()
        val response: P = client.sendMessage(action, request)
        val responseTime = Clock.System.now()
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, requestTime, responseTime),
            request,
            response
        )
    }

    private fun <T : Any> getActionFromReq(req: T): String = when (req) {
        is HeartbeatReq -> ActionOcpp.HEARTBEAT.value
        is AuthorizeReq -> ActionOcpp.AUTHORIZE.value
        is MeterValuesReq -> ActionOcpp.METER_VALUES.value
        is StartTransactionReq -> ActionOcpp.START_TRANSACTION.value
        is StopTransactionReq -> ActionOcpp.STOP_TRANSACTION.value
        is StatusNotificationReq -> ActionOcpp.STATUS_NOTIFICATION.value
        is BootNotificationReq -> ActionOcpp.BOOT_NOTIFICATION.value
        is FirmwareStatusNotificationReq -> ActionOcpp.FIRMWARE_STATUS_NOTIFICATION.value
        is DiagnosticsStatusNotificationReq -> ActionOcpp.DIAGNOSTICS_STATUS_NOTIFICATION.value
        else -> throw IllegalArgumentException("Unknown action ${req::class}")
    }
}
