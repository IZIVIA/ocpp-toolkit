package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.core20.model.sendlocallist.SendLocalListResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.sendlocallist.SendLocalListReq as SendLocalListReqGen
import fr.simatix.cs.simulator.api.model.sendlocallist.SendLocalListResp as SendLocalListRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SendLocalListMapper {

    fun genToCoreResp(sendLocalListResp: SendLocalListRespGen?): SendLocalListResp

    fun coreToGenReq(sendLocalListReq: SendLocalListReq): SendLocalListReqGen

}