package com.izivia.ocpp.json20

import com.izivia.ocpp.core20.model.authorize.AuthorizeReq
import com.izivia.ocpp.core20.model.authorize.AuthorizeResp
import com.izivia.ocpp.core20.model.authorize.enumeration.AuthorizeCertificateStatusEnumType
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
import com.izivia.ocpp.core20.model.clearchargingprofile.enumeration.ClearChargingProfileEnumType
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageResp
import com.izivia.ocpp.core20.model.cleardisplaymessage.enumeration.ClearMessageStatusEnumType
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearMonitoringResultType
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringResp
import com.izivia.ocpp.core20.model.clearvariablemonitoring.enumeration.ClearMonitoringStatusEnumType
import com.izivia.ocpp.core20.model.common.*
import com.izivia.ocpp.core20.model.common.enumeration.*
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedResp
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
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesResp
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
import com.izivia.ocpp.core20.model.getreport.GetReportReq
import com.izivia.ocpp.core20.model.getreport.GetReportResp
import com.izivia.ocpp.core20.model.getreport.enumeration.ComponentCriterionEnumType
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusReq
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusResp
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
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
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
import com.izivia.ocpp.core20.model.notifyreport.*
import com.izivia.ocpp.core20.model.notifyreport.enumeration.DataEnumType
import com.izivia.ocpp.core20.model.notifyreport.enumeration.MutabilityEnumType
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationResp
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType
import com.izivia.ocpp.core20.model.remotestart.*
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
import com.izivia.ocpp.core20.model.reset.ResetReq
import com.izivia.ocpp.core20.model.reset.ResetResp
import com.izivia.ocpp.core20.model.reset.enumeration.ResetEnumType
import com.izivia.ocpp.core20.model.reset.enumeration.ResetStatusEnumType
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
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseResp
import com.izivia.ocpp.core20.model.setmonitoringbase.enumeration.MonitoringBaseEnumType
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.core20.model.setnetworkprofile.*
import com.izivia.ocpp.core20.model.setnetworkprofile.enumeration.*
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetMonitoringDataType
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetMonitoringResultType
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringResp
import com.izivia.ocpp.core20.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType
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
import com.izivia.ocpp.json.JsonMessage
import com.izivia.ocpp.json.JsonMessageType
import kotlin.time.Clock
import kotlin.time.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA

class JsonSchemaTest {

    companion object {
        val parser = Ocpp20JsonParser()
    }

    @Test
    fun `heartbeat request format`() {
        validateObject(HeartbeatReq())
    }

    @Test
    fun `authorize request format`() {
        /* Required field only */
        validateObject(
            AuthorizeReq(
                idToken = IdTokenType("Tag1", IdTokenEnumType.Local)
            )
        )

        validateObject(
            AuthorizeReq(
                idToken = IdTokenType(
                    "Tag1",
                    IdTokenEnumType.Central,
                    listOf(AdditionalInfoType("additionalIdToken", "type"))
                ),
                certificate = "certificate1",
                iso15118CertificateHashData = listOf(
                    OCSPRequestDataType(
                        HashAlgorithmEnumType.SHA256,
                        "issuerNameHash",
                        "issuerKeyHash",
                        "serialNumber",
                        "responseUrl"
                    )
                )
            )
        )
    }

