package com.izivia.ocpp.core.test

import com.izivia.ocpp.core20.CSMSOperations
import com.izivia.ocpp.core20.ChargePointOperations
import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedReq
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedResp
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core20.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core20.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringResp
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageResp
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationReq
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationResp
import com.izivia.ocpp.core20.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core20.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportResp
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesReq
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesResp
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core20.model.getlog.GetLogReq
import com.izivia.ocpp.core20.model.getlog.GetLogResp
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportReq
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportResp
import com.izivia.ocpp.core20.model.getreport.GetReportReq
import com.izivia.ocpp.core20.model.getreport.GetReportResp
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusReq
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusResp
import com.izivia.ocpp.core20.model.getvariables.GetVariablesReq
import com.izivia.ocpp.core20.model.getvariables.GetVariablesResp
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.core20.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core20.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core20.model.reset.ResetReq
import com.izivia.ocpp.core20.model.reset.ResetResp
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageReq
import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageResp
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseResp
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringResp
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileReq
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileResp
import com.izivia.ocpp.core20.model.setvariables.SetVariablesReq
import com.izivia.ocpp.core20.model.setvariables.SetVariablesResp
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareReq
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareResp
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.transport.ClientTransport
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
    private lateinit var transport: ClientTransport

    @BeforeEach
    fun init(){
        transport = mockk()
        every {transport.receiveMessage<Any,Any>(any(),any())} returns Unit
        every { transport.receiveMessageClass<Any,Any>(any(),any(),any()) } returns Unit

    }

    @Test
    fun `heartbeat request`() {
        every { transport.sendMessage<HeartbeatReq, HeartbeatResp>(any(), any()) } returns HeartbeatResp(
            currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
        )

        val csmsOperations : CSMSOperations = object : CSMSOperations{
            override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
                TODO("Not implemented")
            }

            override fun changeAvailability(
                meta: RequestMetadata,
                req: ChangeAvailabilityReq
            ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> {
                TODO("Not implemented")
            }

            override fun clearCache(
                meta: RequestMetadata,
                req: ClearCacheReq
            ): OperationExecution<ClearCacheReq, ClearCacheResp> {
                TODO("Not implemented")
            }

            override fun requestStartTransaction(
                meta: RequestMetadata,
                req: RequestStartTransactionReq
            ): OperationExecution<RequestStartTransactionReq, RequestStartTransactionResp> {
                TODO("Not implemented")
            }

            override fun requestStopTransaction(
                meta: RequestMetadata,
                req: RequestStopTransactionReq
            ): OperationExecution<RequestStopTransactionReq, RequestStopTransactionResp> {
                TODO("Not implemented")
            }

            override fun setVariables(
                meta: RequestMetadata,
                req: SetVariablesReq
            ): OperationExecution<SetVariablesReq, SetVariablesResp> {
                TODO("Not implemented")
            }

            override fun unlockConnector(
                meta: RequestMetadata,
                req: UnlockConnectorReq
            ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> {
                TODO("Not implemented")
            }

            override fun getBaseReport(
                meta: RequestMetadata,
                req: GetBaseReportReq
            ): OperationExecution<GetBaseReportReq, GetBaseReportResp> {
                TODO("Not implemented")
            }

            override fun getReport(
                meta: RequestMetadata,
                req: GetReportReq
            ): OperationExecution<GetReportReq, GetReportResp> {
                TODO("Not implemented")
            }

            override fun getVariables(
                meta: RequestMetadata,
                req: GetVariablesReq
            ): OperationExecution<GetVariablesReq, GetVariablesResp> {
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

            override fun updateFirmware(
                meta: RequestMetadata,
                req: UpdateFirmwareReq
            ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> {
                TODO("Not implemented")
            }

            override fun sendLocalList(
                meta: RequestMetadata,
                req: SendLocalListReq
            ): OperationExecution<SendLocalListReq, SendLocalListResp> {
                TODO("Not implemented")
            }

            override fun triggerMessage(meta: RequestMetadata, req: TriggerMessageReq): OperationExecution<TriggerMessageReq, TriggerMessageResp> {
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

            override fun clearVariableMonitoring(
                meta: RequestMetadata,
                req: ClearVariableMonitoringReq
            ): OperationExecution<ClearVariableMonitoringReq, ClearVariableMonitoringResp> {
                TODO("Not implemented")
            }

            override fun certificateSigned(
                meta: RequestMetadata,
                req: CertificateSignedReq
            ): OperationExecution<CertificateSignedReq, CertificateSignedResp> {
                TODO("Not yet implemented")
            }


            override fun getLog(
                meta: RequestMetadata,
                req: GetLogReq
            ): OperationExecution<GetLogReq, GetLogResp> {
                TODO("Not implemented")
            }

            override fun clearDisplayMessage(
                meta: RequestMetadata,
                req: ClearDisplayMessageReq
            ): OperationExecution<ClearDisplayMessageReq, ClearDisplayMessageResp> {
                TODO("Not implemented")
            }

            override fun publishFirmware(
                    meta: RequestMetadata,
                    req: PublishFirmwareReq
            ): OperationExecution<PublishFirmwareReq, PublishFirmwareResp> {
                TODO("Not yet implemented")
            }

            override fun getChargingProfiles(meta: RequestMetadata, req: GetChargingProfilesReq): OperationExecution<GetChargingProfilesReq, GetChargingProfilesResp> {
                TODO("Not yet implemented")
            }

            override fun installCertificate(
                    meta: RequestMetadata,
                    req: InstallCertificateReq
            ): OperationExecution<InstallCertificateReq, InstallCertificateResp> {
                TODO("Not yet implemented")
            }

            override fun customerInformation(
                    meta: RequestMetadata,
                    req: CustomerInformationReq
            ): OperationExecution<CustomerInformationReq, CustomerInformationResp> {
                TODO("Not yet implemented")
            }

            override fun getInstalledCertificateIds(
                    meta: RequestMetadata,
                    req: GetInstalledCertificateIdsReq
            ): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp> {
                TODO("Not yet implemented")
            }

            override fun unpublishFirmware(
                    meta: RequestMetadata,
                    req: UnpublishFirmwareReq
            ): OperationExecution<UnpublishFirmwareReq, UnpublishFirmwareResp> {
                TODO("Not yet implemented")
            }

            override fun setVariableMonitoring(
                    meta: RequestMetadata,
                    req: SetVariableMonitoringReq
            ): OperationExecution<SetVariableMonitoringReq, SetVariableMonitoringResp> {
                TODO("Not yet implemented")
            }

            override fun setMonitoringLevel(
                    meta: RequestMetadata,
                    req: SetMonitoringLevelReq
            ): OperationExecution<SetMonitoringLevelReq, SetMonitoringLevelResp> {
                TODO("Not yet implemented")
            }

            override fun setNetworkProfile(
                    meta: RequestMetadata,
                    req: SetNetworkProfileReq
            ): OperationExecution<SetNetworkProfileReq, SetNetworkProfileResp> {
                TODO("Not yet implemented")
            }

            override fun getTransactionStatus(
                    meta: RequestMetadata,
                    req: GetTransactionStatusReq
            ): OperationExecution<GetTransactionStatusReq, GetTransactionStatusResp> {
                TODO("Not yet implemented")
            }

            override fun setMonitoringBase(
                    meta: RequestMetadata,
                    req: SetMonitoringBaseReq
            ): OperationExecution<SetMonitoringBaseReq, SetMonitoringBaseResp> {
                TODO("Not yet implemented")
            }

            override fun getDisplayMessages(
                    meta: RequestMetadata,
                    req: GetDisplayMessagesReq
            ): OperationExecution<GetDisplayMessagesReq, GetDisplayMessagesResp> {
                TODO("Not yet implemented")
            }

            override fun setDisplayMessage(
                    meta: RequestMetadata,
                    req: SetDisplayMessageReq
            ): OperationExecution<SetDisplayMessageReq, SetDisplayMessageResp> {
                TODO("Not yet implemented")
            }

            override fun costUpdated(meta: RequestMetadata, req: CostUpdatedReq
            ): OperationExecution<CostUpdatedReq, CostUpdatedResp> {
                TODO("Not yet implemented")
            }

            override fun deleteCertificate(
                    meta: RequestMetadata,
                    req: DeleteCertificateReq
            ): OperationExecution<DeleteCertificateReq, DeleteCertificateResp> {
                TODO("Not yet implemented")
            }

            override fun getMonitoringReport(
                    meta: RequestMetadata,
                    req: GetMonitoringReportReq
            ): OperationExecution<GetMonitoringReportReq, GetMonitoringReportResp> {
                TODO("Not yet implemented")
            }
        }

        val operations =
            ChargePointOperations.newChargePointOperations("",transport,csmsOperations)
        val response = operations.heartbeat(RequestMetadata(""), HeartbeatReq())
        expectThat(response)
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }
}