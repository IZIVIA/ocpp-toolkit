package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.setdisplaymessage.SetDisplayMessageResp
import fr.simatix.cs.simulator.api.model.setdisplaymessage.SetDisplayMessageResp as SetDisplayMessageRespGen

import fr.simatix.cs.simulator.core20.model.setdisplaymessage.SetDisplayMessageReq
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.setdisplaymessage.SetDisplayMessageReq as SetDisplayMessageReqGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetDisplayMessageMapper {
    fun genToCoreResp(setDisplayMessageResp: SetDisplayMessageRespGen?): SetDisplayMessageResp

    fun coreToGenReq(setDisplayMessageReq: SetDisplayMessageReq?): SetDisplayMessageReqGen
}