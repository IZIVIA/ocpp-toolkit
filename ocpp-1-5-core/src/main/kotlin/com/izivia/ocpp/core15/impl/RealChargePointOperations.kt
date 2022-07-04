package com.izivia.ocpp.core15.impl

import com.izivia.ocpp.core15.CMSOperations
import com.izivia.ocpp.core15.ChargePointOperations
import com.izivia.ocpp.core15.model.authorize.AuthorizeReq
import com.izivia.ocpp.core15.model.authorize.AuthorizeResp
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core15.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core15.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core15.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core15.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core15.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core15.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core15.model.reset.ResetReq
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.operation.information.*
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import kotlinx.datetime.Clock
import java.net.ConnectException

typealias OcppId = String
typealias Action = String

class RealChargePointOperations(
    private val chargeStationId: String, private val client: ClientTransport, private val csmsOperations: CMSOperations
) : ChargePointOperations {

    init {
        client.receiveMessage(ActionOcpp.RESET.value) { req: ResetReq ->
            csmsOperations.reset(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.CHANGE_AVAILABILITY.value) { req: ChangeAvailabilityReq ->
            csmsOperations.changeAvailability(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.CHANGE_CONFIGURATION.value) { req: ChangeConfigurationReq ->
            csmsOperations.changeConfiguration(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.CLEAR_CACHE.value) { req: ClearCacheReq ->
            csmsOperations.clearCache(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.REMOTE_START_TRANSACTION.value) { req: RemoteStartTransactionReq ->
            csmsOperations.remoteStartTransaction(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.REMOTE_STOP_TRANSACTION.value) { req: RemoteStopTransactionReq ->
            csmsOperations.remoteStopTransaction(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.UNLOCK_CONNECTOR.value) { req: UnlockConnectorReq ->
            csmsOperations.unlockConnector(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.GET_CONFIGURATION.value) { req: GetConfigurationReq ->
            csmsOperations.getConfiguration(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.RESERVE_NOW.value) { req: ReserveNowReq ->
            csmsOperations.reserveNow(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.UPDATE_FIRMWARE.value) { req: UpdateFirmwareReq ->
            csmsOperations.updateFirmware(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.CANCEL_RESERVATION.value) { req: CancelReservationReq ->
            csmsOperations.cancelReservation(
                RequestMetadata(chargeStationId), req
            ).response
        }


        client.receiveMessage(ActionOcpp.GET_LOCAL_LIST_VERSION.value) { req: GetLocalListVersionReq ->
            csmsOperations.getLocalListVersion(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.SEND_LOCAL_LIST.value) { req: SendLocalListReq ->
            csmsOperations.sendLocalList(
                RequestMetadata(chargeStationId), req
            ).response
        }



        client.receiveMessage(ActionOcpp.DATA_TRANSFER.value) { req: DataTransferReq ->
            csmsOperations.dataTransfer(
                RequestMetadata(chargeStationId), req
            ).response
        }

        client.receiveMessage(ActionOcpp.GET_DIAGNOSTICS.value) { req: GetDiagnosticsReq ->
            csmsOperations.getDiagnostics(
                RequestMetadata(chargeStationId), req
            ).response
        }

    }

    private inline fun <T, reified P> sendMessage(
        meta: RequestMetadata, action: String, request: T
    ): OperationExecution<T, P> {
        val requestTime = Clock.System.now()
        val response: P = client.sendMessage(action, request)
        val responseTime = Clock.System.now()
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, requestTime, responseTime), request, response
        )
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
    override fun dataTransfer(
        meta: RequestMetadata,
        request: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp> =
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

    private fun <T : Any> getActionFromReq(req: T): Action =
        when (req) {
            is HeartbeatReq -> ActionOcpp.HEARTBEAT.value
            is AuthorizeReq -> ActionOcpp.AUTHORIZE.value
            is MeterValuesReq -> ActionOcpp.METER_VALUES.value
            is StartTransactionReq -> ActionOcpp.START_TRANSACTION.value
            is StopTransactionReq -> ActionOcpp.STOP_TRANSACTION.value
            is StatusNotificationReq -> ActionOcpp.STATUS_NOTIFICATION.value
            is DataTransferReq -> ActionOcpp.DATA_TRANSFER.value
            is BootNotificationReq -> ActionOcpp.BOOT_NOTIFICATION.value
            is FirmwareStatusNotificationReq -> ActionOcpp.FIRMWARE_STATUS_NOTIFICATION.value
            is DiagnosticsStatusNotificationReq -> ActionOcpp.DIAGNOSTICS_STATUS_NOTIFICATION.value
            else -> throw IllegalArgumentException("Unknown action ${req::class}")
        }
}
