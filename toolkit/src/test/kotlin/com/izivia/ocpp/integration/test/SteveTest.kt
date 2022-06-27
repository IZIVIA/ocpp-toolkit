package com.izivia.ocpp.integration.test

import com.izivia.ocpp.api.model.common.enumeration.IdTokenEnumType
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.CSMSApi
import com.izivia.ocpp.api.model.authorize.AuthorizeReq
import com.izivia.ocpp.api.model.common.enumeration.HashAlgorithmEnumType
import com.izivia.ocpp.api.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.api.model.bootnotification.ChargingStationType
import com.izivia.ocpp.api.model.bootnotification.enumeration.BootReasonEnumType
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
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.enumeration.GenericDeviceModelStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.MonitorEnumType
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageResp
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearMonitoringResultType
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringResp
import com.izivia.ocpp.api.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.api.model.clearvariablemonitoring.enumeration.ClearMonitoringStatusEnumType
import com.izivia.ocpp.api.model.common.CertificateHashDataType
import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.SignedMeterValueType
import com.izivia.ocpp.api.model.common.VariableType
import com.izivia.ocpp.api.model.common.enumeration.LocationEnumType
import com.izivia.ocpp.api.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.api.model.common.enumeration.PhaseEnumType
import com.izivia.ocpp.api.model.common.enumeration.RequestStartStopStatusEnumType
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq
import com.izivia.ocpp.api.model.datatransfer.DataTransferResp
import com.izivia.ocpp.api.model.datatransfer.enumeration.DataTransferStatusEnumType
import com.izivia.ocpp.api.model.getallvariables.GetAllVariablesReq
import com.izivia.ocpp.api.model.getallvariables.GetAllVariablesResp
import com.izivia.ocpp.api.model.getallvariables.KeyValue
import com.izivia.ocpp.api.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.api.model.getbasereport.GetBaseReportResp
import com.izivia.ocpp.api.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.api.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.api.model.common.enumeration.GenericStatusEnumType
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.api.model.getdisplaymessages.enumeration.GetDisplayMessagesStatusEnumType
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
import com.izivia.ocpp.api.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.api.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.api.model.deletecertificate.enumerations.DeleteCertificateStatusEnumType
import com.izivia.ocpp.api.model.getlog.GetLogReq
import com.izivia.ocpp.api.model.getlog.GetLogResp
import com.izivia.ocpp.api.model.getlog.enumeration.LogStatusEnumType
import com.izivia.ocpp.api.model.getmonitoringreport.GetMonitoringReportReq
import com.izivia.ocpp.api.model.getmonitoringreport.GetMonitoringReportResp
import com.izivia.ocpp.api.model.getreport.GetReportReq
import com.izivia.ocpp.api.model.getreport.GetReportResp
import com.izivia.ocpp.api.model.gettransactionstatus.GetTransactionStatusReq
import com.izivia.ocpp.api.model.gettransactionstatus.GetTransactionStatusResp
import com.izivia.ocpp.api.model.getvariables.GetVariableResultType
import com.izivia.ocpp.api.model.getvariables.GetVariablesReq
import com.izivia.ocpp.api.model.getvariables.GetVariablesResp
import com.izivia.ocpp.api.model.getvariables.enumeration.GetVariableStatusEnumType
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateStatusEnumType
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType
import com.izivia.ocpp.api.model.metervalues.MeterValuesReq
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.api.model.reservenow.ReserveNowReq
import com.izivia.ocpp.api.model.reservenow.ReserveNowResp
import com.izivia.ocpp.api.model.reservenow.enumeration.ReserveNowStatusEnumType
import com.izivia.ocpp.api.model.reset.ResetReq
import com.izivia.ocpp.api.model.reset.ResetResp
import com.izivia.ocpp.api.model.reset.enumeration.ResetStatusEnumType
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.api.model.sendlocallist.enumeration.SendLocalListStatusEnumType
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.api.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType
import com.izivia.ocpp.api.model.setvariablemonitoring.SetMonitoringResultType
import com.izivia.ocpp.api.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.api.model.setvariablemonitoring.SetVariableMonitoringResp
import com.izivia.ocpp.api.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.api.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.api.model.setnetworkprofile.SetNetworkProfileReq
import com.izivia.ocpp.api.model.setnetworkprofile.SetNetworkProfileResp
import com.izivia.ocpp.api.model.setnetworkprofile.enumeration.SetNetworkProfileStatusEnumType
import com.izivia.ocpp.api.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.api.model.setmonitoringbase.SetMonitoringBaseResp
import com.izivia.ocpp.api.model.setdisplaymessage.SetDisplayMessageReq
import com.izivia.ocpp.api.model.setdisplaymessage.SetDisplayMessageResp
import com.izivia.ocpp.api.model.setdisplaymessage.enumeration.DisplayMessageStatusEnumType
import com.izivia.ocpp.api.model.setvariables.SetVariableResultType
import com.izivia.ocpp.api.model.setvariables.SetVariablesReq
import com.izivia.ocpp.api.model.setvariables.SetVariablesResp
import com.izivia.ocpp.api.model.setvariables.enumeration.SetVariableStatusEnumType
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.api.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import com.izivia.ocpp.api.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.api.model.transactionevent.TransactionType
import com.izivia.ocpp.api.model.transactionevent.enumeration.ChargingStateEnumType
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
import com.izivia.ocpp.api.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.OcppVersion
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.lang.Thread.sleep

