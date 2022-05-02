package fr.simatix.cs.simulator

import fr.simatix.cs.simulator.adapter16.Ocpp16Adapter
import fr.simatix.cs.simulator.adapter16.impl.RealTransactionRepository
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.api.model.bootnotification.ChargingStationType
import fr.simatix.cs.simulator.api.model.bootnotification.ModemType
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.BootReasonEnumType
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.RegistrationStatusEnumType
import fr.simatix.cs.simulator.api.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.api.model.cancelreservation.CancelReservationResp
import fr.simatix.cs.simulator.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import fr.simatix.cs.simulator.api.model.certificateSigned.CertificateSignedReq
import fr.simatix.cs.simulator.api.model.certificateSigned.CertificateSignedResp
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.api.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType
import fr.simatix.cs.simulator.api.model.cleardisplaymessage.ClearDisplayMessageReq
import fr.simatix.cs.simulator.api.model.cleardisplaymessage.ClearDisplayMessageResp
import fr.simatix.cs.simulator.api.model.common.*
import fr.simatix.cs.simulator.api.model.common.enumeration.*
import fr.simatix.cs.simulator.api.model.datatransfer.enumeration.DataTransferStatusEnumType
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesReq
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesResp
import fr.simatix.cs.simulator.api.model.getallvariables.KeyValue
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportResp
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.api.model.common.enumeration.GenericStatusEnumType
import fr.simatix.cs.simulator.api.model.deletecertificate.DeleteCertificateReq
import fr.simatix.cs.simulator.api.model.deletecertificate.DeleteCertificateResp
import fr.simatix.cs.simulator.api.model.costupdated.CostUpdatedReq
import fr.simatix.cs.simulator.api.model.costupdated.CostUpdatedResp
import fr.simatix.cs.simulator.api.model.getchargingprofiles.GetChargingProfilesReq
import fr.simatix.cs.simulator.api.model.getchargingprofiles.GetChargingProfilesResp
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import fr.simatix.cs.simulator.api.model.customerinformation.CustomerInformationReq
import fr.simatix.cs.simulator.api.model.customerinformation.CustomerInformationResp
import fr.simatix.cs.simulator.api.model.getdisplaymessages.GetDisplayMessagesReq
import fr.simatix.cs.simulator.api.model.getdisplaymessages.GetDisplayMessagesResp
import fr.simatix.cs.simulator.api.model.getlog.GetLogReq
import fr.simatix.cs.simulator.api.model.getlog.GetLogResp
import fr.simatix.cs.simulator.api.model.getlog.enumeration.LogStatusEnumType
import fr.simatix.cs.simulator.api.model.getreport.GetReportReq
import fr.simatix.cs.simulator.api.model.getreport.GetReportResp
import fr.simatix.cs.simulator.api.model.gettransactionstatus.GetTransactionStatusReq
import fr.simatix.cs.simulator.api.model.gettransactionstatus.GetTransactionStatusResp
import fr.simatix.cs.simulator.api.model.getvariables.GetVariableResultType
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.api.model.getvariables.enumeration.GetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.installcertificate.InstallCertificateReq
import fr.simatix.cs.simulator.api.model.installcertificate.InstallCertificateResp
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationReq
import fr.simatix.cs.simulator.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType
import fr.simatix.cs.simulator.api.model.publishfirmware.PublishFirmwareReq
import fr.simatix.cs.simulator.api.model.publishfirmware.PublishFirmwareResp
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.api.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.api.model.reservenow.ReserveNowResp
import fr.simatix.cs.simulator.api.model.reservenow.enumeration.ReserveNowStatusEnumType
import fr.simatix.cs.simulator.api.model.reset.ResetReq
import fr.simatix.cs.simulator.api.model.reset.ResetResp
import fr.simatix.cs.simulator.api.model.reset.enumeration.ResetStatusEnumType
import fr.simatix.cs.simulator.api.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.api.model.sendlocallist.SendLocalListResp
import fr.simatix.cs.simulator.api.model.sendlocallist.enumeration.SendLocalListStatusEnumType
import fr.simatix.cs.simulator.api.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.api.model.setchargingprofile.SetChargingProfileResp
import fr.simatix.cs.simulator.api.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType
import fr.simatix.cs.simulator.api.model.setmonitoringbase.SetMonitoringBaseReq
import fr.simatix.cs.simulator.api.model.setmonitoringbase.SetMonitoringBaseResp
import fr.simatix.cs.simulator.api.model.setvariablemonitoring.SetVariableMonitoringReq
import fr.simatix.cs.simulator.api.model.setvariablemonitoring.SetVariableMonitoringResp
import fr.simatix.cs.simulator.api.model.setmonitoringlevel.SetMonitoringLevelReq
import fr.simatix.cs.simulator.api.model.setmonitoringlevel.SetMonitoringLevelResp
import fr.simatix.cs.simulator.api.model.setnetworkprofile.SetNetworkProfileReq
import fr.simatix.cs.simulator.api.model.setnetworkprofile.SetNetworkProfileResp
import fr.simatix.cs.simulator.api.model.setnetworkprofile.enumeration.SetNetworkProfileStatusEnumType
import fr.simatix.cs.simulator.api.model.setdisplaymessage.SetDisplayMessageReq
import fr.simatix.cs.simulator.api.model.setdisplaymessage.SetDisplayMessageResp
import fr.simatix.cs.simulator.api.model.setvariables.SetVariableResultType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.api.model.setvariables.enumeration.SetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.ChargingStateEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.ReasonEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TransactionEventEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TriggerReasonEnumType
import fr.simatix.cs.simulator.api.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.api.model.triggermessage.TriggerMessageResp
import fr.simatix.cs.simulator.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import fr.simatix.cs.simulator.api.model.unpublishfirmware.UnpublishFirmwareReq
import fr.simatix.cs.simulator.api.model.unpublishfirmware.UnpublishFirmwareResp
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareResp
import fr.simatix.cs.simulator.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType
import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core16.model.bootnotification.enumeration.RegistrationStatus
import fr.simatix.cs.simulator.core16.model.common.IdTagInfo
import fr.simatix.cs.simulator.core16.model.common.enumeration.AuthorizationStatus
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core16.model.datatransfer.enumeration.DataTransferStatus
import fr.simatix.cs.simulator.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import fr.simatix.cs.simulator.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import fr.simatix.cs.simulator.core16.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import fr.simatix.cs.simulator.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import fr.simatix.cs.simulator.core16.model.firmwarestatusnotification.enumeration.FirmwareStatus
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionReq
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionResp
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointErrorCode
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointStatus
import fr.simatix.cs.simulator.core16.model.stoptransaction.StopTransactionReq
import fr.simatix.cs.simulator.core16.model.stoptransaction.StopTransactionResp
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import fr.simatix.cs.simulator.transport.Transport
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq as BootNotificationReqGen
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq as DataTransferReqGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq as MeterValuesReqGen
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq as StatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ChargePointErrorCode as ChargePointErrorCodeGen
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferResp as DataTransferRespGen

