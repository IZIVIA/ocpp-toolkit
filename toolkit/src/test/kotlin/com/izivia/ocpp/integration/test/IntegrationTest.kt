package com.izivia.ocpp.integration.test

import com.izivia.ocpp.api.model.common.enumeration.IdTokenEnumType
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.model.authorize.AuthorizeReq
import com.izivia.ocpp.api.model.common.enumeration.HashAlgorithmEnumType
import com.izivia.ocpp.api.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.api.model.bootnotification.ChargingStationType
import com.izivia.ocpp.api.model.bootnotification.enumeration.BootReasonEnumType
import com.izivia.ocpp.api.model.bootnotification.enumeration.RegistrationStatusEnumType
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedReq
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedResp
import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import com.izivia.ocpp.api.model.clearcache.ClearCacheReq
import com.izivia.ocpp.api.model.clearcache.ClearCacheResp
import com.izivia.ocpp.api.model.clearcache.enumeration.ClearCacheStatusEnumType
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType
import com.izivia.ocpp.api.model.common.SampledValueType
import com.izivia.ocpp.api.model.common.enumeration.AuthorizationStatusEnumType
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.api.model.common.IdTokenInfoType
import com.izivia.ocpp.api.model.common.MessageContentType
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.enumeration.ChargingRateUnitEnumType
import com.izivia.ocpp.api.model.common.enumeration.GenericDeviceModelStatusEnumType
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageResp
import com.izivia.ocpp.api.model.clearedcharginglimit.ClearedChargingLimitReq
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringResp
import com.izivia.ocpp.api.model.common.CertificateHashDataType
import com.izivia.ocpp.api.model.common.ChargingProfileType
import com.izivia.ocpp.api.model.common.ChargingSchedulePeriodType
import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.OCSPRequestDataType
import com.izivia.ocpp.api.model.common.SignedMeterValueType
import com.izivia.ocpp.api.model.common.VariableType
import com.izivia.ocpp.api.model.common.enumeration.ChargingProfilePurposeEnumType
import com.izivia.ocpp.api.model.common.enumeration.LocationEnumType
import com.izivia.ocpp.api.model.common.enumeration.MessageFormatEnumType
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq
import com.izivia.ocpp.api.model.datatransfer.DataTransferResp
import com.izivia.ocpp.api.model.datatransfer.enumeration.DataTransferStatusEnumType
import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import com.izivia.ocpp.api.model.getallvariables.GetAllVariablesReq
import com.izivia.ocpp.api.model.getallvariables.GetAllVariablesResp
import com.izivia.ocpp.api.model.getallvariables.KeyValue
import com.izivia.ocpp.api.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.api.model.getbasereport.GetBaseReportResp
import com.izivia.ocpp.api.model.getcertificatestatus.GetCertificateStatusReq
import com.izivia.ocpp.api.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType
import com.izivia.ocpp.api.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.api.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.api.model.getlog.GetLogReq
import com.izivia.ocpp.api.model.getlog.GetLogResp
import com.izivia.ocpp.api.model.getlog.enumeration.LogStatusEnumType
import com.izivia.ocpp.api.model.getreport.GetReportReq
import com.izivia.ocpp.api.model.getreport.GetReportResp
import com.izivia.ocpp.api.model.getvariables.GetVariableResultType
import com.izivia.ocpp.api.model.getvariables.GetVariablesReq
import com.izivia.ocpp.api.model.getvariables.GetVariablesResp
import com.izivia.ocpp.api.model.getvariables.enumeration.GetVariableStatusEnumType
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.api.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType
import com.izivia.ocpp.api.model.metervalues.MeterValuesReq
import com.izivia.ocpp.api.model.notifycharginglimit.ChargingLimitType
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitReq
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.api.model.common.MessageInfoType
import com.izivia.ocpp.api.model.common.enumeration.MessagePriorityEnumType
import com.izivia.ocpp.api.model.common.enumeration.MessageStateEnumType
import com.izivia.ocpp.api.model.notifyevchargingneeds.ChargingNeedsType
import com.izivia.ocpp.api.model.notifyevchargingneeds.DCChargingParametersType
import com.izivia.ocpp.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import com.izivia.ocpp.api.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType
import com.izivia.ocpp.api.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType
import com.izivia.ocpp.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import com.izivia.ocpp.api.model.notifyevent.EventDataType
import com.izivia.ocpp.api.model.notifyevent.NotifyEventReq
import com.izivia.ocpp.api.model.notifyevent.enumeration.EventNotificationEnumType
import com.izivia.ocpp.api.model.notifyevent.enumeration.EventTriggerEnumType
import com.izivia.ocpp.api.model.notifymonitoringreport.MonitoringDataType
import com.izivia.ocpp.api.model.notifymonitoringreport.NotifyMonitoringReportReq
import com.izivia.ocpp.api.model.notifymonitoringreport.VariableMonitoringType
import com.izivia.ocpp.api.model.notifyreport.NotifyReportReq
import com.izivia.ocpp.api.model.notifyreport.ReportDataType
import com.izivia.ocpp.api.model.notifyreport.VariableAttributeType
import com.izivia.ocpp.api.model.notifyreport.VariableCharacteristicsType
import com.izivia.ocpp.api.model.notifyreport.enumeration.DataEnumType
import com.izivia.ocpp.api.model.common.ChargingScheduleType
import com.izivia.ocpp.api.model.common.enumeration.CertificateSigningUseEnumType
import com.izivia.ocpp.api.model.common.enumeration.ChargingLimitSourceEnumType
import com.izivia.ocpp.api.model.common.enumeration.GenericStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.api.model.getmonitoringreport.GetMonitoringReportReq
import com.izivia.ocpp.api.model.getmonitoringreport.GetMonitoringReportResp
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.api.model.deletecertificate.enumerations.DeleteCertificateStatusEnumType
import com.izivia.ocpp.api.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.api.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.api.model.getdisplaymessages.enumeration.GetDisplayMessagesStatusEnumType
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.api.model.getchargingprofiles.GetChargingProfilesReq
import com.izivia.ocpp.api.model.getchargingprofiles.GetChargingProfilesResp
import com.izivia.ocpp.api.model.getchargingprofiles.enumeration.GetChargingProfileStatusEnumType
import com.izivia.ocpp.api.model.getinstalledcertificateids.CertificateHashDataChainType
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType
import com.izivia.ocpp.api.model.customerinformation.CustomerInformationReq
import com.izivia.ocpp.api.model.customerinformation.CustomerInformationResp
import com.izivia.ocpp.api.model.customerinformation.enumeration.CustomerInformationStatusEnumType
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.MonitorEnumType
import com.izivia.ocpp.api.model.common.enumeration.PhaseEnumType
import com.izivia.ocpp.api.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.api.model.common.enumeration.RequestStartStopStatusEnumType
import com.izivia.ocpp.api.model.gettransactionstatus.GetTransactionStatusReq
import com.izivia.ocpp.api.model.gettransactionstatus.GetTransactionStatusResp
import com.izivia.ocpp.api.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.api.model.remotestart.enumeration.ChargingProfileKindEnumType
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.api.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.api.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.api.model.reservationstatusupdate.enumeration.ReservationUpdateStatusEnumType
import com.izivia.ocpp.api.model.reservenow.ReserveNowReq
import com.izivia.ocpp.api.model.reservenow.ReserveNowResp
import com.izivia.ocpp.api.model.reservenow.enumeration.ReserveNowStatusEnumType
import com.izivia.ocpp.api.model.reset.ResetReq
import com.izivia.ocpp.api.model.reset.ResetResp
import com.izivia.ocpp.api.model.reset.enumeration.ResetStatusEnumType
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.api.model.sendlocallist.enumeration.SendLocalListStatusEnumType
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.api.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType
import com.izivia.ocpp.api.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.api.model.setmonitoringbase.SetMonitoringBaseResp
import com.izivia.ocpp.api.model.setvariablemonitoring.SetMonitoringResultType
import com.izivia.ocpp.api.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.api.model.setvariablemonitoring.SetVariableMonitoringResp
import com.izivia.ocpp.api.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.api.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.api.model.setnetworkprofile.SetNetworkProfileReq
import com.izivia.ocpp.api.model.setnetworkprofile.SetNetworkProfileResp
import com.izivia.ocpp.api.model.setnetworkprofile.enumeration.SetNetworkProfileStatusEnumType
import com.izivia.ocpp.api.model.setdisplaymessage.SetDisplayMessageReq
import com.izivia.ocpp.api.model.setdisplaymessage.SetDisplayMessageResp
import com.izivia.ocpp.api.model.setdisplaymessage.enumeration.DisplayMessageStatusEnumType
import com.izivia.ocpp.api.model.setvariables.SetVariableResultType
import com.izivia.ocpp.api.model.setvariables.SetVariablesReq
import com.izivia.ocpp.api.model.setvariables.SetVariablesResp
import com.izivia.ocpp.api.model.setvariables.enumeration.SetVariableStatusEnumType
import com.izivia.ocpp.api.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import com.izivia.ocpp.api.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.api.model.transactionevent.TransactionType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TransactionEventEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TriggerReasonEnumType
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import com.izivia.ocpp.api.model.unpublishfirmware.UnpublishFirmwareReq
import com.izivia.ocpp.api.model.unpublishfirmware.UnpublishFirmwareResp
import com.izivia.ocpp.api.model.unpublishfirmware.enumeration.UnpublishFirmwareStatusEnumType
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType
import com.izivia.ocpp.api.send
import com.izivia.ocpp.api.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType
import com.izivia.ocpp.api.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType as PublishFirmwareStatusEnumTypeGen
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.client.impl.OkHttpOcppWampClient
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageType
import kotlinx.datetime.Instant
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import java.util.*

