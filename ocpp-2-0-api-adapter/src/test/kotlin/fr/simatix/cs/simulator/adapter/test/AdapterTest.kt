package fr.simatix.cs.simulator.adapter.test

import fr.simatix.cs.simulator.adapter20.Ocpp20Adapter
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.api.model.bootnotification.ModemType
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
import fr.simatix.cs.simulator.api.model.cleardisplaymessage.enumeration.ClearMessageStatusEnumType
import fr.simatix.cs.simulator.api.model.common.*
import fr.simatix.cs.simulator.api.model.common.enumeration.*
import fr.simatix.cs.simulator.api.model.customerinformation.CustomerInformationReq
import fr.simatix.cs.simulator.api.model.customerinformation.CustomerInformationResp
import fr.simatix.cs.simulator.api.model.customerinformation.enumeration.CustomerInformationStatusEnumType
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesReq
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesResp
import fr.simatix.cs.simulator.api.model.getallvariables.KeyValue
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportResp
import fr.simatix.cs.simulator.api.model.getchargingprofiles.GetChargingProfilesReq
import fr.simatix.cs.simulator.api.model.getchargingprofiles.GetChargingProfilesResp
import fr.simatix.cs.simulator.api.model.getchargingprofiles.enumeration.GetChargingProfileStatusEnumType
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.api.model.getlog.GetLogReq
import fr.simatix.cs.simulator.api.model.getlog.GetLogResp
import fr.simatix.cs.simulator.api.model.getlog.enumeration.LogStatusEnumType
import fr.simatix.cs.simulator.api.model.getreport.GetReportReq
import fr.simatix.cs.simulator.api.model.getreport.GetReportResp
import fr.simatix.cs.simulator.api.model.getvariables.GetVariableResultType
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.api.model.getvariables.enumeration.GetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.installcertificate.InstallCertificateReq
import fr.simatix.cs.simulator.api.model.installcertificate.InstallCertificateResp
import fr.simatix.cs.simulator.api.model.installcertificate.enumeration.InstallCertificateStatusEnumType
import fr.simatix.cs.simulator.api.model.installcertificate.enumeration.InstallCertificateUseEnumType
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.publishfirmware.PublishFirmwareReq
import fr.simatix.cs.simulator.api.model.publishfirmware.PublishFirmwareResp
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.core20.model.reportchargingprofiles.ReportChargingProfilesReq
import fr.simatix.cs.simulator.core20.model.reportchargingprofiles.ReportChargingProfilesResp
import fr.simatix.cs.simulator.api.model.reportchargingprofiles.ReportChargingProfilesReq as ReportChargingProfilesReqGen
import fr.simatix.cs.simulator.api.model.reportchargingprofiles.ReportChargingProfilesResp as ReportChargingProfilesRespGen
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
import fr.simatix.cs.simulator.api.model.setvariablemonitoring.SetMonitoringResultType
import fr.simatix.cs.simulator.api.model.setvariablemonitoring.SetVariableMonitoringReq
import fr.simatix.cs.simulator.api.model.setvariablemonitoring.SetVariableMonitoringResp
import fr.simatix.cs.simulator.api.model.setmonitoringlevel.SetMonitoringLevelReq
import fr.simatix.cs.simulator.api.model.setmonitoringlevel.SetMonitoringLevelResp
import fr.simatix.cs.simulator.api.model.setnetworkprofile.SetNetworkProfileReq
import fr.simatix.cs.simulator.api.model.setnetworkprofile.SetNetworkProfileResp
import fr.simatix.cs.simulator.api.model.setnetworkprofile.enumeration.SetNetworkProfileStatusEnumType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariableResultType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.api.model.setvariables.enumeration.SetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.api.model.triggermessage.TriggerMessageResp
import fr.simatix.cs.simulator.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import fr.simatix.cs.simulator.api.model.unpublishfirmware.UnpublishFirmwareReq
import fr.simatix.cs.simulator.api.model.unpublishfirmware.UnpublishFirmwareResp
import fr.simatix.cs.simulator.api.model.unpublishfirmware.enumeration.UnpublishFirmwareStatusEnumType
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareResp
import fr.simatix.cs.simulator.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core20.model.common.enumeration.HashAlgorithmEnumType
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core20.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core20.model.bootnotification.ChargingStationType
import fr.simatix.cs.simulator.core20.model.bootnotification.enumeration.BootReasonEnumType
import fr.simatix.cs.simulator.core20.model.bootnotification.enumeration.RegistrationStatusEnumType
import fr.simatix.cs.simulator.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import fr.simatix.cs.simulator.core20.model.clearedcharginglimit.ClearedChargingLimitResp
import fr.simatix.cs.simulator.api.model.common.CertificateHashDataType
import fr.simatix.cs.simulator.core20.model.common.ChargingProfileType
import fr.simatix.cs.simulator.api.model.common.ChargingProfileType as ChargingProfileTypeGen
import fr.simatix.cs.simulator.core20.model.common.ChargingSchedulePeriodType
import fr.simatix.cs.simulator.core20.model.common.ChargingScheduleType
import fr.simatix.cs.simulator.core20.model.common.EVSEType
import fr.simatix.cs.simulator.core20.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.core20.model.common.IdTokenType
import fr.simatix.cs.simulator.core20.model.common.MessageContentType
import fr.simatix.cs.simulator.core20.model.common.OCSPRequestDataType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.AuthorizationStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingLimitSourceEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingRateUnitEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.GenericStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.IdTokenEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.MessageFormatEnumType
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core20.model.datatransfer.enumeration.DataTransferStatusEnumType
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import fr.simatix.cs.simulator.core20.model.getcertificatestatus.GetCertificateStatusReq
import fr.simatix.cs.simulator.core20.model.getcertificatestatus.GetCertificateStatusResp
import fr.simatix.cs.simulator.core20.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core20.model.logstatusnotification.LogStatusNotificationReq
import fr.simatix.cs.simulator.core20.model.logstatusnotification.LogStatusNotificationResp
import fr.simatix.cs.simulator.core20.model.logstatusnotification.enumeration.UploadLogStatusEnumType
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core20.model.notifycharginglimit.ChargingLimitType
import fr.simatix.cs.simulator.core20.model.notifycharginglimit.NotifyChargingLimitReq
import fr.simatix.cs.simulator.core20.model.notifycharginglimit.NotifyChargingLimitResp
import fr.simatix.cs.simulator.core20.model.notifycustomerinformation.NotifyCustomerInformationReq
import fr.simatix.cs.simulator.core20.model.notifycustomerinformation.NotifyCustomerInformationResp
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.MessageInfoType
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.enumeration.MessagePriorityEnumType
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.enumeration.MessageStateEnumType
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.ChargingNeedsType
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType
import fr.simatix.cs.simulator.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import fr.simatix.cs.simulator.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import fr.simatix.cs.simulator.core20.model.notifymonitoringreport.MonitoringDataType
import fr.simatix.cs.simulator.core20.model.notifymonitoringreport.NotifyMonitoringReportReq
import fr.simatix.cs.simulator.core20.model.notifymonitoringreport.NotifyMonitoringReportResp
import fr.simatix.cs.simulator.core20.model.notifymonitoringreport.VariableMonitoringType
import fr.simatix.cs.simulator.core20.model.common.enumeration.MonitorEnumType
import fr.simatix.cs.simulator.core20.model.notifyreport.NotifyReportReq
import fr.simatix.cs.simulator.core20.model.notifyreport.NotifyReportResp
import fr.simatix.cs.simulator.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import fr.simatix.cs.simulator.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationResp
import fr.simatix.cs.simulator.core20.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType
import fr.simatix.cs.simulator.core20.model.reservationstatusupdate.ReservationStatusUpdateReq
import fr.simatix.cs.simulator.core20.model.reservationstatusupdate.ReservationStatusUpdateResp
import fr.simatix.cs.simulator.core20.model.reservationstatusupdate.enumeration.ReservationUpdateStatusEnumType
import fr.simatix.cs.simulator.core20.model.securityeventnotification.SecurityEventNotificationReq
import fr.simatix.cs.simulator.core20.model.securityeventnotification.SecurityEventNotificationResp
import fr.simatix.cs.simulator.core20.model.signcertificate.SignCertificateReq
import fr.simatix.cs.simulator.core20.model.signcertificate.SignCertificateResp
import fr.simatix.cs.simulator.core20.model.common.enumeration.CertificateSigningUseEnumType
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.CertificateHashDataChainType
import fr.simatix.cs.simulator.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.ChargingProfileKindEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingProfilePurposeEnumType as ChargingProfilePurposeEnumTypeGen
import fr.simatix.cs.simulator.api.model.remotestart.enumeration.ChargingProfileKindEnumType as ChargingProfileKindEnumTypeGen
import fr.simatix.cs.simulator.api.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.CertificateSigningUseEnumType as CertificateSigningUseEnumTypeGen
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
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq as AuthorizeReqGen
import fr.simatix.cs.simulator.api.model.common.enumeration.HashAlgorithmEnumType as HashAlgorithmEnumTypeGen
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq as BootNotificationReqGen
import fr.simatix.cs.simulator.api.model.bootnotification.ChargingStationType as ChargingStationTypeGen
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.BootReasonEnumType as BootReasonEnumTypeGen
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.RegistrationStatusEnumType as RegistrationStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.clearedcharginglimit.ClearedChargingLimitReq as ClearedChargingLimitReqGen
import fr.simatix.cs.simulator.api.model.common.ChargingSchedulePeriodType as ChargingSchedulePeriodTypeGen
import fr.simatix.cs.simulator.api.model.common.ChargingScheduleType as ChargingScheduleTypeGen
import fr.simatix.cs.simulator.api.model.common.EVSEType as EVSETypeGen
import fr.simatix.cs.simulator.api.model.common.IdTokenInfoType as IdTokenInfoTypeGen
import fr.simatix.cs.simulator.api.model.common.IdTokenType as IdTokenTypeGen
import fr.simatix.cs.simulator.api.model.common.MessageContentType as MessageContentTypeGen
import fr.simatix.cs.simulator.api.model.common.OCSPRequestDataType as OCSPRequestDataTypeGen
import fr.simatix.cs.simulator.api.model.common.StatusInfoType as StatusInfoTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.AuthorizationStatusEnumType as AuthorizationStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingLimitSourceEnumType as ChargingLimitSourceEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingRateUnitEnumType as ChargingRateUnitEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.GenericStatusEnumType as GenericStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.IdTokenEnumType as IdTokenEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.MessageFormatEnumType as MessageFormatEnumTypeGen
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferResp as DataTransferRespGen
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType as FirmwareStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusReq as GetCertificateStatusReqGen
import fr.simatix.cs.simulator.api.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType as GetCertificateStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq as HeartbeatReqGen
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationReq as LogStatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType as UploadLogStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifycharginglimit.ChargingLimitType as ChargingLimitTypeGen
import fr.simatix.cs.simulator.api.model.notifycharginglimit.NotifyChargingLimitReq as NotifyChargingLimitReqGen
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationReq as NotifyCustomerInformationReqGen
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.MessageInfoType as MessageInfoTypeGen
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.NotifyDisplayMessagesReq as NotifyDisplayMessagesReqGen
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.enumeration.MessagePriorityEnumType as MessagePriorityEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.enumeration.MessageStateEnumType as MessageStateEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.ChargingNeedsType as ChargingNeedsTypeGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.DCChargingParametersType as DCChargingParametersTypeGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq as NotifyEVChargingNeedsReqGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType as EnergyTransferModeEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType as NotifyEVChargingNeedsStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifyevchargingschedule.NotifyEVChargingScheduleReq as NotifyEVChargingScheduleReqGen
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.MonitoringDataType as MonitoringDataTypeGen
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.NotifyMonitoringReportReq as NotifyMonitoringReportReqGen
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.VariableMonitoringType as VariableMonitoringTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.MonitorEnumType as MonitorEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifyreport.NotifyReportReq as NotifyReportReqGen
import fr.simatix.cs.simulator.api.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq as PublishFirmwareStatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType as PublishFirmwareStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.reservationstatusupdate.ReservationStatusUpdateReq as ReservationStatusUpdateReqGen
import fr.simatix.cs.simulator.api.model.reservationstatusupdate.enumeration.ReservationUpdateStatusEnumType as ReservationUpdateStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.securityeventnotification.SecurityEventNotificationReq as SecurityEventNotificationReqGen
import fr.simatix.cs.simulator.api.model.signcertificate.SignCertificateReq as SignCertificateReqGen
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq as StatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ConnectorStatusEnumType as ConnectorStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq as TransactionEventReqGen
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionType as TransactionTypeGen
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TransactionEventEnumType as TransactionEventEnumTypeGen
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TriggerReasonEnumType as TriggerReasonEnumTypeGen

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
                statusInfo = StatusInfoTypeGen("reason", "additional")
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun triggerMessage(
            meta: RequestMetadata,
            req: TriggerMessageReq
        ): OperationExecution<TriggerMessageReq, TriggerMessageResp> {
            val response = TriggerMessageResp(
                status = TriggerMessageStatusEnumType.Accepted,
                statusInfo = StatusInfoTypeGen("reason", "additional")
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
        ): OperationExecution<DataTransferReq, DataTransferRespGen> {
            val response = DataTransferRespGen(
                status = fr.simatix.cs.simulator.api.model.datatransfer.enumeration.DataTransferStatusEnumType.Accepted,
                data = "Some data",
                statusInfo = fr.simatix.cs.simulator.api.model.common.StatusInfoType(
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
            val response = GetCompositeScheduleResp(GenericStatusEnumTypeGen.Accepted)
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
            TODO("Not yet implemented")
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
            val response = PublishFirmwareResp(GenericStatusEnumTypeGen.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun getChargingProfiles(
                meta: RequestMetadata,
                req: GetChargingProfilesReq
        ): OperationExecution<GetChargingProfilesReq, GetChargingProfilesResp> {
            val response = GetChargingProfilesResp(
                    GetChargingProfileStatusEnumType.Accepted,
            );
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun installCertificate(
                meta: RequestMetadata,
                req: InstallCertificateReq
        ): OperationExecution<InstallCertificateReq, InstallCertificateResp> {
            val response = InstallCertificateResp(
                InstallCertificateStatusEnumType.Accepted,
                StatusInfoTypeGen("reason","info")
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun customerInformation(
                meta: RequestMetadata,
                req: CustomerInformationReq
        ): OperationExecution<CustomerInformationReq, CustomerInformationResp> {
            val response = CustomerInformationResp(
                    CustomerInformationStatusEnumType.Accepted
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
                            CertificateHashDataChainType(GetCertificateIdUseEnumType.CSMSRootCertificate, CertificateHashDataType(HashAlgorithmEnumTypeGen.SHA512,"","",""),listOf(CertificateHashDataType(HashAlgorithmEnumTypeGen.SHA512,"","",""), CertificateHashDataType(HashAlgorithmEnumTypeGen.SHA512,"","",""))),
                    ),
                    StatusInfoTypeGen("reason","info")
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
                    SetMonitoringResultType(
                            type= MonitorEnumTypeGen.Delta,
                            id=213,
                            component = ComponentType("name"),
                            variable=VariableType("name"),
                            statusInfo=StatusInfoTypeGen("reason","info"),
                            severity=3,
                            status = SetMonitoringStatusEnumType.Accepted,
                    )
                )
            )
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun setMonitoringLevel(
                meta: RequestMetadata,
                req: SetMonitoringLevelReq
        ): OperationExecution<SetMonitoringLevelReq, SetMonitoringLevelResp> {
            val response = SetMonitoringLevelResp(GenericStatusEnumTypeGen.Accepted)
            return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
        }

        override fun setNetworkProfile(
                meta: RequestMetadata,
                req: SetNetworkProfileReq
        ): OperationExecution<SetNetworkProfileReq, SetNetworkProfileResp> {
            val response = SetNetworkProfileResp(
                    SetNetworkProfileStatusEnumType.Accepted
            )
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
        val request = NotifyReportReqGen(
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
        val request = FirmwareStatusNotificationReqGen(
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

    @Test
    fun `getCertificateStatus request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.getCertificateStatus(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            GetCertificateStatusReq(OCSPRequestDataType(HashAlgorithmEnumType.SHA256, "", "", "", "")),
            GetCertificateStatusResp(
                GetCertificateStatusEnumType.Accepted,
                "",
                StatusInfoType("reason", "additional")
            )
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request =
            GetCertificateStatusReqGen(OCSPRequestDataTypeGen(HashAlgorithmEnumTypeGen.SHA256, "", "", "", ""))
        val response = operations.getCertificateStatus(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
            .and { get { this.response.status }.isEqualTo(GetCertificateStatusEnumTypeGen.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(StatusInfoTypeGen("reason", "additional")) }
            .and { get { this.response.ocspResult }.isEqualTo("") }
    }

    @Test
    fun `notifyCustomerInformation request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.notifyCustomerInformation(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            NotifyCustomerInformationReq(
                data = "Some data",
                tbc = true,
                seqNo = 0,
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                requestId = 1
            ),
            NotifyCustomerInformationResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = NotifyCustomerInformationReqGen(
            data = "Some data",
            tbc = true,
            seqNo = 0,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            requestId = 1
        )
        val response = operations.notifyCustomerInformation(requestMetadata, request)
        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
    }

    @Test
    fun `notifyEVChargingSchedule request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.notifyEVChargingSchedule(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
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
            NotifyEVChargingScheduleResp(
                status = GenericStatusEnumType.Accepted,
                statusInfo = StatusInfoType("123")
            )
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = NotifyEVChargingScheduleReqGen(
            timeBase = Instant.parse("2022-02-15T00:00:00.000Z"),
            evseId = 123,
            chargingSchedule = ChargingScheduleTypeGen(
                chargingRateUnit = ChargingRateUnitEnumTypeGen.A,
                chargingSchedulePeriod = listOf(
                    ChargingSchedulePeriodTypeGen(
                        startPeriod = 0,
                        limit = 1.0
                    )
                )
            )
        )
        val response = operations.notifyEVChargingSchedule(requestMetadata, request)
        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            get { this.response.status }.isEqualTo(GenericStatusEnumTypeGen.Accepted)
            get { this.response.statusInfo }.isEqualTo(StatusInfoTypeGen("123"))
        }
    }

    @Test
    fun `notifyChargingLimit request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.notifyChargingLimit(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            NotifyChargingLimitReq(ChargingLimitType(ChargingLimitSourceEnumType.CSO, true)),
            NotifyChargingLimitResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = NotifyChargingLimitReqGen(
            chargingLimit = ChargingLimitTypeGen(ChargingLimitSourceEnumTypeGen.CSO, true),
            evseId = 1
        )
        val response = operations.notifyChargingLimit(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `notifyDisplayMessages request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.notifyDisplayMessages(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
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
                        display = fr.simatix.cs.simulator.core20.model.common.ComponentType(
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
            NotifyDisplayMessagesResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = NotifyDisplayMessagesReqGen(
            requestId = 1,
            tbc = false,
            messageInfo = listOf(
                MessageInfoTypeGen(
                    id = 2,
                    priority = MessagePriorityEnumTypeGen.InFront,
                    state = MessageStateEnumTypeGen.Charging,
                    startDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                    endDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                    transactionId = "2",
                    message = MessageContentTypeGen(
                        format = MessageFormatEnumTypeGen.URI,
                        language = "language",
                        content = "Message content"
                    ),
                    display = ComponentType(
                        name = "name",
                        instance = "instance",
                        evse = EVSETypeGen(
                            id = 1,
                            connectorId = 2
                        )
                    )
                )
            )
        )
        val response = operations.notifyDisplayMessages(requestMetadata, request)
        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
    }

    @Test
    fun `notifyEVChargingNeeds request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.notifyEVChargingNeeds(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            NotifyEVChargingNeedsReq(1, ChargingNeedsType(EnergyTransferModeEnumType.DC)),
            NotifyEVChargingNeedsResp(
                NotifyEVChargingNeedsStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)

        var request = NotifyEVChargingNeedsReqGen(1, ChargingNeedsTypeGen(EnergyTransferModeEnumTypeGen.DC))
        var response = operations.notifyEVChargingNeeds(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
            .and { get { this.response.status }.isEqualTo(NotifyEVChargingNeedsStatusEnumTypeGen.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(StatusInfoTypeGen("reason", "additional")) }

        request = NotifyEVChargingNeedsReqGen(
            1,
            ChargingNeedsTypeGen(
                EnergyTransferModeEnumTypeGen.DC,
                dcChargingParameters = DCChargingParametersTypeGen(
                    evMaxCurrent = 1,
                    evMaxVoltage = 1,
                    stateOfCharge = 5,
                    fullSoC = 5
                )
            )
        )
        response = operations.notifyEVChargingNeeds(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
            .and { get { this.response.status }.isEqualTo(NotifyEVChargingNeedsStatusEnumTypeGen.Accepted) }
            .and { get { this.response.statusInfo }.isEqualTo(StatusInfoTypeGen("reason", "additional")) }

        request = NotifyEVChargingNeedsReqGen(
            1,
            ChargingNeedsTypeGen(
                EnergyTransferModeEnumTypeGen.DC,
                dcChargingParameters = DCChargingParametersTypeGen(
                    evMaxCurrent = 1,
                    evMaxVoltage = 1,
                    stateOfCharge = -5,
                    fullSoC = 5
                )
            )
        )
        expectThrows<IllegalStateException> { operations.notifyEVChargingNeeds(requestMetadata, request) }
    }

    @Test
    fun `logStatusNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.logStatusNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            LogStatusNotificationReq(
                status = UploadLogStatusEnumType.Uploaded,
                requestId = 1
            ),
            LogStatusNotificationResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request =  LogStatusNotificationReqGen(
            status = UploadLogStatusEnumTypeGen.Uploaded,
            requestId = 1
        )
        val response = operations.logStatusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }
    }

    @Test
    fun `publishFirmwareStatusNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.publishFirmwareStatusNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            PublishFirmwareStatusNotificationReq(
                status = PublishFirmwareStatusEnumType.Published,
                location = listOf("location"),
                requestId = 1
            ),
            PublishFirmwareStatusNotificationResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        var request =  PublishFirmwareStatusNotificationReqGen(
            status = PublishFirmwareStatusEnumTypeGen.DownloadFailed,
            requestId = 1
        )
        val response = operations.publishFirmwareStatusNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and {
                get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
            }


        request =  PublishFirmwareStatusNotificationReqGen(
            status = PublishFirmwareStatusEnumTypeGen.Published,
            requestId = 1
        )
        expectThrows<IllegalStateException> { operations.publishFirmwareStatusNotification(requestMetadata, request) }
    }

    @Test
    fun `notifyMonitoringReport request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.notifyMonitoringReport(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            NotifyMonitoringReportReq(
                requestId = 1,
                generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                seqNo = 2,
                tbc = true,
                monitor = listOf(
                    MonitoringDataType(
                        component = fr.simatix.cs.simulator.core20.model.common.ComponentType("component"),
                        variable = fr.simatix.cs.simulator.core20.model.common.VariableType("variable"),
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
            NotifyMonitoringReportResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        var request = NotifyMonitoringReportReqGen(
            requestId = 1,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            seqNo = 2,
            tbc = true,
            monitor = listOf(
                MonitoringDataTypeGen(
                    component = ComponentType("component"),
                    variable = VariableType("variable"),
                    variableMonitoring = listOf(
                        VariableMonitoringTypeGen(
                            id = 3,
                            transaction = true,
                            value = 10.0,
                            type = MonitorEnumTypeGen.Periodic,
                            severity = 3
                        )
                    )
                )
            )
        )
        var response = operations.notifyMonitoringReport(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }

        request = NotifyMonitoringReportReqGen(
            requestId = 1,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            seqNo = 2
        )
        response = operations.notifyMonitoringReport(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }

        request = NotifyMonitoringReportReqGen(
            requestId = 1,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            seqNo = 2,
            tbc = true,
            monitor = listOf(
                MonitoringDataTypeGen(
                    component = ComponentType("component"),
                    variable = VariableType("variable"),
                    variableMonitoring = listOf(
                        VariableMonitoringTypeGen(
                            id = 3,
                            transaction = true,
                            value = 10.0,
                            type = MonitorEnumTypeGen.Periodic,
                            severity = 10
                        )
                    )
                )
            )
        )
        expectThrows<IllegalStateException> { operations.notifyMonitoringReport(requestMetadata, request) }

        request = NotifyMonitoringReportReqGen(
            requestId = 1,
            generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
            seqNo = 2,
            tbc = true,
            monitor = listOf(
                MonitoringDataTypeGen(
                    component = ComponentType("component"),
                    variable = VariableType("variable"),
                    variableMonitoring = listOf(
                        VariableMonitoringTypeGen(
                            id = 3,
                            transaction = true,
                            value = 10.0,
                            type = MonitorEnumTypeGen.Periodic,
                            severity = 3
                        ),
                        VariableMonitoringTypeGen(
                            id = 3,
                            transaction = true,
                            value = 10.0,
                            type = MonitorEnumTypeGen.Periodic,
                            severity = 12
                        )
                    )
                )
            )
        )
        expectThrows<IllegalStateException> { operations.notifyMonitoringReport(requestMetadata, request) }
    }

    @Test
    fun `reservationStatusUpdate request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.reservationStatusUpdate(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            ReservationStatusUpdateReq(1, ReservationUpdateStatusEnumType.Expired),
            ReservationStatusUpdateResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = ReservationStatusUpdateReqGen(
            reservationId = 1,
            reservationUpdateStatus = ReservationUpdateStatusEnumTypeGen.Expired
        )
        val response = operations.reservationStatusUpdate(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `securityEventNotification request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.securityEventNotification(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            SecurityEventNotificationReq(
                type = "type",
                timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                techInfo = "techInfo"
            ),
            SecurityEventNotificationResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = SecurityEventNotificationReqGen(
            type = "type",
            timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
            techInfo = "techInfo"
        )
        val response = operations.securityEventNotification(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `signCertificate request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.signCertificate(any(), any()) } returns OperationExecution(
            ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
            SignCertificateReq(
                csr = "csr",
                certificateType = CertificateSigningUseEnumType.V2GCertificate
            ),
            SignCertificateResp(GenericStatusEnumType.Accepted, StatusInfoType("reason", "additional"))
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = SignCertificateReqGen(
            csr = "csr",
            certificateType = CertificateSigningUseEnumTypeGen.V2GCertificate
        )
        val response = operations.signCertificate(requestMetadata, request)
        expectThat(response)
            .and { get { this.request }.isEqualTo(request) }
            .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }

    @Test
    fun `reportChargingProfiles request`() {
        val requestMetadata = RequestMetadata("")
        every { chargePointOperations.reportChargingProfiles(any(), any()) } returns OperationExecution(
                ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS, Clock.System.now(), Clock.System.now()),
                ReportChargingProfilesReq(
                        2,
                        ChargingLimitSourceEnumType.CSO,
                        2,
                        listOf(
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
                        ),
                    false
                ),
                ReportChargingProfilesResp()
        )

        val operations = Ocpp20Adapter("c1", transport, csApi)
        val request = ReportChargingProfilesReqGen(
                2,
                ChargingLimitSourceEnumTypeGen.CSO,
                2,
                listOf(
                        ChargingProfileTypeGen(
                                id = 1,
                                stackLevel = 1,
                                chargingProfilePurpose = ChargingProfilePurposeEnumTypeGen.ChargingStationMaxProfile,
                                chargingProfileKind = ChargingProfileKindEnumTypeGen.Absolute,
                                chargingSchedule = listOf(
                                        ChargingScheduleTypeGen(
                                                id = 1,
                                                chargingRateUnit = ChargingRateUnitEnumTypeGen.A,
                                                chargingSchedulePeriod = listOf(ChargingSchedulePeriodTypeGen(1, 1.0)),
                                                startSchedule = Instant.parse("2022-02-15T00:00:00.000Z")
                                        )
                                )
                        )
                ),
                false
        )
        val response = operations.reportChargingProfiles(requestMetadata, request)
        expectThat(response)
                .and { get { this.request }.isEqualTo(request) }
                .and { get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS) }
    }
}