class AdapterTest {
    private lateinit var transport: Transport
    private lateinit var chargePointOperations: RealChargePointOperations

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

        override fun updateFirmware(
            meta: RequestMetadata,
            req: UpdateFirmwareReq
        ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> {
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

        override fun dataTransfer(
            meta: RequestMetadata,
            req: DataTransferReqGen
        ): OperationExecution<DataTransferReqGen, DataTransferRespGen> {
            val response = DataTransferRespGen(
                status = DataTransferStatusEnumType.Accepted,
                data = "Some data",
                statusInfo = StatusInfoType(
                    reasonCode = "reasonCode",
                    additionalInfo = "additionalInfo"
                )
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
                    attributeStatus = if (it.variable.name == "AllowOfflineTxForUnknownId") {
                        GetVariableStatusEnumType.Accepted
                    } else {
                        GetVariableStatusEnumType.Rejected
                    },
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

        override fun publishFirmware(
                meta: RequestMetadata,
                req: PublishFirmwareReq
        ): OperationExecution<PublishFirmwareReq, PublishFirmwareResp> {
            throw NotImplementedError()
        }

        override fun getChargingProfiles(
                meta: RequestMetadata,
                req: GetChargingProfilesReq
        ): OperationExecution<GetChargingProfilesReq, GetChargingProfilesResp> {
            throw NotImplementedError()
        }

        override fun getInstalledCertificateIds(
            meta: RequestMetadata,
            req: GetInstalledCertificateIdsReq
        ): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp> {
            throw NotImplementedError()
        }

        override fun installCertificate(
                meta: RequestMetadata,
                req: InstallCertificateReq
        ): OperationExecution<InstallCertificateReq, InstallCertificateResp> {
            throw NotImplementedError()
        }

        override fun customerInformation(
                meta: RequestMetadata,
                req: CustomerInformationReq
        ): OperationExecution<CustomerInformationReq, CustomerInformationResp> {
            throw NotImplementedError()
        }

        override fun unpublishFirmware(
                meta: RequestMetadata,
                req: UnpublishFirmwareReq
        ): OperationExecution<UnpublishFirmwareReq, UnpublishFirmwareResp> {
            throw NotImplementedError()
        }

        override fun setVariableMonitoring(
                meta: RequestMetadata,
                req: SetVariableMonitoringReq
        ): OperationExecution<SetVariableMonitoringReq, SetVariableMonitoringResp> {
            throw NotImplementedError()
        }

        override fun setMonitoringLevel(
                meta: RequestMetadata,
                req: SetMonitoringLevelReq
        ): OperationExecution<SetMonitoringLevelReq, SetMonitoringLevelResp> {
            throw NotImplementedError()
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
            val response = GetTransactionStatusResp(true)
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
            throw NotImplementedError()
        }

        override fun costUpdated(meta: RequestMetadata, req: CostUpdatedReq): OperationExecution<CostUpdatedReq, CostUpdatedResp> {
            throw NotImplementedError()
        }

        override fun setDisplayMessage(
                meta: RequestMetadata,
                req: SetDisplayMessageReq
        ): OperationExecution<SetDisplayMessageReq, SetDisplayMessageResp> {
            throw NotImplementedError()
        }

        override fun deleteCertificate(
                meta: RequestMetadata,
                req: DeleteCertificateReq
        ): OperationExecution<DeleteCertificateReq, DeleteCertificateResp> {
            throw NotImplementedError()
        }
    }

    @BeforeEach
    fun init() {
        transport = mockk()
        chargePointOperations = mockk()

        mockkObject(ChargePointOperations.Companion)
        every {
            ChargePointOperations.Companion.newChargePointOperations(
                any(),
                any(),
                any()
            )
        } returns chargePointOperations
    }

    @Test
    fun `heartbeat request`() {
        val requestMetadata = RequestMetadata("")

        every { chargePointOperations.heartbeat(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            HeartbeatReq(),
            HeartbeatResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
            )
        )

        val operations = Ocpp16Adapter("", transport, csApi, RealTransactionRepository())
        val request = HeartbeatReqGen()
        val response = operations.heartbeat(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }

    @Test
    fun `authorize request`() {
        val requestMetadata = RequestMetadata("")

        every { chargePointOperations.authorize(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            AuthorizeReq(""),
            AuthorizeResp(
                idTagInfo = IdTagInfo(
                    AuthorizationStatus.ConcurrentTx,
                    expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                    parentIdTag = "Tag0"
                )
            )
        )

        val operations = Ocpp16Adapter("", transport, csApi, RealTransactionRepository())
        val request = AuthorizeReqGen(idToken = IdTokenType("Tag1", IdTokenEnumType.Central))
        val response = operations.authorize(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.response.idTokenInfo }.isEqualTo(
                    IdTokenInfoType(
                        status = AuthorizationStatusEnumType.ConcurrentTx,
                        cacheExpiryDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                        groupIdToken = IdTokenType("Tag0", IdTokenEnumType.Central)
                    )
                )
            }
    }

    @Test
    fun `meterValues request`() {
        val requestMetadata = RequestMetadata("")

        every { chargePointOperations.meterValues(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            MeterValuesReq(0, listOf()),
            MeterValuesResp()
        )

        val operations = Ocpp16Adapter("", transport, csApi, RealTransactionRepository())
        val request = MeterValuesReqGen(
            1, listOf(
                MeterValueType(
                    listOf(
                        SampledValueType(0.6), SampledValueType(
                            2.4,
                            ReadingContextEnumType.SampleClock,
                            MeasurandEnumType.EnergyActiveNet,
                            PhaseEnumType.L1,
                            LocationEnumType.Body,
                            signedMeterValue = SignedMeterValueType("Hello", "Bye", "Welcome", "Nice"),
                            UnitOfMeasure("kWh")
                        )
                    ),
                    Instant.parse("2022-02-15T00:00:00.000Z"),
                )
            )
        )
        val response = operations.meterValues(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.NOT_SEND) }
    }

    @Test
    fun `dataTransfer request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.dataTransfer(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            DataTransferReq(""),
            DataTransferResp(
                status = DataTransferStatus.Accepted,
                data = "Hello",
            )
        )

        val operations = Ocpp16Adapter("", transport, csApi, RealTransactionRepository())
        val request = DataTransferReqGen(
            vendorId = "vendor1",
            messageId = "ID100",
            data = "Bye"
        )
        val response = operations.dataTransfer(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
            .and { get { this.response.data }.isEqualTo("Hello") }
            .and { get { this.response.status }.isEqualTo(DataTransferStatusEnumType.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(null) }
    }

    @Test
    fun `bootNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.bootNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            BootNotificationReq("", ""),
            BootNotificationResp(
                Instant.parse("2022-02-15T00:00:00.000Z"),
                10,
                RegistrationStatus.Accepted
            )
        )

        val operations = Ocpp16Adapter("", transport, csApi, RealTransactionRepository())
        val request =
            BootNotificationReqGen(
                ChargingStationType("model", "vendor", "firmware", ModemType("a", "b")),
                BootReasonEnumType.ApplicationReset
            )
        val response = operations.bootNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
            .and { get { this.response.interval }.isEqualTo(10) }
            .and { get { this.response.status }.isEqualTo(RegistrationStatusEnumType.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(null) }
    }

    @Test
    fun `startTransaction and stopTransaction request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.startTransaction(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            StartTransactionReq(1, "Tag1", 100, Instant.parse("2022-02-15T00:00:00.000Z")),
            StartTransactionResp(
                IdTagInfo(AuthorizationStatus.Accepted, Instant.parse("2022-02-15T00:00:00.000Z"), "Tag2"), 10,
            )
        )

        every { chargePointOperations.stopTransaction(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            StopTransactionReq(200, Instant.parse("2022-02-15T00:00:00.000Z"), 12),
            StopTransactionResp(
                IdTagInfo(AuthorizationStatus.Accepted, Instant.parse("2022-02-15T00:00:00.000Z"), "Tag2")
            )
        )

        val operations = Ocpp16Adapter("", transport, csApi, RealTransactionRepository())
        val requestStart =
            TransactionEventReq(
                eventType = TransactionEventEnumType.Started,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                triggerReason = TriggerReasonEnumType.EVDetected,
                seqNo = 100,
                transactionInfo = TransactionType("T1"),
                evse = EVSEType(1),
                meterValue = listOf(
                    MeterValueType(
                        listOf(SampledValueType(10.0, ReadingContextEnumType.TransactionBegin)),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                ),
                idToken = IdTokenType("Tag1", IdTokenEnumType.Central),
                reservationId = 10
            )
        val responseStart = operations.transactionEvent(requestMetadata, requestStart)
        expectThat(responseStart)
            .and { get { this.request }.isEqualTo(requestStart) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
            .and {
                get { this.response.idTokenInfo }.isEqualTo(
                    IdTokenInfoType(
                        status = AuthorizationStatusEnumType.Accepted,
                        cacheExpiryDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                        groupIdToken = IdTokenType("Tag2", IdTokenEnumType.Central)
                    )
                )
            }

        val requestStop =
            TransactionEventReq(
                eventType = TransactionEventEnumType.Ended,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                triggerReason = TriggerReasonEnumType.Deauthorized,
                seqNo = 100,
                transactionInfo = TransactionType("T1", stoppedReason = ReasonEnumType.Timeout),
                evse = EVSEType(1),
                meterValue = listOf(
                    MeterValueType(
                        listOf(
                            SampledValueType(10.0, ReadingContextEnumType.TransactionEnd),
                            SampledValueType(
                                20.0,
                                ReadingContextEnumType.TransactionEnd,
                                MeasurandEnumType.CurrentExport
                            )
                        ),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    ),
                    MeterValueType(
                        listOf(
                            SampledValueType(
                                10.0,
                                ReadingContextEnumType.TransactionEnd,
                                MeasurandEnumType.EnergyActiveExportRegister
                            ),
                            SampledValueType(20.0, ReadingContextEnumType.TransactionBegin)
                        ),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                ),
                idToken = IdTokenType("Tag1", IdTokenEnumType.Central),
                reservationId = 10
            )
        val responseStop = operations.transactionEvent(requestMetadata, requestStop)
        expectThat(responseStop)
            .and { get { this.request }.isEqualTo(requestStop) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
            .and {
                get { this.response.idTokenInfo }.isEqualTo(
                    IdTokenInfoType(
                        status = AuthorizationStatusEnumType.Accepted,
                        cacheExpiryDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                        groupIdToken = IdTokenType("Tag2", IdTokenEnumType.Central)
                    )
                )
            }
    }

    @Test
    fun `statusNotification request with transactionEvent`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.statusNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            StatusNotificationReq(1, ChargePointErrorCode.NoError, ChargePointStatus.Available),
            StatusNotificationResp()
        )

        val operations = Ocpp16Adapter("", transport, csApi, RealTransactionRepository())
        val request = TransactionEventReq(
            eventType = TransactionEventEnumType.Updated,
            timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
            triggerReason = TriggerReasonEnumType.Deauthorized,
            seqNo = 100,
            evse = EVSEType(10),
            transactionInfo = TransactionType(
                "T1",
                ChargingStateEnumType.Charging,
                errorCode = ChargePointErrorCodeGen.EVCommunicationError
            ),
        )
        val response = operations.transactionEvent(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
    }

    @Test
    fun `statusNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.statusNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            StatusNotificationReq(1, ChargePointErrorCode.NoError, ChargePointStatus.Available),
            StatusNotificationResp()
        )

        val operations = Ocpp16Adapter("", transport, csApi, RealTransactionRepository())
        val request = StatusNotificationReqGen(
            connectorId = 1,
            connectorStatus = ConnectorStatusEnumType.Available,
            evseId = 2,
            timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
            errorCode = ChargePointErrorCodeGen.EVCommunicationError
        )
        val response = operations.statusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
    }

    @Test
    fun `firmwareStatusNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.firmwareStatusNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            FirmwareStatusNotificationReq(
                FirmwareStatus.Downloaded
            ),
            FirmwareStatusNotificationResp()
        )

        val operations = Ocpp16Adapter("", transport, csApi, RealTransactionRepository())
        val request =  FirmwareStatusNotificationReqGen(
            FirmwareStatusEnumType.Downloaded
        )
        val response = operations.firmwareStatusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
    }

    @Test
    fun `diagnosticsStatusNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.diagnosticsStatusNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            DiagnosticsStatusNotificationReq(
                status = DiagnosticsStatus.Uploaded
            ),
            DiagnosticsStatusNotificationResp()
        )

        val operations = Ocpp16Adapter("", transport, csApi, RealTransactionRepository())
        val request =  LogStatusNotificationReq(
            status = UploadLogStatusEnumType.Uploaded,
            requestId = 1
        )
        val response = operations.logStatusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
    }
}