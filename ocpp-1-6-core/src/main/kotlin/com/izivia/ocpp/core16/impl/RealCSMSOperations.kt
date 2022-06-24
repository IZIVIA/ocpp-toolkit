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
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareResp
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

    init{
        servers.forEach {
            it.receiveMessage("Heartbeat", OcppVersion.OCPP_1_6, { meta: RequestMetadata, req: HeartbeatReq ->
                chargePointOperations.heartbeat(meta, req).response
            }, acceptConnection)

            it.receiveMessage("Authorize", OcppVersion.OCPP_1_6, { meta: RequestMetadata, req: AuthorizeReq ->
                chargePointOperations.authorize(meta, req).response
            }, acceptConnection)

            it.receiveMessage("MeterValues", OcppVersion.OCPP_1_6, { meta: RequestMetadata, req: MeterValuesReq ->
                chargePointOperations.meterValues(meta, req).response
            }, acceptConnection)

            it.receiveMessage(
                "StartTransaction",
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: StartTransactionReq ->
                    chargePointOperations.startTransaction(meta, req).response
                },
                acceptConnection
            )

            it.receiveMessage(
                "StopTransaction",
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: StopTransactionReq ->
                    chargePointOperations.stopTransaction(meta, req).response
                },
                acceptConnection
            )

            it.receiveMessage(
                "StatusNotification",
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: StatusNotificationReq ->
                    chargePointOperations.statusNotification(meta, req).response
                },
                acceptConnection
            )

            it.receiveMessage(
                "DataTransfer",
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: DataTransferReq ->
                    chargePointOperations.dataTransfer(meta, req).response
                },
                acceptConnection
            )

            it.receiveMessage(
                "BootNotification",
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: BootNotificationReq ->
                    chargePointOperations.bootNotification(meta, req).response
                },
                acceptConnection
            )

            it.receiveMessage(
                "FirmwareStatusNotification",
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: FirmwareStatusNotificationReq ->
                    chargePointOperations.firmwareStatusNotification(meta, req).response
                },
                acceptConnection
            )

            it.receiveMessage(
                "DiagnosticsStatusNotification",
                OcppVersion.OCPP_1_6,
                { meta: RequestMetadata, req: DiagnosticsStatusNotificationReq ->
                    chargePointOperations.diagnosticsStatusNotification(meta, req).response
                },
                acceptConnection
            )
        }
    }

    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> =
        sendMessage(meta, meta.chargingStationId, "Reset", req)

    override fun changeAvailability(meta: RequestMetadata, req: ChangeAvailabilityReq): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> =
        sendMessage(meta, meta.chargingStationId, "ChangeAvailability", req)

    override fun changeConfiguration(meta: RequestMetadata,  req: ChangeConfigurationReq): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp> =
        sendMessage(meta, meta.chargingStationId, "ChangeConfiguration", req)

    override fun clearCache(meta: RequestMetadata,  req: ClearCacheReq): OperationExecution<ClearCacheReq, ClearCacheResp> =
        sendMessage(meta, meta.chargingStationId, "ClearCache", req)

    override fun remoteStartTransaction(meta: RequestMetadata,  req: RemoteStartTransactionReq): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp> =
        sendMessage(meta, meta.chargingStationId, "RemoteStartTransaction", req)

    override fun remoteStopTransaction(meta: RequestMetadata,  req: RemoteStopTransactionReq): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp> =
        sendMessage(meta, meta.chargingStationId, "RemoteStopTransaction", req)

    override fun unlockConnector(meta: RequestMetadata,  req: UnlockConnectorReq): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> =
        sendMessage(meta, meta.chargingStationId, "UnlockConnector", req)

    override fun getConfiguration(meta: RequestMetadata,  req: GetConfigurationReq): OperationExecution<GetConfigurationReq, GetConfigurationResp> =
        sendMessage(meta, meta.chargingStationId, "GetConfiguration", req)

    override fun cancelReservation(meta: RequestMetadata, req: CancelReservationReq): OperationExecution<CancelReservationReq, CancelReservationResp> =
        sendMessage(meta, meta.chargingStationId, "CancelReservation", req)

    override fun clearChargingProfile(meta: RequestMetadata, req: ClearChargingProfileReq): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp> =
        sendMessage(meta, meta.chargingStationId, "ClearChargingProfile", req)

    override fun getCompositeSchedule(meta: RequestMetadata, req: GetCompositeScheduleReq): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp> =
        sendMessage(meta, meta.chargingStationId, "GetCompositeSchedule", req)

    override fun getLocalListVersion(meta: RequestMetadata, req: GetLocalListVersionReq): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp> =
        sendMessage(meta, meta.chargingStationId, "GetLocalListVersion", req)

    override fun updateFirmware(meta: RequestMetadata, req : UpdateFirmwareReq): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> =
        sendMessage(meta, meta.chargingStationId, "UpdateFirmware", req)

    override fun sendLocalList(meta: RequestMetadata, req: SendLocalListReq): OperationExecution<SendLocalListReq, SendLocalListResp> =
        sendMessage(meta, meta.chargingStationId, "SendLocalList", req)

    override fun triggerMessage(meta: RequestMetadata,  req: TriggerMessageReq): OperationExecution<TriggerMessageReq, TriggerMessageResp> =
        sendMessage(meta, meta.chargingStationId, "TriggerMessage", req)

    override fun setChargingProfile(meta: RequestMetadata, req: SetChargingProfileReq): OperationExecution<SetChargingProfileReq, SetChargingProfileResp> =
        sendMessage(meta, meta.chargingStationId, "SetChargingProfile", req)

    override fun reserveNow(meta: RequestMetadata,  req: ReserveNowReq): OperationExecution<ReserveNowReq, ReserveNowResp> =
        sendMessage(meta, meta.chargingStationId, "ReserveNow", req)

    override fun dataTransfer(meta: RequestMetadata,  req: DataTransferReq): OperationExecution<DataTransferReq, DataTransferResp> =
        sendMessage(meta, meta.chargingStationId, "DataTransfer", req)

    override fun getDiagnostics(meta: RequestMetadata, req: GetDiagnosticsReq): OperationExecution<GetDiagnosticsReq, GetDiagnosticsResp> =
        sendMessage(meta, meta.chargingStationId, "GetDiagnostics", req)

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
}
