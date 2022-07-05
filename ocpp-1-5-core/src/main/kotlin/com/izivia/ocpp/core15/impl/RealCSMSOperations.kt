package com.izivia.ocpp.core15.impl

import com.izivia.ocpp.core15.CSMSOperations
import com.izivia.ocpp.core15.ChargePointOperations
import com.izivia.ocpp.core15.model.authorize.AuthorizeReq
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core15.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core15.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core15.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core15.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core15.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core15.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core15.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core15.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core15.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core15.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core15.model.reset.ResetReq
import com.izivia.ocpp.core15.model.reset.ResetResp
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core15.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core15.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.*
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import kotlinx.datetime.Clock

class RealCSMSOperations(
    private val servers: Set<ServerTransport>,
    private val acceptConnection: (OcppId) -> ChargingStationConfig,
    chargePointOperations: ChargePointOperations
) : CSMSOperations {
    init {
        servers.forEach { server ->
            server.receiveMessage(
                ActionOcpp.HEARTBEAT.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: HeartbeatReq ->
                    chargePointOperations.heartbeat(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                ActionOcpp.AUTHORIZE.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: AuthorizeReq ->
                    chargePointOperations.authorize(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                ActionOcpp.METER_VALUES.value,
                OcppVersion.OCPP_1_5,
                { meta: RequestMetadata, req: MeterValuesReq ->
                    chargePointOperations.meterValues(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                ActionOcpp.START_TRANSACTION.value,
                OcppVersion.OCPP_1_5,
                { meta: RequestMetadata, req: StartTransactionReq ->
                    chargePointOperations.startTransaction(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                ActionOcpp.STOP_TRANSACTION.value,
                OcppVersion.OCPP_1_5,
                { meta: RequestMetadata, req: StopTransactionReq ->
                    chargePointOperations.stopTransaction(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                ActionOcpp.STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_5,
                { meta: RequestMetadata, req: StatusNotificationReq ->
                    chargePointOperations.statusNotification(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                ActionOcpp.DATA_TRANSFER.value,
                OcppVersion.OCPP_1_5,
                { meta: RequestMetadata, req: DataTransferReq ->
                    chargePointOperations.dataTransfer(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                ActionOcpp.BOOT_NOTIFICATION.value,
                OcppVersion.OCPP_1_5,
                { meta: RequestMetadata, req: BootNotificationReq ->
                    chargePointOperations.bootNotification(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                ActionOcpp.FIRMWARE_STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_5,
                { meta: RequestMetadata, req: FirmwareStatusNotificationReq ->
                    chargePointOperations.firmwareStatusNotification(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                ActionOcpp.DIAGNOSTICS_STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_5,
                { meta: RequestMetadata, req: DiagnosticsStatusNotificationReq ->
                    chargePointOperations.diagnosticsStatusNotification(meta, req).response
                },
                acceptConnection
            )
        }
    }


    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun changeAvailability(
        meta: RequestMetadata,
        req: ChangeAvailabilityReq
    ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun changeConfiguration(
        meta: RequestMetadata,
        req: ChangeConfigurationReq
    ): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun clearCache(
        meta: RequestMetadata,
        req: ClearCacheReq
    ): OperationExecution<ClearCacheReq, ClearCacheResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun remoteStartTransaction(
        meta: RequestMetadata,
        req: RemoteStartTransactionReq
    ): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun remoteStopTransaction(
        meta: RequestMetadata,
        req: RemoteStopTransactionReq
    ): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun unlockConnector(
        meta: RequestMetadata,
        req: UnlockConnectorReq
    ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun getConfiguration(
        meta: RequestMetadata,
        req: GetConfigurationReq
    ): OperationExecution<GetConfigurationReq, GetConfigurationResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun cancelReservation(
        meta: RequestMetadata,
        req: CancelReservationReq
    ): OperationExecution<CancelReservationReq, CancelReservationResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun getLocalListVersion(
        meta: RequestMetadata,
        req: GetLocalListVersionReq
    ): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun updateFirmware(
        meta: RequestMetadata,
        req: UpdateFirmwareReq
    ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun sendLocalList(
        meta: RequestMetadata,
        req: SendLocalListReq
    ): OperationExecution<SendLocalListReq, SendLocalListResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun reserveNow(
        meta: RequestMetadata,
        req: ReserveNowReq
    ): OperationExecution<ReserveNowReq, ReserveNowResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun dataTransfer(
        meta: RequestMetadata,
        req: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun getDiagnostics(
        meta: RequestMetadata,
        req: GetDiagnosticsReq
    ): OperationExecution<GetDiagnosticsReq, GetDiagnosticsResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    private inline fun <T, reified P> sendMessage(meta: RequestMetadata, ocppId: String, action: String, request: T)
            : OperationExecution<T, P> {
        val transport = getTransport(ocppId)
        val requestTime = Clock.System.now()
        val response: P = transport.sendMessage(ocppId, action, request)
        val responseTime = Clock.System.now()
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS, requestTime, responseTime),
            request,
            response
        )
    }

    private fun getTransport(ocppId: String): ServerTransport =
        servers.filter { it.canSendToChargingStation(acceptConnection(ocppId)) }.firstOrNull()
            ?: throw IllegalStateException("No transport to send a message to $ocppId")

    private fun <T : Any> getActionFromReq(req: T): Action = when (req) {
        is ResetReq -> ActionOcpp.RESET.value
        is ChangeAvailabilityReq -> ActionOcpp.CHANGE_AVAILABILITY.value
        is ChangeConfigurationReq -> ActionOcpp.CHANGE_CONFIGURATION.value
        is ClearCacheReq -> ActionOcpp.CLEAR_CACHE.value
        is RemoteStartTransactionReq -> ActionOcpp.REMOTE_START_TRANSACTION.value
        is RemoteStopTransactionReq -> ActionOcpp.REMOTE_STOP_TRANSACTION.value
        is UnlockConnectorReq -> ActionOcpp.UNLOCK_CONNECTOR.value
        is GetConfigurationReq -> ActionOcpp.GET_CONFIGURATION.value
        is CancelReservationReq -> ActionOcpp.CANCEL_RESERVATION.value
        is GetLocalListVersionReq -> ActionOcpp.GET_LOCAL_LIST_VERSION.value
        is UpdateFirmwareReq -> ActionOcpp.UPDATE_FIRMWARE.value
        is SendLocalListReq -> ActionOcpp.SEND_LOCAL_LIST.value
        is ReserveNowReq -> ActionOcpp.RESERVE_NOW.value
        is DataTransferReq -> ActionOcpp.DATA_TRANSFER.value
        is GetDiagnosticsReq -> ActionOcpp.GET_DIAGNOSTICS.value
        else -> throw IllegalArgumentException("Unknown action ${req::class}")
    }
}
