package fr.simatix.cs.simulator.adapter.test

import fr.simatix.cs.simulator.adapter20.mapper.*
import fr.simatix.cs.simulator.api.model.common.enumeration.AttributeEnumType as AttributeEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.ComponentType as ComponentTypeGen
import fr.simatix.cs.simulator.api.model.common.VariableType as VariableTypeGen
import fr.simatix.cs.simulator.api.model.getvariables.GetVariableResultType as GetVariableResultTypeGen
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.api.model.getvariables.enumeration.GetVariableStatusEnumType as GetVariableStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.enumeration.RequestStartStopStatusEnumType as RequestStartStopStatusEnumTypeGen
import fr.simatix.cs.simulator.core20.model.common.enumeration.RequestStartStopStatusEnumType
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.api.model.setvariables.SetVariableResultType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.api.model.setvariables.enumeration.SetVariableStatusEnumType as SetVariableStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.OperationalStatusEnumType
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core20.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.*
import fr.simatix.cs.simulator.core20.model.common.enumeration.AttributeEnumType
import fr.simatix.cs.simulator.api.model.common.IdTokenType as IdTokenTypeGen
import fr.simatix.cs.simulator.core20.model.common.enumeration.IdTokenEnumType
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariableDataType
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.core20.model.getvariables.enumeration.GetVariableStatusEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.*
import fr.simatix.cs.simulator.api.model.common.enumeration.IdTokenEnumType as IdTokenEnumTypeGen
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.ChargingProfileKindEnumType
import fr.simatix.cs.simulator.api.model.remotestart.enumeration.ChargingProfileKindEnumType as ChargingProfileKindEnumTypeGen
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.ChargingProfilePurposeEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.ChargingRateUnitEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.enumeration.RecurrencyKindEnumType
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariableDataType
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.core20.model.setvariables.enumeration.SetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.remotestart.enumeration.RecurrencyKindEnumType as RecurrencyKindEnumTypeGen
import fr.simatix.cs.simulator.api.model.remotestart.enumeration.ChargingProfilePurposeEnumType as ChargingProfilePurposeEnumTypeGen
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core20.model.unlockconnector.enumeration.UnlockStatusEnumType
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityResp as ChangeAvailabilityRespGen
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType as ChangeAvailabilityStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.OperationalStatusEnumType as OperationalStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheReq as ClearCacheReqGen
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheResp as ClearCacheRespGen
import fr.simatix.cs.simulator.api.model.clearcache.enumeration.ClearCacheStatusEnumType as ClearCacheStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.EVSEType as EVSETypeGen
import fr.simatix.cs.simulator.api.model.common.StatusInfoType as StatusInfoTypeGen
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

}