fun heartbeat(csmsApi: CSMSApi, ocppId: String, request: HeartbeatReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.heartbeat(requestMetadata, request)
    println("Heartbeat: $response\n")
}

fun authorize(csmsApi: CSMSApi, ocppId: String, request: AuthorizeReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.authorize(requestMetadata, request)
    println("Authorize: $response\n")
}

fun meterValues(csmsApi: CSMSApi, ocppId: String, request: MeterValuesReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.meterValues(requestMetadata, request)
    println("MeterValues : $response\n")
}

fun dataTransfer(csmsApi: CSMSApi, ocppId: String, request: DataTransferReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.dataTransfer(requestMetadata, request)
    println("DataTransfer: $response\n")
}

fun bootNotification(csmsApi: CSMSApi, ocppId: String, request: BootNotificationReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.bootNotification(requestMetadata, request)
    println("BootNotification: $response\n")
}

fun transactionEvent(csmsApi: CSMSApi, ocppId: String, request: TransactionEventReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.transactionEvent(requestMetadata, request)
    println("TransactionEvent: $response\n")
}

fun statusNotification(csmsApi: CSMSApi, ocppId: String, request: StatusNotificationReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.statusNotification(requestMetadata, request)
    println("StatusNotification: $response\n")
}

fun notifyCustomerInformation(csmsApi: CSMSApi, ocppId: String, request: NotifyCustomerInformationReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.notifyCustomerInformation(requestMetadata, request)
    println("NotifyCustomerInformation: $response\n")
}

