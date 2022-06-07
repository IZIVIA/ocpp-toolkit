package com.izivia.ocpp.core.test

import com.izivia.ocpp.core16.CSMSOperations
import com.izivia.ocpp.core16.ChargePointOperations
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core16.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core16.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core16.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core16.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core16.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core16.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core16.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core16.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core16.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
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
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core16.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core16.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.transport.Transport
import com.izivia.ocpp.transport.receiveMessage
import com.izivia.ocpp.transport.sendMessage
import io.mockk.every
import io.mockk.mockk
import kotlinx.datetime.Instant
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CoreTest {
    private lateinit var transport: Transport

    @BeforeEach
    fun init() {
        transport = mockk()
        every { transport.receiveMessage<Any, Any>(any(), any()) } returns Unit
        every { transport.receiveMessageClass<Any, Any>(any(), any(), any()) } returns Unit

    }

    @Test
    fun `heartbeat request`() {

        every { transport.sendMessage<HeartbeatReq, HeartbeatResp>(any(), any()) } returns HeartbeatResp(
            currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
        )

        val csmsOperations: CSMSOperations = object : CSMSOperations {
            override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
                TODO("Not implemented")
            }

            override fun changeAvailability(
                meta: RequestMetadata,
                req: ChangeAvailabilityReq
            ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> {
                TODO("Not implemented")
            }

            override fun changeConfiguration(
                meta: RequestMetadata,
                req: ChangeConfigurationReq
            ): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp> {
                TODO("Not implemented")
            }

            override fun clearCache(
                meta: RequestMetadata,
                req: ClearCacheReq
            ): OperationExecution<ClearCacheReq, ClearCacheResp> {
                TODO("Not implemented")
            }

            override fun remoteStartTransaction(
                meta: RequestMetadata,
                req: RemoteStartTransactionReq
            ): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp> {
                TODO("Not implemented")
            }

            override fun remoteStopTransaction(
                meta: RequestMetadata,
                req: RemoteStopTransactionReq
            ): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp> {
                TODO("Not implemented")
            }

            override fun unlockConnector(
                meta: RequestMetadata,
                req: UnlockConnectorReq
            ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> {
                TODO("Not implemented")
            }

            override fun getConfiguration(
                meta: RequestMetadata,
                req: GetConfigurationReq
            ): OperationExecution<GetConfigurationReq, GetConfigurationResp> {
                TODO("Not implemented")
            }

            override fun cancelReservation(
                meta: RequestMetadata,
                req: CancelReservationReq
            ): OperationExecution<CancelReservationReq, CancelReservationResp> {
                TODO("Not implemented")
            }

            override fun clearChargingProfile(
                meta: RequestMetadata,
                req: ClearChargingProfileReq
            ): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp> {
                TODO("Not implemented")
            }

            override fun getCompositeSchedule(
                meta: RequestMetadata,
                req: GetCompositeScheduleReq
            ): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp> {
                TODO("Not implemented")
            }

            override fun getLocalListVersion(
                meta: RequestMetadata,
                req: GetLocalListVersionReq
            ): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp> {
                TODO("Not implemented")
            }

            override fun updateFirmware(meta: RequestMetadata, req: UpdateFirmwareReq): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> {
                TODO("Not implemented")
            }

            override fun sendLocalList(
                meta: RequestMetadata,
                req: SendLocalListReq
            ): OperationExecution<SendLocalListReq, SendLocalListResp> {
                TODO("Not implemented")
            }

            override fun triggerMessage(
                meta: RequestMetadata,
                req: TriggerMessageReq
            ): OperationExecution<TriggerMessageReq, TriggerMessageResp> {
                TODO("Not implemented")
            }

            override fun setChargingProfile(
                meta: RequestMetadata,
                req: SetChargingProfileReq
            ): OperationExecution<SetChargingProfileReq, SetChargingProfileResp> {
                TODO("Not implemented")
            }

            override fun reserveNow(
                meta: RequestMetadata,
                req: ReserveNowReq
            ): OperationExecution<ReserveNowReq, ReserveNowResp> {
                TODO("Not implemented")
            }

            override fun dataTransfer(
                meta: RequestMetadata,
                req: DataTransferReq
            ): OperationExecution<DataTransferReq, DataTransferResp> {
                TODO("Not implemented")
            }

            override fun getDiagnostics(
                meta: RequestMetadata,
                req: GetDiagnosticsReq
            ): OperationExecution<GetDiagnosticsReq, GetDiagnosticsResp> {
                TODO("Not implemented")
            }
        }

        val operations =
            ChargePointOperations.newChargePointOperations("", transport, csmsOperations)
        val response = operations.heartbeat(RequestMetadata(""), HeartbeatReq())
        expectThat(response).and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }
}