    @Test
    fun `meterValues request format`() {
        /* Required field only */
        validateObject(
            MeterValuesReq(
                evseId = 1,
                meterValue = listOf(
                    MeterValueType(
                        listOf(SampledValueType(value = 0.9)),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            )
        )

        /* Every field */
        validateObject(
            MeterValuesReq(
                evseId = 1,
                meterValue = listOf(
                    MeterValueType(
                        listOf(
                            SampledValueType(
                                value = 0.9,
                                phase = PhaseEnumType.L1,
                                signedMeterValue = SignedMeterValueType(
                                    "signedMeterData",
                                    "signingMethod",
                                    "encodingMethod",
                                    "publicKey"
                                ),
                                unitOfMeasure = UnitOfMeasure("unit", 2)
                            )
                        ),
                        Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            )
        )
    }

    @Test
    fun `transactionEvent request format`() {
        validateObject(
            TransactionEventReq(
                TransactionEventEnumType.Started,
                Instant.parse("2022-02-15T00:00:00.000Z"),
                TriggerReasonEnumType.Authorized,
                1,
                TransactionType("transactionId")
            )
        )

        validateObject(
            TransactionEventReq(
                TransactionEventEnumType.Started,
                Instant.parse("2022-02-15T00:00:00.000Z"),
                TriggerReasonEnumType.Authorized,
                1,
                TransactionType("transactionId"),
                1,
                EVSEType(1, 2),
                IdTokenType(
                    "idToken",
                    IdTokenEnumType.Central,
                    listOf(AdditionalInfoType("additionalToken", "type"))
                ),
                listOf(MeterValueType(listOf(SampledValueType(10.9)), Instant.parse("2022-02-15T00:00:00.000Z"))),
                3,
                true,
                100
            )
        )
    }

    @Test
    fun `statusNotification request format`() {
        /* Required field only */
        validateObject(
            StatusNotificationReq(
                connectorId = 2,
                connectorStatus = ConnectorStatusEnumType.Available,
                evseId = 12,
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z")
            )
        )
    }

    @Test
    fun `dataTransfer request format`() {
        /* Required field only */
        validateObject(
            DataTransferReq("vendor1")
        )

        validateObject(
            DataTransferReq(vendorId = "vendor1", messageId = "message1", data = "data1")
        )
    }

    @Test
    fun `bootNotification request format`() {
        /* Required field only */
        validateObject(
            BootNotificationReq(
                chargingStation = ChargingStationType(model = "model", vendorName = "vendor"),
                reason = BootReasonEnumType.ApplicationReset
            )
        )

        /* Every field */
        validateObject(
            BootNotificationReq(
                chargingStation = ChargingStationType(
                    model = "model",
                    vendorName = "vendor",
                    firmwareVersion = "version",
                    modem = ModemType("iccid", "imsi"),
                    serialNumber = "0"
                ),
                reason = BootReasonEnumType.ApplicationReset
            )
        )
    }

    @Test
    fun `changeAvailability request format`() {
        validateObject(
            ChangeAvailabilityReq(OperationalStatusEnumType.Operative)
        )

        validateObject(ChangeAvailabilityReq(OperationalStatusEnumType.Operative, EVSEType(1, 1)))
    }

    @Test
    fun `clearCache request format`() {
        validateObject(
            ClearCacheReq()
        )
    }

    @Test
    fun `unlockConnector request format`() {
        validateObject(
            UnlockConnectorReq(1, 2)
        )
    }

    @Test
    fun `requestStartTransaction request format`() {
        validateObject(
            RequestStartTransactionReq(
                remoteStartId = 1,
                idToken = IdTokenType("token", IdTokenEnumType.Central)
            )
        )

        validateObject(
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
            )
        )

        validateObject(
            RequestStartTransactionReq(
                remoteStartId = 1,
                idToken = IdTokenType(
                    "token",
                    IdTokenEnumType.Central,
                    listOf(AdditionalInfoType("additionalToken", "type"))
                ),
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
                    transactionId = "transactionId"
                )
            )
        )
    }

    @Test
    fun `requestStopTransaction request format`() {
        validateObject(
            RequestStopTransactionReq("tag1")
        )
    }

    @Test
    fun `getVariables request format`() {
        validateObject(
            GetVariablesReq(listOf(GetVariableDataType(ComponentType("component"), VariableType("variable"))))
        )

        validateObject(
            GetVariablesReq(
                listOf(
                    GetVariableDataType(
                        component = ComponentType(name = "component", instance = "instance", evse = EVSEType(1)),
                        variable = VariableType(name = "variable", instance = "instance"),
                        attributeType = AttributeEnumType.Target
                    )
                )
            )
        )
    }

    @Test
    fun `setVariables request format`() {
        validateObject(
            SetVariablesReq(
                listOf(
                    SetVariableDataType(
                        attributeValue = "value",
                        component = ComponentType("component"),
                        variable = VariableType("variable")
                    )
                )
            )
        )

        validateObject(
            SetVariablesReq(
                listOf(
                    SetVariableDataType(
                        attributeValue = "value",
                        component = ComponentType("component", "instance", EVSEType(1, 2)),
                        variable = VariableType("variable", "instance"),
                        attributeType = AttributeEnumType.Target
                    )
                )
            )
        )
    }

    @Test
    fun `getReport request format`() {
        validateObject(
            GetReportReq(1)
        )

        validateObject(
            GetReportReq(
                1,
                listOf(ComponentCriterionEnumType.Active),
                listOf(ComponentVariableType(ComponentType("component")))
            )
        )
    }

    @Test
    fun `getBaseReport request format`() {
        validateObject(
            GetBaseReportReq(1, ReportBaseEnumType.ConfigurationInventory)
        )
    }

    @Test
    fun `setChargingProfile request format`() {
        validateObject(
            SetChargingProfileReq(
                1,
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
    }

    @Test
    fun `getLocalListVersion request format`() {
        validateObject(
            GetLocalListVersionReq()
        )
    }

    @Test
    fun `cancelReservation request format`() {
        validateObject(
            CancelReservationReq(3)
        )
    }

    @Test
    fun `firmwareStatusNotification request format`() {
        validateObject(
            FirmwareStatusNotificationReq(FirmwareStatusEnumType.Downloaded)
        )
    }

    @Test
    fun `clearChargingProfile request format`() {
        validateObject(
            ClearChargingProfileReq()
        )

        validateObject(
            ClearChargingProfileResp(
                ClearChargingProfileEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `getCompositeSchedule request format`() {
        validateObject(
            GetCompositeScheduleReq(1, 3)
        )

        validateObject(
            GetCompositeScheduleReq(1, 2, ChargingRateUnitEnumType.A)
        )
    }

    @Test
    fun `updateFirmware request format`() {
        validateObject(
            UpdateFirmwareReq(
                requestId = 2,
                firmware = FirmwareType(
                    location = "http://www.ietf.org/rfc/rfc2396.txt", // URI
                    retrieveDateTime = Instant.parse("2022-02-15T00:00:00.000Z")
                )
            )
        )

        validateObject(
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
            )
        )
    }

    @Test
    fun `clearedChargingLimit request format`() {
        validateObject(
            ClearedChargingLimitReq(ChargingLimitSourceEnumType.CSO)
        )

        validateObject(
            ClearedChargingLimitReq(ChargingLimitSourceEnumType.EMS, 1)
        )
    }

    @Test
    fun `sendLocalList request format`() {
        validateObject(
            SendLocalListReq(1, UpdateEnumType.Full)
        )

        validateObject(
            SendLocalListReq(
                1,
                UpdateEnumType.Full,
                listOf(
                    AuthorizationData(
                        IdTokenType(
                            "idToken",
                            IdTokenEnumType.Central,
                            listOf(AdditionalInfoType("additionalToken", "type"))
                        ),
                        IdTokenInfoType(
                            AuthorizationStatusEnumType.Accepted,
                            Instant.parse("2022-02-15T00:00:00.000Z"),
                            9,
                            "fr",
                            listOf(2, 4),
                            "en",
                            IdTokenType(
                                "idToken",
                                IdTokenEnumType.Central,
                                listOf(AdditionalInfoType("additionalToken", "type"))
                            ),
                            MessageContentType(MessageFormatEnumType.ASCII, "content", "language")
                        )
                    )
                )
            )
        )
    }

    @Test
    fun `triggerMessage request format`() {
        validateObject(
            TriggerMessageReq(
                MessageTriggerEnumType.BootNotification
            )
        )

        validateObject(
            TriggerMessageReq(
                MessageTriggerEnumType.Heartbeat,
                EVSEType(1, 1)
            )
        )
    }

    @Test
    fun `notifyChargingLimit request format`() {
        validateObject(
            NotifyChargingLimitReq(ChargingLimitType(ChargingLimitSourceEnumType.CSO))
        )

        validateObject(
            NotifyChargingLimitReq(
                ChargingLimitType(ChargingLimitSourceEnumType.CSO, true),
                1,
                listOf(
                    ChargingScheduleType(
                        id = 1,
                        chargingRateUnit = ChargingRateUnitEnumType.A,
                        chargingSchedulePeriod = listOf(ChargingSchedulePeriodType(1, 1.0)),
                        startSchedule = Instant.parse("2022-02-15T00:00:00.000Z")
                    )
                )
            )
        )
    }

    @Test
    fun `getCertificateStatus request format`() {
        validateObject(
            GetCertificateStatusReq(
                OCSPRequestDataType(
                    HashAlgorithmEnumType.SHA256,
                    "issuerNameHash",
                    "issuerKeyHash",
                    "serialNumber",
                    "responderUrl"
                )
            )
        )
    }

    @Test
    fun `reserveNow request format`() {
        validateObject(
            ReserveNowReq(
                id = 1,
                expiryDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                idToken = IdTokenType("token", IdTokenEnumType.Central)
            )
        )

        validateObject(
            ReserveNowReq(
                id = 1,
                expiryDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                connectorType = ConnectorEnumType.cTesla,
                evseId = 2,
                idToken = IdTokenType("token1", IdTokenEnumType.Central),
                groupIdToken = IdTokenType("token2", IdTokenEnumType.Central)
            )
        )
    }

    @Test
    fun `notifyCustomerInformation request format`() {
        validateObject(
            NotifyCustomerInformationReq(
                data = "Some data",
                seqNo = 0,
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                requestId = 1
            )
        )

        validateObject(
            NotifyCustomerInformationReq(
                data = "Some data",
                tbc = true,
                seqNo = 0,
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                requestId = 1
            )
        )
    }

    @Test
    fun `notifyDisplayMessage request format`() {
        validateObject(
            NotifyDisplayMessagesReq(
                requestId = 1
            )
        )

        validateObject(
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
            )
        )
    }

    @Test
    fun `notifyEVChargingNeeds request format`() {
        validateObject(
            NotifyEVChargingNeedsReq(
                1,
                ChargingNeedsType(EnergyTransferModeEnumType.AC_single_phase)
            )
        )

        validateObject(
            NotifyEVChargingNeedsReq(
                1,
                ChargingNeedsType(
                    requestedEnergyTransfer = EnergyTransferModeEnumType.AC_single_phase,
                    departureTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                    ACChargingParametersType(2, 3, 4, 5),
                    DCChargingParametersType(
                        2,
                        3,
                        4,
                        5,
                        6,
                        7,
                        8,
                        9
                    )
                ),
                1
            )
        )
    }

    @Test
    fun `notify request response format`() {
        validateObject(
            NotifyReportReq(
                requestId = 1,
                generatedAt = Clock.System.now(),
                seqNo = 1
            )
        )

        validateObject(
            NotifyReportReq(
                requestId = 1,
                generatedAt = Clock.System.now(),
                seqNo = 1,
                tbc = true,
                reportData = listOf(
                    ReportDataType(
                        ComponentType("component"),
                        VariableType("variable"),
                        listOf(VariableAttributeType()),
                        VariableCharacteristicsType(DataEnumType.DECIMAL, true)
                    )
                )
            )
        )

        validateObject(
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
                        VariableCharacteristicsType(
                            DataEnumType.DECIMAL,
                            true,
                            "unit",
                            10.0,
                            20.0,
                            "valuesList"
                        )
                    )
                )
            )
        )
    }

    @Test
    fun `notifyEvent request format`() {
        validateObject(
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
            )
        )
        validateObject(
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
            )
        )
    }

