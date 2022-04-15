package fr.simatix.cs.simulator.adapter.test

import fr.simatix.cs.simulator.adapter20.mapper.*
import fr.simatix.cs.simulator.api.model.cancelreservation.CancelReservationResp
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.api.model.common.MessageContentType as MessageContentTypeGen
import fr.simatix.cs.simulator.core20.model.common.enumeration.MessageFormatEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.MessageFormatEnumType as MessageFormatEnumTypeGen
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferResp as DataTransferRespGen
import fr.simatix.cs.simulator.core20.model.datatransfer.enumeration.DataTransferStatusEnumType
import fr.simatix.cs.simulator.api.model.datatransfer.enumeration.DataTransferStatusEnumType as DataTransferStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.getbasereport.GetBaseReportResp
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.api.model.getreport.GetReportResp
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.MessageInfoType as MessageInfoTypeGen
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.NotifyDisplayMessagesReq as NotifyDisplayMessagesReqGen
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.enumeration.MessagePriorityEnumType
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.enumeration.MessagePriorityEnumType as MessagePriorityEnumTypeGen
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.enumeration.MessageStateEnumType
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.enumeration.MessageStateEnumType as MessageStateEnumTypeGen
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationReq as LogStatusNotificationReqGen
import fr.simatix.cs.simulator.core20.model.logstatusnotification.LogStatusNotificationResp
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationResp as LogStatusNotificationRespGen
import fr.simatix.cs.simulator.core20.model.logstatusnotification.enumeration.UploadLogStatusEnumType
import fr.simatix.cs.simulator.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType as UploadLogStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationReq
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.NotifyMonitoringReportReq
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventResp as NotifyEventRespGen
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.api.model.sendlocallist.SendLocalListResp
import fr.simatix.cs.simulator.api.model.setchargingprofile.SetChargingProfileResp
import fr.simatix.cs.simulator.api.model.setvariables.SetVariableResultType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.api.model.triggermessage.TriggerMessageResp
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareResp
import fr.simatix.cs.simulator.core20.model.authorize.enumeration.HashAlgorithmEnumType
import fr.simatix.cs.simulator.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType as UpdateFirmwareStatusEnumTypeGen
import fr.simatix.cs.simulator.core20.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType
import fr.simatix.cs.simulator.core20.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core20.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.OperationalStatusEnumType
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core20.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileType
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.enumeration.ClearChargingProfileEnumType
import fr.simatix.cs.simulator.core20.model.common.*
import fr.simatix.cs.simulator.core20.model.common.enumeration.*
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReqGen
import fr.simatix.cs.simulator.core20.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType as FirmwareStatusEnumTypeGen
import fr.simatix.cs.simulator.core20.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.core20.model.getbasereport.enumeration.ReportBaseEnumType
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core20.model.getreport.ComponentVariableType
import fr.simatix.cs.simulator.core20.model.getreport.GetReportReq
import fr.simatix.cs.simulator.core20.model.getreport.enumeration.ComponentCriterionEnumType
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariableDataType
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.core20.model.getvariables.enumeration.GetVariableStatusEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.*
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.ChargingProfileKindEnumType
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.enumeration.GenericStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq as DataTransferReqGen
import fr.simatix.cs.simulator.core20.model.getcertificatestatus.GetCertificateStatusResp
import fr.simatix.cs.simulator.core20.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType
import fr.simatix.cs.simulator.core20.model.notifycustomerinformation.NotifyCustomerInformationResp
import fr.simatix.cs.simulator.api.model.notifyevent.EventDataType as EventDataTypeGen
import fr.simatix.cs.simulator.api.model.notifyevent.NotifyEventReq as NotifyEventReqGen
import fr.simatix.cs.simulator.core20.model.notifyevent.NotifyEventResp
import fr.simatix.cs.simulator.core20.model.notifyevent.enumeration.EventNotificationEnumType
import fr.simatix.cs.simulator.api.model.notifyevent.enumeration.EventNotificationEnumType as EventNotificationEnumTypeGen
import fr.simatix.cs.simulator.core20.model.notifyevent.enumeration.EventTriggerEnumType
import fr.simatix.cs.simulator.api.model.notifyevent.enumeration.EventTriggerEnumType as EventTriggerEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationResp as NotifyCustomerInformationRespGen
import fr.simatix.cs.simulator.core20.model.notifycharginglimit.NotifyChargingLimitResp
import fr.simatix.cs.simulator.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
import fr.simatix.cs.simulator.core20.model.notifymonitoringreport.NotifyMonitoringReportResp
import fr.simatix.cs.simulator.core20.model.notifymonitoringreport.enumeration.MonitorEnumType
import fr.simatix.cs.simulator.api.model.notifydisplaymessages.NotifyDisplayMessagesResp as NotifyDisplayMessagesRespGen
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType
import fr.simatix.cs.simulator.core20.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.RecurrencyKindEnumType
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.core20.model.sendlocallist.AuthorizationData
import fr.simatix.cs.simulator.core20.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.core20.model.sendlocallist.enumeration.SendLocalListStatusEnumType
import fr.simatix.cs.simulator.core20.model.sendlocallist.enumeration.UpdateEnumType
import fr.simatix.cs.simulator.core20.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core20.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariableDataType
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.core20.model.setvariables.enumeration.SetVariableStatusEnumType
import fr.simatix.cs.simulator.core20.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.core20.model.triggermessage.enumeration.MessageTriggerEnumType
import fr.simatix.cs.simulator.core20.model.triggermessage.enumeration.TriggerMessageStatusEnumType
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core20.model.unlockconnector.enumeration.UnlockStatusEnumType
import fr.simatix.cs.simulator.core20.model.updatefirmware.FirmwareType
import fr.simatix.cs.simulator.core20.model.updatefirmware.UpdateFirmwareReq
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import fr.simatix.cs.simulator.api.model.authorize.enumeration.HashAlgorithmEnumType as HashAlgorithmEnumTypeGen
import strikt.assertions.isTrue
import fr.simatix.cs.simulator.api.model.updatefirmware.FirmwareType as FirmwareTypeGen
import fr.simatix.cs.simulator.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType as CancelReservationStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityResp as ChangeAvailabilityRespGen
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType as ChangeAvailabilityStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.OperationalStatusEnumType as OperationalStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheReq as ClearCacheReqGen
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheResp as ClearCacheRespGen
import fr.simatix.cs.simulator.api.model.clearcache.enumeration.ClearCacheStatusEnumType as ClearCacheStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.ChargingSchedulePeriodType as ChargingSchedulePeriodTypeGen
import fr.simatix.cs.simulator.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType as ClearChargingProfileStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.ChargingScheduleType as ChargingScheduleTypeGen
import fr.simatix.cs.simulator.api.model.common.ComponentType as ComponentTypeGen
import fr.simatix.cs.simulator.api.model.common.EVSEType as EVSETypeGen
import fr.simatix.cs.simulator.api.model.common.IdTokenType as IdTokenTypeGen
import fr.simatix.cs.simulator.api.model.common.OCSPRequestDataType as OCSPRequestDataTypeGen
import fr.simatix.cs.simulator.api.model.common.StatusInfoType as StatusInfoTypeGen
import fr.simatix.cs.simulator.api.model.common.VariableType as VariableTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.AttributeEnumType as AttributeEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingProfilePurposeEnumType as ChargingProfilePurposeEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingLimitSourceEnumType as ChargingLimitSourceEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingRateUnitEnumType as ChargingRateUnitEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.GenericDeviceModelStatusEnumType as GenericDeviceModelStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.IdTokenEnumType as IdTokenEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.RequestStartStopStatusEnumType as RequestStartStopStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.getbasereport.enumeration.ReportBaseEnumType as ReportBaseEnumTypeGen
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionReq as GetLocalListVersionReqGen
import fr.simatix.cs.simulator.api.model.getcertificatestatus.enumeration.GetCertificateStatusEnumType as GetCertificateStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.getcertificatestatus.GetCertificateStatusReq as GetCertificateStatusReqGen
import fr.simatix.cs.simulator.api.model.getcompositeschedule.enumeration.GenericStatusEnumType as GenericStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.getcompositeschedule.CompositeScheduleType as CompositeScheduleTypeGen
import fr.simatix.cs.simulator.api.model.getreport.ComponentVariableType as ComponentVariableTypeGen
import fr.simatix.cs.simulator.api.model.getreport.enumeration.ComponentCriterionEnumType as ComponentCriterionEnumTypeGen
import fr.simatix.cs.simulator.api.model.getvariables.GetVariableResultType as GetVariableResultTypeGen
import fr.simatix.cs.simulator.api.model.getvariables.enumeration.GetVariableStatusEnumType as GetVariableStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifycharginglimit.ChargingLimitType as ChargingLimitTypeGen
import fr.simatix.cs.simulator.api.model.notifycharginglimit.NotifyChargingLimitReq as NotifyChargingLimitReqGen
import fr.simatix.cs.simulator.api.model.notifycharginglimit.NotifyChargingLimitResp as NotifyChargingLimitRespGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.ACChargingParametersType as ACChargingParametersTypeGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.DCChargingParametersType as DCChargingParametersTypeGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.ChargingNeedsType as ChargingNeedsTypeGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.enumeration.EnergyTransferModeEnumType as EnergyTransferModeEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.enumeration.NotifyEVChargingNeedsStatusEnumType as NotifyEVChargingNeedsStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifyevchargingneeds.NotifyEVChargingNeedsReq as NotifyEVChargingNeedsReqGen
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.enumeration.MonitorEnumType as MonitorEnumTypeGen
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.MonitoringDataType as MonitoringDataTypeGen
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.NotifyMonitoringReportResp as NotifyMonitoringReportRespGen
import fr.simatix.cs.simulator.api.model.notifymonitoringreport.VariableMonitoringType as VariableMonitoringTypeGen
import fr.simatix.cs.simulator.api.model.remotestart.enumeration.ChargingProfileKindEnumType as ChargingProfileKindEnumTypeGen
import fr.simatix.cs.simulator.api.model.remotestart.enumeration.RecurrencyKindEnumType as RecurrencyKindEnumTypeGen
import fr.simatix.cs.simulator.api.model.sendlocallist.AuthorizationData as AuthorizationDataGen
import fr.simatix.cs.simulator.api.model.sendlocallist.enumeration.SendLocalListStatusEnumType as SendLocalListStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.sendlocallist.enumeration.UpdateEnumType as UpdateEnumTypeGen
import fr.simatix.cs.simulator.api.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType as ChargingProfileStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.setvariables.enumeration.SetVariableStatusEnumType as SetVariableStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.triggermessage.enumeration.MessageTriggerEnumType as MessageTriggerEnumTypeGen
import fr.simatix.cs.simulator.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType as TriggerMessageStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.unlockconnector.enumeration.UnlockStatusEnumType as UnlockStatusEnumTypeGen

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
}

