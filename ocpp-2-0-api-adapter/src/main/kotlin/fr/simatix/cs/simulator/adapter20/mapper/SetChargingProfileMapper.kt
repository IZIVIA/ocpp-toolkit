package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core20.model.setchargingprofile.SetChargingProfileResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.setchargingprofile.SetChargingProfileReq as SetChargingProfileReqGen
import fr.simatix.cs.simulator.api.model.setchargingprofile.SetChargingProfileResp as SetChargingProfileRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetChargingProfileMapper {

    fun genToCoreResp(setChargingProfileResp: SetChargingProfileRespGen?): SetChargingProfileResp

    fun coreToGenReq(setChargingProfileReq: SetChargingProfileReq): SetChargingProfileReqGen

}