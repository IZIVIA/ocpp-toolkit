package fr.simatix.cs.simulator

import fr.simatix.cs.simulator.adapter16.mapper.ChangeAvailabilityMapper
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.OperationalStatusEnumType
import fr.simatix.cs.simulator.api.model.common.EVSEType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core16.model.changeavailability.enumeration.AvailabilityStatus
import fr.simatix.cs.simulator.core16.model.changeavailability.enumeration.AvailabilityType
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityResp as ChangeAvailabilityRespGen

class MapperTest {
    @Test
    fun changeAvailabilityMapper() {
        val mapper: ChangeAvailabilityMapper = Mappers.getMapper(ChangeAvailabilityMapper::class.java)
        val resp = mapper.genToCoreResp(ChangeAvailabilityRespGen(ChangeAvailabilityStatusEnumType.Accepted,
            StatusInfoType("reason","additional")
        ))
        expectThat(resp)
            .and { get {status}.isEqualTo(AvailabilityStatus.Accepted) }

        val req = mapper.coreToGenReq(ChangeAvailabilityReq(1,AvailabilityType.Operative))
        expectThat(req)
            .and { get {operationalStatus}.isEqualTo(OperationalStatusEnumType.Operative) }
            .and { get {evse}.isEqualTo(EVSEType(1,1)) }
    }
}