package fr.simatix.cs.simulator

import fr.simatix.cs.simulator.adapter16.mapper.*
import fr.simatix.cs.simulator.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.OperationalStatusEnumType
import fr.simatix.cs.simulator.api.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.api.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.api.model.clearchargingprofile.enumeration.ClearChargingProfileStatusEnumType
import fr.simatix.cs.simulator.api.model.common.*
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.ChargingRateUnitEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.IdTokenEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.RequestStartStopStatusEnumType
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import fr.simatix.cs.simulator.api.model.firmwarestatusnotification.enumeration.FirmwareStatusEnumType
import fr.simatix.cs.simulator.api.model.getallvariables.GetAllVariablesReq
import fr.simatix.cs.simulator.api.model.getcompositeschedule.CompositeScheduleType
import fr.simatix.cs.simulator.api.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.api.model.getcompositeschedule.enumeration.GenericStatusEnumType
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.api.model.getvariables.GetVariableResultType
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.api.model.getvariables.enumeration.GetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.remotestart.ChargingScheduleType
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.api.model.remotestart.enumeration.ChargingProfileKindEnumType
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.api.model.reservenow.ReserveNowResp
import fr.simatix.cs.simulator.api.model.reservenow.enumeration.ReserveNowStatusEnumType
import fr.simatix.cs.simulator.api.model.sendlocallist.SendLocalListResp
import fr.simatix.cs.simulator.api.model.sendlocallist.enumeration.SendLocalListStatusEnumType
import fr.simatix.cs.simulator.api.model.sendlocallist.enumeration.UpdateEnumType
import fr.simatix.cs.simulator.api.model.setchargingprofile.SetChargingProfileResp
import fr.simatix.cs.simulator.api.model.setchargingprofile.enumeration.ChargingProfileStatusEnumType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariableResultType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.api.model.setvariables.enumeration.SetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.triggermessage.TriggerMessageResp
import fr.simatix.cs.simulator.api.model.triggermessage.enumeration.MessageTriggerEnumType
import fr.simatix.cs.simulator.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import fr.simatix.cs.simulator.api.model.updatefirmware.FirmwareType
import fr.simatix.cs.simulator.core16.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core16.model.cancelreservation.enumeration.CancelReservationStatus
import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core16.model.changeavailability.enumeration.AvailabilityStatus
import fr.simatix.cs.simulator.core16.model.changeavailability.enumeration.AvailabilityType
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationReq
import fr.simatix.cs.simulator.core16.model.changeconfiguration.enumeration.ConfigurationStatus
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core16.model.clearcache.enumeration.ClearCacheStatus
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.enumeration.ClearChargingProfileStatus
import fr.simatix.cs.simulator.core16.model.common.ChargingProfile
import fr.simatix.cs.simulator.core16.model.common.ChargingSchedule
import fr.simatix.cs.simulator.core16.model.common.enumeration.ChargingProfilePurposeType
import fr.simatix.cs.simulator.core16.model.common.enumeration.ChargingRateUnitType
import fr.simatix.cs.simulator.core16.model.common.enumeration.RemoteStartStopStatus
import fr.simatix.cs.simulator.core16.model.firmwarestatusnotification.enumeration.FirmwareStatus
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.enumeration.GetCompositeScheduleStatus
import fr.simatix.cs.simulator.core16.model.getconfiguration.GetConfigurationReq
import fr.simatix.cs.simulator.core16.model.getconfiguration.KeyValue
import fr.simatix.cs.simulator.core16.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core16.model.remotestart.ChargingSchedulePeriod
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionReq
import fr.simatix.cs.simulator.core16.model.remotestart.enumeration.ChargingProfileKindType
import fr.simatix.cs.simulator.core16.model.remotestop.RemoteStopTransactionReq
import fr.simatix.cs.simulator.core16.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.core16.model.reservenow.enumeration.ReservationStatus
import fr.simatix.cs.simulator.core16.model.sendlocallist.AuthorizationData
import fr.simatix.cs.simulator.core16.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.core16.model.sendlocallist.enumeration.UpdateStatus
import fr.simatix.cs.simulator.core16.model.sendlocallist.enumeration.UpdateType
import fr.simatix.cs.simulator.core16.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core16.model.setchargingprofile.enumeration.ChargingProfileStatus
import fr.simatix.cs.simulator.core16.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.core16.model.triggermessage.enumeration.MessageTrigger
import fr.simatix.cs.simulator.core16.model.triggermessage.enumeration.TriggerMessageStatus
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core16.model.unlockconnector.enumeration.UnlockStatus
import fr.simatix.cs.simulator.core16.model.updatefirmware.UpdateFirmwareReq
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import fr.simatix.cs.simulator.api.model.cancelreservation.CancelReservationResp as CancelReservationRespGen
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityResp as ChangeAvailabilityRespGen
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheReq as ClearCacheReqGen
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheResp as ClearCacheRespGen
import fr.simatix.cs.simulator.api.model.getlocallistversion.GetLocalListVersionReq as GetLocalListVersionReqGen
import fr.simatix.cs.simulator.api.model.sendlocallist.AuthorizationData as AuthorizationDataGen

