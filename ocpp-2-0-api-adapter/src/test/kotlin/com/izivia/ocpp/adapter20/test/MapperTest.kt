package com.izivia.ocpp.adapter.test

import com.izivia.ocpp.adapter20.mapper.*
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.api.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.api.model.cleardisplaymessage.ClearDisplayMessageResp
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.api.model.deletecertificate.enumerations.DeleteCertificateStatusEnumType as DeleteCertificateStatusEnumTypeGen
import com.izivia.ocpp.api.model.customerinformation.CustomerInformationResp
import com.izivia.ocpp.api.model.getbasereport.GetBaseReportResp
import com.izivia.ocpp.api.model.getchargingprofiles.GetChargingProfilesResp
import com.izivia.ocpp.api.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.api.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.api.model.getdisplaymessages.enumeration.GetDisplayMessagesStatusEnumType as GetDisplayMessagesStatusEnumTypeGen
import com.izivia.ocpp.api.model.getinstalledcertificateids.CertificateHashDataChainType
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.api.model.getlog.GetLogResp
import com.izivia.ocpp.api.model.getmonitoringreport.GetMonitoringReportResp
import com.izivia.ocpp.api.model.getreport.GetReportResp
import com.izivia.ocpp.api.model.gettransactionstatus.GetTransactionStatusResp
import com.izivia.ocpp.api.model.getvariables.GetVariablesResp
import com.izivia.ocpp.api.model.common.MessageInfoType as MessageInfoTypeGen
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesReq as NotifyDisplayMessagesReqGen
import com.izivia.ocpp.core20.model.common.enumeration.MessagePriorityEnumType
import com.izivia.ocpp.api.model.common.enumeration.MessagePriorityEnumType as MessagePriorityEnumTypeGen
import com.izivia.ocpp.core20.model.common.enumeration.MessageStateEnumType
import com.izivia.ocpp.api.model.common.enumeration.MessageStateEnumType as MessageStateEnumTypeGen
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationReq as LogStatusNotificationReqGen
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationResp as LogStatusNotificationRespGen
import com.izivia.ocpp.core20.model.logstatusnotification.enumeration.UploadLogStatusEnumType
import com.izivia.ocpp.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType as UploadLogStatusEnumTypeGen
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.api.model.notifymonitoringreport.NotifyMonitoringReportReq
import com.izivia.ocpp.api.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.api.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.api.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.api.model.setnetworkprofile.enumeration.VPNEnumType as VPNEnumTypeGen
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.api.model.setdisplaymessage.SetDisplayMessageResp
import com.izivia.ocpp.api.model.setdisplaymessage.enumeration.DisplayMessageStatusEnumType as DisplayMessageStatusEnumTypeGen
import com.izivia.ocpp.api.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.api.model.setvariablemonitoring.SetVariableMonitoringResp
import com.izivia.ocpp.api.model.setnetworkprofile.SetNetworkProfileResp
import com.izivia.ocpp.api.model.setnetworkprofile.enumeration.SetNetworkProfileStatusEnumType as SetNetworkProfileStatusEnumTypeGen
import com.izivia.ocpp.api.model.setvariables.SetVariableResultType
import com.izivia.ocpp.api.model.setvariables.SetVariablesResp
import com.izivia.ocpp.api.model.setnetworkprofile.enumeration.APNAuthenticationEnumType as APNAuthenticationEnumTypeGen
import com.izivia.ocpp.api.model.setnetworkprofile.enumeration.OCPPVersionEnumType as OCPPVersionEnumTypeGen
import com.izivia.ocpp.api.model.setnetworkprofile.enumeration.OCPPTransportEnumType as OCPPTransportEnumTypeGen
import com.izivia.ocpp.api.model.setnetworkprofile.enumeration.OCPPInterfaceEnumType as OCPPInterfaceEnumTypeGen
import com.izivia.ocpp.api.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.api.model.unpublishfirmware.UnpublishFirmwareResp
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core20.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedReq
import com.izivia.ocpp.core20.model.certificateSigned.enumeration.CertificateSignedStatusEnumType
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core20.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import com.izivia.ocpp.core20.model.changeavailability.enumeration.OperationalStatusEnumType
import com.izivia.ocpp.core20.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core20.model.clearcache.enumeration.ClearCacheStatusEnumType
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileType
import com.izivia.ocpp.core20.model.clearchargingprofile.enumeration.ClearChargingProfileEnumType
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.core20.model.cleardisplaymessage.enumeration.ClearMessageStatusEnumType
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringResp
import com.izivia.ocpp.core20.model.common.*
import com.izivia.ocpp.core20.model.common.enumeration.*
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationReq
import com.izivia.ocpp.core20.model.customerinformation.enumeration.CustomerInformationStatusEnumType
import com.izivia.ocpp.core20.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core20.model.common.enumeration.ChargingProfilePurposeEnumType
import com.izivia.ocpp.core20.model.common.enumeration.MessageFormatEnumType
import com.izivia.ocpp.core20.model.common.enumeration.CertificateSigningUseEnumType
import com.izivia.ocpp.core20.model.common.enumeration.GenericStatusEnumType
import com.izivia.ocpp.core20.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core20.model.datatransfer.enumeration.DataTransferStatusEnumType
import com.izivia.ocpp.core20.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.core20.model.getbasereport.enumeration.ReportBaseEnumType
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusResp
import com.izivia.ocpp.core20.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesReq
import com.izivia.ocpp.core20.model.getchargingprofiles.enumeration.GetChargingProfileStatusEnumType
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.core20.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType
import com.izivia.ocpp.core20.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core20.model.getlog.GetLogReq
import com.izivia.ocpp.core20.model.getlog.LogParametersType
import com.izivia.ocpp.core20.model.getlog.enumeration.LogEnumType
import com.izivia.ocpp.core20.model.getlog.enumeration.LogStatusEnumType
import com.izivia.ocpp.core20.model.common.ComponentVariableType
import com.izivia.ocpp.core20.model.getreport.GetReportReq
import com.izivia.ocpp.core20.model.getreport.enumeration.ComponentCriterionEnumType
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusReq
import com.izivia.ocpp.core20.model.getvariables.GetVariableDataType
import com.izivia.ocpp.core20.model.getvariables.GetVariablesReq
import com.izivia.ocpp.core20.model.getvariables.enumeration.GetVariableStatusEnumType
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitResp
import com.izivia.ocpp.core20.model.remotestart.enumeration.ChargingProfileKindEnumType
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq as DataTransferReqGen
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core20.model.installcertificate.enumeration.InstallCertificateStatusEnumType
import com.izivia.ocpp.core20.model.installcertificate.enumeration.InstallCertificateUseEnumType
import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationResp
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
import com.izivia.ocpp.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import com.izivia.ocpp.core20.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType
import com.izivia.ocpp.core20.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventResp
import com.izivia.ocpp.core20.model.notifyevent.enumeration.EventNotificationEnumType
import com.izivia.ocpp.core20.model.notifyevent.enumeration.EventTriggerEnumType
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportResp
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationResp
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.core20.model.remotestart.SalesTariffType
import com.izivia.ocpp.core20.model.remotestart.enumeration.RecurrencyKindEnumType
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesResp
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateResp
import com.izivia.ocpp.core20.model.reservationstatusupdate.enumeration.ReservationUpdateStatusEnumType
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.core20.model.sendlocallist.AuthorizationData
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core20.model.sendlocallist.enumeration.SendLocalListStatusEnumType
import com.izivia.ocpp.core20.model.sendlocallist.enumeration.UpdateEnumType
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core20.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetMonitoringDataType
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.core20.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType
import com.izivia.ocpp.core20.model.setvariables.SetVariableDataType
import com.izivia.ocpp.core20.model.setvariables.SetVariablesReq
import com.izivia.ocpp.core20.model.setvariables.enumeration.SetVariableStatusEnumType
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.api.model.setmonitoringbase.SetMonitoringBaseResp
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.core20.model.setmonitoringbase.enumeration.MonitoringBaseEnumType
import com.izivia.ocpp.api.model.setmonitoringbase.enumeration.MonitoringBaseEnumType as MonitoringBaseEnumTypeGen
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.core20.model.setnetworkprofile.APNType
import com.izivia.ocpp.core20.model.setnetworkprofile.NetworkConnectionProfileType
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileReq
import com.izivia.ocpp.core20.model.setnetworkprofile.VPNType
import com.izivia.ocpp.core20.model.setnetworkprofile.enumeration.*
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.core20.model.getdisplaymessages.enumeration.GetDisplayMessagesStatusEnumType
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.core20.model.deletecertificate.enumeration.DeleteCertificateStatusEnumType
import com.izivia.ocpp.core20.model.common.MessageInfoType
import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageReq
import com.izivia.ocpp.api.model.setdisplaymessage.SetDisplayMessageReq as SetDisplayMessageReqGen
import com.izivia.ocpp.core20.model.setdisplaymessage.enumeration.DisplayMessageStatusEnumType
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportReq
import com.izivia.ocpp.core20.model.getmonitoringreport.enumeration.MonitoringCriterionEnumType
import com.izivia.ocpp.api.model.getmonitoringreport.enumeration.MonitoringCriterionEnumType as MonitoringCriterionEnumTypeGen
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core20.model.triggermessage.enumeration.MessageTriggerEnumType
import com.izivia.ocpp.core20.model.triggermessage.enumeration.TriggerMessageStatusEnumType
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core20.model.unlockconnector.enumeration.UnlockStatusEnumType
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareReq
import com.izivia.ocpp.core20.model.unpublishfirmware.enumeration.UnpublishFirmwareStatusEnumType
import com.izivia.ocpp.core20.model.updatefirmware.FirmwareType
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core20.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import strikt.assertions.isTrue
import com.izivia.ocpp.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType as CancelReservationStatusEnumTypeGen
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedResp as CertificateSignedRespGen
import com.izivia.ocpp.api.model.certificateSigned.enumeration.CertificateSignedStatusEnumType as CertificateSignedStatusEnumTypeGen
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityResp as ChangeAvailabilityRespGen
import com.izivia.ocpp.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType as ChangeAvailabilityStatusEnumTypeGen
import com.izivia.ocpp.api.model.changeavailability.enumeration.OperationalStatusEnumType as OperationalStatusEnumTypeGen
import com.izivia.ocpp.api.model.clearcache.ClearCacheReq as ClearCacheReqGen
import com.izivia.ocpp.api.model.clearcache.ClearCacheResp as ClearCacheRespGen
import com.izivia.ocpp.api.model.clearcache.enumeration.ClearCacheStatusEnumType as ClearCacheStatusEnumTypeGen
import com.izivia.ocpp.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType as ClearChargingProfileStatusEnumTypeGen
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearMonitoringResultType as ClearMonitoringResultTypeGen
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringReq as ClearVariableMonitoringReqGen
import com.izivia.ocpp.api.model.clearvariablemonitoring.ClearVariableMonitoringResp as ClearVariableMonitoringRespGen
import com.izivia.ocpp.api.model.clearvariablemonitoring.enumeration.ClearMonitoringStatusEnumType as ClearMonitoringStatusEnumTypeGen
import com.izivia.ocpp.api.model.common.ChargingSchedulePeriodType as ChargingSchedulePeriodTypeGen
import com.izivia.ocpp.api.model.cleardisplaymessage.enumeration.ClearMessageStatusEnumType as ClearMessageStatusEnumTypeGen
import com.izivia.ocpp.api.model.common.CertificateHashDataType as CertificateHashDataTypeGen
import com.izivia.ocpp.api.model.common.ChargingProfileType as ChargingProfileTypeGen
import com.izivia.ocpp.api.model.common.ChargingScheduleType as ChargingScheduleTypeGen
import com.izivia.ocpp.api.model.common.ComponentType as ComponentTypeGen
import com.izivia.ocpp.api.model.common.EVSEType as EVSETypeGen
import com.izivia.ocpp.api.model.common.IdTokenType as IdTokenTypeGen
import com.izivia.ocpp.api.model.common.MessageContentType as MessageContentTypeGen
import com.izivia.ocpp.api.model.common.OCSPRequestDataType as OCSPRequestDataTypeGen
import com.izivia.ocpp.api.model.common.StatusInfoType as StatusInfoTypeGen
import com.izivia.ocpp.api.model.common.VariableType as VariableTypeGen
import com.izivia.ocpp.api.model.common.enumeration.AttributeEnumType as AttributeEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.CertificateSigningUseEnumType as CertificateSigningUseEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.ChargingLimitSourceEnumType as ChargingLimitSourceEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.ChargingProfilePurposeEnumType as ChargingProfilePurposeEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.ChargingRateUnitEnumType as ChargingRateUnitEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.GenericDeviceModelStatusEnumType as GenericDeviceModelStatusEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.GenericStatusEnumType as GenericStatusEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.HashAlgorithmEnumType as HashAlgorithmEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.IdTokenEnumType as IdTokenEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.MessageFormatEnumType as MessageFormatEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.MonitorEnumType as MonitorEnumTypeGen
import com.izivia.ocpp.api.model.common.enumeration.RequestStartStopStatusEnumType as RequestStartStopStatusEnumTypeGen
import com.izivia.ocpp.api.model.customerinformation.enumeration.CustomerInformationStatusEnumType as CustomerInformationStatusEnumTypeGen
import com.izivia.ocpp.api.model.datatransfer.DataTransferResp as DataTransferRespGen
import com.izivia.ocpp.api.model.datatransfer.enumeration.DataTransferStatusEnumType as DataTransferStatusEnumTypeGen
import com.izivia.ocpp.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReqGen
import com.izivia.ocpp.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType as FirmwareStatusEnumTypeGen
import com.izivia.ocpp.api.model.getbasereport.enumeration.ReportBaseEnumType as ReportBaseEnumTypeGen
import com.izivia.ocpp.api.model.getcertificatestatus.GetCertificateStatusReq as GetCertificateStatusReqGen
import com.izivia.ocpp.api.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType as GetCertificateStatusEnumTypeGen
import com.izivia.ocpp.api.model.getchargingprofiles.enumeration.GetChargingProfileStatusEnumType as GetChargingProfileStatusEnumTypeGen
import com.izivia.ocpp.api.model.getcompositeschedule.CompositeScheduleType as CompositeScheduleTypeGen
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType as GetCertificateIdUseEnumTypeGen
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType as GetInstalledCertificateStatusEnumTypeGen
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionReq as GetLocalListVersionReqGen
import com.izivia.ocpp.api.model.getlog.enumeration.LogEnumType as LogEnumTypeGen
import com.izivia.ocpp.api.model.getlog.enumeration.LogStatusEnumType as LogStatusEnumTypeGen
import com.izivia.ocpp.api.model.common.ComponentVariableType as ComponentVariableTypeGen
import com.izivia.ocpp.api.model.getreport.enumeration.ComponentCriterionEnumType as ComponentCriterionEnumTypeGen
import com.izivia.ocpp.api.model.getvariables.GetVariableResultType as GetVariableResultTypeGen
import com.izivia.ocpp.api.model.getvariables.enumeration.GetVariableStatusEnumType as GetVariableStatusEnumTypeGen
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateStatusEnumType as InstallCertificateStatusEnumTypeGen
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateUseEnumType as InstallCertificateUseEnumTypeGen
import com.izivia.ocpp.api.model.notifycharginglimit.ChargingLimitType as ChargingLimitTypeGen
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitReq as NotifyChargingLimitReqGen
import com.izivia.ocpp.api.model.notifycharginglimit.NotifyChargingLimitResp as NotifyChargingLimitRespGen
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationResp as NotifyCustomerInformationRespGen
import com.izivia.ocpp.api.model.notifydisplaymessages.NotifyDisplayMessagesResp as NotifyDisplayMessagesRespGen
import com.izivia.ocpp.api.model.notifyevchargingneeds.ACChargingParametersType as ACChargingParametersTypeGen
import com.izivia.ocpp.api.model.notifyevchargingneeds.ChargingNeedsType as ChargingNeedsTypeGen
import com.izivia.ocpp.api.model.notifyevchargingneeds.DCChargingParametersType as DCChargingParametersTypeGen
import com.izivia.ocpp.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq as NotifyEVChargingNeedsReqGen
import com.izivia.ocpp.api.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType as EnergyTransferModeEnumTypeGen
import com.izivia.ocpp.api.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType as NotifyEVChargingNeedsStatusEnumTypeGen
import com.izivia.ocpp.api.model.notifyevent.EventDataType as EventDataTypeGen
import com.izivia.ocpp.api.model.notifyevent.NotifyEventReq as NotifyEventReqGen
import com.izivia.ocpp.api.model.notifyevent.NotifyEventResp as NotifyEventRespGen
import com.izivia.ocpp.api.model.notifyevent.enumeration.EventNotificationEnumType as EventNotificationEnumTypeGen
import com.izivia.ocpp.api.model.notifyevent.enumeration.EventTriggerEnumType as EventTriggerEnumTypeGen
import com.izivia.ocpp.api.model.notifymonitoringreport.MonitoringDataType as MonitoringDataTypeGen
import com.izivia.ocpp.api.model.notifymonitoringreport.NotifyMonitoringReportResp as NotifyMonitoringReportRespGen
import com.izivia.ocpp.api.model.notifymonitoringreport.VariableMonitoringType as VariableMonitoringTypeGen
import com.izivia.ocpp.api.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq as PublishFirmwareStatusNotificationReqGen
import com.izivia.ocpp.api.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationResp as PublishFirmwareStatusNotificationRespGen
import com.izivia.ocpp.api.model.publishfirmwarestatusnotification.enumeration.PublishFirmwareStatusEnumType as PublishFirmwareStatusEnumTypeGen
import com.izivia.ocpp.api.model.remotestart.enumeration.ChargingProfileKindEnumType as ChargingProfileKindEnumTypeGen
import com.izivia.ocpp.api.model.remotestart.enumeration.RecurrencyKindEnumType as RecurrencyKindEnumTypeGen
import com.izivia.ocpp.api.model.reportchargingprofiles.ReportChargingProfilesResp as ReportChargingProfilesRespGen
import com.izivia.ocpp.api.model.reservationstatusupdate.ReservationStatusUpdateResp as ReservationStatusUpdateRespGen
import com.izivia.ocpp.api.model.reservationstatusupdate.enumeration.ReservationUpdateStatusEnumType as ReservationUpdateStatusEnumTypeGen
import com.izivia.ocpp.api.model.securityeventnotification.SecurityEventNotificationResp as SecurityEventNotificationRespGen
import com.izivia.ocpp.api.model.sendlocallist.AuthorizationData as AuthorizationDataGen
import com.izivia.ocpp.api.model.sendlocallist.enumeration.SendLocalListStatusEnumType as SendLocalListStatusEnumTypeGen
import com.izivia.ocpp.api.model.sendlocallist.enumeration.UpdateEnumType as UpdateEnumTypeGen
import com.izivia.ocpp.api.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType as ChargingProfileStatusEnumTypeGen
import com.izivia.ocpp.api.model.setvariablemonitoring.SetMonitoringResultType as SetMonitoringResultTypeGen
import com.izivia.ocpp.api.model.setvariablemonitoring.enumeration.SetMonitoringStatusEnumType as SetMonitoringStatusEnumTypeGen
import com.izivia.ocpp.api.model.setvariables.enumeration.SetVariableStatusEnumType as SetVariableStatusEnumTypeGen
import com.izivia.ocpp.api.model.triggermessage.enumeration.MessageTriggerEnumType as MessageTriggerEnumTypeGen
import com.izivia.ocpp.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType as TriggerMessageStatusEnumTypeGen
import com.izivia.ocpp.api.model.unlockconnector.enumeration.UnlockStatusEnumType as UnlockStatusEnumTypeGen
import com.izivia.ocpp.api.model.updatefirmware.FirmwareType as FirmwareTypeGen
import com.izivia.ocpp.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType as UpdateFirmwareStatusEnumTypeGen
import com.izivia.ocpp.api.model.costupdated.CostUpdatedResp as CostUpdatedRespGen
import com.izivia.ocpp.api.model.unpublishfirmware.enumeration.UnpublishFirmwareStatusEnumType as UnpublishFirmwareStatusEnumTypeGen
class MapperTest {
    @Test
    fun changeAvailabilityMapper() {
        val mapper: ChangeAvailabilityMapper = Mappers.getMapper(ChangeAvailabilityMapper::class.java)
        val resp = mapper.genToCoreResp(ChangeAvailabilityRespGen(ChangeAvailabilityStatusEnumTypeGen.Accepted))
        expectThat(resp)
                .and { get { status }.isEqualTo(ChangeAvailabilityStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(null) }

        val req = mapper.coreToGenReq(ChangeAvailabilityReq(OperationalStatusEnumType.Operative, EVSEType(1)))
        expectThat(req)
                .and { get { operationalStatus }.isEqualTo(OperationalStatusEnumTypeGen.Operative) }
                .and { get { evse }.isEqualTo(EVSETypeGen(1)) }
    }

    @Test
    fun clearCacheMapper() {
        val mapper: ClearCacheMapper = Mappers.getMapper(ClearCacheMapper::class.java)
        val resp = mapper.genToCoreResp(
                ClearCacheRespGen(
                        ClearCacheStatusEnumTypeGen.Accepted,
                        StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(ClearCacheStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(ClearCacheReq())
        expectThat(req)
                .and { get { req }.isA<ClearCacheReqGen>() }
    }

    @Test
    fun unlockConnectorMapper() {
        val mapper: UnlockConnectorMapper = Mappers.getMapper(UnlockConnectorMapper::class.java)
        val req = mapper.genToCoreResp(
                UnlockConnectorResp(
                        UnlockStatusEnumTypeGen.UnknownConnector,
                        StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(req)
                .and { get { req.status }.isEqualTo(UnlockStatusEnumType.UnknownConnector) }
                .and { get { req.statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val resp = mapper.coreToGenReq(UnlockConnectorReq(1, 2))
        expectThat(resp)
                .and { get { resp.connectorId }.isEqualTo(1) }
                .and { get { resp.evseId }.isEqualTo(2) }
    }

    @Test
    fun requestStartTransactionMapper() {
        val mapper: RequestStartTransactionMapper = Mappers.getMapper(RequestStartTransactionMapper::class.java)
        val resp = mapper.genToCoreResp(
                RequestStartTransactionResp(
                        RequestStartStopStatusEnumTypeGen.Accepted,
                        "1234",
                        StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(RequestStartStopStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }
                .and { get { transactionId }.isEqualTo("1234") }

        val req = mapper.coreToGenReq(
                RequestStartTransactionReq(
                        1, IdTokenType("token1", IdTokenEnumType.Central), 2,
                        ChargingProfileType(
                                id = 3,
                                stackLevel = 4,
                                chargingProfilePurpose = ChargingProfilePurposeEnumType.ChargingStationMaxProfile,
                                chargingProfileKind = ChargingProfileKindEnumType.Absolute,
                                chargingSchedule = listOf(
                                        ChargingScheduleType(
                                                id = 5,
                                                chargingRateUnit = ChargingRateUnitEnumType.A,
                                                chargingSchedulePeriod = listOf(ChargingSchedulePeriodType(9, 10.0)),
                                                startSchedule = Instant.parse("2022-02-15T00:00:00.000Z"),
                                                duration = 6,
                                                minChargingRate = 7.0,
                                                salesTariff = SalesTariffType(8, listOf())
                                        )
                                ),
                                recurrencyKind = RecurrencyKindEnumType.Daily,
                                validFrom = Instant.parse("2022-02-15T00:00:00.001Z"),
                                validTo = Instant.parse("2022-02-15T00:00:00.002Z"),
                                transactionId = "transaction"
                        )
                )
        )
        expectThat(req)
                .and { get { remoteStartId }.isEqualTo(1) }
                .and { get { idToken }.isEqualTo(IdTokenTypeGen("token1", IdTokenEnumTypeGen.Central)) }
                .and { get { evseId }.isEqualTo(2) }
                .and { get { chargingProfile?.id }.isEqualTo(3) }
                .and { get { chargingProfile?.stackLevel }.isEqualTo(4) }
                .and { get { chargingProfile?.chargingProfilePurpose }.isEqualTo(ChargingProfilePurposeEnumTypeGen.ChargingStationMaxProfile) }
                .and { get { chargingProfile?.chargingProfileKind }.isEqualTo(ChargingProfileKindEnumTypeGen.Absolute) }
                .and { get { chargingProfile?.recurrencyKind }.isEqualTo(RecurrencyKindEnumTypeGen.Daily) }
                .and { get { chargingProfile?.validFrom }.isEqualTo(Instant.parse("2022-02-15T00:00:00.001Z")) }
                .and { get { chargingProfile?.validTo }.isEqualTo(Instant.parse("2022-02-15T00:00:00.002Z")) }
                .and { get { chargingProfile?.transactionId }.isEqualTo("transaction") }
    }

    @Test
    fun requestStopTransactionMapper() {
        val mapper: RequestStopTransactionMapper = Mappers.getMapper(RequestStopTransactionMapper::class.java)
        val resp = mapper.genToCoreResp(
                RequestStopTransactionResp(
                        RequestStartStopStatusEnumTypeGen.Rejected,
                        StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(RequestStartStopStatusEnumType.Rejected) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(RequestStopTransactionReq("tag1"))
        expectThat(req)
                .and { get { transactionId }.isEqualTo("tag1") }
    }

    @Test
    fun getVariablesMapper() {
        val mapper: GetVariablesMapper = Mappers.getMapper(GetVariablesMapper::class.java)
        val resp = mapper.genToCoreResp(
                GetVariablesResp(
                        listOf(
                                GetVariableResultTypeGen(
                                        attributeStatus = GetVariableStatusEnumTypeGen.Accepted,
                                        component = ComponentTypeGen("component", "instance", EVSETypeGen(1, 2)),
                                        variable = VariableTypeGen("variable", "instance"),
                                        attributeType = AttributeEnumTypeGen.MaxSet,
                                        attributeValue = "value",
                                        attributeStatusInfo = StatusInfoTypeGen("reason", "additional")
                                )
                        )
                )
        )

        expectThat(resp.getVariableResult[0])
                .and { get { attributeStatus }.isEqualTo(GetVariableStatusEnumType.Accepted) }
                .and { get { component }.isEqualTo(ComponentType("component", "instance", EVSEType(1, 2))) }
                .and { get { variable }.isEqualTo(VariableType("variable", "instance")) }
                .and { get { attributeType }.isEqualTo(AttributeEnumType.MaxSet) }
                .and { get { attributeValue }.isEqualTo("value") }
                .and {
                    get { attributeStatusInfo }.isEqualTo(
                            StatusInfoType(
                                    "reason",
                                    "additional"
                            )
                    )
                }


        val req = mapper.coreToGenReq(
                GetVariablesReq(
                        listOf(
                                GetVariableDataType(
                                        component = ComponentType(
                                                "component", "instance",
                                                EVSEType(1, 2)
                                        ),
                                        variable = VariableType("variable", "instance"),
                                        attributeType = AttributeEnumType.MaxSet
                                )
                        )
                )
        )
        expectThat(req.getVariableData[0])
                .and {
                    get { component }.isEqualTo(
                            ComponentTypeGen(
                                    "component",
                                    "instance",
                                    EVSETypeGen(1, 2)
                            )
                    )
                }
                .and { get { variable }.isEqualTo(VariableTypeGen("variable", "instance")) }
                .and { get { attributeType }.isEqualTo(AttributeEnumTypeGen.MaxSet) }
    }

    @Test
    fun setVariablesMapper() {
        val mapper: SetVariablesMapper = Mappers.getMapper(SetVariablesMapper::class.java)
        val resp = mapper.genToCoreResp(
                SetVariablesResp(
                        listOf(
                                SetVariableResultType(
                                        attributeStatus = SetVariableStatusEnumTypeGen.RebootRequired,
                                        component = ComponentTypeGen("component", "instance", EVSETypeGen(1, 2)),
                                        variable = VariableTypeGen("variable", "instance"),
                                        attributeType = AttributeEnumTypeGen.MaxSet,
                                        attributeStatusInfo = StatusInfoTypeGen("reason", "additional")
                                )
                        )
                )
        )
        expectThat(resp)
                .and { get { setVariableResult.size }.isEqualTo(1) }
                .and { get { setVariableResult[0].attributeStatus }.isEqualTo(SetVariableStatusEnumType.RebootRequired) }
                .and {
                    get { setVariableResult[0].component }.isEqualTo(
                            ComponentType(
                                    "component",
                                    "instance",
                                    EVSEType(1, 2)
                            )
                    )
                }
                .and { get { setVariableResult[0].variable }.isEqualTo(VariableType("variable", "instance")) }
                .and { get { setVariableResult[0].attributeType }.isEqualTo(AttributeEnumType.MaxSet) }
                .and {
                    get { setVariableResult[0].attributeStatusInfo }.isEqualTo(
                            StatusInfoType(
                                    "reason",
                                    "additional"
                            )
                    )
                }

        val req = mapper.coreToGenReq(
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
        expectThat(req)
                .and { get { setVariableData.size }.isEqualTo(1) }
                .and { get { setVariableData[0].attributeValue }.isEqualTo("value") }
                .and {
                    get { setVariableData[0].component }.isEqualTo(
                            ComponentTypeGen(
                                    "component",
                                    "instance",
                                    EVSETypeGen(1, 2)
                            )
                    )
                }
                .and { get { setVariableData[0].variable }.isEqualTo(VariableTypeGen("variable", "instance")) }
                .and { get { setVariableData[0].attributeType }.isEqualTo(AttributeEnumTypeGen.Target) }
    }

    @Test
    fun getBaseReportMapper() {
        val mapper: GetBaseReportMapper = Mappers.getMapper(GetBaseReportMapper::class.java)
        val resp = mapper.genToCoreResp(
                GetBaseReportResp(GenericDeviceModelStatusEnumTypeGen.Accepted, StatusInfoTypeGen("reason", "additional"))
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(GenericDeviceModelStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                GetBaseReportReq(1, ReportBaseEnumType.ConfigurationInventory)
        )
        expectThat(req)
                .and { get { requestId }.isEqualTo(1) }
                .and { get { reportBase }.isEqualTo(ReportBaseEnumTypeGen.ConfigurationInventory) }
    }

    @Test
    fun getReportMapper() {
        val mapper: GetReportMapper = Mappers.getMapper(GetReportMapper::class.java)
        val resp = mapper.genToCoreResp(
                GetReportResp(GenericDeviceModelStatusEnumTypeGen.Accepted, StatusInfoTypeGen("reason", "additional"))
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(GenericDeviceModelStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                GetReportReq(
                        1, listOf(ComponentCriterionEnumType.Active), listOf(
                        ComponentVariableType(
                                ComponentType("component"),
                                VariableType("variable")
                        )
                )
                )
        )
        expectThat(req)
                .and { get { requestId }.isEqualTo(1) }
                .and { get { componentCriteria }.isEqualTo(listOf(ComponentCriterionEnumTypeGen.Active)) }
                .and {
                    get { componentVariable }.isEqualTo(
                            listOf(
                                    ComponentVariableTypeGen(
                                            ComponentTypeGen("component"),
                                            VariableTypeGen("variable")
                                    )
                            )
                    )
                }
    }

    @Test
    fun cancelReservationMapper() {
        val mapper: CancelReservationMapper = Mappers.getMapper(CancelReservationMapper::class.java)
        val resp = mapper.genToCoreResp(
                CancelReservationResp(
                        CancelReservationStatusEnumTypeGen.Rejected,
                        StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { resp.status }.isEqualTo(CancelReservationStatusEnumType.Rejected) }
                .and { get { resp.statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(CancelReservationReq(3))
        expectThat(req).and { get { req.reservationId }.isEqualTo(3) }
    }

    @Test
    fun requestFirmwareStatusNotificationMapper() {
        val mapper: FirmwareStatusNotificationMapper = Mappers.getMapper(FirmwareStatusNotificationMapper::class.java)

        val req = mapper.genToCoreReq(FirmwareStatusNotificationReqGen(FirmwareStatusEnumTypeGen.InstallRebooting))
        expectThat(req)
                .and { get { status }.isEqualTo(FirmwareStatusEnumType.InstallRebooting) }
    }

    @Test
    fun clearChargingProfileMapper() {
        val mapper: ClearChargingProfileMapper = Mappers.getMapper(ClearChargingProfileMapper::class.java)
        val resp = mapper.genToCoreResp(
                ClearChargingProfileResp(
                        ClearChargingProfileStatusEnumTypeGen.Accepted,
                        StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(ClearChargingProfileEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                ClearChargingProfileReq(
                        1,
                        ClearChargingProfileType(
                                1,
                                ChargingProfilePurposeEnumType.ChargingStationMaxProfile,
                                1
                        )
                )
        )
        expectThat(req)
                .and { get { chargingProfileId }.isEqualTo(1) }
                .and { get { chargingProfileCriteria?.evseId }.isEqualTo(1) }
                .and { get { chargingProfileCriteria?.chargingProfilePurpose }.isEqualTo(ChargingProfilePurposeEnumTypeGen.ChargingStationMaxProfile) }
                .and { get { chargingProfileCriteria?.stackLevel }.isEqualTo(1) }
    }

    @Test
    fun getCompositeScheduleMapper() {
        val mapper: GetCompositeScheduleMapper = Mappers.getMapper(GetCompositeScheduleMapper::class.java)
        val resp = mapper.genToCoreResp(
                GetCompositeScheduleResp(
                        GenericStatusEnumTypeGen.Accepted,
                        CompositeScheduleTypeGen(
                                1,
                                3,
                                Instant.parse("2022-02-15T00:00:00.001Z"),
                                ChargingRateUnitEnumTypeGen.A,
                                listOf(ChargingSchedulePeriodTypeGen(9, 10.0))
                        ),
                        StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(GenericStatusEnumType.Accepted) }
                .and { get { schedule?.evseId }.isEqualTo(1) }
                .and { get { schedule?.duration }.isEqualTo(3) }
                .and { get { schedule?.scheduleStart }.isEqualTo(Instant.parse("2022-02-15T00:00:00.001Z")) }
                .and { get { schedule?.chargingRateUnit }.isEqualTo(ChargingRateUnitEnumType.A) }
                .and { get { schedule?.chargingSchedulePeriod }.isEqualTo(listOf(ChargingSchedulePeriodType(9, 10.0))) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                GetCompositeScheduleReq(
                        1,
                        3,
                        ChargingRateUnitEnumType.A
                )
        )
        expectThat(req)
                .and { get { evseId }.isEqualTo(1) }
                .and { get { duration }.isEqualTo(3) }
                .and { get { chargingRateUnit }.isEqualTo(ChargingRateUnitEnumTypeGen.A) }
    }

    @Test
    fun getLocalListVersionMapper() {
        val mapper: GetLocalListVersionMapper = Mappers.getMapper(GetLocalListVersionMapper::class.java)
        val resp = mapper.genToCoreResp(GetLocalListVersionResp(1))
        expectThat(resp).and { get { versionNumber }.isEqualTo(1) }

        val req = mapper.coreToGenReq(GetLocalListVersionReq())
        expectThat(req)
                .and { get { req }.isA<GetLocalListVersionReqGen>() }
    }

    @Test
    fun updateFirmwareMapper() {
        val mapper: UpdateFirmwareMapper = Mappers.getMapper(UpdateFirmwareMapper::class.java)
        val resp = mapper.genToCoreResp(
                UpdateFirmwareResp(
                        status = UpdateFirmwareStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp) {
            get { resp.status }.isEqualTo(UpdateFirmwareStatusEnumType.Accepted)
            get { resp.statusInfo }.isEqualTo(StatusInfoType("reason", "additional"))
        }

        val req = mapper.coreToGenReq(
                UpdateFirmwareReq(
                        retries = 3,
                        retryInterval = 1,
                        requestId = 2,
                        firmware = FirmwareType(
                                location = "http://www.ietf.org/rfc/rfc2396.txt", // URI
                                retrieveDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                                installDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                                signingCertificate = "signingCertificate",
                                signature = "signature"
                        )
                )
        )
        expectThat(req) {
            get { req.retries }.isEqualTo(3)
            get { req.retryInterval }.isEqualTo(1)
            get { req.requestId }.isEqualTo(2)
            get { req.firmware }.isEqualTo(
                    FirmwareTypeGen(
                            location = "http://www.ietf.org/rfc/rfc2396.txt", // URI
                            retrieveDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                            installDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                            signingCertificate = "signingCertificate",
                            signature = "signature"
                    )
            )
        }
    }

    @Test
    fun sendLocalListMapper() {
        val mapper: SendLocalListMapper = Mappers.getMapper(SendLocalListMapper::class.java)
        val resp = mapper.genToCoreResp(
                SendLocalListResp(
                        SendLocalListStatusEnumTypeGen.Accepted,
                        StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(SendLocalListStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                SendLocalListReq(
                        1,
                        UpdateEnumType.Full,
                        listOf(AuthorizationData(IdTokenType("", IdTokenEnumType.Local)))
                )
        )
        expectThat(req)
                .and { get { versionNumber }.isEqualTo(1) }
                .and { get { updateType }.isEqualTo(UpdateEnumTypeGen.Full) }
                .and {
                    get { localAuthorizationList }.isEqualTo(
                            listOf(AuthorizationDataGen(IdTokenTypeGen("", IdTokenEnumTypeGen.Local)))
                    )
                }
    }

    @Test
    fun triggerMessage() {
        val mapper: TriggerMessageMapper = Mappers.getMapper(TriggerMessageMapper::class.java)
        val resp = mapper.genToCoreResp(
                TriggerMessageResp(
                        status = TriggerMessageStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp) {
            get { status }.isEqualTo(TriggerMessageStatusEnumType.Accepted)
            get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional"))
        }

        val req = mapper.coreToGenReq(
                TriggerMessageReq(
                        MessageTriggerEnumType.Heartbeat,
                        EVSEType(1, 1)
                )
        )
        expectThat(req) {
            get { requestedMessage }.isEqualTo(MessageTriggerEnumTypeGen.Heartbeat)
            get { evse }.isEqualTo(EVSETypeGen(1, 1))
        }
    }

    @Test
    fun setChargingProfileMapper() {
        val mapper: SetChargingProfileMapper = Mappers.getMapper(SetChargingProfileMapper::class.java)
        val resp = mapper.genToCoreResp(
                SetChargingProfileResp(ChargingProfileStatusEnumTypeGen.Accepted, StatusInfoTypeGen("reason", "additional"))
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(ChargingProfileStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                SetChargingProfileReq(
                        1, ChargingProfileType(
                        id = 3,
                        stackLevel = 4,
                        chargingProfilePurpose = ChargingProfilePurposeEnumType.ChargingStationMaxProfile,
                        chargingProfileKind = ChargingProfileKindEnumType.Absolute,
                        chargingSchedule = listOf(
                                ChargingScheduleType(
                                        id = 5,
                                        chargingRateUnit = ChargingRateUnitEnumType.A,
                                        chargingSchedulePeriod = listOf(ChargingSchedulePeriodType(9, 10.0)),
                                        startSchedule = Instant.parse("2022-02-15T00:00:00.000Z"),
                                        duration = 6,
                                        minChargingRate = 7.0,
                                        salesTariff = SalesTariffType(8, listOf())
                                )
                        ),
                        recurrencyKind = RecurrencyKindEnumType.Daily,
                        validFrom = Instant.parse("2022-02-15T00:00:00.001Z"),
                        validTo = Instant.parse("2022-02-15T00:00:00.002Z"),
                        transactionId = "transaction"
                )
                )
        )
        expectThat(req)
                .and { get { evseId }.isEqualTo(1) }
                .and { get { chargingProfile.id }.isEqualTo(3) }
                .and { get { chargingProfile.stackLevel }.isEqualTo(4) }
                .and { get { chargingProfile.chargingProfilePurpose }.isEqualTo(ChargingProfilePurposeEnumTypeGen.ChargingStationMaxProfile) }
                .and { get { chargingProfile.chargingProfileKind }.isEqualTo(ChargingProfileKindEnumTypeGen.Absolute) }
                .and { get { chargingProfile.recurrencyKind }.isEqualTo(RecurrencyKindEnumTypeGen.Daily) }
                .and { get { chargingProfile.validFrom }.isEqualTo(Instant.parse("2022-02-15T00:00:00.001Z")) }
                .and { get { chargingProfile.validTo }.isEqualTo(Instant.parse("2022-02-15T00:00:00.002Z")) }
                .and { get { chargingProfile.transactionId }.isEqualTo("transaction") }
    }

    @Test
    fun getCertificateStatusMapper() {
        val mapper: GetCertificateStatusMapper = Mappers.getMapper(GetCertificateStatusMapper::class.java)
        val req = mapper.genToCoreReq(
                GetCertificateStatusReqGen(
                        OCSPRequestDataTypeGen(
                                hashAlgorithm = HashAlgorithmEnumTypeGen.SHA256,
                                issuerNameHash = "",
                                issuerKeyHash = "",
                                serialNumber = "",
                                responderURL = ""
                        )
                )
        )
        expectThat(req).and {
            get { ocspRequestData }.isEqualTo(
                    OCSPRequestDataType(
                            hashAlgorithm = HashAlgorithmEnumType.SHA256,
                            issuerNameHash = "",
                            issuerKeyHash = "",
                            serialNumber = "",
                            responderURL = ""
                    )
            )
        }

        val resp = mapper.coreToGenResp(
                GetCertificateStatusResp(
                        GetCertificateStatusEnumType.Accepted,
                        "",
                        StatusInfoType("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(GetCertificateStatusEnumTypeGen.Accepted) }
                .and { get { ocspResult }.isEqualTo("") }
                .and { get { statusInfo }.isEqualTo(StatusInfoTypeGen("reason", "additional")) }
    }

    @Test
    fun notifyEVChargingNeedsMapper() {
        val mapper: NotifyEVChargingNeedsMapper = Mappers.getMapper(NotifyEVChargingNeedsMapper::class.java)
        val req = mapper.genToCoreReq(
                NotifyEVChargingNeedsReqGen(
                        evseId = 1,
                        maxScheduleTuples = 3,
                        chargingNeeds = ChargingNeedsTypeGen(
                                requestedEnergyTransfer = EnergyTransferModeEnumTypeGen.DC,
                                departureTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                                acChargingParameters = ACChargingParametersTypeGen(
                                        energyAmount = 1,
                                        evMinCurrent = 2,
                                        evMaxCurrent = 3,
                                        evMaxVoltage = 4
                                ),
                                dcChargingParameters = DCChargingParametersTypeGen(
                                        evMaxCurrent = 1,
                                        evMaxVoltage = 2,
                                        energyAmount = 3,
                                        evMaxPower = 4,
                                        stateOfCharge = 5,
                                        evEnergyCapacity = 6,
                                        fullSoC = 7,
                                        bulkSoC = 8
                                )
                        )
                )
        )
        expectThat(req)
                .and { get { evseId }.isEqualTo(1) }
                .and { get { maxScheduleTuples }.isEqualTo(3) }
                .and { get { chargingNeeds.requestedEnergyTransfer }.isEqualTo(EnergyTransferModeEnumType.DC) }
                .and { get { chargingNeeds.departureTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
                .and { get { chargingNeeds.acChargingParameters?.energyAmount }.isEqualTo(1) }
                .and { get { chargingNeeds.acChargingParameters?.evMinCurrent }.isEqualTo(2) }
                .and { get { chargingNeeds.acChargingParameters?.evMaxCurrent }.isEqualTo(3) }
                .and { get { chargingNeeds.acChargingParameters?.evMaxVoltage }.isEqualTo(4) }
                .and { get { chargingNeeds.dcChargingParameters?.evMaxCurrent }.isEqualTo(1) }
                .and { get { chargingNeeds.dcChargingParameters?.evMaxVoltage }.isEqualTo(2) }
                .and { get { chargingNeeds.dcChargingParameters?.energyAmount }.isEqualTo(3) }
                .and { get { chargingNeeds.dcChargingParameters?.evMaxPower }.isEqualTo(4) }
                .and { get { chargingNeeds.dcChargingParameters?.stateOfCharge }.isEqualTo(5) }
                .and { get { chargingNeeds.dcChargingParameters?.evEnergyCapacity }.isEqualTo(6) }
                .and { get { chargingNeeds.dcChargingParameters?.fullSoC }.isEqualTo(7) }
                .and { get { chargingNeeds.dcChargingParameters?.bulkSoC }.isEqualTo(8) }

        val resp = mapper.coreToGenResp(
                NotifyEVChargingNeedsResp(
                        NotifyEVChargingNeedsStatusEnumType.Accepted,
                        StatusInfoType("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(NotifyEVChargingNeedsStatusEnumTypeGen.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoTypeGen("reason", "additional")) }
    }

    @Test
    fun notifyCustomerInformation() {
        val mapper: NotifyCustomerInformationMapper = Mappers.getMapper(NotifyCustomerInformationMapper::class.java)
        val resp = mapper.coreToGenResp(
                NotifyCustomerInformationResp()
        )
        expectThat(resp) {
            get { resp }.isA<NotifyCustomerInformationRespGen>()
        }

        val req = mapper.genToCoreReq(
                NotifyCustomerInformationReq(
                        data = "Some data",
                        tbc = true,
                        seqNo = 0,
                        generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                        requestId = 1
                )
        )
        expectThat(req) {
            get { req.data }.isEqualTo("Some data")
            get { req.tbc }.isEqualTo(true)
            get { req.seqNo }.isEqualTo(0)
            get { req.generatedAt }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z"))
            get { req.requestId }.isEqualTo(1)
        }
    }

    @Test
    fun notifyEventMapper() {
        val mapper: NotifyEventMapper = Mappers.getMapper(NotifyEventMapper::class.java)
        val resp = mapper.coreToGenResp(NotifyEventResp())
        expectThat(resp).isA<NotifyEventRespGen>()

        val req = mapper.genToCoreReq(
                NotifyEventReqGen(
                        generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                        seqNo = 0,
                        eventData = listOf(
                                EventDataTypeGen(
                                        eventId = 1,
                                        timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                                        trigger = EventTriggerEnumTypeGen.Delta,
                                        actualValue = "actualValue",
                                        eventNotificationType = EventNotificationEnumTypeGen.HardWiredNotification,
                                        component = ComponentTypeGen("component"),
                                        variable = VariableTypeGen("variable"),
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
        )
        expectThat(req) {
            get { req.generatedAt }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z"))
            get { req.seqNo }.isEqualTo(0)
            get { req.tbc }.isEqualTo(true)
            get { req.eventData[0].eventId }.isEqualTo(1)
            get { req.eventData[0].timestamp }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z"))
            get { req.eventData[0].trigger }.isEqualTo(EventTriggerEnumType.Delta)
            get { req.eventData[0].actualValue }.isEqualTo("actualValue")
            get { req.eventData[0].eventNotificationType }.isEqualTo(EventNotificationEnumType.HardWiredNotification)
            get { req.eventData[0].component }.isEqualTo(ComponentType("component"))
            get { req.eventData[0].variable }.isEqualTo(VariableType("variable"))
            get { req.eventData[0].cause }.isEqualTo(2)
            get { req.eventData[0].techCode }.isEqualTo("techCode")
            get { req.eventData[0].techInfo }.isEqualTo("techInfo")
            get { req.eventData[0].cleared }.isEqualTo(true)
            get { req.eventData[0].transactionId }.isEqualTo("transaction")
            get { req.eventData[0].variableMonitoringId }.isEqualTo(3)
        }
    }

    @Test
    fun notifyChargingLimitMapper() {
        val mapper: NotifyChargingLimitMapper = Mappers.getMapper(NotifyChargingLimitMapper::class.java)
        val resp = mapper.coreToGenResp(NotifyChargingLimitResp())
        expectThat(resp).and { get { resp }.isA<NotifyChargingLimitRespGen>() }

        val req = mapper.genToCoreReq(
                NotifyChargingLimitReqGen(
                        ChargingLimitTypeGen(
                                chargingLimitSource = ChargingLimitSourceEnumTypeGen.CSO,
                                isGridCritical = true
                        ),
                        1,
                        listOf(
                                ChargingScheduleTypeGen(
                                        id = 5,
                                        chargingRateUnit = ChargingRateUnitEnumTypeGen.A,
                                        chargingSchedulePeriod = listOf(ChargingSchedulePeriodTypeGen(9, 10.0)),
                                        startSchedule = Instant.parse("2022-02-15T00:00:00.000Z"),
                                        duration = 6,
                                        minChargingRate = 7.0
                                )
                        )
                )
        )
        expectThat(req)
                .and { get { chargingLimit.chargingLimitSource }.isEqualTo(ChargingLimitSourceEnumType.CSO) }
                .and { get { chargingLimit.isGridCritical }.isTrue() }
                .and { get { evseId }.isEqualTo(1) }
                .and {
                    get { chargingSchedule }.isEqualTo(
                            listOf(
                                    ChargingScheduleType(
                                            id = 5,
                                            chargingRateUnit = ChargingRateUnitEnumType.A,
                                            chargingSchedulePeriod = listOf(ChargingSchedulePeriodType(9, 10.0)),
                                            startSchedule = Instant.parse("2022-02-15T00:00:00.000Z"),
                                            duration = 6,
                                            minChargingRate = 7.0
                                    )
                            )
                    )
                }
    }

    @Test
    fun notifyDisplayMessagesMapper() {
        val mapper: NotifyDisplayMessagesMapper = Mappers.getMapper(NotifyDisplayMessagesMapper::class.java)
        val resp = mapper.coreToGenResp(NotifyDisplayMessagesResp())
        expectThat(resp).and { get { resp }.isA<NotifyDisplayMessagesRespGen>() }

        val req = mapper.genToCoreReq(
                NotifyDisplayMessagesReqGen(
                        requestId = 1,
                        tbc = false,
                        messageInfo = listOf(
                                MessageInfoTypeGen(
                                        id = 2,
                                        priority = MessagePriorityEnumTypeGen.InFront,
                                        state = MessageStateEnumTypeGen.Charging,
                                        startDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                                        endDateTime = Instant.parse("2022-02-15T00:00:00.001Z"),
                                        transactionId = "2",
                                        message = MessageContentTypeGen(
                                                format = MessageFormatEnumTypeGen.URI,
                                                language = "language",
                                                content = "Message content"
                                        ),
                                        display = ComponentTypeGen(
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
        )
        expectThat(req) {
            get { req }.isA<NotifyDisplayMessagesReq>()
            get { requestId }.isEqualTo(1)
            get { tbc }.isEqualTo(false)
            get { messageInfo?.get(0)?.id }.isEqualTo(2)
            get { messageInfo?.get(0)?.priority }.isEqualTo(MessagePriorityEnumType.InFront)
            get { messageInfo?.get(0)?.state }.isEqualTo(MessageStateEnumType.Charging)
            get { messageInfo?.get(0)?.startDateTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z"))
            get { messageInfo?.get(0)?.endDateTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.001Z"))
            get { messageInfo?.get(0)?.transactionId }.isEqualTo("2")
            get { messageInfo?.get(0)?.message?.format }.isEqualTo(MessageFormatEnumType.URI)
            get { messageInfo?.get(0)?.message?.language }.isEqualTo("language")
            get { messageInfo?.get(0)?.message?.content }.isEqualTo("Message content")
            get { messageInfo?.get(0)?.display?.name }.isEqualTo("name")
            get { messageInfo?.get(0)?.display?.instance }.isEqualTo("instance")
            get { messageInfo?.get(0)?.display?.evse?.id }.isEqualTo(1)
            get { messageInfo?.get(0)?.display?.evse?.connectorId }.isEqualTo(2)
        }
    }

    @Test
    fun dataTransferMapper() {
        val mapper: DataTransferMapper = Mappers.getMapper(DataTransferMapper::class.java)
        val resp = mapper.genToCoreResp(
                DataTransferRespGen(
                        status = DataTransferStatusEnumTypeGen.Accepted,
                        data = "Some data",
                        statusInfo = StatusInfoTypeGen(
                                reasonCode = "reasonCode",
                                additionalInfo = "additionalInfo"
                        )
                )
        )
        expectThat(resp) {
            isA<DataTransferResp>()
            get { resp.status }.isEqualTo(DataTransferStatusEnumType.Accepted)
            get { resp.data }.isEqualTo("Some data")
            get { resp.statusInfo?.reasonCode }.isEqualTo("reasonCode")
            get { resp.statusInfo?.additionalInfo }.isEqualTo("additionalInfo")
        }

        val req = mapper.coreToGenReq(
                DataTransferReq(
                        messageId = "messageId",
                        data = "Some data",
                        vendorId = "vendorId"
                )
        )
        expectThat(req) {
            isA<DataTransferReqGen>()
            get { req.messageId }.isEqualTo("messageId")
            get { req.data }.isEqualTo("Some data")
            get { req.vendorId }.isEqualTo("vendorId")
        }
    }

    @Test
    fun diagnosticsStatusNotificationMapper() {
        val mapper: LogStatusNotificationMapper = Mappers.getMapper(LogStatusNotificationMapper::class.java)
        val resp = mapper.coreToGenResp(
                LogStatusNotificationResp()
        )
        expectThat(resp) {
            get { resp }.isA<LogStatusNotificationRespGen>()
        }

        val req = mapper.genToCoreReq(LogStatusNotificationReqGen(
                status = UploadLogStatusEnumTypeGen.Uploaded,
                requestId = 1
        ))
        expectThat(req) {
            get { status }.isEqualTo(UploadLogStatusEnumType.Uploaded)
            get { requestId }.isEqualTo(1)
        }
    }

    @Test
    fun publishFirmwareStatusNotificationMapper() {
        val mapper: PublishFirmwareStatusNotificationMapper = Mappers.getMapper(PublishFirmwareStatusNotificationMapper::class.java)
        val resp = mapper.coreToGenResp(
                PublishFirmwareStatusNotificationResp()
        )
        expectThat(resp) {
            get { resp }.isA<PublishFirmwareStatusNotificationRespGen>()
        }

        val req = mapper.genToCoreReq(PublishFirmwareStatusNotificationReqGen(
                status = PublishFirmwareStatusEnumTypeGen.PublishFailed,
                location = listOf("location"),
                requestId = 1
        ))
        expectThat(req) {
            get { status }.isEqualTo(PublishFirmwareStatusEnumType.PublishFailed)
            get { location }.isEqualTo(listOf("location"))
            get { requestId }.isEqualTo(1)
        }
    }

    @Test
    fun notifyMonitoringReportMapper() {
        val mapper: NotifyMonitoringReportMapper = Mappers.getMapper(NotifyMonitoringReportMapper::class.java)
        val resp = mapper.coreToGenResp(NotifyMonitoringReportResp())
        expectThat(resp).and { get { resp }.isA<NotifyMonitoringReportRespGen>() }

        val req = mapper.genToCoreReq(
                NotifyMonitoringReportReq(
                        requestId = 1,
                        generatedAt = Instant.parse("2022-02-15T00:00:00.000Z"),
                        seqNo = 2,
                        tbc = true,
                        monitor = listOf(
                                MonitoringDataTypeGen(
                                        component = ComponentTypeGen("component"),
                                        variable = VariableTypeGen("variable"),
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
        )
        expectThat(req)
                .and { get { requestId }.isEqualTo(1) }
                .and { get { seqNo }.isEqualTo(2) }
                .and { get { tbc }.isEqualTo(true) }
                .and { get { generatedAt }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
                .and { get { monitor?.get(0)?.component }.isEqualTo(ComponentType("component")) }
                .and { get { monitor?.get(0)?.variable }.isEqualTo(VariableType("variable")) }
                .and { get { monitor?.get(0)?.variableMonitoring?.get(0)?.id }.isEqualTo(3) }
                .and { get { monitor?.get(0)?.variableMonitoring?.get(0)?.transaction }.isEqualTo(true) }
                .and { get { monitor?.get(0)?.variableMonitoring?.get(0)?.value }.isEqualTo(10.0) }
                .and { get { monitor?.get(0)?.variableMonitoring?.get(0)?.type }.isEqualTo(MonitorEnumType.Periodic) }
                .and { get { monitor?.get(0)?.variableMonitoring?.get(0)?.severity }.isEqualTo(3) }
    }

    @Test
    fun reservationStatusUpdateMapper() {
        val mapper: ReservationStatusUpdateMapper = Mappers.getMapper(ReservationStatusUpdateMapper::class.java)
        val resp = mapper.coreToGenResp(ReservationStatusUpdateResp())
        expectThat(resp).and { get { resp }.isA<ReservationStatusUpdateRespGen>() }

        val req = mapper.genToCoreReq(
                ReservationStatusUpdateReq(
                        reservationId = 1,
                        reservationUpdateStatus = ReservationUpdateStatusEnumTypeGen.Expired
                )
        )
        expectThat(req)
                .and { get { reservationId }.isEqualTo(1) }
                .and { get { reservationUpdateStatus }.isEqualTo(ReservationUpdateStatusEnumType.Expired) }
    }

    @Test
    fun securityEventNotificationMapper() {
        val mapper: SecurityEventNotificationMapper = Mappers.getMapper(SecurityEventNotificationMapper::class.java)
        val resp = mapper.coreToGenResp(SecurityEventNotificationResp())
        expectThat(resp).and { get { resp }.isA<SecurityEventNotificationRespGen>() }

        val req = mapper.genToCoreReq(
                SecurityEventNotificationReq(
                        type = "type",
                        timestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                        techInfo = "techInfo"
                )
        )
        expectThat(req)
                .and { get { type }.isEqualTo("type") }
                .and { get { timestamp }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
                .and { get { techInfo }.isEqualTo("techInfo") }
    }

    @Test
    fun certificateSignedMapper() {
        val mapper: CertificateSignedMapper = Mappers.getMapper(CertificateSignedMapper::class.java)
        val resp = mapper.genToCoreResp(CertificateSignedRespGen(CertificateSignedStatusEnumTypeGen.Accepted, StatusInfoTypeGen("reason", "info")))
        expectThat(resp)
                .and { get { status }.isEqualTo(CertificateSignedStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "info")) }

        val req = mapper.coreToGenReq(CertificateSignedReq("certificateChain", CertificateSigningUseEnumType.V2GCertificate))
        expectThat(req) {
            get { certificateChain }.isEqualTo("certificateChain")
            get { certificateType }.isEqualTo(CertificateSigningUseEnumTypeGen.V2GCertificate)
        }
    }

    @Test
    fun signCertificateMapper() {
        val mapper: SignCertificateMapper = Mappers.getMapper(SignCertificateMapper::class.java)
        val req = mapper.genToCoreReq(
                SignCertificateReq(
                        csr = "csr",
                        certificateType = CertificateSigningUseEnumTypeGen.V2GCertificate
                )
        )
        expectThat(req)
                .and { get { csr }.isEqualTo("csr") }
                .and { get { certificateType }.isEqualTo(CertificateSigningUseEnumType.V2GCertificate) }

        val resp = mapper.coreToGenResp(
                SignCertificateResp(
                        status = GenericStatusEnumType.Accepted,
                        statusInfo = StatusInfoType("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(GenericStatusEnumTypeGen.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoTypeGen("reason", "additional")) }
    }

    @Test
    fun getLogMapper() {
        val mapper: GetLogMapper = Mappers.getMapper(GetLogMapper::class.java)
        val resp = mapper.genToCoreResp(
                GetLogResp(
                        status = LogStatusEnumTypeGen.Accepted,
                        filename = "filename",
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(LogStatusEnumType.Accepted) }
                .and { get { filename }.isEqualTo("filename") }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                GetLogReq(
                        requestId = 1,
                        logType = LogEnumType.DiagnosticsLog,
                        log = LogParametersType(
                                remoteLocation = "remoteLocation",
                                oldestTimestamp = Instant.parse("2022-02-15T00:00:00.000Z"),
                                latestTimestamp = Instant.parse("2022-02-15T00:00:00.000Z")
                        ),
                        retries = 2,
                        retryInterval = 3
                )
        )
        expectThat(req)
                .and { get { requestId }.isEqualTo(1) }
                .and { get { logType }.isEqualTo(LogEnumTypeGen.DiagnosticsLog) }
                .and { get { log.remoteLocation }.isEqualTo("remoteLocation") }
                .and { get { log.oldestTimestamp }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
                .and { get { log.latestTimestamp }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z")) }
                .and { get { retries }.isEqualTo(2) }
                .and { get { retryInterval }.isEqualTo(3) }
    }


    @Test
    fun clearDisplayMessageMapper() {
        val mapper: ClearDisplayMessageMapper = Mappers.getMapper(ClearDisplayMessageMapper::class.java)
        val resp = mapper.genToCoreResp(
                ClearDisplayMessageResp(
                        status = ClearMessageStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(ClearMessageStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(ClearDisplayMessageReq(2))
        expectThat(req)
                .and { get { id }.isEqualTo(2) }
    }

    @Test
    fun reportChargingProfilesMapper() {
        val mapper: ReportChargingProfilesMapper = Mappers.getMapper(ReportChargingProfilesMapper::class.java)
        val req = mapper.genToCoreReq(
                ReportChargingProfilesReq(
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
        )
        expectThat(req) {
            get { requestId }.isEqualTo(2)
            get { chargingLimitSource }.isEqualTo(ChargingLimitSourceEnumType.CSO)
            get { tbc }.isEqualTo(false)
            get { evseId }.isEqualTo(2)
            get { chargingProfile[0] }.and {
                get { id }.isEqualTo(1)
                get { stackLevel }.isEqualTo(1)
                get { chargingProfilePurpose }.isEqualTo(ChargingProfilePurposeEnumType.ChargingStationMaxProfile)
                get { chargingProfileKind }.isEqualTo(ChargingProfileKindEnumType.Absolute)
                get { chargingSchedule[0].id }.isEqualTo(1)
                get { chargingSchedule[0].chargingRateUnit }.isEqualTo(ChargingRateUnitEnumType.A)
            }
        }

        val resp = mapper.coreToGenResp(ReportChargingProfilesResp())

        expectThat(resp).isA<ReportChargingProfilesRespGen>()
    }

    @Test
    fun setDisplayMessageMapper() {
        val mapper: SetDisplayMessageMapper = Mappers.getMapper(SetDisplayMessageMapper::class.java)
        val resp = mapper.genToCoreResp(
                SetDisplayMessageResp(
                        status = DisplayMessageStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp) {
            get { status }.isEqualTo(DisplayMessageStatusEnumType.Accepted)
            get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional"))
        }


        val req = mapper.coreToGenReq(SetDisplayMessageReq(
                MessageInfoType(
                        id = 2,
                        priority = MessagePriorityEnumType.InFront,
                        state = MessageStateEnumType.Charging,
                        startDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                        endDateTime = Instant.parse("2022-02-15T00:00:00.001Z"),
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

        expectThat(req) {
            get { req }.isA<SetDisplayMessageReqGen>()
            get { message }.and {
                get { id }.isEqualTo(2)
                get { priority }.isEqualTo(MessagePriorityEnumTypeGen.InFront)
                get { state }.isEqualTo(MessageStateEnumTypeGen.Charging)
                get { startDateTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z"))
                get { endDateTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.001Z"))
                get { transactionId }.isEqualTo("2")
                get { message }.and {
                    get { format }.isEqualTo(MessageFormatEnumTypeGen.URI)
                    get { language }.isEqualTo("language")
                    get { content }.isEqualTo("Message content")
                }
                get { display }.and {
                    get { this?.name }.isEqualTo("name")
                    get { this?.instance }.isEqualTo("instance")
                    get { this?.evse?.id }.isEqualTo(1)
                    get { this?.evse?.connectorId }.isEqualTo(2)
                }
            }
        }
    }

    @Test
    fun getInstalledCertificateIds() {
        val mapper: GetInstalledCertificateIdsMapper = Mappers.getMapper(GetInstalledCertificateIdsMapper::class.java)
        val resp = mapper.genToCoreResp(
            GetInstalledCertificateIdsResp(
                status = GetInstalledCertificateStatusEnumTypeGen.Accepted,
                certificateHashDataChain = listOf(
                    CertificateHashDataChainType(
                        GetCertificateIdUseEnumTypeGen.CSMSRootCertificate,
                        CertificateHashDataTypeGen(
                            HashAlgorithmEnumTypeGen.SHA512,
                            "issuerNameHash",
                            "issuerKeyHash",
                            "serial"
                        ),
                        listOf(
                            CertificateHashDataTypeGen(
                                HashAlgorithmEnumTypeGen.SHA512,
                                "issuerNameHash",
                                "issuerKeyHash",
                                "serial"
                            ),
                            CertificateHashDataTypeGen(
                                HashAlgorithmEnumTypeGen.SHA512,
                                "issuerNameHash2",
                                "issuerKeyHash2",
                                "serial2"
                            )
                        )
                    ),
                ),
                statusInfo = StatusInfoTypeGen("reason", "info")
            )
        )
        expectThat(resp)
            .and { get { status }.isEqualTo(GetInstalledCertificateStatusEnumType.Accepted) }
            .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "info")) }

            .and { get { certificateHashDataChain?.get(0)?.certificateType }.isEqualTo(GetCertificateIdUseEnumType.CSMSRootCertificate) }
            .and {
                get { certificateHashDataChain?.get(0)?.certificateHashData }
                    .and {
                        get { this?.hashAlgorithm }.isEqualTo(HashAlgorithmEnumType.SHA512)
                        get { this?.issuerNameHash }.isEqualTo("issuerNameHash")
                        get { this?.hashAlgorithm }.isEqualTo(HashAlgorithmEnumType.SHA512)
                        get { this?.issuerNameHash }.isEqualTo("issuerNameHash")
                        get { this?.issuerKeyHash }.isEqualTo("issuerKeyHash")
                        get { this?.serialNumber }.isEqualTo("serial")
                    }
            }
            .and {
                get { certificateHashDataChain?.get(0)?.childCertificateHashData }
                    .and {
                        get { this?.get(0)?.hashAlgorithm }.isEqualTo(HashAlgorithmEnumType.SHA512)
                        get { this?.get(0)?.issuerNameHash }.isEqualTo("issuerNameHash")
                        get { this?.get(0)?.issuerKeyHash }.isEqualTo("issuerKeyHash")
                        get { this?.get(0)?.serialNumber }.isEqualTo("serial")
                        get { this?.get(1)?.hashAlgorithm }.isEqualTo(HashAlgorithmEnumType.SHA512)
                        get { this?.get(1)?.issuerNameHash }.isEqualTo("issuerNameHash2")
                        get { this?.get(1)?.issuerKeyHash }.isEqualTo("issuerKeyHash2")
                        get { this?.get(1)?.serialNumber }.isEqualTo("serial2")
                    }
            }

        val req = mapper.coreToGenReq(
            GetInstalledCertificateIdsReq(
                certificateType = listOf(
                    GetCertificateIdUseEnumType.CSMSRootCertificate,
                    GetCertificateIdUseEnumType.MORootCertificate
                )
            )
        )

        expectThat(req)
            .and { get { certificateType?.get(0) }.isEqualTo(GetCertificateIdUseEnumTypeGen.CSMSRootCertificate) }
            .and { get { certificateType?.get(1) }.isEqualTo(GetCertificateIdUseEnumTypeGen.MORootCertificate) }
    }

    @Test
    fun getDisplayMessageMapper() {
        val mapper: GetDisplayMessagesMapper = Mappers.getMapper(GetDisplayMessagesMapper::class.java)
        val resp = mapper.genToCoreResp(
                GetDisplayMessagesResp(
                        status = GetDisplayMessagesStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(GetDisplayMessagesStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                GetDisplayMessagesReq(
                        123,
                        listOf(26,23),
                        MessagePriorityEnumType.InFront,
                        MessageStateEnumType.Charging
                )
        )

        expectThat(req)
                .and { get { id }.isEqualTo(listOf(26,23)) }
                .and { get { requestId }.isEqualTo(123) }
                .and { get { priority }.isEqualTo(MessagePriorityEnumTypeGen.InFront) }
                .and { get { state }.isEqualTo(MessageStateEnumTypeGen.Charging) }
    }

    @Test
    fun setNetworkProfileMapper() {
        val mapper: SetNetworkProfileMapper = Mappers.getMapper(SetNetworkProfileMapper::class.java)
        val resp = mapper.genToCoreResp(
                SetNetworkProfileResp(
                        status = SetNetworkProfileStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp) {
            get { status }.isEqualTo(SetNetworkProfileStatusEnumType.Accepted)
            get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional"))
        }

        val req = mapper.coreToGenReq(
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
        expectThat(req) {
            get { configurationSlot }.isEqualTo(2)
            get { connectionData.ocppVersion }.isEqualTo(OCPPVersionEnumTypeGen.OCPP12)
            get { connectionData.ocppTransport }.isEqualTo(OCPPTransportEnumTypeGen.JSON)
            get { connectionData.ocppCsmsUrl }.isEqualTo("url")
            get { connectionData.messageTimeout }.isEqualTo(312)
            get { connectionData.securityProfile }.isEqualTo(123)
            get { connectionData.ocppInterface }.isEqualTo(OCPPInterfaceEnumTypeGen.Wired0)
            get { connectionData.vpn }.and {
                get { this?.server  }.isEqualTo("server")
                get { this?.user }.isEqualTo("user")
                get { this?.password }.isEqualTo("pass")
                get { this?.group }.isEqualTo("group")
                get { this?.key }.isEqualTo("key")
                get { this?.type }.isEqualTo(VPNEnumTypeGen.IKEv2)
            }
            get { connectionData.apn }.and {
                get { this?.apn  }.isEqualTo("APN")
                get { this?.apnUserName }.isEqualTo("userName")
                get { this?.apnPassword }.isEqualTo("pass")
                get { this?.simPin }.isEqualTo(3)
                get { this?.preferredNetwork }.isEqualTo("pref")
                get { this?.useOnlyPreferredNetwork }.isEqualTo(false)
                get { this?.apnAuthentication }.isEqualTo(APNAuthenticationEnumTypeGen.AUTO)
            }
        }
    }

    @Test
    fun setMonitoringLevelMapper() {
        val mapper: SetMonitoringLevelMapper = Mappers.getMapper(SetMonitoringLevelMapper::class.java)
        val resp = mapper.genToCoreResp(
                SetMonitoringLevelResp(
                        status = GenericStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(GenericStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(SetMonitoringLevelReq(severity = 2))
        expectThat(req)
                .and { get { severity }.isEqualTo(2) }
    }

    @Test
    fun installCertificateMapper() {
        val mapper: InstallCertificateMapper = Mappers.getMapper(InstallCertificateMapper::class.java)
        val resp = mapper.genToCoreResp(
                InstallCertificateResp(
                        status = InstallCertificateStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(InstallCertificateStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                InstallCertificateReq(
                        certificateType=InstallCertificateUseEnumType.CSMSRootCertificate,
                        certificate="certificate"
                )
        )
        expectThat(req)
                .and { get { certificateType }.isEqualTo(InstallCertificateUseEnumTypeGen.CSMSRootCertificate) }
                .and { get { certificate }.isEqualTo("certificate") }
    }

    @Test
    fun customerInformationMapper() {
        val mapper: CustomerInformationMapper = Mappers.getMapper(CustomerInformationMapper::class.java)
        val resp = mapper.genToCoreResp(
                CustomerInformationResp(
                        status = CustomerInformationStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(CustomerInformationStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                CustomerInformationReq(
                        requestId = 3,
                        report = false,
                        clear =true,
                        customerIdentifier ="identifier",
                        idToken = IdTokenType(
                                idToken = "idToken",
                                type = IdTokenEnumType.Central,
                                additionalInfo = null
                        ),
                        customerCertificate = CertificateHashDataType(
                                hashAlgorithm= HashAlgorithmEnumType.SHA512,
                                issuerNameHash="issuerNameHash",
                                issuerKeyHash="issuerKeyHash",
                                serialNumber="serial"
                        ),
                )
        )
        expectThat(req)
                .and { get { requestId }.isEqualTo(3) }
                .and { get { report }.isEqualTo(false) }
                .and { get { clear }.isEqualTo(true) }
                .and { get { customerIdentifier }.isEqualTo("identifier") }
                .and { get { idToken?.idToken }.isEqualTo("idToken") }
                .and { get { idToken?.type }.isEqualTo(IdTokenEnumTypeGen.Central) }
                .and { get { idToken?.additionalInfo }.isEqualTo(null) }
                .and { get { customerCertificate?.hashAlgorithm }.isEqualTo(HashAlgorithmEnumTypeGen.SHA512) }
                .and { get { customerCertificate?.issuerNameHash }.isEqualTo("issuerNameHash") }
                .and { get { customerCertificate?.issuerKeyHash }.isEqualTo("issuerKeyHash") }
                .and { get { customerCertificate?.serialNumber }.isEqualTo("serial") }
    }

    @Test
    fun unpublishFirmwareMapper() {
        val mapper: UnpublishFirmwareMapper = Mappers.getMapper(UnpublishFirmwareMapper::class.java)
        val resp = mapper.genToCoreResp(
                UnpublishFirmwareResp(
                    UnpublishFirmwareStatusEnumTypeGen.DownloadOngoing
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(UnpublishFirmwareStatusEnumType.DownloadOngoing) }

        val req = mapper.coreToGenReq(UnpublishFirmwareReq("checksum"))
        expectThat(req)
                .and { get { checksum }.isEqualTo("checksum") }
    }

    @Test
    fun getChargingProfilesMapper() {
        val mapper: GetChargingProfilesMapper = Mappers.getMapper(GetChargingProfilesMapper::class.java)
        val resp = mapper.genToCoreResp(
                GetChargingProfilesResp(
                    status = GetChargingProfileStatusEnumTypeGen.Accepted,
                    statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(GetChargingProfileStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
            GetChargingProfilesReq(
                requestId=3233,
                evseId=2323,
                chargingProfile = ChargingProfileCriterionType(
                        chargingProfilePurpose = ChargingProfilePurposeEnumType.ChargingStationMaxProfile,
                        stackLevel = 23,
                        chargingProfileId = listOf(1,2),
                        chargingLimitSource = listOf(ChargingLimitSourceEnumType.CSO,ChargingLimitSourceEnumType.EMS)

                )
            )
        )

        expectThat(req)
                .and { get { requestId }.isEqualTo(3233) }
                .and { get { evseId }.isEqualTo(2323) }
                .and { get { chargingProfile.chargingProfilePurpose}.isEqualTo(ChargingProfilePurposeEnumTypeGen.ChargingStationMaxProfile) }
                .and { get { chargingProfile.stackLevel}.isEqualTo(23) }
                .and { get { chargingProfile.chargingProfileId}.isEqualTo(listOf(1,2)) }
                .and { get { chargingProfile.chargingLimitSource}.isEqualTo(listOf(ChargingLimitSourceEnumTypeGen.CSO,ChargingLimitSourceEnumTypeGen.EMS)) }
    }

    @Test
    fun publishFirmwareMapper() {
        val mapper: PublishFirmwareMapper = Mappers.getMapper(PublishFirmwareMapper::class.java)
        val resp = mapper.genToCoreResp(
                PublishFirmwareResp(
                        status = GenericStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp) {
            get { status }.isEqualTo(GenericStatusEnumType.Accepted)
            get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional"))
        }

        val req = mapper.coreToGenReq(
                PublishFirmwareReq(
                        location = "location",
                        retries = 1,
                        checksum = "checksum",
                        requestId = 31,
                        retryInterval = 4
                )
        )
        expectThat(req) {
            get { location }.isEqualTo("location")
            get { retries }.isEqualTo(1)
            get { checksum }.isEqualTo("checksum")
            get { requestId }.isEqualTo(31)
            get { retryInterval }.isEqualTo(4)
        }
    }

    @Test
    fun deleteCertificateMapper() {
        val mapper: DeleteCertificateMapper = Mappers.getMapper(DeleteCertificateMapper::class.java)
        val resp = mapper.genToCoreResp(
                DeleteCertificateResp(
                        status = DeleteCertificateStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp) {
            get { status }.isEqualTo(DeleteCertificateStatusEnumType.Accepted)
            get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional"))
        }

        val req = mapper.coreToGenReq(
            DeleteCertificateReq(
                CertificateHashDataType(
                    HashAlgorithmEnumType.SHA512,
                        "issuerNameHash",
                        "issuerKeyHash",
                        "serialNumber"
                )
            )
        )
        expectThat(req) {
            get { certificateHashData }.and {
                get{ hashAlgorithm }.isEqualTo(HashAlgorithmEnumTypeGen.SHA512)
                get{ issuerNameHash }.isEqualTo("issuerNameHash")
                get{ issuerKeyHash }.isEqualTo("issuerKeyHash")
                get{ serialNumber }.isEqualTo("serialNumber")

            }
        }
    }
    @Test
    fun costUpdatedMapper() {
        val mapper: CostUpdatedMapper = Mappers.getMapper(CostUpdatedMapper::class.java)
        val req = mapper.coreToGenReq(
                CostUpdatedReq(50.0,"451")
        )
        expectThat(req) {
            get { totalCost }.isEqualTo(50.0)
            get { transactionId }.isEqualTo("451")
        }

        val resp = mapper.genToCoreResp(
                CostUpdatedRespGen()
        )
        expectThat(resp).isA<CostUpdatedResp>()
    }

    @Test
    fun getMonitoringReportMapper() {
        val mapper: GetMonitoringReportMapper = Mappers.getMapper(GetMonitoringReportMapper::class.java)
        val resp = mapper.genToCoreResp(
                GetMonitoringReportResp(
                    status = GenericDeviceModelStatusEnumTypeGen.Accepted,
                    statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp)
                .and { get { status }.isEqualTo(GenericDeviceModelStatusEnumType.Accepted) }
                .and { get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional")) }

        val req = mapper.coreToGenReq(
                GetMonitoringReportReq(
                        432768,
                        listOf(MonitoringCriterionEnumType.DeltaMonitoring),
                        listOf(ComponentVariableType(
                              ComponentType(
                                      "name",
                                      "instance"
                              ),
                                VariableType(
                                        "name",
                                        "instance"
                                )
                        ))
                )
        )
        expectThat(req)
            .and { get { requestId }.isEqualTo(432768) }
            .and { get { monitoringCriteria }.isEqualTo(listOf(MonitoringCriterionEnumTypeGen.DeltaMonitoring)) }
            .and { get { componentVariable }.isEqualTo(
                    listOf(ComponentVariableTypeGen(
                            ComponentTypeGen(
                                    "name",
                                    "instance"
                            ),
                            VariableTypeGen(
                                    "name",
                                    "instance"
                            )
                        )
                    )
                )
            }
    }

    @Test
    fun setVariableMonitoringMapper() {
        val mapper: SetVariableMonitoringMapper = Mappers.getMapper(SetVariableMonitoringMapper::class.java)
        val resp = mapper.genToCoreResp(
            SetVariableMonitoringResp(
                listOf(
                    SetMonitoringResultTypeGen(
                        id = 23,
                        status = SetMonitoringStatusEnumTypeGen.Accepted,
                        type = MonitorEnumTypeGen.Delta,
                        severity = 213,
                        component = ComponentTypeGen("name"),
                        variable = VariableTypeGen("name"),
                        statusInfo = StatusInfoTypeGen("reason", "info")
                    )
                )
            )
        )
        expectThat(resp) {
            get { setMonitoringResult[0] }.and {
                get { id }.isEqualTo(23)
                get { status }.isEqualTo(SetMonitoringStatusEnumType.Accepted)
                get { type }.isEqualTo(MonitorEnumType.Delta)
                get { severity }.isEqualTo(213)
                get { component }.isEqualTo(ComponentType("name"))
                get { variable }.isEqualTo(VariableType("name"))
                get { statusInfo }.isEqualTo(StatusInfoType("reason", "info"))
            }
        }

        val req = mapper.coreToGenReq(
            SetVariableMonitoringReq(
                setMonitoringData = listOf(
                    SetMonitoringDataType(
                        id = 231,
                        transaction = false,
                        value = 78.3,
                        type = MonitorEnumType.Delta,
                        severity = 5,
                        component = ComponentType("name"),
                        variable = VariableType("name")
                    )
                )
            )
        )

        expectThat(req)
        {
            get { setMonitoringData[0] }.and {
                get { id }.isEqualTo(231)
                get { transaction }.isEqualTo(false)
                get { value }.isEqualTo(78.3)
                get { type }.isEqualTo(MonitorEnumTypeGen.Delta)
                get { severity }.isEqualTo(5)
                get { component }.isEqualTo(ComponentTypeGen("name"))
                get { variable }.isEqualTo(VariableTypeGen("name"))
            }
        }
    }

    @Test
    fun setMonitoringBaseMapper() {
        val mapper: SetMonitoringBaseMapper = Mappers.getMapper(SetMonitoringBaseMapper::class.java)
        val resp = mapper.genToCoreResp(
                SetMonitoringBaseResp(
                        status = GenericDeviceModelStatusEnumTypeGen.Accepted,
                        statusInfo = StatusInfoTypeGen("reason", "additional")
                )
        )
        expectThat(resp) {
            get { status }.isEqualTo(GenericDeviceModelStatusEnumType.Accepted)
            get { statusInfo }.isEqualTo(StatusInfoType("reason", "additional"))
        }

        val req = mapper.coreToGenReq(
                SetMonitoringBaseReq(
                        monitoringBase = MonitoringBaseEnumType.All
                )
        )
        expectThat(req.monitoringBase).isEqualTo(MonitoringBaseEnumTypeGen.All)
    }

    @Test
    fun clearVariableMonitoringMapper() {
        val mapper: ClearVariableMonitoringMapper = Mappers.getMapper(ClearVariableMonitoringMapper::class.java)
        val resp = mapper.genToCoreResp(
            ClearVariableMonitoringRespGen(
                clearMonitoringResults =
                listOf(
                    ClearMonitoringResultTypeGen(
                        status = ClearMonitoringStatusEnumTypeGen.Accepted,
                        id = 1,
                        statusInfo = StatusInfoTypeGen(
                            reasonCode = "reasonCode",
                            additionalInfo = "additionalInfo"
                        )
                    )
                )
            )
        )
        expectThat(resp).and { get { resp }.isA<ClearVariableMonitoringResp>() }

        val req = mapper.coreToGenReq(
            ClearVariableMonitoringReq(
                ids = listOf(1, 2)
            )
        )
        expectThat(req) {
            get { req }.isA<ClearVariableMonitoringReqGen>()
            get { ids }.isEqualTo(listOf(1, 2))
        }
    }


    @Test
    fun getTransactionStatusMapper()
    {
        val mapper: GetTransactionStatusMapper = Mappers.getMapper(GetTransactionStatusMapper::class.java)
        val resp = mapper.genToCoreResp(
                GetTransactionStatusResp(
                    false,
                        true
                )
        )
        expectThat(resp) {
            get { messagesInQueue }.isEqualTo(false)
            get { ongoingIndicator }.isEqualTo(true)
        }

        val req = mapper.coreToGenReq(
                GetTransactionStatusReq(
                        "id"
                )
        )

        expectThat(req.transactionId).isEqualTo("id")
    }
}