    @Test
    fun `notifyEVChargingSchedule request format`() {
        validateObject(
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
            )
        )
    }

    @Test
    fun `notifyEVChargingSchedule response format`() {
        validateObject(
            NotifyEVChargingScheduleResp(
                status = GenericStatusEnumType.Accepted,
                statusInfo = StatusInfoType("123")
            )
        )
    }

    @Test
    fun `securityEventNotification request format`() {
        validateObject(
            SecurityEventNotificationReq("type", Instant.parse("2022-02-15T00:00:00.000Z"))
        )

        validateObject(
            SecurityEventNotificationReq(
                type = "type",
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                techInfo = "techInfo"
            )
        )
    }

    @Test
    fun `publishFirmwareStatusNotification request format`() {
        validateObject(
            PublishFirmwareStatusNotificationReq(
                status = PublishFirmwareStatusEnumType.Published,
                location = listOf("location"),
                requestId = 1
            )
        )
    }

    @Test
    fun `logStatusNotification request format`() {
        validateObject(
            LogStatusNotificationReq(
                status = UploadLogStatusEnumType.Uploaded,
                requestId = 1
            )
        )
    }

    @Test
    fun `notifyMonitoringReport request format`() {
        validateObject(
            NotifyMonitoringReportReq(
                requestId = 1,
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                seqNo = 2
            )
        )

        validateObject(
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
            )
        )
    }

    @Test
    fun `reservationStatusUpdate request format`() {
        validateObject(
            ReservationStatusUpdateReq(1, ReservationUpdateStatusEnumType.Expired)
        )
    }

    @Test
    fun `clearDisplayMessage request format`() {
        validateObject(ClearDisplayMessageReq(1))
    }

    @Test
    fun `certificateSigned request format`() {
        validateObject(
            CertificateSignedReq("certificateChain")
        )

        validateObject(
            CertificateSignedReq("certificateChain", CertificateSigningUseEnumType.V2GCertificate)
        )
    }

    @Test
    fun `signCertificate request format`() {
        validateObject(SignCertificateReq("csr"))

        validateObject(
            SignCertificateReq(
                "csr",
                CertificateSigningUseEnumType.ChargingStationCertificate
            )
        )
    }

    @Test
    fun `clearVariableMonitoring request format`() {
        validateObject(
            ClearVariableMonitoringReq(
                ids = listOf(1, 2)
            )
        )
    }

    @Test
    fun `getMonitoringReport request format`() {
        validateObject(
            GetMonitoringReportReq(
                243432,
                listOf(MonitoringCriterionEnumType.DeltaMonitoring),
                listOf(
                    ComponentVariableType(
                        ComponentType(
                            "typename",
                            "instance"
                        )
                    )
                )
            )
        )

        validateObject(
            GetMonitoringReportReq(
                243432
            )
        )
    }

    @Test
    fun `deleteCertificate request format`() {
        val certif = CertificateHashDataType(
            HashAlgorithmEnumType.SHA512,
            "3041edbcdd46190c0acc504ed195f8a90129efcab173a7b9ac4646b92d04cc80005acaa3554f4b1df839" +
                "eacadc2491cb623bf3aa6f9eb44f6ea8ca005821d25d",
            "1f40fc92da241694750979ee6cf582f2d5d7d28e18335de05abc54d0560e0f5302860c652bf08d560252" +
                "aa5e74210546f369fbbbce8c12cfc7957b2652fe9a75",
            "7683246784"
        )
        validateObject(
            DeleteCertificateReq(
                certif
            )
        )
    }

    @Test
    fun `setDisplayMessage request format`() {
        validateObject(
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
            )
        )

        validateObject(
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
            )
        )
    }

    @Test
    fun `costUpdated request format`() {
        validateObject(
            CostUpdatedReq(5465.2, "45465")
        )
    }

    @Test
    fun `getDisplayMessages request format`() {
        validateObject(
            GetDisplayMessagesReq(
                id = listOf(32, 23),
                requestId = 2,
                priority = MessagePriorityEnumType.AlwaysFront,
                state = MessageStateEnumType.Charging
            )
        )
        validateObject(
            GetDisplayMessagesReq(
                requestId = 2
            )
        )
    }

    @Test
    fun `getChargingProfiles request format`() {
        // without optionnal parameters
        validateObject(
            GetChargingProfilesReq(
                78687,
                chargingProfile = ChargingProfileCriterionType()
            )
        )

        // with all parameters
        validateObject(
            GetChargingProfilesReq(
                78687,
                ChargingProfileCriterionType(
                    ChargingProfilePurposeEnumType.ChargingStationMaxProfile,
                    675,
                    listOf(768),
                    listOf(ChargingLimitSourceEnumType.CSO)
                ),
                6778
            )
        )
    }

    @Test
    fun `unpublishFirmware request format`() {
        validateObject(UnpublishFirmwareReq("checksum"))
    }

    @Test
    fun `getInstalledCertificateIds request format`() {
        validateObject(
            GetInstalledCertificateIdsReq(
                listOf(GetCertificateIdUseEnumType.CSMSRootCertificate)
            )
        )
        validateObject(
            GetInstalledCertificateIdsReq()
        )
    }

    @Test
    fun `SetNetworkProfile request format`() {
        validateObject(
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
            )
        )

        validateObject(
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
            )
        )

        validateObject(
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
            )
        )
    }

    @Test
    fun `installCertificate request format`() {
        validateObject(
            InstallCertificateReq(
                InstallCertificateUseEnumType.CSMSRootCertificate,
                "certificate"
            )
        )
    }

    @Test
    fun `customerInformation request format`() {
        validateObject(
            CustomerInformationReq(
                requestId = 3,
                report = false,
                clear = true,
                customerIdentifier = "identifier",
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
                    hashAlgorithm = HashAlgorithmEnumType.SHA512,
                    issuerNameHash = "issuerNameHash",
                    issuerKeyHash = "issuerKeyHash",
                    serialNumber = "serial"
                )
            )
        )

        validateObject(
            CustomerInformationReq(
                requestId = 3,
                report = false,
                clear = true,
                customerIdentifier = "identifier"
            )
        )
    }

    @Test
    fun `reportChargingProfiles request format`() {
        validateObject(
            ReportChargingProfilesReq(
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
        )

        validateObject(
            ReportChargingProfilesReq(
                requestId = 2,
                chargingLimitSource = ChargingLimitSourceEnumType.CSO,
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
        )
    }

    @Test
    fun `getLog request format`() {
        validateObject(
            GetLogReq(
                requestId = 1,
                logType = LogEnumType.DiagnosticsLog,
                log = LogParametersType(
                    remoteLocation = "remoteLocation",
                    oldestTimestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                    latestTimestamp = Instant.parse("2022-02-15T00:00:00.000Z")
                )
            )
        )

        validateObject(
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
            )
        )
    }

    @Test
    fun `setMonitoringLevel request format`() {
        validateObject(
            SetMonitoringLevelReq(2)
        )
    }

    @Test
    fun `setVariableMonitoring request format`() {
        validateObject(
            SetVariableMonitoringReq(
                listOf(
                    SetMonitoringDataType(
                        id = 0,
                        transaction = false,
                        value = 432.4,
                        type = MonitorEnumType.Periodic,
                        severity = 3,
                        component = ComponentType("name"),
                        variable = VariableType("name")

                    )
                )
            )
        )
        validateObject(
            SetVariableMonitoringReq(
                listOf(
                    SetMonitoringDataType(
                        value = 432.4,
                        type = MonitorEnumType.Periodic,
                        severity = 3,
                        component = ComponentType("name"),
                        variable = VariableType("name")
                    )
                )
            )
        )
    }

    @Test
    fun `publishFirmware request format`() {
        validateObject(
            PublishFirmwareReq(
                "location",
                "identifier string",
                43,
                24243,
                23443
            )
        )
        validateObject(
            PublishFirmwareReq(
                location = "location",
                checksum = "identifier string",
                requestId = 24243
            )
        )
    }

    @Test
    fun `setMonitoringBase request format`() {
        validateObject(
            SetMonitoringBaseReq(
                MonitoringBaseEnumType.All
            )
        )
    }

    @Test
    fun `notifyChargingLimit response format`() {
        validateObject(
            NotifyChargingLimitResp()
        )
    }

    @Test
    fun `getCertificateStatus response format`() {
        validateObject(
            GetCertificateStatusResp(GetCertificateStatusEnumType.Accepted)
        )

        validateObject(
            GetCertificateStatusResp(
                GetCertificateStatusEnumType.Accepted,
                "",
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `notifyEVChargingNeeds response format`() {
        validateObject(
            NotifyEVChargingNeedsResp(NotifyEVChargingNeedsStatusEnumType.Accepted)
        )
        validateObject(
            NotifyEVChargingNeedsResp(
                NotifyEVChargingNeedsStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `heartbeat response format`() {
        validateObject(
            HeartbeatResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z")
            )
        )
    }

    @Test
    fun `authorize response format`() {
        /* Required field only */
        validateObject(
            AuthorizeResp(
                idTokenInfo = IdTokenInfoType(AuthorizationStatusEnumType.Accepted)
            )
        )

        /* Every field */
        validateObject(
            AuthorizeResp(
                idTokenInfo = IdTokenInfoType(AuthorizationStatusEnumType.Blocked),
                certificateStatus = AuthorizeCertificateStatusEnumType.CertificateExpired
            )
        )
    }

    @Test
    fun `meterValues response format`() {
        validateObject(
            MeterValuesResp()
        )
    }

    @Test
    fun `transactionEvent response format`() {
        /* Required field only */
        validateObject(
            TransactionEventResp()
        )

        /* Every field */
        validateObject(
            TransactionEventResp(
                200.0,
                9,
                IdTokenInfoType(
                    AuthorizationStatusEnumType.Accepted,
                    Instant.parse("2022-02-15T00:00:00.000Z"),
                    9,
                    "fr",
                    listOf(2, 4),
                    "en",
                    IdTokenType(
                        "idToken",
                        IdTokenEnumType.Central,
                        listOf(AdditionalInfoType("addititionalIdToken", "type"))
                    ),
                    MessageContentType(MessageFormatEnumType.ASCII, "content", "language")
                ),
                MessageContentType(MessageFormatEnumType.ASCII, "content", "language")
            )
        )
    }

    @Test
    fun `statusNotification response format`() {
        validateObject(StatusNotificationResp())
    }

    @Test
    fun `dataTransfer response format`() {
        /* Required field only */
        validateObject(
            DataTransferResp(DataTransferStatusEnumType.Accepted)
        )

        /* Every field */
        validateObject(
            DataTransferResp(
                status = DataTransferStatusEnumType.Accepted,
                data = "data1",
                statusInfo = StatusInfoType("reasonCode", "additionalInfo")
            )
        )
    }

    @Test
    fun `bootNotification response format`() {
        /* Required field only */
        validateObject(
            BootNotificationResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                interval = 10,
                status = RegistrationStatusEnumType.Accepted
            )
        )

        validateObject(
            BootNotificationResp(
                currentTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                interval = 10,
                status = RegistrationStatusEnumType.Accepted,
                statusInfo = StatusInfoType("reasonCode", "additionalInfo")
            )
        )
    }

    @Test
    fun `changeAvailability response format`() {
        validateObject(
            ChangeAvailabilityResp(ChangeAvailabilityStatusEnumType.Accepted)
        )

        validateObject(
            ChangeAvailabilityResp(
                ChangeAvailabilityStatusEnumType.Accepted,
                StatusInfoType("reasonCode", "additionalInfo")
            )
        )
    }

    @Test
    fun `clearCache response format`() {
        validateObject(
            ClearCacheResp(ClearCacheStatusEnumType.Accepted)
        )

        validateObject(
            ClearCacheResp(
                ClearCacheStatusEnumType.Accepted,
                StatusInfoType("reasonCode", "additionalInfo")
            )
        )
    }

    @Test
    fun `unlockConnector response format`() {
        validateObject(
            UnlockConnectorResp(UnlockStatusEnumType.Unlocked)
        )

        validateObject(
            UnlockConnectorResp(
                UnlockStatusEnumType.Unlocked,
                StatusInfoType("reasonCode", "additionalInfo")
            )
        )
    }

    @Test
    fun `remoteStartTransaction response format`() {
        validateObject(
            RequestStartTransactionResp(RequestStartStopStatusEnumType.Accepted)
        )
    }

    @Test
    fun `requestStopTransaction response format`() {
        validateObject(
            RequestStopTransactionResp(RequestStartStopStatusEnumType.Accepted)
        )

        validateObject(
            RequestStopTransactionResp(
                RequestStartStopStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `getVariables response format`() {
        validateObject(
            GetVariablesResp(
                listOf(
                    GetVariableResultType(
                        attributeStatus = GetVariableStatusEnumType.NotSupportedAttributeType,
                        component = ComponentType("component"),
                        variable = VariableType("variable")
                    )
                )
            )
        )

        validateObject(
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
            )
        )
    }

    @Test
    fun `setVariables response format`() {
        validateObject(
            SetVariablesResp(
                listOf(
                    SetVariableResultType(
                        attributeStatus = SetVariableStatusEnumType.Accepted,
                        component = ComponentType("component"),
                        variable = VariableType("variable")
                    )
                )
            )
        )

        validateObject(
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
            )
        )
    }

    @Test
    fun `getReport response format`() {
        validateObject(
            GetReportResp(GenericDeviceModelStatusEnumType.Accepted)
        )

        validateObject(
            GetReportResp(
                GenericDeviceModelStatusEnumType.EmptyResultSet,
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `getBaseReport response format`() {
        validateObject(
            GetBaseReportResp(GenericDeviceModelStatusEnumType.Rejected)
        )

        validateObject(
            GetBaseReportResp(
                GenericDeviceModelStatusEnumType.Rejected,
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `notify report response format`() {
        validateObject(
            NotifyReportResp()
        )
    }

    @Test
    fun `setChargingProfile response format`() {
        validateObject(
            SetChargingProfileResp(ChargingProfileStatusEnumType.Accepted)
        )

        validateObject(
            SetChargingProfileResp(
                ChargingProfileStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `getLocalListVersion response format`() {
        validateObject(GetLocalListVersionResp(1))
    }

    @Test
    fun `firmwareStatusNotification response format`() {
        validateObject(
            FirmwareStatusNotificationResp()
        )
    }

    @Test
    fun `cancelReservation response format`() {
        validateObject(
            CancelReservationResp(CancelReservationStatusEnumType.Rejected)
        )
    }

    @Test
    fun `clearChargingProfile response format`() {
        validateObject(
            ClearChargingProfileResp(ClearChargingProfileEnumType.Accepted)
        )
    }

    @Test
    fun `getCompositeSchedule response format`() {
        validateObject(
            GetCompositeScheduleResp(GenericStatusEnumType.Accepted)
        )

        validateObject(
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
            )
        )
    }

    @Test
    fun `updateFirmware response format`() {
        validateObject(
            UpdateFirmwareResp(status = UpdateFirmwareStatusEnumType.Accepted)
        )

        validateObject(
            UpdateFirmwareResp(
                status = UpdateFirmwareStatusEnumType.Accepted,
                statusInfo = StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `sendLocalList response format`() {
        validateObject(SendLocalListResp(SendLocalListStatusEnumType.Accepted))

        validateObject(
            SendLocalListResp(
                SendLocalListStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `triggerMessage response format`() {
        validateObject(
            TriggerMessageResp(
                TriggerMessageStatusEnumType.Accepted
            )
        )

        validateObject(
            TriggerMessageResp(
                TriggerMessageStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `reserveNow response format`() {
        validateObject(
            ReserveNowResp(ReserveNowStatusEnumType.Accepted)
        )
    }

    @Test
    fun `notifyDisplayMessages response format`() {
        validateObject(
            NotifyDisplayMessagesResp()
        )
    }

    @Test
    fun `notifyEvent response format`() {
        validateObject(
            NotifyEventResp()
        )
    }

    @Test
    fun `getLog response format`() {
        validateObject(
            GetLogResp(LogStatusEnumType.Accepted)
        )

        validateObject(
            GetLogResp(
                status = LogStatusEnumType.Accepted,
                filename = "filename",
                statusInfo = StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `securityEventNotification response format`() {
        validateObject(
            SecurityEventNotificationResp()
        )
    }

    @Test
    fun `reservationStatusUpdate response format`() {
        validateObject(
            ReservationStatusUpdateResp()
        )
    }

    @Test
    fun `notifyMonitoringReport response format`() {
        validateObject(
            NotifyMonitoringReportResp()
        )
    }

    @Test
    fun `reset request format`() {
        validateObject(
            ResetReq(type = ResetEnumType.Immediate)
        )

        validateObject(
            ResetReq(type = ResetEnumType.OnIdle)
        )
    }

    @Test
    fun `logStatusNotification response format`() {
        validateObject(
            LogStatusNotificationResp()
        )
    }

    @Test
    fun `reset response format`() {
        validateObject(
            ResetResp(status = ResetStatusEnumType.Accepted)
        )

        validateObject(
            ResetResp(status = ResetStatusEnumType.Rejected, StatusInfoType("reasonCode", "additionalInfo"))
        )
    }

    @Test
    fun `publishFirmwareStatusNotification response format`() {
        validateObject(
            PublishFirmwareStatusNotificationResp()
        )
    }

    @Test
    fun `signCertificate response format`() {
        validateObject(
            SignCertificateResp(GenericStatusEnumType.Accepted)
        )
        validateObject(
            SignCertificateResp(
                GenericStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `clearDisplayMessage response format`() {
        validateObject(
            ClearDisplayMessageResp(ClearMessageStatusEnumType.Accepted)
        )
        validateObject(
            ClearDisplayMessageResp(
                ClearMessageStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
    }

    @Test
    fun `getDisplayMessages response format`() {
        // without optionnal parameters
        validateObject(
            GetDisplayMessagesResp(GetDisplayMessagesStatusEnumType.Accepted)
        )

        // with all parameters
        validateObject(
            GetDisplayMessagesResp(
                GetDisplayMessagesStatusEnumType.Accepted,
                StatusInfoType("reason", "info")
            )
        )
    }

    @Test
    fun `setMonitoringBase response format`() {
        validateObject(
            SetMonitoringBaseResp(
                GenericDeviceModelStatusEnumType.Accepted,
                StatusInfoType("reason", "info")
            )
        )
        validateObject(
            SetMonitoringBaseResp(
                GenericDeviceModelStatusEnumType.Accepted
            )
        )
    }

    @Test
    fun `setNetworkProfile response format`() {
        validateObject(
            SetNetworkProfileResp(
                SetNetworkProfileStatusEnumType.Accepted
            )
        )
        validateObject(
            SetNetworkProfileResp(
                SetNetworkProfileStatusEnumType.Accepted,
                StatusInfoType("reason", "info")
            )
        )
    }

    @Test
    fun `setMonitoringLevel response format`() {
        validateObject(
            SetMonitoringLevelResp(
                GenericStatusEnumType.Accepted,
                StatusInfoType("reason", "additionnal")
            )
        )
        validateObject(
            SetMonitoringLevelResp(
                GenericStatusEnumType.Accepted
            )
        )
    }

    @Test
    fun `getChargingProfiles response format`() {
        validateObject(
            GetChargingProfilesResp(
                GetChargingProfileStatusEnumType.NoProfiles,
                StatusInfoType("reason", "additionnal")
            )
        )
        validateObject(
            GetChargingProfilesResp(
                GetChargingProfileStatusEnumType.NoProfiles
            )
        )
    }

    @Test
    fun `getInstalledCertificateIds response format`() {
        validateObject(
            GetInstalledCertificateIdsResp(
                GetInstalledCertificateStatusEnumType.Accepted,
                listOf(
                    CertificateHashDataChainType(
                        GetCertificateIdUseEnumType.CSMSRootCertificate,
                        CertificateHashDataType(
                            HashAlgorithmEnumType.SHA512,
                            "issuerNameHash",
                            "issuerKeyHash",
                            "serialNumber"
                        ),
                        listOf(
                            CertificateHashDataType(
                                HashAlgorithmEnumType.SHA512,
                                "issuerNameHash",
                                "issuerKeyHash",
                                "serialNumber"
                            ),
                            CertificateHashDataType(
                                HashAlgorithmEnumType.SHA512, "issuerNameHash",
                                "issuerKeyHash",
                                "serialNumber"
                            )
                        )
                    )
                ),
                StatusInfoType("reason", "info")
            )
        )
        validateObject(
            GetInstalledCertificateIdsResp(
                GetInstalledCertificateStatusEnumType.Accepted
            )
        )
    }

    @Test
    fun `installCertificate response format`() {
        validateObject(
            InstallCertificateResp(
                InstallCertificateStatusEnumType.Accepted,
                StatusInfoType("reason", "info")
            )
        )
        validateObject(
            InstallCertificateResp(
                InstallCertificateStatusEnumType.Accepted
            )
        )
    }

    @Test
    fun `customerInformation response format`() {
        validateObject(
            CustomerInformationResp(
                CustomerInformationStatusEnumType.Accepted,
                StatusInfoType("reason", "code")
            )
        )
        validateObject(
            CustomerInformationResp(
                CustomerInformationStatusEnumType.Accepted
            )
        )
    }

    @Test
    fun `reportChargingProfiles response format`() {
        validateObject(
            ReportChargingProfilesResp()
        )
    }

    @Test
    fun `unpublishFirmware response format`() {
        validateObject(
            UnpublishFirmwareResp(UnpublishFirmwareStatusEnumType.Unpublished)
        )
    }

    @Test
    fun `publishFirmware response format`() {
        validateObject(
            PublishFirmwareResp(
                GenericStatusEnumType.Accepted,
                StatusInfoType("reason", "info")
            )
        )
        validateObject(
            PublishFirmwareResp(
                GenericStatusEnumType.Accepted
            )
        )
    }

    @Test
    fun `setVariableMonitoring response format`() {
        validateObject(
            SetVariableMonitoringResp(
                setMonitoringResult = listOf(
                    SetMonitoringResultType(
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
        )
        validateObject(
            SetVariableMonitoringResp(
                setMonitoringResult = listOf(
                    SetMonitoringResultType(
                        status = SetMonitoringStatusEnumType.Accepted,
                        type = MonitorEnumType.Delta,
                        severity = 3,
                        component = ComponentType("name"),
                        variable = VariableType("name")
                    )
                )
            )
        )
    }

    @Test
    fun `getTransactionStatus response format`() {
        validateObject(
            GetTransactionStatusResp(
                true,
                false
            )
        )
        validateObject(
            GetTransactionStatusResp(
                true
            )
        )
    }

    @Test
    fun `getTransactionStatus request format`() {
        validateObject(
            GetTransactionStatusReq(
                "id"
            )
        )
        validateObject(
            GetTransactionStatusReq()
        )
    }

    @Test
    fun `setDisplayMessage response format`() {
        validateObject(
            SetDisplayMessageResp(
                DisplayMessageStatusEnumType.Accepted,
                StatusInfoType("reason", "code")
            )
        )

        validateObject(
            SetDisplayMessageResp(
                DisplayMessageStatusEnumType.Accepted
            )
        )
    }

    @Test
    fun `costUpdated response format`() {
        validateObject(
            CostUpdatedResp()
        )
    }

    @Test
    fun `deleteCertificate response format`() {
        validateObject(
            DeleteCertificateResp(

                DeleteCertificateStatusEnumType.Accepted
            )
        )

        validateObject(
            DeleteCertificateResp(
                DeleteCertificateStatusEnumType.Accepted,
                StatusInfoType("reason", "info")
            )
        )
    }

    @Test
    fun `getMonitoringReport response format`() {
        validateObject(
            GetMonitoringReportResp(
                GenericDeviceModelStatusEnumType.Accepted,
                StatusInfoType("reason", "info")
            )
        )
        validateObject(
            GetMonitoringReportResp(
                GenericDeviceModelStatusEnumType.Accepted
            )
        )
    }

    @Test
    fun `clearVariableMonitoring response format`() {
        validateObject(
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
            )
        )
        validateObject(
            ClearVariableMonitoringResp(
                clearMonitoringResults =
                listOf(
                    ClearMonitoringResultType(
                        status = ClearMonitoringStatusEnumType.Accepted,
                        id = 1
                    )
                )
            )
        )
    }

    @Test
    fun `certificateSigned response format`() {
        validateObject(
            CertificateSignedResp(CertificateSignedStatusEnumType.Accepted)
        )
        validateObject(
            CertificateSignedResp(
                CertificateSignedStatusEnumType.Accepted,
                StatusInfoType("reason", "info")
            )
        )
    }

    inline fun <reified T : Any> validateObject(instance: T) {
        val instanceClass = instance::class.java.simpleName
        expectThat(
            parser.parseAnyFromJson<T>(
                parser.mapToJson(
                    JsonMessage(
                        msgType = JsonMessageType.CALL.takeIf { instanceClass.endsWith("Req") }
                            ?: JsonMessageType.CALL_RESULT,
                        msgId = "123456",
                        action = instanceClass.replace(Regex("Req$"), "").replace(Regex("Resp$"), ""),
                        payload = instance
                    )
                )
            )
        ).isA<JsonMessage<T>>().get { payload }.isA<T>()
    }
}
