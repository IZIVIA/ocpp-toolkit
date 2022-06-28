package com.izivia.ocpp.core16.impl

import com.izivia.ocpp.core16.CSMSOperations
import com.izivia.ocpp.core16.ChargePointOperations
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core16.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core16.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core16.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core16.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core16.model.reset.ResetReq
import com.izivia.ocpp.core16.model.reset.ResetResp
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.*
import com.izivia.ocpp.operation.information.ActionOcpp.*
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import kotlinx.datetime.Clock

typealias OcppId = String
typealias Action = String

class RealCSMSOperations(
    private val servers: Set<ServerTransport>,
    private val acceptConnection: (OcppId) -> ChargingStationConfig,
    chargePointOperations: ChargePointOperations
) : CSMSOperations {

    init {
        servers.forEach {server ->
            server.receiveMessage(HEARTBEAT.value, OcppVersion.OCPP_1_6, { meta: RequestMetadata, req: HeartbeatReq ->
                chargePointOperations.heartbeat(meta, req).response
            }, acceptConnection)

            server.receiveMessage(AUTHORIZE.value, OcppVersion.OCPP_1_6, { meta: RequestMetadata, req: AuthorizeReq ->
                chargePointOperations.authorize(meta, req).response
            }, acceptConnection)

            server.receiveMessage(METER_VALUES.value, OcppVersion.OCPP_1_6, { meta: RequestMetadata, req: MeterValuesReq ->
                chargePointOperations.meterValues(meta, req).response
            }, acceptConnection)

            server.receiveMessage(
                START_TRANSACTION.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: StartTransactionReq ->
                    chargePointOperations.startTransaction(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                STOP_TRANSACTION.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: StopTransactionReq ->
                    chargePointOperations.stopTransaction(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: StatusNotificationReq ->
                    chargePointOperations.statusNotification(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                DATA_TRANSFER.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: DataTransferReq ->
                    chargePointOperations.dataTransfer(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                BOOT_NOTIFICATION.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: BootNotificationReq ->
                    chargePointOperations.bootNotification(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                FIRMWARE_STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: FirmwareStatusNotificationReq ->
                    chargePointOperations.firmwareStatusNotification(meta, req).response
                },
                acceptConnection
            )

            server.receiveMessage(
                DIAGNOSTICS_STATUS_NOTIFICATION.value,
                OcppVersion.OCPP_1_6,
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

    override fun clearChargingProfile(
        meta: RequestMetadata,
        req: ClearChargingProfileReq
    ): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun getCompositeSchedule(
        meta: RequestMetadata,
        req: GetCompositeScheduleReq
    ): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp> =
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

    override fun triggerMessage(
        meta: RequestMetadata,
        req: TriggerMessageReq
    ): OperationExecution<TriggerMessageReq, TriggerMessageResp> =
        sendMessage(meta, meta.chargingStationId, getActionFromReq(req), req)

    override fun setChargingProfile(
        meta: RequestMetadata,
        req: SetChargingProfileReq
    ): OperationExecution<SetChargingProfileReq, SetChargingProfileResp> =
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
        is ResetReq -> RESET.value
        is ChangeAvailabilityReq -> CHANGE_AVAILABILITY.value
        is ChangeConfigurationReq -> CHANGE_CONFIGURATION.value
        is ClearCacheReq -> CLEAR_CACHE.value
        is RemoteStartTransactionReq -> REMOTE_START_TRANSACTION.value
        is RemoteStopTransactionReq -> REMOTE_STOP_TRANSACTION.value
        is UnlockConnectorReq -> UNLOCK_CONNECTOR.value
        is GetConfigurationReq -> GET_CONFIGURATION.value
        is CancelReservationReq -> CANCEL_RESERVATION.value
        is ClearChargingProfileReq -> CLEAR_CHARGING_PROFILE.value
        is GetCompositeScheduleReq -> GET_COMPOSITE_SCHEDULE.value
        is GetLocalListVersionReq -> GET_LOCAL_LIST_VERSION.value
        is UpdateFirmwareReq -> UPDATE_FIRMWARE.value
        is SendLocalListReq -> SEND_LOCAL_LIST.value
        is TriggerMessageReq -> TRIGGER_MESSAGE.value
        is SetChargingProfileReq -> SET_CHARGING_PROFILE.value
        is ReserveNowReq -> RESERVE_NOW.value
        is DataTransferReq -> DATA_TRANSFER.value
        is GetDiagnosticsReq -> GET_DIAGNOSTICS.value
        else -> throw IllegalArgumentException("Unknown action ${req::class}")
    }
}
