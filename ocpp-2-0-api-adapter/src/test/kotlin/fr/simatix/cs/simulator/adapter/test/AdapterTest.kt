package fr.simatix.cs.simulator.adapter.test

import fr.simatix.cs.simulator.adapter20.Ocpp20Adapter
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.api.model.bootnotification.ModemType
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
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesReq
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesResp
import fr.simatix.cs.simulator.api.model.getallvariables.KeyValue
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportResp
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.api.model.getcompositeschedule.enumeration.GenericStatusEnumType
import fr.simatix.cs.simulator.api.model.getreport.GetReportReq
import fr.simatix.cs.simulator.api.model.getreport.GetReportResp
import fr.simatix.cs.simulator.api.model.getvariables.GetVariableResultType
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.api.model.getvariables.enumeration.GetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.api.model.reset.ResetReq
import fr.simatix.cs.simulator.api.model.reset.ResetResp
import fr.simatix.cs.simulator.api.model.reset.enumeration.ResetStatusEnumType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariableResultType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.api.model.setvariables.enumeration.SetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core20.model.bootnotification.ChargingStationType
import fr.simatix.cs.simulator.core20.model.bootnotification.enumeration.BootReasonEnumType
import fr.simatix.cs.simulator.core20.model.bootnotification.enumeration.RegistrationStatusEnumType
import fr.simatix.cs.simulator.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import fr.simatix.cs.simulator.core20.model.clearedcharginglimit.ClearedChargingLimitResp
import fr.simatix.cs.simulator.core20.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.core20.model.common.IdTokenType
import fr.simatix.cs.simulator.core20.model.common.MessageContentType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.AuthorizationStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingLimitSourceEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingLimitSourceEnumType as ChargingLimitSourceEnumTypeGen
import fr.simatix.cs.simulator.core20.model.common.enumeration.IdTokenEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.MessageFormatEnumType
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core20.model.datatransfer.enumeration.DataTransferStatusEnumType
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReqGen
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType as FirmwareStatusEnumTypeGen
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core20.model.notifyreport.NotifyReportReq
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportReq as NotifyReportReqGen
import fr.simatix.cs.simulator.core20.model.notifyreport.NotifyReportResp
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.core20.model.statusnotification.enumeration.ConnectorStatusEnumType
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionType
import fr.simatix.cs.simulator.core20.model.transactionevent.enumeration.TransactionEventEnumType
import fr.simatix.cs.simulator.core20.model.transactionevent.enumeration.TriggerReasonEnumType
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
import fr.simatix.cs.simulator.api.model.bootnotification.ChargingStationType as ChargingStationTypeGen
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.BootReasonEnumType as BootReasonEnumTypeGen
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.RegistrationStatusEnumType as RegistrationStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.IdTokenInfoType as IdTokenInfoTypeGen
import fr.simatix.cs.simulator.api.model.common.IdTokenType as IdTokenTypeGen
import fr.simatix.cs.simulator.api.model.common.MessageContentType as MessageContentTypeGen
import fr.simatix.cs.simulator.api.model.common.StatusInfoType as StatusInfoTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.AuthorizationStatusEnumType as AuthorizationStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.IdTokenEnumType as IdTokenEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.MessageFormatEnumType as MessageFormatEnumTypeGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq as StatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ConnectorStatusEnumType as ConnectorStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq as TransactionEventReqGen
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionType as TransactionTypeGen
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TransactionEventEnumType as TransactionEventEnumTypeGen
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TriggerReasonEnumType as TriggerReasonEnumTypeGen
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitReq as ClearedChargingLimitReqGen

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

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = HeartbeatReqGen()
        val response = operations.heartbeat(requestMetadata, request)
        expectThat(response)
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
    }

    @Test
    fun `authorize request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.authorize(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            AuthorizeReq(IdTokenType("", IdTokenEnumType.Central)),
            AuthorizeResp(
                idTokenInfo = IdTokenInfoType(
                    status = AuthorizationStatusEnumType.NotAllowedTypeEVSE,
                    groupIdToken = IdTokenType("Tag3", IdTokenEnumType.ISO14443)
                )
            )
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = AuthorizeReqGen(idToken = IdTokenTypeGen("Tag1", IdTokenEnumTypeGen.Central))
        val response = operations.authorize(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.response.idTokenInfo }.isEqualTo(
                    IdTokenInfoTypeGen(
                        status = AuthorizationStatusEnumTypeGen.NotAllowedTypeEVSE,
                        groupIdToken = IdTokenTypeGen("Tag3", IdTokenEnumTypeGen.ISO14443)
                    )
                )
            }
    }

    @Test
    fun `meterValues request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.meterValues(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesReq(1, listOf()),
            MeterValuesResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = MeterValuesReq(
            1, listOf(
                MeterValueType(
                    listOf(
                        SampledValueType(0.6), SampledValueType(
                            value = 2.4,
                            context = ReadingContextEnumType.SampleClock,
                            measurand = MeasurandEnumType.Temperature,
                            phase = PhaseEnumType.L1,
                            location = LocationEnumType.Body,
                            signedMeterValue = SignedMeterValueType("Hello", "Bye", "Welcome", "Nice")
                        )
                    ),
                    Instant.parse("2022-02-15T00:00:00.000Z"),
                )
            )
        )
        val response = operations.meterValues(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.NOT_SEND)
            }
    }

    @Test
    fun `dataTransfer request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.dataTransfer(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferReq(""),
            DataTransferResp(
                status = DataTransferStatusEnumType.Accepted,
                data = "Hello",
                statusInfo = StatusInfoType("reason", "additional")
            )
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = DataTransferReq(
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
            .and { get { this.response.status }.isEqualTo(fr.simatix.cs.simulator.api.model.datatransfer.enumeration.DataTransferStatusEnumType.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(StatusInfoTypeGen("reason", "additional")) }
    }

    @Test
    fun `bootNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.bootNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            BootNotificationReq(ChargingStationType("", ""), BootReasonEnumType.ApplicationReset),
            BootNotificationResp(
                Instant.parse("2022-02-15T00:00:00.000Z"),
                10,
                RegistrationStatusEnumType.Accepted
            )
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request =
            BootNotificationReqGen(
                ChargingStationTypeGen("model", "vendor", "firmware", ModemType("a", "b")),
                BootReasonEnumTypeGen.ApplicationReset
            )
        val response = operations.bootNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
            .and { get { this.response.currentTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
            .and { get { this.response.interval }.isEqualTo(10) }
            .and { get { this.response.status }.isEqualTo(RegistrationStatusEnumTypeGen.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(null) }
    }

    @Test
    fun `transactionEvent request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.transactionEvent(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            TransactionEventReq(
                TransactionEventEnumType.Started,
                Instant.parse("2022-02-15T00:00:00.000Z"),
                TriggerReasonEnumType.Authorized,
                0,
                TransactionType("tr")
            ),
            TransactionEventResp(
                totalCost = 100.0,
                chargingPriority = -1,
                idTokenInfo = IdTokenInfoType(
                    AuthorizationStatusEnumType.Accepted,
                    Instant.parse("2022-02-15T00:00:00.000Z")
                ),
                updatedPersonalMessage = MessageContentType(MessageFormatEnumType.ASCII, "Hello")
            )
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request =
            TransactionEventReqGen(
                TransactionEventEnumTypeGen.Started,
                Instant.parse("2022-02-15T00:00:00.000Z"),
                TriggerReasonEnumTypeGen.Authorized,
                0,
                TransactionTypeGen("tr")
            )
        val response = operations.transactionEvent(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
            .and { get { this.response.totalCost }.isEqualTo(100.0) }
            .and { get { this.response.chargingPriority }.isEqualTo(-1) }
            .and {
                get { this.response.idTokenInfo }.isEqualTo(
                    IdTokenInfoTypeGen(
                        AuthorizationStatusEnumTypeGen.Accepted,
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            }
            .and {
                get { this.response.updatedPersonalMessage }.isEqualTo(
                    MessageContentTypeGen(
                        MessageFormatEnumTypeGen.ASCII,
                        "Hello"
                    )
                )
            }
    }

    @Test
    fun `statusNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.statusNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            StatusNotificationReq(
                connectorId = 1,
                connectorStatus = ConnectorStatusEnumType.Available,
                evseId = 2,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z")
            ),
            StatusNotificationResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = StatusNotificationReqGen(
            connectorId = 1,
            connectorStatus = ConnectorStatusEnumTypeGen.Available,
            evseId = 2,
            timestamp = Instant.parse("2022-02-15T00:00:00.000Z")
        )
        val response = operations.statusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
    }

    @Test
    fun `notifyReport request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.notifyReport(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            NotifyReportReq(
                requestId = 1,
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                seqNo = 2,
            ),
            NotifyReportResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request =  NotifyReportReqGen(
            requestId = 1,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            seqNo = 2,
        )
        val response = operations.notifyReport(requestMetadata, request)
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
                FirmwareStatusEnumType.Downloaded
            ),
            FirmwareStatusNotificationResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request =  FirmwareStatusNotificationReqGen(
            FirmwareStatusEnumTypeGen.Downloaded
        )
        val response = operations.firmwareStatusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
    }

    @Test
    fun `clearedChargingLimit request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.clearedChargingLimit(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            ClearedChargingLimitReq(ChargingLimitSourceEnumType.CSO),
            ClearedChargingLimitResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = ClearedChargingLimitReqGen(
            chargingLimitSource = ChargingLimitSourceEnumTypeGen.CSO,
            evseId = 1
        )
        val response = operations.clearedChargingLimit(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
    }
}