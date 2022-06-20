package com.izivia.ocpp.integration.test

import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.model.common.enumeration.HashAlgorithmEnumType
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedReq
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedResp
import com.izivia.ocpp.api.model.certificateSigned.enumeration.CertificateSignedStatusEnumType
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import com.izivia.ocpp.api.model.clearcache.ClearCacheReq
import com.izivia.ocpp.api.model.clearcache.ClearCacheResp
import com.izivia.ocpp.api.model.clearcache.enumeration.ClearCacheStatusEnumType
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearMonitoringResultType
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringResp
import com.izivia.ocpp.api.model.clearvariablemonitoring.enumeration.ClearMonitoringStatusEnumType
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageResp
import com.izivia.ocpp.api.model.cleardisplaymessage.enumeration.ClearMessageStatusEnumType
import com.izivia.ocpp.api.model.common.CertificateHashDataType
import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.common.VariableType
import com.izivia.ocpp.api.model.common.enumeration.GenericDeviceModelStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.GenericStatusEnumType
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
import com.izivia.ocpp.api.model.common.enumeration.MonitorEnumType
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.api.model.getdisplaymessages.enumeration.GetDisplayMessagesStatusEnumType
import com.izivia.ocpp.api.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.api.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.api.model.deletecertificate.enumerations.DeleteCertificateStatusEnumType
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionResp
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
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateStatusEnumType
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.api.model.reservenow.ReserveNowReq
import com.izivia.ocpp.api.model.reservenow.ReserveNowResp
import com.izivia.ocpp.api.model.reservenow.enumeration.ReserveNowStatusEnumType
import com.izivia.ocpp.api.model.reset.ResetReq
import com.izivia.ocpp.api.model.reset.ResetResp
import com.izivia.ocpp.api.model.reset.enumeration.ResetEnumType
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
import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.messages.WampMessageType
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import kotlinx.datetime.Clock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.spy
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.lang.Thread.sleep

class IntegrationTestCSApi {

    private lateinit var server: OcppWampServer
    private val port = 12345
    private lateinit var csApi: CSApi

