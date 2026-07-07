package com.izivia.ocpp.core12.impl

import com.izivia.ocpp.core12.CSMSOperations
import com.izivia.ocpp.core12.ChargePointOperations
import com.izivia.ocpp.core12.model.authorize.AuthorizeReq
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core12.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core12.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core12.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core12.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core12.model.reset.ResetReq
import com.izivia.ocpp.core12.model.reset.ResetResp
import com.izivia.ocpp.core12.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.ActionOcpp
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import kotlinx.datetime.Clock

class RealCSMSOperations(
    private val servers: Set<ServerTransport>,
    private val acceptConnection: (String) -> ChargingStationConfig,
    chargePointOperations: ChargePointOperations
) : CSMSOperations {
    init {
        servers.forEach { server ->
            server.receiveMessage(
                ActionOcpp.HEARTBEAT.value,
                OcppVersion.OCPP_1_2,
                { meta: RequestMetadata, req: HeartbeatReq -> chargePointOperations.heartbeat(meta, req).response },
                acceptConnection
            )
            server.receiveMessage(
                ActionOcpp.AUTHORIZE.value,
                OcppVersion.OCPP_1_2,
                { meta: RequestMetadata, req: AuthorizeReq -> chargePointOperations.authorize(meta, req).response },
                acceptConnection
            )
            server.receiveMessage(
                ActionOcpp.METER_VALUES.value,
                OcppVersion.OCPP_1_2,
                { meta: RequestMetadata, req: MeterValuesReq -> chargePointOperations.meterValues(meta, req).response },
                acceptConnection
            )
            server.receiveMessage(
                ActionOcpp.START_TRANSACTION.value,
                OcppVersion.OCPP_1_2,
                { meta: RequestMetadata, req: StartTransactionReq ->
                    chargePointOperations.startTransaction(meta, req).response
                },
                acceptConnection
            )
            server.receiveMessage(
                ActionOcpp.STOP_TRANSACTION.value,
                OcppVersion.OCPP_1_2,
                { meta: RequestMetadata, req: StopTransactionReq -> chargePointOperations.stopTransaction(meta, req).response },
                acceptConnection
            )
            server.receiveMessage(
                ActionOcpp.STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_2,
                { meta: RequestMetadata, req: StatusNotificationReq ->
                    chargePointOperations.statusNotification(meta, req).response
                },
                acceptConnection
            )
            server.receiveMessage(
                ActionOcpp.BOOT_NOTIFICATION.value,
                OcppVersion.OCPP_1_2,
                { meta: RequestMetadata, req: BootNotificationReq ->
                    chargePointOperations.bootNotification(meta, req).response
                },
                acceptConnection
            )
            server.receiveMessage(
                ActionOcpp.FIRMWARE_STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_2,
                { meta: RequestMetadata, req: FirmwareStatusNotificationReq ->
                    chargePointOperations.firmwareStatusNotification(meta, req).response
                },
                acceptConnection
            )
            server.receiveMessage(
                ActionOcpp.DIAGNOSTICS_STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_2,
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

    override fun clearCache(meta: RequestMetadata, req: ClearCacheReq): OperationExecution<ClearCacheReq, ClearCacheResp> =
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

    override fun updateFirmware(
        meta: RequestMetadata,
        req: UpdateFirmwareReq
    ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun getDiagnostics(
        meta: RequestMetadata,
        req: GetDiagnosticsReq
    ): OperationExecution<GetDiagnosticsReq, GetDiagnosticsResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    private inline fun <T, reified P> sendMessage(
        meta: RequestMetadata,
        ocppId: String,
        action: String,
        request: T
    ): OperationExecution<T, P> {
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
        servers.firstOrNull { it.canSendToChargingStation(acceptConnection(ocppId)) }
            ?: throw IllegalStateException("No transport to send a message to $ocppId")

    private fun <T : Any> getActionFromReq(req: T): String = when (req) {
        is ResetReq -> ActionOcpp.RESET.value
        is ChangeAvailabilityReq -> ActionOcpp.CHANGE_AVAILABILITY.value
        is ChangeConfigurationReq -> ActionOcpp.CHANGE_CONFIGURATION.value
        is ClearCacheReq -> ActionOcpp.CLEAR_CACHE.value
        is RemoteStartTransactionReq -> ActionOcpp.REMOTE_START_TRANSACTION.value
        is RemoteStopTransactionReq -> ActionOcpp.REMOTE_STOP_TRANSACTION.value
        is UnlockConnectorReq -> ActionOcpp.UNLOCK_CONNECTOR.value
        is UpdateFirmwareReq -> ActionOcpp.UPDATE_FIRMWARE.value
        is GetDiagnosticsReq -> ActionOcpp.GET_DIAGNOSTICS.value
        else -> throw IllegalArgumentException("Unknown action ${req::class}")
    }
}
