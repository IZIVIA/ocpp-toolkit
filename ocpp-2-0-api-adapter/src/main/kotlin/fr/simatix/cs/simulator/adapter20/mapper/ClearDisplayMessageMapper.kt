package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import fr.simatix.cs.simulator.core20.model.cleardisplaymessage.ClearDisplayMessageResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.cleardisplaymessage.ClearDisplayMessageReq as ClearDisplayMessageReqGen
import fr.simatix.cs.simulator.api.model.cleardisplaymessage.ClearDisplayMessageResp as ClearDisplayMessageRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ClearDisplayMessageMapper {

    fun genToCoreResp(clearDisplayMessageResp: ClearDisplayMessageRespGen): ClearDisplayMessageResp

    fun coreToGenReq(clearDisplayMessageReq: ClearDisplayMessageReq): ClearDisplayMessageReqGen

}