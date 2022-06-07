package com.izivia.ocpp.core16.impl

import com.izivia.ocpp.core16.CSMSOperations
import com.izivia.ocpp.core16.ChargePointOperations
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core16.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core16.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core16.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core16.model.reset.ResetReq
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core16.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.Transport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
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

        client.receiveMessage("ChangeConfiguration") { req: ChangeConfigurationReq ->
            csmsOperations.changeConfiguration(
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

        client.receiveMessage("RemoteStartTransaction") { req: RemoteStartTransactionReq ->
            csmsOperations.remoteStartTransaction(
                RequestMetadata(chargeStationId),
                req
            ).response
        }

        client.receiveMessage("RemoteStopTransaction") { req: RemoteStopTransactionReq ->
            csmsOperations.remoteStopTransaction(
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

        client.receiveMessage("GetConfiguration") { req: GetConfigurationReq ->
            csmsOperations.getConfiguration(
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

        client.receiveMessage("TriggerMessage") { req: TriggerMessageReq ->
            csmsOperations.triggerMessage(
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

        client.receiveMessage("GetLocalListVersion") { req: GetLocalListVersionReq ->
            csmsOperations.getLocalListVersion(
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

        client.receiveMessage("SetChargingProfile") { req: SetChargingProfileReq ->
            csmsOperations.setChargingProfile(
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

        client.receiveMessage("GetDiagnostics") { req: GetDiagnosticsReq ->
            csmsOperations.getDiagnostics(
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

    override fun connect() {
        client.connect()
    }

    override fun close() {
        client.close()
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
    override fun startTransaction(
        meta: RequestMetadata,
        request: StartTransactionReq
    ): OperationExecution<StartTransactionReq, StartTransactionResp> =
        sendMessage(meta, "StartTransaction", request)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun stopTransaction(
        meta: RequestMetadata,
        request: StopTransactionReq
    ): OperationExecution<StopTransactionReq, StopTransactionResp> =
        sendMessage(meta, "StopTransaction", request)

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
    override fun firmwareStatusNotification(
        meta: RequestMetadata,
        request: FirmwareStatusNotificationReq
    ): OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp> =
            sendMessage(meta, "FirmwareStatusNotification", request)

    override fun diagnosticsStatusNotification(
        meta: RequestMetadata,
        request: DiagnosticsStatusNotificationReq
    ): OperationExecution<DiagnosticsStatusNotificationReq, DiagnosticsStatusNotificationResp> =
        sendMessage(meta, "DiagnosticsStatusNotification", request)
}