    @BeforeEach
    fun init() {
        server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
        server.register(object : OcppWampServerHandler {
            override fun accept(ocppId: String): Boolean = "chargePoint2" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage =
                when (msg.action?.lowercase()) {
                    "heartbeat" ->
                        WampMessage.CallResult(msg.msgId, """{"currentTime":"${Clock.System.now()}"}""")
                    else -> {
                        println("unhandled action for message: ${msg.toJson()}")
                        WampMessage.CallError(msg.msgId, "{}")
                    }
                }
        })

        server.start()

        csApi = object : CSApi {

            override fun start() {
                throw NotImplementedError()
            }

            override fun stop() {
                throw NotImplementedError()
            }

            override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
                val response = if (req.type == ResetEnumType.Immediate) {
                    ResetResp(ResetStatusEnumType.Scheduled)
                } else {
                    ResetResp(ResetStatusEnumType.Rejected)
                }
                return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
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
                val response = CertificateSignedResp(CertificateSignedStatusEnumType.Accepted, StatusInfoType("reason"))
                return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
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
                val response = ClearDisplayMessageResp(ClearMessageStatusEnumType.Accepted)
                return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
            }

            override fun publishFirmware(
                    meta: RequestMetadata,
                    req: PublishFirmwareReq
            ): OperationExecution<PublishFirmwareReq, PublishFirmwareResp> {
                val response = PublishFirmwareResp(GenericStatusEnumType.Accepted)
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

            override fun customerInformation(
                    meta: RequestMetadata,
                    req: CustomerInformationReq
            ): OperationExecution<CustomerInformationReq, CustomerInformationResp> {
                val response = CustomerInformationResp(
                        CustomerInformationStatusEnumType.Accepted,
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
                                                        "")
                                        )
                                ),
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
        }
    }

    @Test
    fun `1-6 test`() {

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "ws://localhost:$port/ws")
        val ocppId = "chargePoint2"

        val csApiSpy = spy(csApi)
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApiSpy)

        csmsApi.connect()

        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "ChangeAvailability", "{\"connectorId\": 1,\"type\": \"Operative\"}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "ChangeConfiguration", "{\"key\": \"key\",\"value\": \"empty\"}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "ClearCache", "{}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "RemoteStartTransaction", "{\"idTag\": \"Tag1\",\"connectorId\": 2}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "RemoteStopTransaction", "{\"transactionId\": 15}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "UnlockConnector", "{\"connectorId\": 2}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "Reset", "{\"type\": \"Hard\"}"
            )
        )

        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetConfiguration", "{}"
            )
        )

        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetConfiguration", "{\"key\": [\"AllowOfflineTxForUnknownId\",\"AuthorizationCacheEnabled\"]}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "CancelReservation", "{\"reservationId\": 3}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "ClearChargingProfile", "{\"id\": 4}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetCompositeSchedule", "{\"connectorId\": 1, \"duration\": 2}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetLocalListVersion", "{}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "UpdateFirmware", "{\"location\": \"http://www.ietf.org/rfc/rfc2396.txt\",\"retrieveDate\": \"2022-02-15T00:00:00.000Z\"}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "SendLocalList", "{\"listVersion\": 1, \"updateType\": \"Full\"}"
            )
        )

        server.sendBlocking(
            "chargePoint2", WampMessage(
            WampMessageType.CALL, "1",
            "TriggerMessage", "{\"requestedMessage\": \"BootNotification\"}"
        )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL,
                "1",
                "SetChargingProfile",
                "{\"connectorId\": 1, \"csChargingProfiles\": {\"chargingProfileId\": 1, \"stackLevel\": 1, \"chargingProfilePurpose\": \"ChargePointMaxProfile\", \"chargingProfileKind\": \"Absolute\", \"chargingSchedule\": {\"chargingRateUnit\": \"W\", \"chargingSchedulePeriod\": [{\"startPeriod\": 1, \"limit\": 1.5}]}}}"
            )
        )

        server.sendBlocking(
            "chargePoint2", WampMessage(
            WampMessageType.CALL, "1",
            "ReserveNow", "{\"connectorId\": 1, \"expiryDate\": \"2022-02-15T00:00:00.000Z\", \"idTag\": \"Tag1\", \"parentIdTag\": \"Tag2\", \"reservationId\": 2}"
        )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetDiagnostics", "{\"location\": \"http://www.ietf.org/rfc/rfc2396.txt\"}"
            )
        )

        server.sendBlocking(
            "chargePoint2", WampMessage(
            WampMessageType.CALL, "1",
            "DataTransfer", "{\"vendorId\": \"vendorId\", \"messageId\": \"messageId\", \"data\": \"Some data\"}"
            )
        )

        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "CertificateSigned", "{\"certificateChain\": \"certificateChain\", \"certificateType\": \"ChargingStationCertificate\"}"
            )
        )

        verify(csApiSpy, times(1)).reset(any(), any())
        verify(csApiSpy, times(1)).changeAvailability(any(), any())
        verify(csApiSpy, times(1)).setVariables(any(), any())
        verify(csApiSpy, times(1)).clearCache(any(), any())
        verify(csApiSpy, times(1)).requestStartTransaction(any(), any())
        verify(csApiSpy, times(1)).requestStopTransaction(any(), any())
        verify(csApiSpy, times(1)).unlockConnector(any(), any())
        verify(csApiSpy, times(1)).getAllVariables(any(), any())
        verify(csApiSpy, times(1)).getVariables(any(), any())
        verify(csApiSpy, times(1)).cancelReservation(any(), any())
        verify(csApiSpy, times(1)).clearChargingProfile(any(), any())
        verify(csApiSpy, times(1)).getCompositeSchedule(any(), any())
        verify(csApiSpy, times(1)).getLocalListVersion(any(), any())
        verify(csApiSpy, times(1)).updateFirmware(any(), any())
        verify(csApiSpy, times(1)).sendLocalList(any(), any())
        verify(csApiSpy, times(1)).triggerMessage(any(), any())
        verify(csApiSpy, times(1)).setChargingProfile(any(), any())
        verify(csApiSpy, times(1)).reserveNow(any(), any())
        verify(csApiSpy, times(1)).getLog(any(), any())
        verify(csApiSpy, times(1)).dataTransfer(any(), any())


        csmsApi.close()
    }

    @Test
    fun `2-0 test`() {
        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "ws://localhost:$port/ws")
        val ocppId = "chargePoint2"

        val csApiSpy = spy(csApi)
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApiSpy)

        csmsApi.connect()

        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "ChangeAvailability", "{\"operationalStatus\": \"Operative\"}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL,
                "1",
                "SetVariables",
                "{\"setVariableData\": [ {\"attributeValue\": \"value\", \"component\": {\"name\": \"component1\"}, \"variable\": {\"name\":\"variable1\"}} ]}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "ClearCache", "{}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL,
                "1",
                "RequestStartTransaction",
                "{\"remoteStartId\": 12,\"idToken\": {\"idToken\": \"Tag1\", \"type\": \"Central\"}}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "RequestStopTransaction", "{\"transactionId\": \"15\"}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "UnlockConnector", "{\"connectorId\": 2,\"evseId\": 0}"
            )
        )
        server.sendBlocking(
            "chargePoint2",
            WampMessage(WampMessageType.CALL, "1", "Reset", "{\"type\": \"OnIdle\"}")
        )
        server.sendBlocking(
            "chargePoint2",
            WampMessage(
                WampMessageType.CALL, "1", "GetVariables",
                "{\"getVariableData\": [{\"component\": {\"name\": \"component\"}, \"variable\": {\"name\":\"AllowOfflineTxForUnknownId\"}}]}"
            )
        )
        server.sendBlocking(
            "chargePoint2",
            WampMessage(WampMessageType.CALL, "1", "GetReport", "{\"requestId\": 1}")
        )
        server.sendBlocking(
            "chargePoint2",
            WampMessage(
                WampMessageType.CALL,
                "1",
                "GetBaseReport",
                "{\"requestId\": 1, \"reportBase\": \"ConfigurationInventory\"}"
            )
        )
        server.sendBlocking(
            "chargePoint2",
            WampMessage(WampMessageType.CALL, "1", "CancelReservation", "{\"reservationId\": 3}")
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "ClearChargingProfile", "{\"chargingProfileId\": 4}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetCompositeSchedule", "{\"evseId\": 1, \"duration\": 2}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetLocalListVersion", "{}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
            WampMessageType.CALL, "1",
            "UpdateFirmware", "{\"requestId\": 1, \"firmware\": { \"location\": \"http://www.ietf.org/rfc/rfc2396.txt\", \"retrieveDateTime\": \"2022-02-15T00:00:00.000Z\"}}"
        )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "SendLocalList", "{\"versionNumber\": 1, \"updateType\": \"Full\"}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
            WampMessageType.CALL, "1",
            "TriggerMessage", "{\"requestedMessage\": \"BootNotification\"}"
        )
        )
        server.sendBlocking(
            "chargePoint2",
            WampMessage(
                WampMessageType.CALL,
                "1",
                "SetChargingProfile",
                "{\"evseId\": 1, \"chargingProfile\": {\"id\": 1, \"stackLevel\": 1, \"chargingProfilePurpose\": \"TxProfile\", \"chargingProfileKind\": \"Absolute\", \"chargingSchedule\": [{\"id\": 1, \"chargingRateUnit\": \"W\", \"chargingSchedulePeriod\": [{\"startPeriod\": 1, \"limit\": 1.5}]}]}}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
            WampMessageType.CALL, "1",
            "ReserveNow", "{\"id\": 1, \"expiryDateTime\": \"2022-02-15T00:00:00.000Z\", \"idToken\": {\"idToken\": \"Tag1\", \"type\": \"Central\"}}"
        )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetLog", "{\"requestId\": 1, \"logType\": \"DiagnosticsLog\", \"log\": {\"remoteLocation\": \"http://www.ietf.org/rfc/rfc2396.txt\"}}"
            )
        )

        server.sendBlocking(
            "chargePoint2", WampMessage(
            WampMessageType.CALL, "1",
            "DataTransfer", "{\"vendorId\": \"vendorId\", \"messageId\": \"messageId\", \"data\": \"Some data\"}"
            )
        )
        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "ClearDisplayMessage", "{\"id\": 1}"
            )
        )

        server.sendBlocking(
            "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "CertificateSigned", "{\"certificateChain\": \"certificateChain\", \"certificateType\": \"ChargingStationCertificate\"}"
            )
        )

        server.sendBlocking(
            "chargePoint2", WampMessage(
            WampMessageType.CALL, "1",
            "GetChargingProfiles",
            "{\"requestId\": \"00\"," +
                    " \"evseId\": \"243\"," +
                    "\"chargingProfile\": " +
                        "{\"chargingProfilePurpose\": \"TxProfile\", " +
                        "\"stackLevel\" : \"243\"," +
                        "\"chargingProfileId\" : [23]," +
                        "\"chargingLimitSource\" : [\"CSO\"]" +
                        "}" +
                    "}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetInstalledCertificateIds",
                "{\"certificateType\": " +
                            "[" +
                                "\"MORootCertificate\"," +
                                "\"CSMSRootCertificate\"" +
                            "]" +
                        "}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "InstallCertificate", "{\"certificateType\": \"CSMSRootCertificate\", \"certificate\": \"certificateString\"}"
            )
        )
        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "CustomerInformation",
                "{" +
                            "\"requestId\": 12378, " +
                            "\"report\": false," +
                            "\"clear\": false," +
                            "\"customerIdentifier\": \"identifier\"," +
                            "\"idToken\": " +
                                "{" +
                                    "\"idToken\": \"id1378\"," +
                                    "\"type\": \"Central\"" +
                                "}," +
                            "\"customerCertificate\": " +
                                "{" +
                                    "\"hashAlgorithm\": \"SHA512\"," +
                                    "\"issuerNameHash\": \"issuernamehash\"," +
                                    "\"issuerKeyHash\": \"issuerkeyhash\"," +
                                    "\"serialNumber\": \"serialn\"" +
                                "}" +
                        "}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "UnpublishFirmware", "{\"checksum\": \"value\"}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "PublishFirmware",
                "{" +
                            "\"location\": \"location string\", " +
                            "\"retries\": 312, " +
                            "\"checksum\": \"identifier string\", " +
                            "\"requestId\": 23, " +
                            "\"retryInterval\": 32 " +
                        "}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "SetVariableMonitoring",
                "{\"setMonitoringData\": " +
                            "[{" +
                                "\"id\": 32," +
                                "\"transaction\": false," +
                                "\"value\": 45.3," +
                                "\"type\": \"UpperThreshold\"," +
                                "\"severity\": 2," +
                                "\"component\": " +
                                    "{" +
                                        "\"name\": \"name\"" +
                                    "}," +
                                "\"variable\":" +
                                    "{" +
                                        "\"name\": \"name\"" +
                                    "}" +
                            "}]" +
                        "}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "SetMonitoringLevel", "{\"severity\": 3}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "SetNetworkProfile",
                "{\"configurationSlot\": 2," +
                        " \"connectionData\": " +
                            "{" +
                                "\"ocppVersion\":\"OCPP12\"," +
                                "\"ocppTransport\":\"JSON\"," +
                                "\"ocppCsmsUrl\":\"url\"," +
                                "\"messageTimeout\": 3," +
                                "\"securityProfile\": 56," +
                                "\"ocppInterface\":\"Wired0\"," +
                                "\"vpn\":" +
                                    "{" +
                                        "\"server\":\"server\"," +
                                        "\"user\":\"user\"," +
                                        "\"group\":\"group\"," +
                                        "\"password\":\"pass\"," +
                                        "\"key\":\"key\"," +
                                        "\"type\":\"IKEv2\"" +
                                    "}" +
                                "," +
                                "\"apn\": " +
                                    "{" +
                                        "\"apn\":\"IKEv2\"," +
                                        "\"apnUserName\":\"username\"," +
                                        "\"apnPassword\":\"pass\"," +
                                        "\"simPin\":43," +
                                        "\"preferredNetwork\":\"pref\"," +
                                        "\"useOnlyPreferredNetwork\":false," +
                                        "\"apnAuthentication\":\"AUTO\"" +
                                    "}" +
                            "}" +
                        "}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetTransactionStatus", "{\"transactionId\": \"valueid\"}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "SetMonitoringBase", "{\"monitoringBase\": \"All\"}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetDisplayMessages", "{\"id\":[321],\"requestId\":\"624387\",\"priority\":\"AlwaysFront\",\"state\":\"Charging\"}"
            )
        )


        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "CostUpdated", "{\"totalCost\": \"43.3\", \"transactionId\": \"transactionId\"}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "SetDisplayMessage",
                "{\"message\": " +
                            "{" +
                                "\"id\":321," +
                                "\"priority\": \"AlwaysFront\"," +
                                "\"state\": \"Charging\"," +
                                "\"startDateTime\": \"2022-02-15T00:00:00.000Z\"," +
                                "\"endDateTime\": \"2022-02-15T00:00:00.000Z\"," +
                                "\"transactionId\": \"identifierstringtransacionid\"," +
                                "\"message\": " +
                                    "{" +
                                        "\"format\": \"ASCII\"," +
                                        "\"language\": \"frfr\"," +
                                        "\"content\": \"messqge content\"" +
                                    "}" +
                            "}" +
                        "}"
            )
        )

        server.sendBlocking(
            "chargePoint2", WampMessage(
            WampMessageType.CALL, "1",
            "DeleteCertificate", "{\"certificateHashData\": " +
                    "{\"hashAlgorithm\": \"SHA512\"," +
                    " \"issuerNameHash\": \"someHash\"," +
                    " \"issuerKeyHash\": \"KeyHashed\", " +
                    "\"serialNumber\": \"763478643276432\"}}"
            )
        )

        server.sendBlocking(
                "chargePoint2", WampMessage(
                WampMessageType.CALL, "1",
                "GetMonitoringReport",
                "{\"requestId\": 43243, " +
                        "\"monitoringCriteria\": [\"ThresholdMonitoring\"]," +
                        "\"componentVariable\": [{" +
                                    "\"component\": {" +
                                        "\"name\": \"name\"," +
                                        "\"instance\": \"instance\"" +
                                    "}," +
                                    "\"variable\":  {" +
                                        "\"name\": \"name\"," +
                                        "\"instance\": \"instance\"" +
                                    "}" +
                                "}" +
                            "]" +
                        "}"
            )
        )


        server.sendBlocking(
            "chargePoint2", WampMessage(
            WampMessageType.CALL, "1",
            "ClearVariableMonitoring", "{\"ids\": [1, 2]}"
        )
        )

        verify(csApiSpy, times(1)).reset(any(), any())
        verify(csApiSpy, times(1)).changeAvailability(any(), any())
        verify(csApiSpy, times(1)).setVariables(any(), any())
        verify(csApiSpy, times(1)).clearCache(any(), any())
        verify(csApiSpy, times(1)).requestStartTransaction(any(), any())
        verify(csApiSpy, times(1)).requestStopTransaction(any(), any())
        verify(csApiSpy, times(1)).unlockConnector(any(), any())
        verify(csApiSpy, times(1)).getVariables(any(), any())
        verify(csApiSpy, times(1)).getBaseReport(any(), any())
        verify(csApiSpy, times(1)).getReport(any(), any())
        verify(csApiSpy, times(1)).cancelReservation(any(), any())
        verify(csApiSpy, times(1)).clearChargingProfile(any(), any())
        verify(csApiSpy, times(1)).getCompositeSchedule(any(), any())
        verify(csApiSpy, times(1)).getLocalListVersion(any(), any())
        verify(csApiSpy, times(1)).updateFirmware(any(), any())
        verify(csApiSpy, times(1)).sendLocalList(any(), any())
        verify(csApiSpy, times(1)).triggerMessage(any(), any())
        verify(csApiSpy, times(1)).setChargingProfile(any(), any())
        verify(csApiSpy, times(1)).reserveNow(any(), any())
        verify(csApiSpy, times(1)).getLog(any(), any())
        verify(csApiSpy, times(1)).dataTransfer(any(), any())
        verify(csApiSpy, times(1)).clearDisplayMessage(any(), any())
        verify(csApiSpy, times(1)).certificateSigned(any(), any())
        verify(csApiSpy, times(1)).customerInformation(any(), any())
        verify(csApiSpy, times(1)).installCertificate(any(), any())
        verify(csApiSpy, times(1)).getInstalledCertificateIds(any(), any())
        verify(csApiSpy, times(1)).unpublishFirmware(any(), any())
        verify(csApiSpy, times(1)).getChargingProfiles(any(), any())
        verify(csApiSpy, times(1)).setVariableMonitoring(any(), any())
        verify(csApiSpy, times(1)).setMonitoringLevel(any(), any())
        verify(csApiSpy, times(1)).publishFirmware(any(), any())
        verify(csApiSpy, times(1)).setNetworkProfile(any(), any())
        verify(csApiSpy, times(1)).getTransactionStatus(any(), any())
        verify(csApiSpy, times(1)).setMonitoringBase(any(), any())
        verify(csApiSpy, times(1)).getDisplayMessages(any(), any())
        verify(csApiSpy, times(1)).costUpdated(any(), any())
        verify(csApiSpy, times(1)).setDisplayMessage(any(), any())
        verify(csApiSpy, times(1)).deleteCertificate(any(),any())
        verify(csApiSpy, times(1)).getMonitoringReport(any(), any())
        verify(csApiSpy, times(1)).clearVariableMonitoring(any(), any())

        csmsApi.close()
    }


    @AfterEach
    fun finalize() {
        //Avoid closing the server while client are still connected
        sleep(50)
        server.stop()
    }
}
