package fr.simatix.cs.simulator

import fr.simatix.cs.simulator.adapter16.mapper.ChangeAvailabilityMapper
import fr.simatix.cs.simulator.adapter16.mapper.ClearCacheMapper
import fr.simatix.cs.simulator.adapter16.mapper.UnlockConnectorMapper
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.OperationalStatusEnumType
import fr.simatix.cs.simulator.api.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.api.model.common.EVSEType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core16.model.changeavailability.enumeration.AvailabilityStatus
import fr.simatix.cs.simulator.core16.model.changeavailability.enumeration.AvailabilityType
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core16.model.clearcache.enumeration.ClearCacheStatus
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core16.model.unlockconnector.enumeration.UnlockStatus
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityResp as ChangeAvailabilityRespGen
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheReq as ClearCacheReqGen
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheResp as ClearCacheRespGen

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
}