class IntegrationTest {

    private lateinit var ocppWampClient: OkHttpOcppWampClient
    private val csApi: CSApi = object : CSApi {

        override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
            return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                ResetResp(ResetStatusEnumType.Scheduled)
            )
        }

        override fun changeAvailability(
            meta: RequestMetadata,
            req: ChangeAvailabilityReq
        ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> {
            val response = ChangeAvailabilityResp(ChangeAvailabilityStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun clearCache(
            meta: RequestMetadata,
            req: ClearCacheReq
        ): OperationExecution<ClearCacheReq, ClearCacheResp> {
            val response = ClearCacheResp(ClearCacheStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun requestStartTransaction(
            meta: RequestMetadata,
            req: RequestStartTransactionReq
        ): OperationExecution<RequestStartTransactionReq, RequestStartTransactionResp> {
            val response = RequestStartTransactionResp(RequestStartStopStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun requestStopTransaction(
            meta: RequestMetadata,
            req: RequestStopTransactionReq
        ): OperationExecution<RequestStopTransactionReq, RequestStopTransactionResp> {
            val response = RequestStopTransactionResp(RequestStartStopStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun setVariables(
            meta: RequestMetadata,
            req: SetVariablesReq
        ): OperationExecution<SetVariablesReq, SetVariablesResp> {
            val response = SetVariablesResp(
                listOf(
                    SetVariableResultType(
                        SetVariableStatusEnumType.Accepted,
                        ComponentType("component"),
                        VariableType("name")
                    )
                )
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun unlockConnector(
            meta: RequestMetadata,
            req: UnlockConnectorReq
        ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> {
            val response = UnlockConnectorResp(UnlockStatusEnumType.Unlocked)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getAllVariables(
            meta: RequestMetadata,
            req: GetAllVariablesReq
        ): OperationExecution<GetAllVariablesReq, GetAllVariablesResp> {
            val response = GetAllVariablesResp(
                listOf(
                    KeyValue("AllowOfflineTxForUnknownId", true, "true"),
                    KeyValue("AuthorizationCacheEnabled", false, "true")
                )
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun updateFirmware(meta: RequestMetadata, req: UpdateFirmwareReq): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> {
            val response = UpdateFirmwareResp(
                status = UpdateFirmwareStatusEnumType.Accepted,
                statusInfo = StatusInfoType("reason", "additional")
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun triggerMessage(
            meta: RequestMetadata,
            req: TriggerMessageReq
        ): OperationExecution<TriggerMessageReq, TriggerMessageResp> {
            val response = TriggerMessageResp(
                status = TriggerMessageStatusEnumType.Accepted,
                statusInfo = StatusInfoType("reason", "additional")
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun reserveNow(
            meta: RequestMetadata,
            req: ReserveNowReq
        ): OperationExecution<ReserveNowReq, ReserveNowResp> {
            val response = ReserveNowResp(
                ReserveNowStatusEnumType.Accepted
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getBaseReport(
            meta: RequestMetadata,
            req: GetBaseReportReq
        ): OperationExecution<GetBaseReportReq, GetBaseReportResp> {
            val response = GetBaseReportResp(GenericDeviceModelStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getReport(
            meta: RequestMetadata,
            req: GetReportReq
        ): OperationExecution<GetReportReq, GetReportResp> {
            val response = GetReportResp(GenericDeviceModelStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getVariables(
            meta: RequestMetadata,
            req: GetVariablesReq
        ): OperationExecution<GetVariablesReq, GetVariablesResp> {
            val response = GetVariablesResp(req.getVariableData.map {
                GetVariableResultType(
                    attributeStatus = GetVariableStatusEnumType.Accepted,
                    component = ComponentType(it.component.name),
                    variable = VariableType(it.variable.name),
                    readonly = true
                )
            })
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun cancelReservation(
            meta: RequestMetadata,
            req: CancelReservationReq
        ): OperationExecution<CancelReservationReq, CancelReservationResp> {
            val response = CancelReservationResp(CancelReservationStatusEnumType.Rejected)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun clearChargingProfile(
            meta: RequestMetadata,
            req: ClearChargingProfileReq
        ): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp> {
            val response = ClearChargingProfileResp(ClearChargingProfileStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getCompositeSchedule(
            meta: RequestMetadata,
            req: GetCompositeScheduleReq
        ): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp> {
            val response = GetCompositeScheduleResp(GenericStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getLocalListVersion(
            meta: RequestMetadata,
            req: GetLocalListVersionReq
        ): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp> {
            val response = GetLocalListVersionResp(1)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun sendLocalList(
            meta: RequestMetadata,
            req: SendLocalListReq
        ): OperationExecution<SendLocalListReq, SendLocalListResp> {
            val response = SendLocalListResp(SendLocalListStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun setChargingProfile(
            meta: RequestMetadata,
            req: SetChargingProfileReq
        ): OperationExecution<SetChargingProfileReq, SetChargingProfileResp> {
            val response = SetChargingProfileResp(ChargingProfileStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun dataTransfer(
            meta: RequestMetadata,
            req: DataTransferReq
        ): OperationExecution<DataTransferReq, DataTransferResp> {
            val response = DataTransferResp(
                status = DataTransferStatusEnumType.Accepted,
                data = "Some data",
                statusInfo = StatusInfoType(
                    reasonCode = "reasonCode",
                    additionalInfo = "additionalInfo"
                )
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun certificateSigned(
            meta: RequestMetadata,
            req: CertificateSignedReq
        ): OperationExecution<CertificateSignedReq, CertificateSignedResp> {
            throw NotImplementedError()
        }

        override fun getLog(
            meta: RequestMetadata,
            req: GetLogReq
        ): OperationExecution<GetLogReq, GetLogResp> {
            val response = GetLogResp(LogStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun clearDisplayMessage(
            meta: RequestMetadata,
            req: ClearDisplayMessageReq
        ): OperationExecution<ClearDisplayMessageReq, ClearDisplayMessageResp> {
            throw NotImplementedError()
        }

        override fun getChargingProfiles(
                meta: RequestMetadata,
                req: GetChargingProfilesReq
        ): OperationExecution<GetChargingProfilesReq, GetChargingProfilesResp> {
            val response = GetChargingProfilesResp (
                    GetChargingProfileStatusEnumType.Accepted,
                    StatusInfoType("reason","info")
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getInstalledCertificateIds(
                meta: RequestMetadata,
                req: GetInstalledCertificateIdsReq
        ): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp> {
            val response = GetInstalledCertificateIdsResp(
                    GetInstalledCertificateStatusEnumType.Accepted,
                    listOf(
                            CertificateHashDataChainType(
                                    GetCertificateIdUseEnumType.CSMSRootCertificate,
                                    CertificateHashDataType(
                                            HashAlgorithmEnumType.SHA512,
                                            "",
                                            "",
                                            ""
                                    ),
                                    listOf(
                                            CertificateHashDataType(
                                                    HashAlgorithmEnumType.SHA512,
                                                    "",
                                                    "",
                                                    ""
                                            ),
                                            CertificateHashDataType(
                                                    HashAlgorithmEnumType.SHA512,
                                                    "",
                                                    "",
                                                    ""
                                            )
                                    )
                            ),
                    ),
                    StatusInfoType("reason", "info")
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun installCertificate(
                meta: RequestMetadata,
                req: InstallCertificateReq
        ): OperationExecution<InstallCertificateReq, InstallCertificateResp> {
            val response = InstallCertificateResp(
                    InstallCertificateStatusEnumType.Accepted,
                    StatusInfoType("reason","info")
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun customerInformation(
                meta: RequestMetadata,
                req: CustomerInformationReq
        ): OperationExecution<CustomerInformationReq, CustomerInformationResp> {
            val response = CustomerInformationResp(
                    CustomerInformationStatusEnumType.Accepted,
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun unpublishFirmware(
                meta: RequestMetadata,
                req: UnpublishFirmwareReq
        ): OperationExecution<UnpublishFirmwareReq, UnpublishFirmwareResp> {
            val response = UnpublishFirmwareResp(UnpublishFirmwareStatusEnumType.DownloadOngoing)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun publishFirmware(
                meta: RequestMetadata,
                req: PublishFirmwareReq
        ): OperationExecution<PublishFirmwareReq, PublishFirmwareResp> {
            val response = PublishFirmwareResp(GenericStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun setVariableMonitoring(
                meta: RequestMetadata,
                req: SetVariableMonitoringReq
        ): OperationExecution<SetVariableMonitoringReq, SetVariableMonitoringResp> {
            val response = SetVariableMonitoringResp(
                listOf(
                    SetMonitoringResultType (
                            id = 23,
                            status = SetMonitoringStatusEnumType.Accepted,
                            type = MonitorEnumType.Delta,
                            severity = 3,
                            component = ComponentType("name"),
                            variable = VariableType("name"),
                            statusInfo = StatusInfoType("reason", "info")
                    )
                )
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }


        override fun setMonitoringLevel(
                meta: RequestMetadata,
                req: SetMonitoringLevelReq
        ): OperationExecution<SetMonitoringLevelReq, SetMonitoringLevelResp> {
            val response = SetMonitoringLevelResp(
                    GenericStatusEnumType.Accepted,
                    StatusInfoType("reason","additionnal")
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun setNetworkProfile(
                meta: RequestMetadata,
                req: SetNetworkProfileReq
        ): OperationExecution<SetNetworkProfileReq, SetNetworkProfileResp> {
            val response = SetNetworkProfileResp(SetNetworkProfileStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getTransactionStatus(
                meta: RequestMetadata,
                req: GetTransactionStatusReq
        ): OperationExecution<GetTransactionStatusReq, GetTransactionStatusResp> {
            val response = GetTransactionStatusResp(false,true)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun setMonitoringBase(
                meta: RequestMetadata,
                req: SetMonitoringBaseReq
        ): OperationExecution<SetMonitoringBaseReq, SetMonitoringBaseResp> {
            val response = SetMonitoringBaseResp(GenericDeviceModelStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getDisplayMessages(
                meta: RequestMetadata,
                req: GetDisplayMessagesReq
        ): OperationExecution<GetDisplayMessagesReq, GetDisplayMessagesResp> {
            val response = GetDisplayMessagesResp(
                GetDisplayMessagesStatusEnumType.Accepted,
                StatusInfoType("reason","more")
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun costUpdated(
                meta: RequestMetadata,
                req: CostUpdatedReq
        ): OperationExecution<CostUpdatedReq, CostUpdatedResp> {
            val response = CostUpdatedResp()
            return OperationExecution(ExecutionMetadata(meta,RequestStatus.SUCCESS),req,response)
        }

        override fun setDisplayMessage(
                meta: RequestMetadata,
                req: SetDisplayMessageReq
        ): OperationExecution<SetDisplayMessageReq, SetDisplayMessageResp> {
            val response = SetDisplayMessageResp(DisplayMessageStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun deleteCertificate(
                meta: RequestMetadata,
                req: DeleteCertificateReq
        ): OperationExecution<DeleteCertificateReq, DeleteCertificateResp> {
            val response = DeleteCertificateResp(DeleteCertificateStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getMonitoringReport(
                meta: RequestMetadata,
                req: GetMonitoringReportReq
        ): OperationExecution<GetMonitoringReportReq, GetMonitoringReportResp> {
            val response = GetMonitoringReportResp(GenericDeviceModelStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun clearVariableMonitoring(
            meta: RequestMetadata,
            req: ClearVariableMonitoringReq
        ): OperationExecution<ClearVariableMonitoringReq, ClearVariableMonitoringResp> {
            throw NotImplementedError()
        }
    }

    @BeforeEach
    fun init() {
        val id = "a727d144-82bb-497a-a0c7-4ef2295910d4"
        val uuid = UUID.fromString(id)
        mockkStatic(UUID::class)
        every { UUID.randomUUID() } returns uuid

        ocppWampClient = mockk()
        every { ocppWampClient.connect() } returns Unit
        every { ocppWampClient.close() } returns Unit
        every { ocppWampClient.onAction(any()) } returns Unit
        mockkObject(OcppWampClient.Companion)
        every { OcppWampClient.Companion.newClient(any(), any(), any(), any()) } returns ocppWampClient
    }

    @Test
    fun `heartbeat request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"currentTime\":\"2022-02-15T00:00:00.000Z\"}",
            action = "heartbeat"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = HeartbeatReq()
        val response = csmsApi.heartbeat(requestMetadata, request)
        expectThat(response)
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }

    @Test
    fun `heartbeat request send`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"currentTime\":\"2022-02-15T00:00:00.000Z\"}",
            action = "heartbeat"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = HeartbeatReq()
        val response = csmsApi.send(requestMetadata, request).response as HeartbeatResp
        expectThat(response)
            .and { get { this.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }

    @Test
    fun `authorize 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"idTagInfo\":{\"status\" : \"Accepted\", \"expiryDate\" : \"2022-02-15T00:00:00.000Z\", \"parentIdTag\" : \"Tag2\" }}",
            action = "authorize"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = AuthorizeReq(idToken = IdTokenType("Tag1", IdTokenEnumType.Central))
        val response = csmsApi.authorize(requestMetadata, request)
        expectThat(response)
            .and { get { this.response.idTokenInfo.status }.isEqualTo(AuthorizationStatusEnumType.Accepted) }
            .and { get { this.response.idTokenInfo.cacheExpiryDateTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
            .and { get { this.response.idTokenInfo.groupIdToken?.idToken }.isEqualTo("Tag2") }
    }

    @Test
    fun `meterValues request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "meterValues"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = MeterValuesReq(
            3, listOf(
                MeterValueType(
                    listOf(
                        SampledValueType(0.6), SampledValueType(
                            2.4,
                            ReadingContextEnumType.SampleClock,
                            MeasurandEnumType.Temperature,
                            PhaseEnumType.L1,
                            LocationEnumType.Body,
                            signedMeterValue = SignedMeterValueType("Hello", "Bye", "Welcome", "Nice")
                        )
                    ),
                    Instant.parse("2022-02-15T00:00:00.000Z"),
                )
            )
        )
        val response = csmsApi.meterValues(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }

        val request2 = MeterValuesReq(
            3, listOf(
                MeterValueType(
                    listOf(
                        SampledValueType(0.6), SampledValueType(
                            2.4,
                            ReadingContextEnumType.SampleClock,
                            MeasurandEnumType.EnergyApparentNet,
                            PhaseEnumType.L1,
                            LocationEnumType.Body,
                            signedMeterValue = SignedMeterValueType("Hello", "Bye", "Welcome", "Nice")
                        )
                    ),
                    Instant.parse("2022-02-15T00:00:00.000Z"),
                )
            )
        )
        val response2 = csmsApi.meterValues(requestMetadata, request2)
        expectThat(response2)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.NOT_SEND) }
    }

    @Test
    fun `dataTransfer 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"status\" : \"Accepted\", \"data\" : \"2022-02-15T00:00:00.000Z\"}",
            action = "DataTransfer"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = DataTransferReq("vendor", "msgId12", "Hello")
        val response = csmsApi.dataTransfer(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
            .and { get { this.response.data }.isEqualTo("2022-02-15T00:00:00.000Z") }
            .and { get { this.response.status }.isEqualTo(DataTransferStatusEnumType.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(null) }
    }

    @Test
    fun `bootNotification 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"currentTime\" : \"2022-02-15T00:00:00.000Z\", \"interval\" : 10, \"status\": \"Accepted\"}",
            action = "BootNotification"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request =
            BootNotificationReq(ChargingStationType("model", "vendor", "firmware"), BootReasonEnumType.ApplicationReset)
        val response = csmsApi.bootNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
            .and { get { this.response.interval }.isEqualTo(10) }
            .and { get { this.response.status }.isEqualTo(RegistrationStatusEnumType.Accepted) }
    }

    @Test
    fun `start transaction 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"idTagInfo\" : { \"expiryDate\" : \"2022-02-15T00:00:00.000Z\", \"status\" : \"Accepted\"}, \"transactionId\" : 100}",
            action = "startTransaction"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = TransactionEventReq(
            eventType = TransactionEventEnumType.Started,
            timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
            triggerReason = TriggerReasonEnumType.Authorized,
            seqNo = 0,
            transactionInfo = TransactionType("1"),
            meterValue = listOf(
                MeterValueType(
                    listOf(SampledValueType(10.0, ReadingContextEnumType.TransactionBegin)),
                    Instant.parse("2022-02-15T00:00:00.000Z")
                )
            ),
            idToken = IdTokenType("Tag1", IdTokenEnumType.Central),
            evse = EVSEType(1, 1)
        )
        val response = csmsApi.transactionEvent(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
            .and { get { this.response.totalCost }.isEqualTo(null) }
            .and { get { this.response.chargingPriority }.isEqualTo(0) }
            .and {
                get { this.response.idTokenInfo }.isEqualTo(
                    IdTokenInfoType(
                        AuthorizationStatusEnumType.Accepted,
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            }
            .and { get { this.response.updatedPersonalMessage }.isEqualTo(null) }
    }

    @Test
    fun `status notification request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "statusNotification"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = StatusNotificationReq(
            1, ConnectorStatusEnumType.Occupied, 1, Instant.parse("2022-02-15T00:00:00.000Z")
        )
        val response = csmsApi.statusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `notifyReport 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyReport"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyReportReq(
            requestId = 1,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            seqNo = 2,
        )
        expectThrows<IllegalStateException> { csmsApi.notifyReport(requestMetadata, request) }
    }

    @Test
    fun `notifyReport 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyReport"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyReportReq(
            requestId = 1,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            seqNo = 2,
            tbc = true,
            reportData = listOf(
                ReportDataType(
                    ComponentType("component"),
                    VariableType("variable"),
                    listOf(VariableAttributeType("value")),
                    VariableCharacteristicsType(DataEnumType.DECIMAL, true)
                )
            )
        )
        val response = csmsApi.notifyReport(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `firmwareStatusNotification 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "FirmwareStatusNotification"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = FirmwareStatusNotificationReq(
            status = FirmwareStatusEnumType.InstallScheduled
        )
        val response = csmsApi.firmwareStatusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `firmwareStatusNotification 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "FirmwareStatusNotification"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = FirmwareStatusNotificationReq(
            status = FirmwareStatusEnumType.InstallScheduled
        )
        val response = csmsApi.firmwareStatusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }
    @Test
    fun `clearedChargingLimit 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "ClearedChargingLimit"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = ClearedChargingLimitReq(
            chargingLimitSource = ChargingLimitSourceEnumType.SO,
            evseId = 1
        )
        expectThrows<IllegalStateException> { csmsApi.clearedChargingLimit(requestMetadata, request) }
    }

    @Test
    fun `clearedChargingLimit 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "ClearedChargingLimit"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = ClearedChargingLimitReq(
            chargingLimitSource = ChargingLimitSourceEnumType.SO,
            evseId = 1
        )
        val response = csmsApi.clearedChargingLimit(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `getCertificateStatus 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"status\": \"Accepted\"}",
            action = "GetCertificateStatus"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = GetCertificateStatusReq(
            ocspRequestData = OCSPRequestDataType(HashAlgorithmEnumType.SHA256, "", "", "", "")
        )
        expectThrows<IllegalStateException> { csmsApi.getCertificateStatus(requestMetadata, request) }
    }

    @Test
    fun `getCertificateStatus 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"status\": \"Accepted\"}",
            action = "GetCertificateStatus"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = GetCertificateStatusReq(
            ocspRequestData = OCSPRequestDataType(HashAlgorithmEnumType.SHA256, "", "", "", "")
        )
        val response = csmsApi.getCertificateStatus(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
            .and { get { this.response.status }.isEqualTo(GetCertificateStatusEnumType.Accepted) }
    }

    @Test
    fun `notifyCustomerInformation 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyCustomerInformation"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyCustomerInformationReq(
            data = "Some data",
            tbc = true,
            seqNo = 0,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            requestId = 1
        )
        expectThrows<IllegalStateException> {
            csmsApi.notifyCustomerInformation(requestMetadata, request)
        }
    }

    @Test
    fun `notifyCustomerInformation 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyCustomerInformation"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyCustomerInformationReq(
            data = "Some data",
            tbc = true,
            seqNo = 0,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            requestId = 1
        )
        val response = csmsApi.notifyCustomerInformation(requestMetadata, request)
        expectThat(response) {
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
    }

    @Test
    fun `notifyEvent 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyEvent"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyEventReq(
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            seqNo = 0,
            eventData = listOf(
                EventDataType(
                    eventId = 1,
                    timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                    trigger = EventTriggerEnumType.Delta,
                    actualValue = "actualValue",
                    eventNotificationType = EventNotificationEnumType.HardWiredNotification,
                    component = ComponentType("component"),
                    variable = VariableType("variable"),
                    cause = 2,
                    techCode = "techCode",
                    techInfo = "techInfo",
                    cleared = true,
                    transactionId = "transaction",
                    variableMonitoringId = 3
                )
            ),
            tbc = true
        )

        val response = csmsApi.notifyEvent(requestMetadata, request)
        expectThat(response) {
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
    }

    @Test
    fun `notifyEVChargingSchedule 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"status\": \"Accepted\", \"statusInfo\": {\"reasonCode\": \"123\"}}",
            action = "NotifyEVChargingSchedule"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyEVChargingScheduleReq(
            timeBase = Instant.parse("2022-02-15T00:00:00.000Z"),
            evseId = 123,
            chargingSchedule = ChargingScheduleType(
                chargingRateUnit = ChargingRateUnitEnumType.A,
                chargingSchedulePeriod = listOf(
                    ChargingSchedulePeriodType(
                        startPeriod = 0,
                        limit = 1.0
                    )
                )
            )
        )
        expectThrows<IllegalStateException> {
            csmsApi.notifyEVChargingSchedule(requestMetadata, request)
        }
    }

    @Test
    fun `notifyEVChargingSchedule 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"status\": \"Accepted\", \"statusInfo\": {\"reasonCode\": \"123\"}}",
            action = "NotifyEVChargingSchedule"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyEVChargingScheduleReq(
            timeBase = Instant.parse("2022-02-15T00:00:00.000Z"),
            evseId = 123,
            chargingSchedule = ChargingScheduleType(
                chargingRateUnit = ChargingRateUnitEnumType.A,
                chargingSchedulePeriod = listOf(
                    ChargingSchedulePeriodType(
                        startPeriod = 0,
                        limit = 1.0
                    )
                )
            )
        )
        val response = csmsApi.notifyEVChargingSchedule(requestMetadata, request)
        expectThat(response) {
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
    }

    @Test
    fun `notifyChargingLimit 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyChargingLimit"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyChargingLimitReq(
            chargingLimit = ChargingLimitType(ChargingLimitSourceEnumType.CSO),
            evseId = 1
        )
        expectThrows<IllegalStateException> { csmsApi.notifyChargingLimit(requestMetadata, request) }
    }

    @Test
    fun `notifyChargingLimit 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyChargingLimit"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyChargingLimitReq(
            chargingLimit = ChargingLimitType(ChargingLimitSourceEnumType.CSO),
            evseId = 1
        )
        val response = csmsApi.notifyChargingLimit(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `logStatusNotification 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "LogStatusNotification"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = LogStatusNotificationReq(
            status = UploadLogStatusEnumType.Uploaded,
            requestId = 1
        )
        val response = csmsApi.logStatusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `logStatusNotification 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "LogStatusNotification"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = LogStatusNotificationReq(
            status = UploadLogStatusEnumType.Uploaded,
            requestId = 1
        )
        val response = csmsApi.logStatusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `publishFirmwareStatusNotification 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "PublishFirmwareStatusNotification"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = PublishFirmwareStatusNotificationReq(
            status = PublishFirmwareStatusEnumTypeGen.Published,
            location = listOf("location"),
            requestId = 1
        )
        expectThrows<IllegalStateException> { csmsApi.publishFirmwareStatusNotification(requestMetadata, request) }
    }

    @Test
    fun `publishFirmwareStatusNotification 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "PublishFirmwareStatusNotification"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = PublishFirmwareStatusNotificationReq(
            status = PublishFirmwareStatusEnumTypeGen.Published,
            location = listOf("location"),
            requestId = 1
        )
        val response = csmsApi.publishFirmwareStatusNotification(requestMetadata, request)
        expectThat(response) {
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
    }

    @Test
    fun `notifyDisplayMessages 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyDisplayMessages"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyDisplayMessagesReq(
            requestId = 1,
            tbc = false,
            messageInfo = listOf(
                MessageInfoType(
                    id = 2,
                    priority = MessagePriorityEnumType.InFront,
                    state = MessageStateEnumType.Charging,
                    startDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                    endDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                    transactionId = "2",
                    message = MessageContentType(
                        format = MessageFormatEnumType.URI,
                        language = "language",
                        content = "Message content"
                    ),
                    display = ComponentType(
                        name = "name",
                        instance = "instance",
                        evse = EVSEType(
                            id = 1,
                            connectorId = 2
                        )
                    )
                )
            )
        )
        val response = csmsApi.notifyDisplayMessages(requestMetadata, request)
        expectThat(response) {
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
    }
    @Test
    fun `notifyEVChargingNeeds 1-6 request`() {
        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyEVChargingNeeds"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyEVChargingNeedsReq(
            evseId = 1,
            chargingNeeds = ChargingNeedsType(EnergyTransferModeEnumType.DC)
        )
        expectThrows<IllegalStateException> { csmsApi.notifyEVChargingNeeds(requestMetadata, request) }
    }

    @Test
    fun `notifyEVChargingNeeds 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"status\": \"Accepted\"}",
            action = "NotifyEVChargingNeeds"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        var request = NotifyEVChargingNeedsReq(
            evseId = 1,
            chargingNeeds = ChargingNeedsType(EnergyTransferModeEnumType.DC),
            maxScheduleTuples = 2
        )
        val response = csmsApi.notifyEVChargingNeeds(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
            .and { get { this.response.status }.isEqualTo(NotifyEVChargingNeedsStatusEnumType.Accepted) }

        request = NotifyEVChargingNeedsReq(
            evseId = 1,
            chargingNeeds = ChargingNeedsType(
                EnergyTransferModeEnumType.DC,
                dcChargingParameters = DCChargingParametersType(evMaxVoltage = 2, evMaxCurrent = 1, stateOfCharge = -5)
            ),
            maxScheduleTuples = 2
        )
        expectThrows<IllegalStateException> { csmsApi.notifyEVChargingNeeds(requestMetadata, request) }
    }


    @Test
    fun `notifyMonitoringReport 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyMonitoringReport"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = NotifyMonitoringReportReq(
            requestId = 1,
            seqNo = 2,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z")
        )
        expectThrows<IllegalStateException> { csmsApi.notifyMonitoringReport(requestMetadata, request) }
    }

    @Test
    fun `notifyMonitoringReport 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "NotifyMonitoringReport"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        var request = NotifyMonitoringReportReq(
            requestId = 1,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            seqNo = 2,
            tbc = true,
            monitor = listOf(
                MonitoringDataType(
                    component = ComponentType("component"),
                    variable = VariableType("variable"),
                    variableMonitoring = listOf(
                        VariableMonitoringType(
                            id = 3,
                            transaction = true,
                            value = 10.0,
                            type = MonitorEnumType.Periodic,
                            severity = 3
                        )
                    )
                )
            )
        )
        val response = csmsApi.notifyMonitoringReport(requestMetadata, request)
        expectThat(response) {
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }

        request = NotifyMonitoringReportReq(
            requestId = 1,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            seqNo = 2,
            tbc = true,
            monitor = listOf(
                MonitoringDataType(
                    component = ComponentType("component"),
                    variable = VariableType("variable"),
                    variableMonitoring = listOf(
                        VariableMonitoringType(
                            id = 3,
                            transaction = true,
                            value = 10.0,
                            type = MonitorEnumType.Periodic,
                            severity = 10
                        )
                    )
                )
            )
        )
        expectThrows<IllegalStateException> { csmsApi.notifyMonitoringReport(requestMetadata, request) }
    }


    @Test
    fun `reservationStatusUpdate 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "ReservationStatusUpdate"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = ReservationStatusUpdateReq(
            reservationId = 1,
            reservationUpdateStatus = ReservationUpdateStatusEnumType.Expired
        )
        expectThrows<IllegalStateException> { csmsApi.reservationStatusUpdate(requestMetadata, request) }
    }

    @Test
    fun `reservationStatusUpdate 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "ReservationStatusUpdate"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = ReservationStatusUpdateReq(
            reservationId = 1,
            reservationUpdateStatus = ReservationUpdateStatusEnumType.Expired
        )
        val response = csmsApi.reservationStatusUpdate(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `securityEventNotification 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "SecurityEventNotification"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = SecurityEventNotificationReq(
            type = "type",
            timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
            techInfo = "techInfo"
        )
        expectThrows<IllegalStateException> { csmsApi.securityEventNotification(requestMetadata, request) }
    }

    @Test
    fun `securityEventNotification 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "SecurityEventNotification"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = SecurityEventNotificationReq(
            type = "type",
            timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
            techInfo = "techInfo"
        )
        val response = csmsApi.securityEventNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `signCertificate 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "SignCertificate"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = SignCertificateReq(
            csr = "csr",
            certificateType = CertificateSigningUseEnumType.V2GCertificate
        )
        expectThrows<IllegalStateException> { csmsApi.signCertificate(requestMetadata, request) }
    }

    @Test
    fun `signCertificate 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{\"status\": \"Accepted\"}",
            action = "SignCertificate"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = SignCertificateReq(
            csr = "csr",
            certificateType = CertificateSigningUseEnumType.V2GCertificate
        )
        val response = csmsApi.signCertificate(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
            .and { get { this.response.status }.isEqualTo(GenericStatusEnumType.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(null) }
    }

    @Test
    fun `reportChargingProfiles 1-6 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
            msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
            msgType = WampMessageType.CALL_RESULT,
            payload = "{}",
            action = "ReportChargingProfiles"
        )

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = ReportChargingProfilesReq(
            requestId = 2,
            chargingLimitSource = ChargingLimitSourceEnumType.CSO,
            tbc = false,
            evseId = 2,
            chargingProfile = listOf(
                ChargingProfileType(
                    id = 1,
                    stackLevel = 1,
                    chargingProfilePurpose = ChargingProfilePurposeEnumType.ChargingStationMaxProfile,
                    chargingProfileKind = ChargingProfileKindEnumType.Absolute,
                    chargingSchedule = listOf(
                        ChargingScheduleType(
                            id = 1,
                            chargingRateUnit = ChargingRateUnitEnumType.A,
                            chargingSchedulePeriod = listOf(ChargingSchedulePeriodType(1, 1.0)),
                            startSchedule = Instant.parse("2022-02-15T00:00:00.000Z")
                        )
                    )
                )
            )
        )
        expectThrows<IllegalStateException> { csmsApi.reportChargingProfiles(requestMetadata, request) }
    }


    @Test
    fun `reportChargingProfiles 2-0 request`() {

        every { ocppWampClient.sendBlocking(any()) } returns WampMessage(
                msgId = "a727d144-82bb-497a-a0c7-4ef2295910d4",
                msgType = WampMessageType.CALL_RESULT,
                payload = "{}",
                action = "ReportChargingProfiles"
        )

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "")
        val ocppId = "chargePoint2"
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

        val requestMetadata = RequestMetadata(ocppId)
        val request = ReportChargingProfilesReq(
                requestId = 2,
                chargingLimitSource = ChargingLimitSourceEnumType.CSO,
                tbc = false,
                evseId = 2,
                chargingProfile = listOf(
                        ChargingProfileType(
                                id = 1,
                                stackLevel = 1,
                                chargingProfilePurpose = ChargingProfilePurposeEnumType.ChargingStationMaxProfile,
                                chargingProfileKind = ChargingProfileKindEnumType.Absolute,
                                chargingSchedule = listOf(
                                        ChargingScheduleType(
                                                id = 1,
                                                chargingRateUnit = ChargingRateUnitEnumType.A,
                                                chargingSchedulePeriod = listOf(ChargingSchedulePeriodType(1, 1.0)),
                                                startSchedule = Instant.parse("2022-02-15T00:00:00.000Z")
                                        )
                                )
                        )
                )
        )
        val response = csmsApi.reportChargingProfiles(requestMetadata, request)
        expectThat(response)
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }
}
