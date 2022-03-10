package fr.simatix.cs.simulator.adapter.test

import fr.simatix.cs.simulator.adapter20.mapper.ChangeAvailabilityMapper
import fr.simatix.cs.simulator.adapter20.mapper.ClearCacheMapper
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.OperationalStatusEnumType
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core20.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.EVSEType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
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

class MapperTest {
    @Test
    fun changeAvailabilityMapper() {
        val mapper: ChangeAvailabilityMapper = Mappers.getMapper(ChangeAvailabilityMapper::class.java)
        val resp = mapper.genToCoreResp(ChangeAvailabilityRespGen(ChangeAvailabilityStatusEnumTypeGen.Accepted))
        expectThat(resp)
            .and { get {status}.isEqualTo(ChangeAvailabilityStatusEnumType.Accepted) }
            .and { get {statusInfo}.isEqualTo(null) }

        val req = mapper.coreToGenReq(ChangeAvailabilityReq(OperationalStatusEnumType.Operative, EVSEType(1)))
        expectThat(req)
            .and { get {operationalStatus}.isEqualTo(OperationalStatusEnumTypeGen.Operative) }
            .and { get {evse}.isEqualTo(EVSETypeGen(1)) }
    }

    @Test
    fun clearCacheMapper() {
        val mapper: ClearCacheMapper = Mappers.getMapper(ClearCacheMapper::class.java)
        val resp = mapper.genToCoreResp(ClearCacheRespGen(ClearCacheStatusEnumTypeGen.Accepted, StatusInfoTypeGen("reason","additional")))
        expectThat(resp)
            .and { get {status}.isEqualTo(ClearCacheStatusEnumType.Accepted) }
            .and { get {statusInfo}.isEqualTo(StatusInfoType("reason","additional")) }

        val req = mapper.coreToGenReq(ClearCacheReq())
        expectThat(req)
            .and { get {req}.isA<ClearCacheReqGen>() }
    }
}