package com.izivia.ocpp.json20

import com.izivia.ocpp.core20.model.authorize.AuthorizeReq
import com.izivia.ocpp.core20.model.authorize.AuthorizeResp
import com.izivia.ocpp.core20.model.authorize.enumeration.AuthorizeCertificateStatusEnumType
import com.izivia.ocpp.core20.model.common.enumeration.HashAlgorithmEnumType
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core20.model.bootnotification.ChargingStationType
import com.izivia.ocpp.core20.model.bootnotification.ModemType
import com.izivia.ocpp.core20.model.bootnotification.enumeration.BootReasonEnumType
import com.izivia.ocpp.core20.model.bootnotification.enumeration.RegistrationStatusEnumType
import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core20.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedReq
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedResp
import com.izivia.ocpp.core20.model.certificateSigned.enumeration.CertificateSignedStatusEnumType
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core20.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import com.izivia.ocpp.core20.model.changeavailability.enumeration.OperationalStatusEnumType
import com.izivia.ocpp.core20.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core20.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core20.model.clearcache.enumeration.ClearCacheStatusEnumType
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileType
import com.izivia.ocpp.core20.model.clearchargingprofile.enumeration.ClearChargingProfileEnumType
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageResp
import com.izivia.ocpp.core20.model.cleardisplaymessage.enumeration.ClearMessageStatusEnumType
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitResp
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearMonitoringResultType
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringResp
import com.izivia.ocpp.core20.model.clearvariablemonitoring.enumeration.ClearMonitoringStatusEnumType
import com.izivia.ocpp.core20.model.common.*
import com.izivia.ocpp.core20.model.common.enumeration.*
import com.izivia.ocpp.core20.model.common.enumeration.GenericStatusEnumType
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationReq
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationResp
import com.izivia.ocpp.core20.model.customerinformation.enumeration.CustomerInformationStatusEnumType
import com.izivia.ocpp.core20.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core20.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core20.model.datatransfer.enumeration.DataTransferStatusEnumType
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.core20.model.deletecertificate.enumeration.DeleteCertificateStatusEnumType
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core20.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportResp
import com.izivia.ocpp.core20.model.getbasereport.enumeration.ReportBaseEnumType
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusReq
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusResp
import com.izivia.ocpp.core20.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesReq
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesResp
import com.izivia.ocpp.core20.model.getchargingprofiles.enumeration.GetChargingProfileStatusEnumType
import com.izivia.ocpp.core20.model.getcompositeschedule.CompositeScheduleType
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.core20.model.getdisplaymessages.enumeration.GetDisplayMessagesStatusEnumType
import com.izivia.ocpp.core20.model.getinstalledcertificateids.CertificateHashDataChainType
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.core20.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType
import com.izivia.ocpp.core20.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core20.model.getlog.GetLogReq
import com.izivia.ocpp.core20.model.getlog.GetLogResp
import com.izivia.ocpp.core20.model.getlog.LogParametersType
import com.izivia.ocpp.core20.model.getlog.enumeration.LogEnumType
import com.izivia.ocpp.core20.model.getlog.enumeration.LogStatusEnumType
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportReq
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportResp
import com.izivia.ocpp.core20.model.getmonitoringreport.enumeration.MonitoringCriterionEnumType
import com.izivia.ocpp.core20.model.common.ComponentVariableType
import com.izivia.ocpp.core20.model.getreport.GetReportReq
import com.izivia.ocpp.core20.model.getreport.GetReportResp
import com.izivia.ocpp.core20.model.getreport.enumeration.ComponentCriterionEnumType
import com.izivia.ocpp.core20.model.getvariables.GetVariableDataType
import com.izivia.ocpp.core20.model.getvariables.GetVariableResultType
import com.izivia.ocpp.core20.model.getvariables.GetVariablesReq
import com.izivia.ocpp.core20.model.getvariables.GetVariablesResp
import com.izivia.ocpp.core20.model.getvariables.enumeration.GetVariableStatusEnumType
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.core20.model.installcertificate.enumeration.InstallCertificateStatusEnumType
import com.izivia.ocpp.core20.model.installcertificate.enumeration.InstallCertificateUseEnumType
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.core20.model.logstatusnotification.enumeration.UploadLogStatusEnumType
import com.izivia.ocpp.core20.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core20.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core20.model.notifycharginglimit.ChargingLimitType
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitReq
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitResp
import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.core20.model.common.MessageInfoType
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
import com.izivia.ocpp.core20.model.common.enumeration.MessagePriorityEnumType
import com.izivia.ocpp.core20.model.common.enumeration.MessageStateEnumType
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.core20.model.notifyevchargingneeds.*
import com.izivia.ocpp.core20.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType
import com.izivia.ocpp.core20.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType
import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import com.izivia.ocpp.core20.model.notifyevent.EventDataType
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventReq
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventResp
import com.izivia.ocpp.core20.model.notifyevent.enumeration.EventNotificationEnumType
import com.izivia.ocpp.core20.model.notifyevent.enumeration.EventTriggerEnumType
import com.izivia.ocpp.core20.model.notifymonitoringreport.MonitoringDataType
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportReq
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportResp
import com.izivia.ocpp.core20.model.notifymonitoringreport.VariableMonitoringType
import com.izivia.ocpp.core20.model.common.enumeration.MonitorEnumType
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusReq
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusResp
import com.izivia.ocpp.core20.model.notifyreport.*
import com.izivia.ocpp.core20.model.notifyreport.enumeration.DataEnumType
import com.izivia.ocpp.core20.model.notifyreport.enumeration.MutabilityEnumType
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationResp
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType
import com.izivia.ocpp.core20.model.remotestart.RelativeTimeIntervalType
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.core20.model.remotestart.SalesTariffEntryType
import com.izivia.ocpp.core20.model.remotestart.SalesTariffType
import com.izivia.ocpp.core20.model.remotestart.enumeration.ChargingProfileKindEnumType
import com.izivia.ocpp.core20.model.remotestart.enumeration.RecurrencyKindEnumType
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesResp
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateResp
import com.izivia.ocpp.core20.model.reservationstatusupdate.enumeration.ReservationUpdateStatusEnumType
import com.izivia.ocpp.core20.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core20.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core20.model.reservenow.enumeration.ConnectorEnumType
import com.izivia.ocpp.core20.model.reservenow.enumeration.ReserveNowStatusEnumType
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.core20.model.sendlocallist.AuthorizationData
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core20.model.sendlocallist.enumeration.SendLocalListStatusEnumType
import com.izivia.ocpp.core20.model.sendlocallist.enumeration.UpdateEnumType
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core20.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType
import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageReq
import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageResp
import com.izivia.ocpp.core20.model.setdisplaymessage.enumeration.DisplayMessageStatusEnumType
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetMonitoringDataType
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetMonitoringResultType
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringResp
import com.izivia.ocpp.core20.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.core20.model.setnetworkprofile.*
import com.izivia.ocpp.core20.model.setnetworkprofile.enumeration.*
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseResp
import com.izivia.ocpp.core20.model.setmonitoringbase.enumeration.MonitoringBaseEnumType
import com.izivia.ocpp.core20.model.setvariables.SetVariableDataType
import com.izivia.ocpp.core20.model.setvariables.SetVariableResultType
import com.izivia.ocpp.core20.model.setvariables.SetVariablesReq
import com.izivia.ocpp.core20.model.setvariables.SetVariablesResp
import com.izivia.ocpp.core20.model.setvariables.enumeration.SetVariableStatusEnumType
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core20.model.statusnotification.enumeration.ConnectorStatusEnumType
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventResp
import com.izivia.ocpp.core20.model.transactionevent.TransactionType
import com.izivia.ocpp.core20.model.transactionevent.enumeration.TransactionEventEnumType
import com.izivia.ocpp.core20.model.transactionevent.enumeration.TriggerReasonEnumType
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core20.model.triggermessage.enumeration.MessageTriggerEnumType
import com.izivia.ocpp.core20.model.triggermessage.enumeration.TriggerMessageStatusEnumType
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core20.model.unlockconnector.enumeration.UnlockStatusEnumType
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareReq
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareResp
import com.izivia.ocpp.core20.model.unpublishfirmware.enumeration.UnpublishFirmwareStatusEnumType
import com.izivia.ocpp.core20.model.updatefirmware.FirmwareType
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.core20.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class JsonSchemaTest {

    @Test
    fun `heartbeat request format`() {
        val errors = JsonSchemaValidator.isValidObject(HeartbeatReq(), "HeartbeatRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `authorize request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObject(
            AuthorizeReq(
                idToken = IdTokenType("Tag1", IdTokenEnumType.Local)
            ), "AuthorizeRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
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
        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
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

        errors = JsonSchemaValidator.isValidObject(
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
        val errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
            DataTransferReq("vendor1"),
            "DataTransferRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObject(
            DataTransferReq(vendorId = "vendor1", messageId = "message1", data = "data1"),
            "DataTransferRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `bootNotification request format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObject(
            BootNotificationReq(
                chargingStation = ChargingStationType(model = "model", vendorName = "vendor"),
                reason = BootReasonEnumType.ApplicationReset
            ),
            "BootNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
            ChangeAvailabilityReq(OperationalStatusEnumType.Operative),
            "ChangeAvailabilityRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            ChangeAvailabilityReq(OperationalStatusEnumType.Operative, EVSEType(1, 1)),
            "ChangeAvailabilityRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearCache request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            ClearCacheReq(),
            "ClearCacheRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `unlockConnector request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            UnlockConnectorReq(1, 2),
            "UnlockConnectorRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `requestStartTransaction request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            RequestStartTransactionReq(
                remoteStartId = 1,
                idToken = IdTokenType("token", IdTokenEnumType.Central)
            ),
            "RequestStartTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
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

        errors = JsonSchemaValidator.isValidObject(
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
        val errors = JsonSchemaValidator.isValidObject(
            RequestStopTransactionReq("tag1"),
            "RequestStopTransactionRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getVariables request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            GetVariablesReq(listOf(GetVariableDataType(ComponentType("component"), VariableType("variable")))),
            "GetVariablesRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
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

        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(GetReportReq(1), "GetReportRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
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
        val errors = JsonSchemaValidator.isValidObject(
            GetBaseReportReq(1, ReportBaseEnumType.ConfigurationInventory),
            "GetBaseReportRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setChargingProfile request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            SetChargingProfileReq(
                1, ChargingProfileType(
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
            "SetChargingProfileRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `sendLocalList request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            SendLocalListReq(1, UpdateEnumType.Full), "SendLocalListRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            SendLocalListReq(
                1, UpdateEnumType.Full, listOf(
                    AuthorizationData(
                        IdTokenType("", IdTokenEnumType.Central, listOf(AdditionalInfoType("", ""))),
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
                        )
                    )
                )
            ), "SendLocalListRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `updatefirmware request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            UpdateFirmwareReq(
                requestId = 2,
                firmware = FirmwareType(
                    location = "http://www.ietf.org/rfc/rfc2396.txt", // URI
                    retrieveDateTime = Instant.parse("2022-02-15T00:00:00.000Z")
                )
            ),
            "UpdateFirmwareRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObject(
            UpdateFirmwareReq(
                retries = 3,
                retryInterval = 1,
                requestId = 2,
                firmware = FirmwareType(
                    location = "location",
                    retrieveDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                    installDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                    signingCertificate = "signingCertificate",
                    signature = "signature"
                )
            ),
            "UpdateFirmwareRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `clearedChargingLimit request format`() {

        var errors = JsonSchemaValidator.isValidObject(
            ClearedChargingLimitReq(ChargingLimitSourceEnumType.CSO),
            "ClearedChargingLimitRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            ClearedChargingLimitReq(ChargingLimitSourceEnumType.EMS, 1),
            "ClearedChargingLimitRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `cancelReservation request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            CancelReservationReq(3), "CancelReservationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `firmwareStatusNotification request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            FirmwareStatusNotificationReq(FirmwareStatusEnumType.Downloaded),
            "FirmwareStatusNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearChargingProfile request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            ClearChargingProfileReq(), "ClearChargingProfileRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
            GetCompositeScheduleReq(1, 2), "GetCompositeScheduleRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            GetCompositeScheduleReq(1, 2, ChargingRateUnitEnumType.A), "GetCompositeScheduleRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getLocalListVersion request format`() {
        val errors = JsonSchemaValidator.isValidObject(GetLocalListVersionReq(), "GetLocalListVersionRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `triggerMessage request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            TriggerMessageReq(
                MessageTriggerEnumType.BootNotification
            ),
            "TriggerMessageRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObject(
            TriggerMessageReq(
                MessageTriggerEnumType.Heartbeat,
                EVSEType(1, 1)
            ),
            "TriggerMessageRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `notifyChargingLimit request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            NotifyChargingLimitReq(ChargingLimitType(ChargingLimitSourceEnumType.CSO)),
            "NotifyChargingLimitRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObject(
            NotifyChargingLimitReq(
                ChargingLimitType(ChargingLimitSourceEnumType.CSO, true), 1, listOf(
                    ChargingScheduleType(
                        id = 1,
                        chargingRateUnit = ChargingRateUnitEnumType.A,
                        chargingSchedulePeriod = listOf(ChargingSchedulePeriodType(1, 1.0)),
                        startSchedule = Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            ), "NotifyChargingLimitRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `getCertificateStatus request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            GetCertificateStatusReq(OCSPRequestDataType(HashAlgorithmEnumType.SHA256, "", "", "", "")),
            "GetCertificateStatusRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `reserveNow request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            ReserveNowReq(
                id = 1,
                expiryDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                idToken = IdTokenType("token", IdTokenEnumType.Central)
            ), "ReserveNowRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObject(
            ReserveNowReq(
                id = 1,
                expiryDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                connectorType = ConnectorEnumType.cTesla,
                evseId = 2,
                idToken = IdTokenType("token1", IdTokenEnumType.Central),
                groupIdToken = IdTokenType("token2", IdTokenEnumType.Central)
            ), "ReserveNowRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `notifyCustomerInformation request format`() {

        var errors = JsonSchemaValidator.isValidObject(
            NotifyCustomerInformationReq(
                data = "Some data",
                seqNo = 0,
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                requestId = 1
            ),
            "NotifyCustomerInformationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            NotifyCustomerInformationReq(
                data = "Some data",
                tbc = true,
                seqNo = 0,
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                requestId = 1
            ),
            "NotifyCustomerInformationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `notifyDisplayMessage request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            NotifyDisplayMessagesReq(
                requestId = 1
            ),
            "NotifyDisplayMessagesRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObject(
            NotifyDisplayMessagesReq(
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
            ),
            "NotifyDisplayMessagesRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `notifyEVChargingNeeds request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            NotifyEVChargingNeedsReq(1, ChargingNeedsType(EnergyTransferModeEnumType.AC_single_phase)),
            "NotifyEVChargingNeedsRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObject(
            NotifyEVChargingNeedsReq(
                1, ChargingNeedsType(
                    requestedEnergyTransfer = EnergyTransferModeEnumType.AC_single_phase,
                    departureTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                    ACChargingParametersType(2, 3, 4, 5),
                    DCChargingParametersType(2, 3, 4, 5, 6, 7, 8, 9)
                ), 1
            ), "NotifyEVChargingNeedsRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `notify request response format`() {
        var errors =
            JsonSchemaValidator.isValidObject(
                NotifyReportReq(
                    requestId = 1,
                    generatedAt = Clock.System.now(),
                    seqNo = 1
                ), "NotifyReportRequest.json"
            )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors =
            JsonSchemaValidator.isValidObject(
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
            JsonSchemaValidator.isValidObject(
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
    fun `notifyEvent request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            NotifyEventReq(
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                seqNo = 0,
                eventData = listOf(
                    EventDataType(
                        eventId = 0,
                        timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                        trigger = EventTriggerEnumType.Delta,
                        actualValue = "value",
                        eventNotificationType = EventNotificationEnumType.HardWiredNotification,
                        component = ComponentType("name"),
                        variable = VariableType("name")
                    )
                )
            ), "NotifyEventRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            NotifyEventReq(
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                seqNo = 0,
                eventData = listOf(
                    EventDataType(
                        eventId = 0,
                        timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                        trigger = EventTriggerEnumType.Delta,
                        actualValue = "value",
                        eventNotificationType = EventNotificationEnumType.HardWiredNotification,
                        component = ComponentType("name"),
                        variable = VariableType("name"),
                        cause = 1,
                        techCode = "techCode",
                        techInfo = "techInfo",
                        cleared = false,
                        transactionId = "transaction",
                        variableMonitoringId = 2
                    )
                ),
                tbc = true
            ), "NotifyEventRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `notifyEVChargingSchedule request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            NotifyEVChargingScheduleReq(
                timeBase = Instant.parse("2022-02-15T00:00:00.000Z"),
                evseId = 123,
                chargingSchedule = ChargingScheduleType(
                    id = 1,
                    chargingRateUnit = ChargingRateUnitEnumType.A,
                    chargingSchedulePeriod = listOf(
                        ChargingSchedulePeriodType(
                            startPeriod = 0,
                            limit = 1.0
                        )
                    )
                )
            ),
            "NotifyEVChargingScheduleRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `securityEventNotification request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            SecurityEventNotificationReq("type", Instant.parse("2022-02-15T00:00:00.000Z")),
            "SecurityEventNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            SecurityEventNotificationReq(
                type = "type",
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                techInfo = "techInfo"
            ),
            "SecurityEventNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `logStatusNotification request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            LogStatusNotificationReq(
                status = UploadLogStatusEnumType.Uploaded,
                requestId = 1
            ),
            "LogStatusNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `publishFirmwareStatusNotification request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            PublishFirmwareStatusNotificationReq(
                status = PublishFirmwareStatusEnumType.Published,
                location = listOf("location"),
                requestId = 1
            ),
            "PublishFirmwareStatusNotificationRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `notifyMonitoringReport request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            NotifyMonitoringReportReq(
                requestId = 1,
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                seqNo = 2
            ),
            "NotifyMonitoringReportRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            NotifyMonitoringReportReq(
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
            ),
            "NotifyMonitoringReportRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `reservationStatusUpdate request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            ReservationStatusUpdateReq(1, ReservationUpdateStatusEnumType.Expired),
            "ReservationStatusUpdateRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearDisplayMessage request format`() {
        val errors = JsonSchemaValidator.isValidObject(ClearDisplayMessageReq(1), "ClearDisplayMessageRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `certificateSigned request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            CertificateSignedReq("certificateChain"), "CertificateSignedRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            CertificateSignedReq("certificateChain", CertificateSigningUseEnumType.V2GCertificate),
            "CertificateSignedRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `signCertificate request format`() {
        var errors = JsonSchemaValidator.isValidObject(SignCertificateReq("csr"), "SignCertificateRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            SignCertificateReq(
                "csr",
                CertificateSigningUseEnumType.ChargingStationCertificate
            ), "SignCertificateRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearVariableMonitoring request format`() {
        val errors = JsonSchemaValidator.isValidObject(
            ClearVariableMonitoringReq(
                ids = listOf(1, 2)
            ),
            "ClearVariableMonitoringRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getMonitoringReport request format`() {
        var errors = JsonSchemaValidator.isValidObject(
                GetMonitoringReportReq(
                        243432,
                        listOf(MonitoringCriterionEnumType.DeltaMonitoring),
                        listOf(ComponentVariableType(
                                ComponentType(
                                        "typename",
                                        "instance"
                                )
                        ))
                ),
                "GetMonitoringReportRequest.json")
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                GetMonitoringReportReq(
                        243432
                ),
                "GetMonitoringReportRequest.json"
        )

        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `deleteCertificate request format`() {
        val certif = CertificateHashDataType(
                HashAlgorithmEnumType.SHA512,
                "3041edbcdd46190c0acc504ed195f8a90129efcab173a7b9ac4646b92d04cc80005acaa3554f4b1df839eacadc2491cb623bf3aa6f9eb44f6ea8ca005821d25d",
                "1f40fc92da241694750979ee6cf582f2d5d7d28e18335de05abc54d0560e0f5302860c652bf08d560252aa5e74210546f369fbbbce8c12cfc7957b2652fe9a75",
                "7683246784"
        )
        val errors = JsonSchemaValidator.isValidObject(
                DeleteCertificateReq(
                        certif
                ),
                "DeleteCertificateRequest.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }


    @Test
    fun `setDisplayMessage request format`() {
        var errors = JsonSchemaValidator.isValidObject(
                SetDisplayMessageReq(
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
                ),
                "SetDisplayMessageRequest.json")
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                SetDisplayMessageReq(
                        MessageInfoType(
                                id = 2,
                                priority = MessagePriorityEnumType.InFront,
                                message = MessageContentType(
                                        format = MessageFormatEnumType.URI,
                                        language = "language",
                                        content = "Message content"
                                )
                        )
                ),
                "SetDisplayMessageRequest.json")
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `costUpdated request format`() {
        val errors = JsonSchemaValidator.isValidObject(
                CostUpdatedReq(5465.2, "45465"),
                "CostUpdatedRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getDisplayMessages request format`() {
        var errors = JsonSchemaValidator.isValidObject(
                GetDisplayMessagesReq(
                        id=listOf(32,23),
                        requestId=2,
                        priority = MessagePriorityEnumType.AlwaysFront,
                        state = MessageStateEnumType.Charging
                ),
                "GetDisplayMessagesRequest.json")
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                GetDisplayMessagesReq(
                        requestId=2
                ),
                "GetDisplayMessagesRequest.json")
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getChargingProfiles request format`() {
        //without optionnal parameters
        var errors = JsonSchemaValidator.isValidObject(
                GetChargingProfilesReq(
                        78687,
                        chargingProfile = ChargingProfileCriterionType()
                ),
                "GetChargingProfilesRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        //with all parameters
        errors = JsonSchemaValidator.isValidObject(
                GetChargingProfilesReq(
                        78687,
                        ChargingProfileCriterionType(
                            ChargingProfilePurposeEnumType.ChargingStationMaxProfile,
                            675,
                            listOf(768),
                            listOf(ChargingLimitSourceEnumType.CSO)
                        ),
                        6778
                ),
                "GetChargingProfilesRequest.json"
        )

        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `unpublishFirmware request format`() {
        val errors = JsonSchemaValidator.isValidObject(
                UnpublishFirmwareReq("checksum"),
                "UnpublishFirmwareRequest.json")
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

    }

    @Test
    fun `getInstalledCertificateIds request format`() {
        var errors = JsonSchemaValidator.isValidObject(
                GetInstalledCertificateIdsReq(
                        listOf(GetCertificateIdUseEnumType.CSMSRootCertificate)
                ),
                "GetInstalledCertificateIdsRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                GetInstalledCertificateIdsReq(
                ),
                "GetInstalledCertificateIdsRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            SetNetworkProfileReq(
                2,
                NetworkConnectionProfileType(
                    OCPPVersionEnumType.OCPP12,
                    OCPPTransportEnumType.JSON,
                    "url",
                    312,
                    123,
                    OCPPInterfaceEnumType.Wired0
                )
            ),
            "SetNetworkProfileRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `installCertificate request format`() {
        val errors = JsonSchemaValidator.isValidObject(
                InstallCertificateReq(
                        InstallCertificateUseEnumType.CSMSRootCertificate,
                        "certificate"
                ),
                "InstallCertificateRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

    }


    @Test
    fun `customerInformation request format`() {
        var errors = JsonSchemaValidator.isValidObject(
                CustomerInformationReq(
                        requestId = 3,
                        report = false,
                        clear =true,
                        customerIdentifier ="identifier",
                        idToken = IdTokenType(
                                idToken = "idToken",
                                type = IdTokenEnumType.Central,
                                additionalInfo = listOf(
                                        AdditionalInfoType(
                                                "add",
                                                "value"
                                        ),
                                        AdditionalInfoType(
                                                "add",
                                                "value"
                                        ),
                                        AdditionalInfoType(
                                                "add",
                                                "value"
                                        )
                                )
                        ),
                        customerCertificate = CertificateHashDataType(
                                hashAlgorithm= HashAlgorithmEnumType.SHA512,
                                issuerNameHash="issuerNameHash",
                                issuerKeyHash="issuerKeyHash",
                                serialNumber="serial"
                        ),
                ),
                "CustomerInformationRequest.json")
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                CustomerInformationReq(
                        requestId = 3,
                        report = false,
                        clear =true,
                        customerIdentifier ="identifier",
                ),
                "CustomerInformationRequest.json")
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

    }


    @Test
    fun `reportChargingProfiles request format`() {
        var errors = JsonSchemaValidator.isValidObject(
                 ReportChargingProfilesReq(
                         requestId = 2,
                         chargingLimitSource = ChargingLimitSourceEnumType.CSO,
                         tbc = false,
                         evseId = 2,
                         chargingProfile =  listOf(
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
                 ),
                "ReportChargingProfilesRequest.json")
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            ReportChargingProfilesReq(
                requestId = 2,
                chargingLimitSource = ChargingLimitSourceEnumType.CSO,
                evseId = 2,
                chargingProfile =  listOf(
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
            ),
            "ReportChargingProfilesRequest.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }


    @Test
    fun `setNetworkProfile request format`() {
        var errors = JsonSchemaValidator.isValidObject(
                SetNetworkProfileReq(
                        2,
                        NetworkConnectionProfileType(
                                OCPPVersionEnumType.OCPP12,
                                OCPPTransportEnumType.JSON,
                                "url",
                                312,
                                123,
                                OCPPInterfaceEnumType.Wired0,
                                VPNType(
                                    "server",
                                    "user",
                                    "pass",
                                    "key",
                                    VPNEnumType.IKEv2,
                                    "group"
                                ),
                                APNType(
                                    "APN",
                                    APNAuthenticationEnumType.AUTO,
                                    "userName",
                                    "pass",
                                    3,
                                    "pref",
                                    false
                                )
                        )
                ),
                "SetNetworkProfileRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            SetNetworkProfileReq(
                2,
                NetworkConnectionProfileType(
                    OCPPVersionEnumType.OCPP12,
                    OCPPTransportEnumType.JSON,
                    "url",
                    312,
                    123,
                    OCPPInterfaceEnumType.Wired0
                )
            ),
            "SetNetworkProfileRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `notifyEVChargingSchedule response format`() {
        val errors = JsonSchemaValidator.isValidObject(
            NotifyEVChargingScheduleResp(
                status = GenericStatusEnumType.Accepted,
                statusInfo = StatusInfoType("123")
            ),
            "NotifyEVChargingScheduleResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getLog request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            GetLogReq(
                requestId = 1,
                logType = LogEnumType.DiagnosticsLog,
                log = LogParametersType(
                    remoteLocation = "remoteLocation",
                    oldestTimestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                    latestTimestamp = Instant.parse("2022-02-15T00:00:00.000Z")
                )
            ),
            "GetLogRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            GetLogReq(
                requestId = 1,
                logType = LogEnumType.DiagnosticsLog,
                log = LogParametersType(
                    remoteLocation = "remoteLocation",
                    oldestTimestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                    latestTimestamp = Instant.parse("2022-02-15T00:00:00.000Z")
                ),
                retries = 3,
                retryInterval = 5
            ),
            "GetLogRequest.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setMonitoringLevel request format`() {
        val errors = JsonSchemaValidator.isValidObject(
                SetMonitoringLevelReq(2),
                "SetMonitoringLevelRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setVariableMonitoring request format`() {
        var errors = JsonSchemaValidator.isValidObject(
                SetVariableMonitoringReq(
                        listOf (
                                SetMonitoringDataType(
                                    id=0,
                                    transaction = false,
                                    value = 432.4,
                                    type = MonitorEnumType.Periodic,
                                    severity =3,
                                    component= ComponentType("name"),
                                    variable = VariableType("name")

                                )
                        )
                ),
                "SetVariableMonitoringRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                SetVariableMonitoringReq(
                        listOf(
                            SetMonitoringDataType(
                                    value = 432.4,
                                    type = MonitorEnumType.Periodic,
                                    severity =3,
                                    component=ComponentType("name"),
                                    variable = VariableType("name")
                            )
                        )
                ),
                "SetVariableMonitoringRequest.json"
        )

        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `publishFirmware request format`() {
        var errors = JsonSchemaValidator.isValidObject(
            PublishFirmwareReq(
                    "location",
                    "identifier string",
                    43,
                    24243,
                    23443
            ),
            "PublishFirmwareRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                PublishFirmwareReq(
                        location="location",
                        checksum="identifier string",
                        requestId=24243,
                ),
                "PublishFirmwareRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setMonitoringBase request format`() {
        val errors = JsonSchemaValidator.isValidObject(
                SetMonitoringBaseReq(
                        MonitoringBaseEnumType.All
                ),
                "SetMonitoringBaseRequest.json")
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `heartbeat response format`() {
        val heartbeatResp = HeartbeatResp(
            currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
        )
        val errors = JsonSchemaValidator.isValidObject(heartbeatResp, "HeartbeatResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }


    @Test
    fun `authorize response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObject(
            AuthorizeResp(
                idTokenInfo = IdTokenInfoType(AuthorizationStatusEnumType.Accepted)
            ), "AuthorizeResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObject(
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
        val errors = JsonSchemaValidator.isValidObject(
            MeterValuesResp(), "MeterValuesResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `transactionEvent response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObject(TransactionEventResp(), "TransactionEventResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObject(
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
        val errors = JsonSchemaValidator.isValidObject(StatusNotificationResp(), "StatusNotificationResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `dataTransfer response format`() {
        /* Required field only */
        var errors = JsonSchemaValidator.isValidObject(
            DataTransferResp(DataTransferStatusEnumType.Accepted),
            "DataTransferResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        /* Every field */
        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
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
        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
            ChangeAvailabilityResp(ChangeAvailabilityStatusEnumType.Accepted),
            "ChangeAvailabilityResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            ChangeAvailabilityResp(ChangeAvailabilityStatusEnumType.Accepted, StatusInfoType("", "")),
            "ChangeAvailabilityResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearCache response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            ClearCacheResp(ClearCacheStatusEnumType.Accepted),
            "ClearCacheResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            ClearCacheResp(ClearCacheStatusEnumType.Accepted, StatusInfoType("", "")),
            "ClearCacheResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `unlockConnector response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            UnlockConnectorResp(UnlockStatusEnumType.Unlocked),
            "UnlockConnectorResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            UnlockConnectorResp(UnlockStatusEnumType.Unlocked, StatusInfoType("", "")),
            "UnlockConnectorResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `requestStopTransaction response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            RequestStopTransactionResp(RequestStartStopStatusEnumType.Accepted),
            "RequestStopTransactionResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            RequestStopTransactionResp(RequestStartStopStatusEnumType.Accepted, StatusInfoType("reason", "additional")),
            "RequestStopTransactionResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getVariables response format`() {
        var errors = JsonSchemaValidator.isValidObject(
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

        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
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

        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
            GetReportResp(GenericDeviceModelStatusEnumType.Accepted),
            "GetReportResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
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
        var errors = JsonSchemaValidator.isValidObject(
            GetBaseReportResp(GenericDeviceModelStatusEnumType.Rejected),
            "GetBaseReportResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
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
        val errors = JsonSchemaValidator.isValidObject(NotifyReportResp(), "NotifyReportResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setChargingProfile response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            SetChargingProfileResp(ChargingProfileStatusEnumType.Accepted), "SetChargingProfileResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            SetChargingProfileResp(
                ChargingProfileStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            ),
            "SetChargingProfileResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `sendLocalList response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            SendLocalListResp(SendLocalListStatusEnumType.Accepted),
            "SendLocalListResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            SendLocalListResp(SendLocalListStatusEnumType.Accepted, StatusInfoType("reason", "additional")),
            "SendLocalListResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `cancelReservation response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            CancelReservationResp(CancelReservationStatusEnumType.Rejected), "CancelReservationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
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
        val errors = JsonSchemaValidator.isValidObject(
            FirmwareStatusNotificationResp(),
            "FirmwareStatusNotificationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearChargingProfile response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            ClearChargingProfileResp(ClearChargingProfileEnumType.Accepted), "ClearChargingProfileResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
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
        val errors = JsonSchemaValidator.isValidObject(
            ClearedChargingLimitResp(), "ClearedChargingLimitResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getCompositeSchedule response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            GetCompositeScheduleResp(GenericStatusEnumType.Accepted), "GetCompositeScheduleResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
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
        val errors = JsonSchemaValidator.isValidObject(GetLocalListVersionResp(1), "GetLocalListVersionResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `updatefirmware response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            UpdateFirmwareResp(
                status = UpdateFirmwareStatusEnumType.Accepted
            ),
            "UpdateFirmwareResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObject(
            UpdateFirmwareResp(
                status = UpdateFirmwareStatusEnumType.Accepted,
                statusInfo = StatusInfoType("reason", "additional")
            ),
            "UpdateFirmwareResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `triggerMessage response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            TriggerMessageResp(
                TriggerMessageStatusEnumType.Accepted
            ),
            "TriggerMessageResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObject(
            TriggerMessageResp(
                TriggerMessageStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            ),
            "TriggerMessageResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `notifyChargingLimit response format`() {
        val errors = JsonSchemaValidator.isValidObject(
            NotifyChargingLimitResp(), "NotifyChargingLimitResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `getCertificateStatus response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            GetCertificateStatusResp(GetCertificateStatusEnumType.Accepted), "GetCertificateStatusResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObject(
            GetCertificateStatusResp(GetCertificateStatusEnumType.Accepted, "", StatusInfoType("reason", "additional")),
            "GetCertificateStatusResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `notifyEVChargingNeeds response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            NotifyEVChargingNeedsResp(NotifyEVChargingNeedsStatusEnumType.Accepted),
            "NotifyEVChargingNeedsResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        errors = JsonSchemaValidator.isValidObject(
            NotifyEVChargingNeedsResp(
                NotifyEVChargingNeedsStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            ), "NotifyEVChargingNeedsResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `reserveNow response format`() {
        val errors = JsonSchemaValidator.isValidObject(
            ReserveNowResp(ReserveNowStatusEnumType.Accepted), "ReserveNowResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `notifyDisplayMessages response format`() {
        val errors = JsonSchemaValidator.isValidObject(
            NotifyDisplayMessagesResp(),
            "NotifyDisplayMessagesResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }

    @Test
    fun `notifyEvent response format`() {
        val errors = JsonSchemaValidator.isValidObject(NotifyEventResp(), "NotifyEventResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getLog response format`() {
        var errors = JsonSchemaValidator.isValidObject(GetLogResp(LogStatusEnumType.Accepted), "GetLogResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            GetLogResp(
                status = LogStatusEnumType.Accepted,
                filename = "filename",
                statusInfo = StatusInfoType("reason", "additional")
            ), "GetLogResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `securityEventNotification response format`() {
        val errors = JsonSchemaValidator.isValidObject(
            SecurityEventNotificationResp(),
            "SecurityEventNotificationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `reservationStatusUpdate response format`() {
        val errors =
            JsonSchemaValidator.isValidObject(ReservationStatusUpdateResp(), "ReservationStatusUpdateResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `notifyMonitoringReport response format`() {
        val errors =
            JsonSchemaValidator.isValidObject(NotifyMonitoringReportResp(), "NotifyMonitoringReportResponse.json")
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `logStatusNotification response format`() {
        val errors = JsonSchemaValidator.isValidObject(
            LogStatusNotificationResp(),  "LogStatusNotificationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `certificateSigned response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            CertificateSignedResp(CertificateSignedStatusEnumType.Accepted), "CertificateSignedResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            CertificateSignedResp(
                CertificateSignedStatusEnumType.Accepted,
                StatusInfoType("reason", "info")
            ),
            "CertificateSignedResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `publishFirmwareStatusNotification response format`() {
        val errors = JsonSchemaValidator.isValidObject(
            PublishFirmwareStatusNotificationResp(),
            "PublishFirmwareStatusNotificationResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `signCertificate response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            SignCertificateResp(GenericStatusEnumType.Accepted),
            "SignCertificateResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            SignCertificateResp(
                GenericStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            ), "SignCertificateResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }
    @Test
    fun `clearDisplayMessage response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            ClearDisplayMessageResp(ClearMessageStatusEnumType.Accepted),
            "ClearDisplayMessageResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            ClearDisplayMessageResp(
                ClearMessageStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            ), "ClearDisplayMessageResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getDisplayMessages response format`() {
        //without optionnal parameters
        var errors = JsonSchemaValidator.isValidObject(
                GetDisplayMessagesResp(GetDisplayMessagesStatusEnumType.Accepted),
                "GetDisplayMessagesResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        //with all parameters

        errors = JsonSchemaValidator.isValidObject(
                GetDisplayMessagesResp(
                        GetDisplayMessagesStatusEnumType.Accepted,
                        StatusInfoType("reason","info")
                ),
                "GetDisplayMessagesResponse.json"
        )

        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setMonitoringBase response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                SetMonitoringBaseResp(
                        GenericDeviceModelStatusEnumType.Accepted,
                        StatusInfoType("reason","info")
                ),
                "SetMonitoringBaseResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                SetMonitoringBaseResp(
                        GenericDeviceModelStatusEnumType.Accepted
                ),
                "SetMonitoringBaseResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

    }

    @Test
    fun `setNetworkProfile response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                SetNetworkProfileResp(
                        SetNetworkProfileStatusEnumType.Accepted
                ),
                "SetNetworkProfileResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                SetNetworkProfileResp(
                        SetNetworkProfileStatusEnumType.Accepted,
                        StatusInfoType("reason","info")
                ),
                "SetNetworkProfileResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

    }

    @Test
    fun `setMonitoringLevel response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                SetMonitoringLevelResp(
                        GenericStatusEnumType.Accepted,
                        StatusInfoType("reason","additionnal")
                ),
                "SetMonitoringLevelResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                SetMonitoringLevelResp(
                        GenericStatusEnumType.Accepted
                ),
                "SetMonitoringLevelResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

    }

    @Test
    fun `getChargingProfiles response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                GetChargingProfilesResp(
                        GetChargingProfileStatusEnumType.NoProfiles,
                        StatusInfoType("reason","additionnal")),
                "GetChargingProfilesResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                GetChargingProfilesResp(
                        GetChargingProfileStatusEnumType.NoProfiles),
                "GetChargingProfilesResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getInstalledCertificateIds response format`() {

        var errors = JsonSchemaValidator.isValidObject(
                GetInstalledCertificateIdsResp(
                    GetInstalledCertificateStatusEnumType.Accepted,
                    listOf(
                            CertificateHashDataChainType(GetCertificateIdUseEnumType.CSMSRootCertificate, CertificateHashDataType(
                                HashAlgorithmEnumType.SHA512,"","",""),listOf(CertificateHashDataType(
                                HashAlgorithmEnumType.SHA512,"","",""),CertificateHashDataType(HashAlgorithmEnumType.SHA512,"","",""))),
                    ),
                    StatusInfoType("reason","info")
                ),
                "GetInstalledCertificateIdsResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                GetInstalledCertificateIdsResp(
                        GetInstalledCertificateStatusEnumType.Accepted,
                ),
                "GetInstalledCertificateIdsResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `installCertificate response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                InstallCertificateResp(
                    InstallCertificateStatusEnumType.Accepted,
                    StatusInfoType("reason","info")
                ),
                "InstallCertificateResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                InstallCertificateResp(
                        InstallCertificateStatusEnumType.Accepted
                ),
                "InstallCertificateResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

    }

    @Test
    fun `customerInformation response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                CustomerInformationResp(
                        CustomerInformationStatusEnumType.Accepted,
                        StatusInfoType("reason","code")
                ),
                "CustomerInformationResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                CustomerInformationResp(
                        CustomerInformationStatusEnumType.Accepted
                ),
                "CustomerInformationResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `reportChargingProfiles response format`() {
        val errors = JsonSchemaValidator.isValidObject(
                ReportChargingProfilesResp(),
                "ReportChargingProfilesResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `unpublishFirmware response format`() {
        val errors = JsonSchemaValidator.isValidObject(
                UnpublishFirmwareResp(UnpublishFirmwareStatusEnumType.Unpublished),
                "UnpublishFirmwareResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `publishFirmware response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                PublishFirmwareResp(
                        GenericStatusEnumType.Accepted,
                        StatusInfoType("reason","info")
                ),
                "PublishFirmwareResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                PublishFirmwareResp(
                        GenericStatusEnumType.Accepted
                ),
                "PublishFirmwareResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setVariableMonitoring response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                SetVariableMonitoringResp
                (
                    setMonitoringResult =listOf(
                         SetMonitoringResultType(
                                id=23,
                                status=SetMonitoringStatusEnumType.Accepted,
                                type=MonitorEnumType.Delta,
                                severity=3,
                                component=ComponentType("name"),
                                variable=VariableType("name"),
                                statusInfo=StatusInfoType("reason","info")
                        )
                    )
                ),
                "SetVariableMonitoringResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                SetVariableMonitoringResp
                (
                        setMonitoringResult =listOf(
                                SetMonitoringResultType(
                                        status=SetMonitoringStatusEnumType.Accepted,
                                        type=MonitorEnumType.Delta,
                                        severity=3,
                                        component=ComponentType("name"),
                                        variable=VariableType("name"),
                                )
                        )
                ),
                "SetVariableMonitoringResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getTransactionStatus response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                GetTransactionStatusResp(
                        true,
                        false
                ),
                "GetTransactionStatusResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                GetTransactionStatusResp(
                        true,
                ),
                "GetTransactionStatusResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `getTransactionStatus request format`() {
        var errors = JsonSchemaValidator.isValidObject(
                GetTransactionStatusReq(
                    "id"
                ),
                "GetTransactionStatusRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                GetTransactionStatusReq(
                ),
                "GetTransactionStatusRequest.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `setDisplayMessage response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                SetDisplayMessageResp(
                        DisplayMessageStatusEnumType.Accepted,
                        StatusInfoType("reason","code")
                ),
                "SetDisplayMessageResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                SetDisplayMessageResp(
                        DisplayMessageStatusEnumType.Accepted
                ),
                "SetDisplayMessageResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `costUpdated response format`() {

        val errors = JsonSchemaValidator.isValidObject(
                CostUpdatedResp(),
                "CostUpdatedResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `deleteCertificate response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                //Test without optional parameter
                DeleteCertificateResp(
                        DeleteCertificateStatusEnumType.Accepted
                ),
                "DeleteCertificateResponse.json"
        )

        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }

        //Test with all parameters
        errors = JsonSchemaValidator.isValidObject(
                //Test without optional parameter
                DeleteCertificateResp(
                        DeleteCertificateStatusEnumType.Accepted,
                        StatusInfoType("reason","info")
                ),
                "DeleteCertificateResponse.json"
        )
        expectThat(errors) {
            get { this.size }.isEqualTo(0)
        }
    }
    @Test
    fun `getMonitoringReport response format`() {
        var errors = JsonSchemaValidator.isValidObject(
                GetMonitoringReportResp(
                        GenericDeviceModelStatusEnumType.Accepted,
                        StatusInfoType("reason","info")
                ),
                "GetMonitoringReportResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
                GetMonitoringReportResp(
                        GenericDeviceModelStatusEnumType.Accepted
                ),
                "GetMonitoringReportResponse.json"
        )
        expectThat(errors)
                .and { get { this.size }.isEqualTo(0) }
    }

    @Test
    fun `clearVariableMonitoring response format`() {
        var errors = JsonSchemaValidator.isValidObject(
            ClearVariableMonitoringResp(
                clearMonitoringResults =
                listOf(
                    ClearMonitoringResultType(
                        status = ClearMonitoringStatusEnumType.Accepted,
                        id = 1,
                        statusInfo = StatusInfoType(
                            reasonCode = "reasonCode",
                            additionalInfo = "additionalInfo"
                        )
                    )
                )
            ),
            "ClearVariableMonitoringResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }

        errors = JsonSchemaValidator.isValidObject(
            ClearVariableMonitoringResp(
                clearMonitoringResults =
                listOf(
                    ClearMonitoringResultType(
                        status = ClearMonitoringStatusEnumType.Accepted,
                        id = 1
                    )
                )
            ),
            "ClearVariableMonitoringResponse.json"
        )
        expectThat(errors)
            .and { get { this.size }.isEqualTo(0) }
    }
}