class MapperTest {
    @Test
    fun changeAvailabilityMapper() {
        val mapper: ChangeAvailabilityMapper = Mappers.getMapper(ChangeAvailabilityMapper::class.java)
        val resp = mapper.genToCoreResp(
            ChangeAvailabilityRespGen(
                ChangeAvailabilityStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
        expectThat(resp)
            .and { get { status }.isEqualTo(AvailabilityStatus.Accepted) }

        val req = mapper.coreToGenReq(ChangeAvailabilityReq(1, AvailabilityType.Operative))
        expectThat(req)
            .and { get { operationalStatus }.isEqualTo(OperationalStatusEnumType.Operative) }
            .and { get { evse }.isEqualTo(EVSEType(1, 1)) }
    }

    @Test
    fun clearCacheMapper() {
        val mapper: ClearCacheMapper = Mappers.getMapper(ClearCacheMapper::class.java)
        val resp = mapper.genToCoreResp(ClearCacheRespGen(ClearCacheStatusEnumType.Accepted))
        expectThat(resp)
            .and { get { status }.isEqualTo(ClearCacheStatus.Accepted) }

        val req = mapper.coreToGenReq(ClearCacheReq())
        expectThat(req)
            .and { get { req }.isA<ClearCacheReqGen>() }
    }

    @Test
    fun unlockConnectorMapper() {
        val mapper: UnlockConnectorMapper = Mappers.getMapper(UnlockConnectorMapper::class.java)
        val req = mapper.genToCoreResp(
            UnlockConnectorResp(
                UnlockStatusEnumType.UnknownConnector,
                StatusInfoType("reason", "additional")
            )
        )
        expectThat(req)
            .and { get { status }.isEqualTo(UnlockStatus.NotSupported) }

        val resp = mapper.coreToGenReq(UnlockConnectorReq(1))
        expectThat(resp)
            .and { get { connectorId }.isEqualTo(1) }
            .and { get { evseId }.isEqualTo(1) }
    }

    @Test
    fun remoteStartTransactionMapper() {
        val mapper: RemoteStartTransactionMapper = Mappers.getMapper(RemoteStartTransactionMapper::class.java)
        val resp = mapper.genToCoreResp(RequestStartTransactionResp(RequestStartStopStatusEnumType.Accepted))
        expectThat(resp)
            .and { get { status }.isEqualTo(RemoteStartStopStatus.Accepted) }

        val req = mapper.coreToGenReq(
            RemoteStartTransactionReq(
                "tag1", 11,
                ChargingProfile(
                    12, 13, ChargingProfilePurposeType.ChargePointMaxProfile, ChargingProfileKindType.Absolute,
                    ChargingSchedule(ChargingRateUnitType.A, listOf(ChargingSchedulePeriod(1, 1.3)))
                )
            ), 10
        )
        expectThat(req)
            .and { get { idToken.idToken }.isEqualTo("tag1") }
            .and { get { idToken.type }.isEqualTo(IdTokenEnumType.Central) }
            .and { get { remoteStartId }.isEqualTo(10) }
            .and { get { evseId }.isEqualTo(11) }
            .and { get { chargingProfile?.id }.isEqualTo(12) }
            .and { get { chargingProfile?.stackLevel }.isEqualTo(13) }
            .and { get { chargingProfile?.chargingProfilePurpose }.isEqualTo(ChargingProfilePurposeEnumType.ChargingStationMaxProfile) }
            .and { get { chargingProfile?.chargingProfileKind }.isEqualTo(ChargingProfileKindEnumType.Absolute) }
            .and {
                get { chargingProfile?.chargingSchedule }.isEqualTo(
                    listOf(
                        ChargingScheduleType(
                            null, ChargingRateUnitEnumType.A,
                            listOf(ChargingSchedulePeriodType(1, 1.3))
                        )
                    )
                )
            }
    }

    @Test
    fun remoteStopTransactionMapper() {
        val mapper: RemoteStopTransactionMapper = Mappers.getMapper(RemoteStopTransactionMapper::class.java)
        val resp = mapper.genToCoreResp(
            RequestStopTransactionResp(RequestStartStopStatusEnumType.Rejected, StatusInfoType("reason", "additional"))
        )
        expectThat(resp)
            .and { get { status }.isEqualTo(RemoteStartStopStatus.Rejected) }

        val req = mapper.coreToGenReq(RemoteStopTransactionReq(1))
        expectThat(req)
            .and { get { transactionId }.isEqualTo("1") }
    }

    @Test
    fun changeConfigurationMapper() {
        val mapper: ChangeConfigurationMapper = Mappers.getMapper(ChangeConfigurationMapper::class.java)
        val resp = mapper.genToCoreResp(
            SetVariablesResp(
                listOf(
                    SetVariableResultType(
                        attributeStatus = SetVariableStatusEnumType.NotSupportedAttributeType,
                        component = ComponentType("component"),
                        variable = VariableType("variable")
                    )
                )
            )
        )
        expectThat(resp)
            .and { get { status }.isEqualTo(ConfigurationStatus.NotSupported) }


        expectThrows<IllegalStateException> {
            mapper.genToCoreResp(
                SetVariablesResp(
                    listOf(
                        SetVariableResultType(
                            SetVariableStatusEnumType.NotSupportedAttributeType,
                            ComponentType("component"), VariableType("variable")
                        ),
                        SetVariableResultType(
                            SetVariableStatusEnumType.Accepted,
                            ComponentType("component"), VariableType("variable")
                        )
                    )
                )
            )
        }

        val req = mapper.coreToGenReq(ChangeConfigurationReq("key", "value"))
        expectThat(req)
            .and { get { setVariableData[0].attributeValue }.isEqualTo("value") }
            .and { get { setVariableData[0].variable.name }.isEqualTo("key") }
            .and { get { setVariableData[0].component.name }.isEqualTo("key") }
    }

    @Test
    fun getConfiguration() {
        val mapper: GetConfigurationMapper = Mappers.getMapper(GetConfigurationMapper::class.java)

        val reqAll = mapper.coreToGenGetAllVariablesReq()
        expectThat(reqAll)
            .and { get { this }.isA<GetAllVariablesReq>() }

        val resp = mapper.genToCoreGetVariablesResp(
            GetVariablesResp(
                listOf(
                    GetVariableResultType(
                        attributeStatus = GetVariableStatusEnumType.Accepted,
                        component = ComponentType("global"),
                        variable = VariableType("variable-1", "instance"),
                        readonly = true,
                        attributeValue = "123"
                    ),
                    GetVariableResultType(
                        attributeStatus = GetVariableStatusEnumType.NotSupportedAttributeType,
                        component = ComponentType("global"),
                        variable = VariableType("variable-2", "instance"),
                    )
                )
            )
        )
        expectThat(resp)
            .and { get { configurationKey }.isEqualTo(listOf(KeyValue("variable-1instance", true, "123"))) }
            .and { get { unknownKey }.isEqualTo(listOf("variable-2instance")) }

        val req = mapper.coreToGenGetVariablesReq(GetConfigurationReq(listOf("variable1", "variable2")))
        expectThat(req)
            .and { get { getVariableData[0].variable.name }.isEqualTo("variable1") }
            .and { get { getVariableData[1].variable.name }.isEqualTo("variable2") }
            .and { get { getVariableData[0].component.name }.isEqualTo("variable1") }
            .and { get { getVariableData[1].component.name }.isEqualTo("variable2") }
    }

    @Test
    fun cancelReservationMapper() {
        val mapper: CancelReservationMapper = Mappers.getMapper(CancelReservationMapper::class.java)
        val resp = mapper.genToCoreResp(CancelReservationRespGen(CancelReservationStatusEnumType.Rejected))
        expectThat(resp)
            .and { get { status }.isEqualTo(CancelReservationStatus.Rejected) }

        val req = mapper.coreToGenReq(CancelReservationReq(3))
        expectThat(req)
            .and { get { reservationId }.isEqualTo(3) }
    }

    @Test
    fun requestFirmwareStatusNotificationMapper() {
        val mapper: FirmwareStatusNotificationMapper = Mappers.getMapper(FirmwareStatusNotificationMapper::class.java)

        val req = mapper.genToCoreReq(FirmwareStatusNotificationReq(FirmwareStatusEnumType.InstallScheduled))
        expectThat(req)
            .and { get { status }.isEqualTo(FirmwareStatus.Idle) }
    }
    @Test
    fun clearChargingProfileMapper() {
        val mapper: ClearChargingProfileMapper = Mappers.getMapper(ClearChargingProfileMapper::class.java)
        val req = mapper.genToCoreResp(
            ClearChargingProfileResp(
                ClearChargingProfileStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
        expectThat(req)
            .and { get { status }.isEqualTo(ClearChargingProfileStatus.Accepted) }

        val resp =
            mapper.coreToGenReq(ClearChargingProfileReq(1, 1, ChargingProfilePurposeType.ChargePointMaxProfile, 1))
        expectThat(resp)
            .and { get { chargingProfileId }.isEqualTo(1) }
            .and { get { chargingProfileCriteria?.evseId }.isEqualTo(1) }
            .and { get { chargingProfileCriteria?.chargingProfilePurpose }.isEqualTo(ChargingProfilePurposeEnumType.ChargingStationMaxProfile) }
            .and { get { chargingProfileCriteria?.stackLevel }.isEqualTo(1) }
    }

    @Test
    fun getCompositeScheduleMapper() {
        val mapper: GetCompositeScheduleMapper = Mappers.getMapper(GetCompositeScheduleMapper::class.java)
        val resp = mapper.genToCoreResp(
            GetCompositeScheduleResp(
                GenericStatusEnumType.Accepted,
                CompositeScheduleType(
                    evseId = 1,
                    duration = 2,
                    scheduleStart = Instant.parse("2022-02-15T00:00:00.001Z"),
                    chargingRateUnit = ChargingRateUnitEnumType.A,
                    chargingSchedulePeriod = listOf(ChargingSchedulePeriodType(1, 3.0))
                ),
                StatusInfoType("reason", "additional")
            )
        )
        expectThat(resp)
            .and { get { status }.isEqualTo(GetCompositeScheduleStatus.Accepted) }
            .and { get { connectorId }.isEqualTo(1) }
            .and { get { scheduleStart }.isEqualTo(Instant.parse("2022-02-15T00:00:00.001Z")) }
            .and { get { chargingSchedule?.duration }.isEqualTo(2) }
            .and { get { chargingSchedule?.startSchedule }.isEqualTo(Instant.parse("2022-02-15T00:00:00.001Z")) }
            .and { get { chargingSchedule?.chargingRateUnit }.isEqualTo(ChargingRateUnitType.A) }
            .and { get { chargingSchedule?.chargingSchedulePeriod }.isEqualTo(listOf(ChargingSchedulePeriod(1, 3.0))) }

        val req = mapper.coreToGenReq(GetCompositeScheduleReq(1, 2, ChargingRateUnitType.A))
        expectThat(req)
            .and { get { evseId }.isEqualTo(1) }
            .and { get { duration }.isEqualTo(2) }
            .and { get { chargingRateUnit }.isEqualTo(ChargingRateUnitEnumType.A) }
    }

    @Test
    fun getLocalListVersionMapper() {
        val mapper: GetLocalListVersionMapper = Mappers.getMapper(GetLocalListVersionMapper::class.java)
        val resp = mapper.genToCoreResp(GetLocalListVersionResp(1))
        expectThat(resp).and { get { listVersion }.isEqualTo(1) }

        val req = mapper.coreToGenReq(GetLocalListVersionReq())
        expectThat(req).and { get { req }.isA<GetLocalListVersionReqGen>() }
    }


    @Test
    fun updateFirmwareMapper() {
        val mapper: UpdateFirmwareMapper = Mappers.getMapper(UpdateFirmwareMapper::class.java)

        val req = mapper.coreToGenReq(
            UpdateFirmwareReq(
                location = "http://www.ietf.org/rfc/rfc2396.txt", // URI
                retries = 3,
                retrieveDate = Instant.parse("2022-02-15T00:00:00.000Z"),
                retryInterval = 1
            )
        )
        expectThat(req) {
            get { req.retries }.isEqualTo(3)
            get { req.retryInterval }.isEqualTo(1)
            get { req.requestId }.isEqualTo(0)
            get { req.firmware }.isEqualTo(
                FirmwareType(
                    location = "http://www.ietf.org/rfc/rfc2396.txt", // URI
                    retrieveDateTime = Instant.parse("2022-02-15T00:00:00.000Z"),
                    installDateTime = null,
                    signingCertificate = null,
                    signature = null
                )
            )
        }
    }

    @Test
    fun sendLocalListMapper() {
        val mapper: SendLocalListMapper = Mappers.getMapper(SendLocalListMapper::class.java)
        val resp = mapper.genToCoreResp(
            SendLocalListResp(
                SendLocalListStatusEnumType.Accepted,
                StatusInfoType("reason", "additional")
            )
        )
        expectThat(resp).and { get { status }.isEqualTo(UpdateStatus.Accepted) }

        val req = mapper.coreToGenReq(
            SendLocalListReq(1, UpdateType.Differential, listOf(AuthorizationData("")))
        )
        expectThat(req)
            .and { get { versionNumber }.isEqualTo(1) }
            .and { get { updateType }.isEqualTo(UpdateEnumType.Differential) }
            .and {
                get { localAuthorizationList }.isEqualTo(
                    listOf(AuthorizationDataGen(IdTokenType("", IdTokenEnumType.Central)))
                )
            }
    }


    @Test
    fun triggerMessage() {
        val mapper: TriggerMessageMapper = Mappers.getMapper(TriggerMessageMapper::class.java)
        val resp = mapper.genToCoreResp(
            TriggerMessageResp(
                status = TriggerMessageStatusEnumType.Accepted,
                statusInfo = StatusInfoType("reason", "additional")
            )
        )
        expectThat(resp) {
            get { status }.isEqualTo(TriggerMessageStatus.Accepted)
        }

        val req = mapper.coreToGenReq(
            TriggerMessageReq(
                requestedMessage = MessageTrigger.Heartbeat,
                connectorId = 1
            )
        )
        expectThat(req) {
            get { requestedMessage }.isEqualTo(MessageTriggerEnumType.Heartbeat)
            get { evse }.isEqualTo(EVSEType(1, 1))
        }
    }

    @Test
    fun setChargingProfileMapper() {
        val mapper: SetChargingProfileMapper = Mappers.getMapper(SetChargingProfileMapper::class.java)
        val resp = mapper.genToCoreResp(
            SetChargingProfileResp(ChargingProfileStatusEnumType.Accepted, StatusInfoType("reason", "additional"))
        )
        expectThat(resp).and { get { status }.isEqualTo(ChargingProfileStatus.Accepted) }

        val req = mapper.coreToGenReq(
            SetChargingProfileReq(
                1, ChargingProfile(
                    chargingProfileId = 1,
                    stackLevel = 2,
                    chargingProfilePurpose = ChargingProfilePurposeType.ChargePointMaxProfile,
                    chargingProfileKind = ChargingProfileKindType.Absolute,
                    chargingSchedule = ChargingSchedule(ChargingRateUnitType.A, listOf(ChargingSchedulePeriod(1, 1.3)))
                )
            )
        )
        expectThat(req)
            .and { get { evseId }.isEqualTo(1) }
            .and { get { chargingProfile.id }.isEqualTo(1) }
            .and { get { chargingProfile.stackLevel }.isEqualTo(2) }
            .and { get { chargingProfile.chargingProfilePurpose }.isEqualTo(ChargingProfilePurposeEnumType.ChargingStationMaxProfile) }
            .and { get { chargingProfile.chargingProfileKind }.isEqualTo(ChargingProfileKindEnumType.Absolute) }
            .and {
                get { chargingProfile.chargingSchedule }.isEqualTo(
                    listOf(
                        ChargingScheduleType(
                            null,
                            ChargingRateUnitEnumType.A,
                            listOf(ChargingSchedulePeriodType(1, 1.3))
                        )
                    )
                )
            }
    }

    @Test
    fun reserveNowMapper() {
        val mapper: ReserveNowMapper = Mappers.getMapper(ReserveNowMapper::class.java)
        val resp = mapper.genToCoreResp(
            ReserveNowResp(
                status = ReserveNowStatusEnumType.Accepted,
                statusInfo = StatusInfoType("reason", "additional")
            )
        )
        expectThat(resp) {
            get { status }.isEqualTo(ReservationStatus.Accepted)
        }

        val req = mapper.coreToGenReq(ReserveNowReq(
            connectorId = 1,
            expiryDate = Instant.parse("2022-02-15T00:00:00.000Z"),
            idTag = "idTag",
            parentIdTag = "idTagParent",
            reservationId = 2
        ))
        expectThat(req) {
            get { id }.isEqualTo(2)
            get { expiryDateTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00.000Z"))
            get { idToken }.isEqualTo(IdTokenType("idTag", IdTokenEnumType.Central))
            get { evseId }.isEqualTo(1)
            get { groupIdToken }.isEqualTo(IdTokenType("idTagParent", IdTokenEnumType.Central))
        }
    }

}