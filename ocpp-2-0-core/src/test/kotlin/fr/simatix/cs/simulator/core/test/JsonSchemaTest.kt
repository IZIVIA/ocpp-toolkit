package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core20.model.authorize.OCSPRequestDataType
import fr.simatix.cs.simulator.core20.model.authorize.enumeration.AuthorizeCertificateStatusEnumType
import fr.simatix.cs.simulator.core20.model.authorize.enumeration.HashAlgorithmEnumType
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core20.model.bootnotification.ChargingStationType
import fr.simatix.cs.simulator.core20.model.bootnotification.ModemType
import fr.simatix.cs.simulator.core20.model.bootnotification.enumeration.BootReasonEnumType
import fr.simatix.cs.simulator.core20.model.bootnotification.enumeration.RegistrationStatusEnumType
import fr.simatix.cs.simulator.core20.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core20.model.cancelreservation.CancelReservationResp
import fr.simatix.cs.simulator.core20.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.OperationalStatusEnumType
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.core20.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import fr.simatix.cs.simulator.core20.model.clearedcharginglimit.ClearedChargingLimitResp
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileType
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.enumeration.ClearChargingProfileEnumType
import fr.simatix.cs.simulator.core20.model.common.*
import fr.simatix.cs.simulator.core20.model.common.enumeration.*
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core20.model.datatransfer.enumeration.DataTransferStatusEnumType
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import fr.simatix.cs.simulator.core20.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.core20.model.getbasereport.GetBaseReportResp
import fr.simatix.cs.simulator.core20.model.getbasereport.enumeration.ReportBaseEnumType
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.core20.model.getreport.ComponentVariableType
import fr.simatix.cs.simulator.core20.model.getreport.GetReportReq
import fr.simatix.cs.simulator.core20.model.getreport.GetReportResp
import fr.simatix.cs.simulator.core20.model.getreport.enumeration.ComponentCriterionEnumType
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariableDataType
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariableResultType
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.core20.model.getvariables.enumeration.GetVariableStatusEnumType
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core20.model.notifyreport.*
import fr.simatix.cs.simulator.core20.model.notifyreport.enumeration.DataEnumType
import fr.simatix.cs.simulator.core20.model.notifyreport.enumeration.MutabilityEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.*
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.ChargingProfileKindEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.ChargingRateUnitEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingRateUnitEnumType
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.CompositeScheduleType
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.enumeration.GenericStatusEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.RecurrencyKindEnumType
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariableDataType
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariableResultType
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.core20.model.setvariables.enumeration.SetVariableStatusEnumType
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.core20.model.statusnotification.enumeration.ConnectorStatusEnumType
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionType
import fr.simatix.cs.simulator.core20.model.transactionevent.enumeration.TransactionEventEnumType
import fr.simatix.cs.simulator.core20.model.transactionevent.enumeration.TriggerReasonEnumType
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.core20.model.unlockconnector.enumeration.UnlockStatusEnumType
import fr.simatix.cs.simulator.core20.model.updatefirmware.FirmwareType
import fr.simatix.cs.simulator.core20.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.utils.JsonSchemaValidator
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class JsonSchemaTest {

    @Test
    fun `heartbeat request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(HeartbeatReq(), "HeartbeatRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `authorize request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(
            AuthorizeReq(
                idToken = IdTokenType("Tag1", IdTokenEnumType.Local)
            ), "AuthorizeRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            AuthorizeReq(
                idToken = IdTokenType("Tag1", IdTokenEnumType.Central, listOf(AdditionalInfoType("", ""))),
                certificate = "certificate1",
                iso15118CertificateHashData = listOf(OCSPRequestDataType(HashAlgorithmEnumType.SHA256, "", "", "", ""))
            ), "AuthorizeRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `meterValues request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(
            MeterValuesReq(
                evseId = 1,
                meterValue = listOf(
                    MeterValueType(
                        listOf(SampledValueType(value = 0.9)),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            ), "MeterValuesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            MeterValuesReq(
                evseId = 1,
                meterValue = listOf(
                    MeterValueType(
                        listOf(
                            SampledValueType(
                                value = 0.9,
                                phase = PhaseEnumType.L1,
                                signedMeterValue = SignedMeterValueType("", "", "", ""),
                                unitOfMeasure = UnitOfMeasure("", 2)
                            )
                        ), Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            ), "MeterValuesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `transactionEvent request format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            TransactionEventReq(
                TransactionEventEnumType.Started,
                Instant.parse("2022-02-15T00:00:00.000Z"),
                TriggerReasonEnumType.Authorized,
                1,
                TransactionType("")
            ), "TransactionEventRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            TransactionEventReq(
                TransactionEventEnumType.Started,
                Instant.parse("2022-02-15T00:00:00.000Z"),
                TriggerReasonEnumType.Authorized,
                1,
                TransactionType(""),
                1,
                EVSEType(1, 2),
                IdTokenType(
                    "", IdTokenEnumType.Central,
                    listOf(AdditionalInfoType("", ""))
                ),
                listOf(MeterValueType(listOf(SampledValueType(10.9)), Instant.parse("2022-02-15T00:00:00.000Z"))),
                3,
                true,
                100
            ), "TransactionEventRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `statusNotification request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            StatusNotificationReq(
                connectorId = 2,
                connectorStatus = ConnectorStatusEnumType.Available,
                evseId = 12,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z")
            ), "StatusNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `dataTransfer request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(
            DataTransferReq("vendor1"),
            "DataTransferRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            DataTransferReq(vendorId = "vendor1", messageId = "message1", data = "data1"),
            "DataTransferRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `bootNotification request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(
            BootNotificationReq(
                chargingStation = ChargingStationType(model = "model", vendorName = "vendor"),
                reason = BootReasonEnumType.ApplicationReset
            ),
            "BootNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            BootNotificationReq(
                chargingStation = ChargingStationType(
                    model = "model",
                    vendorName = "vendor",
                    firmwareVersion = "version",
                    modem = ModemType("", ""),
                    serialNumber = "0"
                ), reason = BootReasonEnumType.ApplicationReset
            ),
            "BootNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `changeAvailability request format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            ChangeAvailabilityReq(OperationalStatusEnumType.Operative),
            "ChangeAvailabilityRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            ChangeAvailabilityReq(OperationalStatusEnumType.Operative, EVSEType(1, 1)),
            "ChangeAvailabilityRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearCache request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            ClearCacheReq(),
            "ClearCacheRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `unlockConnector request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            UnlockConnectorReq(1, 2),
            "UnlockConnectorRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `requestStartTransaction request format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            RequestStartTransactionReq(
                remoteStartId = 1,
                idToken = IdTokenType("token", IdTokenEnumType.Central)
            ),
            "RequestStartTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            RequestStartTransactionReq(
                remoteStartId = 1,
                idToken = IdTokenType("token", IdTokenEnumType.Central),
                evseId = 1,
                chargingProfile = ChargingProfileType(
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
            ),
            "RequestStartTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            RequestStartTransactionReq(
                remoteStartId = 1,
                idToken = IdTokenType("token", IdTokenEnumType.Central, listOf(AdditionalInfoType("", ""))),
                evseId = 1,
                chargingProfile = ChargingProfileType(
                    id = 1,
                    stackLevel = 1,
                    chargingProfilePurpose = ChargingProfilePurposeEnumType.ChargingStationMaxProfile,
                    chargingProfileKind = ChargingProfileKindEnumType.Absolute,
                    chargingSchedule = listOf(
                        ChargingScheduleType(
                            id = 1,
                            chargingRateUnit = ChargingRateUnitEnumType.A,
                            chargingSchedulePeriod = listOf(ChargingSchedulePeriodType(1, 1.0)),
                            startSchedule = Instant.parse("2022-02-15T00:00:00.000Z"),
                            duration = 1,
                            minChargingRate = 1.0,
                            salesTariff = SalesTariffType(1, listOf(SalesTariffEntryType(RelativeTimeIntervalType(1))))
                        )
                    ),
                    recurrencyKind = RecurrencyKindEnumType.Daily,
                    validFrom = Instant.parse("2022-02-15T00:00:00.000Z"),
                    validTo = Instant.parse("2022-02-15T00:00:00.000Z"),
                    transactionId = ""
                )
            ),
            "RequestStartTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `requestStopTransaction request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            RequestStopTransactionReq("tag1"),
            "RequestStopTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getVariables request format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            GetVariablesReq(listOf(GetVariableDataType(ComponentType("component"), VariableType("variable")))),
            "GetVariablesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            GetVariablesReq(
                listOf(
                    GetVariableDataType(
                        component = ComponentType(name = "component", instance = "instance", evse = EVSEType(1)),
                        variable = VariableType(name = "variable", instance = "instance"),
                        attributeType = AttributeEnumType.Target
                    )
                )
            ),
            "GetVariablesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setVariables request format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            SetVariablesReq(
                listOf(
                    SetVariableDataType(
                        attributeValue = "value",
                        component = ComponentType("component"),
                        variable = VariableType("variable")
                    )
                )
            ),
            "SetVariablesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            SetVariablesReq(
                listOf(
                    SetVariableDataType(
                        attributeValue = "value",
                        component = ComponentType("component", "instance", EVSEType(1, 2)),
                        variable = VariableType("variable", "instance"),
                        attributeType = AttributeEnumType.Target
                    )
                )
            ),
            "SetVariablesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getReport request format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(GetReportReq(1), "GetReportRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            GetReportReq(
                1, listOf(ComponentCriterionEnumType.Active),
                listOf(ComponentVariableType(ComponentType("component")))
            ), "GetReportRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getBaseReport request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            GetBaseReportReq(1, ReportBaseEnumType.ConfigurationInventory),
            "GetBaseReportRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `updatefirmware request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            UpdateFirmwareReq(
                requestId = 2,
                firmware = FirmwareType(
                    // TODO: 11/04/2022
                )
            ),
            "UpdateFirmwareRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearedChargingLimit request format`() {

        var errors = JsonSchemaValidator.isValidObjectV6(
            ClearedChargingLimitReq(ChargingLimitSourceEnumType.CSO),
            "ClearedChargingLimitRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            ClearedChargingLimitReq(ChargingLimitSourceEnumType.EMS, 1),
            "ClearedChargingLimitRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `cancelReservation request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            CancelReservationReq(3), "CancelReservationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `firmwareStatusNotification request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            FirmwareStatusNotificationReq(FirmwareStatusEnumType.Downloaded),
            "FirmwareStatusNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearChargingProfile request format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            ClearChargingProfileReq(), "ClearChargingProfileRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            ClearChargingProfileReq(
                1,
                ClearChargingProfileType(
                    1,
                    ChargingProfilePurposeEnumType.ChargingStationMaxProfile,
                    1
                )
            ), "ClearChargingProfileRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getCompositeSchedule request format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            GetCompositeScheduleReq(1, 2), "GetCompositeScheduleRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            GetCompositeScheduleReq(1, 2, ChargingRateUnitEnumType.A), "GetCompositeScheduleRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getLocalListVersion request format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(GetLocalListVersionReq(), "GetLocalListVersionRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `notify request response format`() {
        var errors =
            JsonSchemaValidator.isValidObjectV6(
                NotifyReportReq(
                    requestId = 1,
                    generatedAt = Clock.System.now(),
                    seqNo = 1
                ), "NotifyReportRequest.json"
            )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors =
            JsonSchemaValidator.isValidObjectV6(
                NotifyReportReq(
                    requestId = 1,
                    generatedAt = Clock.System.now(),
                    seqNo = 1,
                    tbc = true,
                    reportData = listOf(
                        ReportDataType(
                            ComponentType("component"), VariableType("variable"), listOf(VariableAttributeType()),
                            VariableCharacteristicsType(DataEnumType.DECIMAL, true)
                        )
                    )
                ), "NotifyReportRequest.json"
            )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors =
            JsonSchemaValidator.isValidObjectV6(
                NotifyReportReq(
                    requestId = 1,
                    generatedAt = Clock.System.now(),
                    seqNo = 1,
                    tbc = true,
                    reportData = listOf(
                        ReportDataType(
                            ComponentType("component"),
                            VariableType("variable"),
                            listOf(
                                VariableAttributeType(
                                    "value",
                                    AttributeEnumType.Actual,
                                    true,
                                    MutabilityEnumType.ReadWrite,
                                    true
                                )
                            ),
                            VariableCharacteristicsType(DataEnumType.DECIMAL, true, "unit", 10.0, 20.0, "valuesList")
                        )
                    )
                ), "NotifyReportRequest.json"
            )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `heartbeat response format`() {
        val heartbeatResp = HeartbeatResp(
            currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
        )
        val errors = JsonSchemaValidator.isValidObjectV6(heartbeatResp, "HeartbeatResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `authorize response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(
            AuthorizeResp(
                idTokenInfo = IdTokenInfoType(AuthorizationStatusEnumType.Accepted)
            ), "AuthorizeResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            AuthorizeResp(
                idTokenInfo = IdTokenInfoType(AuthorizationStatusEnumType.Blocked),
                certificateStatus = AuthorizeCertificateStatusEnumType.CertificateExpired
            ), "AuthorizeResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `meterValues response format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(
            MeterValuesResp(), "MeterValuesResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `transactionEvent response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(TransactionEventResp(), "TransactionEventResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            TransactionEventResp(
                200.0, 9,
                IdTokenInfoType(
                    AuthorizationStatusEnumType.Accepted,
                    Instant.parse("2022-02-15T00:00:00.000Z"),
                    9,
                    "",
                    listOf(2, 4),
                    "",
                    IdTokenType(
                        "", IdTokenEnumType.Central,
                        listOf(AdditionalInfoType("", ""))
                    ), MessageContentType(MessageFormatEnumType.ASCII, "", "")
                ), MessageContentType(MessageFormatEnumType.ASCII, "", "")
            ), "TransactionEventResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `statusNotification response format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(StatusNotificationResp(), "StatusNotificationResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `dataTransfer response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(
            DataTransferResp(DataTransferStatusEnumType.Accepted),
            "DataTransferResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            DataTransferResp(
                status = DataTransferStatusEnumType.Accepted,
                data = "data1",
                statusInfo = StatusInfoType("", "")
            ),
            "DataTransferResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `bootNotification response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObjectV6(
            BootNotificationResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                interval = 10,
                status = RegistrationStatusEnumType.Accepted
            ),
            "BootNotificationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObjectV6(
            BootNotificationResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                interval = 10,
                status = RegistrationStatusEnumType.Accepted,
                statusInfo = StatusInfoType("", "")
            ),
            "BootNotificationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `changeAvailability response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            ChangeAvailabilityResp(ChangeAvailabilityStatusEnumType.Accepted),
            "ChangeAvailabilityResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            ChangeAvailabilityResp(ChangeAvailabilityStatusEnumType.Accepted, StatusInfoType("", "")),
            "ChangeAvailabilityResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearCache response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            ClearCacheResp(ClearCacheStatusEnumType.Accepted),
            "ClearCacheResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            ClearCacheResp(ClearCacheStatusEnumType.Accepted, StatusInfoType("", "")),
            "ClearCacheResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `unlockConnector response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            UnlockConnectorResp(UnlockStatusEnumType.Unlocked),
            "UnlockConnectorResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            UnlockConnectorResp(UnlockStatusEnumType.Unlocked, StatusInfoType("", "")),
            "UnlockConnectorResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `requestStopTransaction response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            RequestStopTransactionResp(RequestStartStopStatusEnumType.Accepted),
            "RequestStopTransactionResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            RequestStopTransactionResp(RequestStartStopStatusEnumType.Accepted, StatusInfoType("reason", "additional")),
            "RequestStopTransactionResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getVariables response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            GetVariablesResp(
                listOf(
                    GetVariableResultType(
                        attributeStatus = GetVariableStatusEnumType.NotSupportedAttributeType,
                        component = ComponentType("component"),
                        variable = VariableType("variable")
                    )
                )
            ),
            "GetVariablesResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            GetVariablesResp(
                listOf(
                    GetVariableResultType(
                        attributeStatus = GetVariableStatusEnumType.NotSupportedAttributeType,
                        component = ComponentType("component", "instance", EVSEType(1, 2)),
                        variable = VariableType("variable", "instance"),
                        attributeType = AttributeEnumType.Target,
                        attributeValue = "value",
                        attributeStatusInfo = StatusInfoType("reason")
                    )
                )
            ),
            "GetVariablesResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setVariables response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            SetVariablesResp(
                listOf(
                    SetVariableResultType(
                        attributeStatus = SetVariableStatusEnumType.Accepted,
                        component = ComponentType("component"),
                        variable = VariableType("variable")
                    )
                )
            ),
            "SetVariablesResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            SetVariablesResp(
                listOf(
                    SetVariableResultType(
                        attributeStatus = SetVariableStatusEnumType.Accepted,
                        component = ComponentType("component", "instance", EVSEType(1, 2)),
                        variable = VariableType("variable", "instance"),
                        AttributeEnumType.Target,
                        StatusInfoType("reason", "additional")
                    )
                )
            ),
            "SetVariablesResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getReport response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            GetReportResp(GenericDeviceModelStatusEnumType.Accepted),
            "GetReportResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            GetReportResp(
                GenericDeviceModelStatusEnumType.EmptyResultSet,
                StatusInfoType("reason", "additional")
            ),
            "GetReportResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getBaseReport response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            GetBaseReportResp(GenericDeviceModelStatusEnumType.Rejected),
            "GetBaseReportResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            GetBaseReportResp(
                GenericDeviceModelStatusEnumType.Rejected,
                StatusInfoType("reason", "additional")
            ),
            "GetBaseReportResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `notify report response format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(NotifyReportResp(), "NotifyReportResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `cancelReservation response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            CancelReservationResp(CancelReservationStatusEnumType.Rejected), "CancelReservationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            CancelReservationResp(
                CancelReservationStatusEnumType.Rejected,
                StatusInfoType("reason", "additional")
            ),
            "CancelReservationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `firmwareStatusNotification response format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(FirmwareStatusNotificationResp(), "FirmwareStatusNotificationResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearChargingProfile response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            ClearChargingProfileResp(ClearChargingProfileEnumType.Accepted), "ClearChargingProfileResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            ClearChargingProfileResp(
                ClearChargingProfileEnumType.Accepted,
                StatusInfoType("reason", "additional")
            ), "ClearChargingProfileResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearedChargingLimit response format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(ClearedChargingLimitResp(), "ClearedChargingLimitResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getCompositeSchedule response format`() {
        var errors = JsonSchemaValidator.isValidObjectV6(
            GetCompositeScheduleResp(GenericStatusEnumType.Accepted), "GetCompositeScheduleResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObjectV6(
            GetCompositeScheduleResp(
                GenericStatusEnumType.Accepted,
                CompositeScheduleType(
                    evseId = 1,
                    duration = 2,
                    scheduleStart = Instant.parse("2022-02-15T00:00:00.000Z"),
                    chargingRateUnit = ChargingRateUnitEnumType.A,
                    chargingSchedulePeriod = listOf(ChargingSchedulePeriodType(1, 1.0))
                ),
                StatusInfoType("reason", "additional")
            ),
            "GetCompositeScheduleResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getLocalListVersion response format`() {
        val errors = JsonSchemaValidator.isValidObjectV6(GetLocalListVersionResp(1), "GetLocalListVersionResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

}