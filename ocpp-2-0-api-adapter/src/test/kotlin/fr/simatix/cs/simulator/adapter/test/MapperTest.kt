package fr.simatix.cs.simulator.adapter.test

import fr.simatix.cs.simulator.adapter20.mapper.ChangeAvailabilityMapper
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.core20.model.changeavailability.enumeration.OperationalStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.EVSEType
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityResp as ChangeAvailabilityRespGen
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType as ChangeAvailabilityStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.OperationalStatusEnumType as OperationalStatusEnumTypeGen
import fr.simatix.cs.simulator.api.model.common.EVSEType as EVSETypeGen

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
}