fun logStatusNotification(csmsApi: CSMSApi, ocppId: String, request: LogStatusNotificationReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.logStatusNotification(requestMetadata, request)
    println("LogStatusNotification: $response\n")
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        throw IllegalArgumentException("1 argument is required")
    }

    val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = args[0])
    val ocppId = "chargePoint2"
    val csApi: CSApi = object : CSApi {

        override fun start() = throw NotImplementedError("ChargePoint can't start a server")

        override fun stop() = throw NotImplementedError("ChargePoint can't stop a server")

        override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
            return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS), req,
                ResetResp(ResetStatusEnumType.Accepted)
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

        override fun clearVariableMonitoring(
            meta: RequestMetadata,
            req: ClearVariableMonitoringReq
        ): OperationExecution<ClearVariableMonitoringReq, ClearVariableMonitoringResp> {
            val response = ClearVariableMonitoringResp(
                listOf(
                    ClearMonitoringResultType(
                        status = ClearMonitoringStatusEnumType.Accepted,
                        id = 1,
                        StatusInfoType(
                            "reasonCode",
                            "additionalInfo"
                        )
                    )
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

        override fun getInstalledCertificateIds(
                meta: RequestMetadata,
                req: GetInstalledCertificateIdsReq
        ): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp> {
            val response = GetInstalledCertificateIdsResp(
                    GetInstalledCertificateStatusEnumType.Accepted,
                    listOf(
                            CertificateHashDataChainType(GetCertificateIdUseEnumType.CSMSRootCertificate, CertificateHashDataType(
                                HashAlgorithmEnumType.SHA512, "", "", ""), listOf(CertificateHashDataType(
                                HashAlgorithmEnumType.SHA512, "", "", ""), CertificateHashDataType(HashAlgorithmEnumType.SHA512, "", "", ""))),
                    ),
                    StatusInfoType("reason", "info")
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

        override fun setDisplayMessage(
                meta: RequestMetadata,
                req: SetDisplayMessageReq
        ): OperationExecution<SetDisplayMessageReq, SetDisplayMessageResp> {
            val response = SetDisplayMessageResp(DisplayMessageStatusEnumType.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun costUpdated(
                meta: RequestMetadata,
                req: CostUpdatedReq
        ): OperationExecution<CostUpdatedReq, CostUpdatedResp> {
            val response = CostUpdatedResp()
            return OperationExecution(ExecutionMetadata(meta,RequestStatus.SUCCESS),req,response)
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
    }

    val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApi)

    csmsApi.connect()

    heartbeat(csmsApi, ocppId, HeartbeatReq())

    bootNotification(
        csmsApi,
        ocppId,
        BootNotificationReq(ChargingStationType("model", "vendor", "firmware"), BootReasonEnumType.ApplicationReset)
    )

    authorize(csmsApi, ocppId, AuthorizeReq(idToken = IdTokenType("Tag1", IdTokenEnumType.Central)))

    heartbeat(csmsApi, ocppId, HeartbeatReq())

    meterValues(
        csmsApi, ocppId, MeterValuesReq(
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
    )

    dataTransfer(csmsApi, ocppId, DataTransferReq("vendor", "msgId12", "Hello"))

    transactionEvent(
        csmsApi, ocppId, TransactionEventReq(
            eventType = TransactionEventEnumType.Started,
            timestamp = Clock.System.now(),
            triggerReason = TriggerReasonEnumType.Authorized,
            seqNo = 0,
            transactionInfo = TransactionType("2", chargingState = ChargingStateEnumType.EVConnected),
            meterValue = listOf(
                MeterValueType(
                    listOf(SampledValueType(10.0, ReadingContextEnumType.TransactionBegin)),
                    Instant.parse("2022-02-15T00:00:00.000Z")
                )
            ),
            idToken = IdTokenType("Tag1", IdTokenEnumType.Central),
            evse = EVSEType(1, 1)
        )
    )

    sleep(8000)

    transactionEvent(
        csmsApi, ocppId, TransactionEventReq(
            eventType = TransactionEventEnumType.Updated,
            timestamp = Clock.System.now(),
            triggerReason = TriggerReasonEnumType.Authorized,
            seqNo = 0,
            transactionInfo = TransactionType("2", chargingState = ChargingStateEnumType.Charging),
            evse = EVSEType(1)
        )
    )

    sleep(8000)

    statusNotification(
        csmsApi,
        ocppId,
        StatusNotificationReq(
            connectorId = 1,
            connectorStatus = ConnectorStatusEnumType.Faulted,
            evseId = 1,
            timestamp = Clock.System.now(),
            errorCode = ChargePointErrorCode.EVCommunicationError
        )
    )
    sleep(8000)

    transactionEvent(
        csmsApi, ocppId, TransactionEventReq(
            eventType = TransactionEventEnumType.Ended,
            timestamp = Clock.System.now(),
            triggerReason = TriggerReasonEnumType.Authorized,
            seqNo = 0,
            transactionInfo = TransactionType("2", ChargingStateEnumType.SuspendedEVSE),
            meterValue = listOf(
                MeterValueType(
                    listOf(SampledValueType(10.0, ReadingContextEnumType.TransactionEnd)),
                    Instant.parse("2022-02-15T00:00:00.000Z")
                )
            ),
            idToken = IdTokenType("Tag1", IdTokenEnumType.Central),
            evse = EVSEType(1, 1)
        )
    )

    sleep(8000)
    try {
        notifyCustomerInformation(
            csmsApi,
            ocppId,
            NotifyCustomerInformationReq(
                data = "Some data",
                tbc = true,
                seqNo = 0,
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                requestId = 1
            )
        )
    } catch (e: Exception) {
        println(e)
    }

    logStatusNotification(
        csmsApi,
        ocppId,
        LogStatusNotificationReq(
            status = UploadLogStatusEnumType.Uploaded,
            requestId = 1
        )
    )

    csmsApi.close()

}
