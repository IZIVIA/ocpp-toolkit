package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.core20.model.reservenow.ReserveNowResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.reservenow.ReserveNowReq as ReserveNowReqGen
import fr.simatix.cs.simulator.api.model.reservenow.ReserveNowResp as ReserveNowRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ReserveNowMapper {

    fun genToCoreResp(reserveNowResp: ReserveNowRespGen): ReserveNowResp

    fun coreToGenReq(reserveNowReq: ReserveNowReq): ReserveNowReqGen

}