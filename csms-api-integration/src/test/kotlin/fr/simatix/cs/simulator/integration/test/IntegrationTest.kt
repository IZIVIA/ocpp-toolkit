package fr.simatix.cs.simulator.integration.test

import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.api.model.authorize.enumeration.HashAlgorithmEnumType
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.api.model.bootnotification.ChargingStationType
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.BootReasonEnumType
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.RegistrationStatusEnumType
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
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitReq
import fr.simatix.cs.simulator.api.model.common.*
import fr.simatix.cs.simulator.api.model.common.enumeration.*
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.datatransfer.enumeration.DataTransferStatusEnumType
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesReq
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesResp
import fr.simatix.cs.simulator.api.model.getallvariables.KeyValue
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportResp
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusReq
import fr.simatix.cs.simulator.api.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.api.model.getcompositeschedule.enumeration.GenericStatusEnumType
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.api.model.getreport.GetReportReq
import fr.simatix.cs.simulator.api.model.getreport.GetReportResp
import fr.simatix.cs.simulator.api.model.getvariables.GetVariableResultType
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.api.model.getvariables.enumeration.GetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import fr.simatix.cs.simulator.api.model.notifyevent.EventDataType
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventReq
import fr.simatix.cs.simulator.api.model.notifyevent.enumeration.EventNotificationEnumType
import fr.simatix.cs.simulator.api.model.notifyevent.enumeration.EventTriggerEnumType
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportReq
import fr.simatix.cs.simulator.api.model.notifyreport.ReportDataType
import fr.simatix.cs.simulator.api.model.notifyreport.VariableAttributeType
import fr.simatix.cs.simulator.api.model.notifyreport.VariableCharacteristicsType
import fr.simatix.cs.simulator.api.model.notifyreport.enumeration.DataEnumType
import fr.simatix.cs.simulator.api.model.remotestart.ChargingScheduleType
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
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionType
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
import fr.simatix.cs.simulator.api.send
import fr.simatix.cs.simulator.integration.ApiFactory
import fr.simatix.cs.simulator.integration.model.Settings
import fr.simatix.cs.simulator.integration.model.TransportEnum
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.client.OcppWampClient
import io.simatix.ev.ocpp.wamp.client.impl.OkHttpOcppWampClient
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
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
    }
}