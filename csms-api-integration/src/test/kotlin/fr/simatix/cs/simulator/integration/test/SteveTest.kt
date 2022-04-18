package fr.simatix.cs.simulator.integration.test

import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.api.model.bootnotification.ChargingStationType
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.BootReasonEnumType
import fr.simatix.cs.simulator.api.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.api.model.cancelreservation.CancelReservationResp
import fr.simatix.cs.simulator.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.api.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType
import fr.simatix.cs.simulator.api.model.common.*
import fr.simatix.cs.simulator.api.model.common.enumeration.*
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.api.model.datatransfer.enumeration.DataTransferStatusEnumType
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesReq
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesResp
import fr.simatix.cs.simulator.api.model.getallvariables.KeyValue
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportResp
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.api.model.getcompositeschedule.enumeration.GenericStatusEnumType
import fr.simatix.cs.simulator.api.model.getreport.GetReportReq
import fr.simatix.cs.simulator.api.model.getreport.GetReportResp
import fr.simatix.cs.simulator.api.model.getvariables.GetVariableResultType
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.api.model.getvariables.enumeration.GetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationReq
import fr.simatix.cs.simulator.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationReq
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
import fr.simatix.cs.simulator.api.model.setvariables.SetVariableResultType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.api.model.setvariables.enumeration.SetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ChargePointErrorCode
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.ChargingStateEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TransactionEventEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TriggerReasonEnumType
import fr.simatix.cs.simulator.api.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.api.model.triggermessage.TriggerMessageResp
import fr.simatix.cs.simulator.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareResp
import fr.simatix.cs.simulator.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType
import fr.simatix.cs.simulator.integration.ApiFactory
import fr.simatix.cs.simulator.integration.model.Settings
import fr.simatix.cs.simulator.integration.model.TransportEnum
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import io.simatix.ev.ocpp.OcppVersion
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

    sleep(8000)

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
