package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorReq as UnlockConnectorReqGen
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp as UnlockConnectorRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UnlockConnectorMapper {

    fun genToCoreResp(unlockConnectorResp: UnlockConnectorRespGen): UnlockConnectorResp

    fun coreToGenReq(unlockConnectorReq: UnlockConnectorReq): UnlockConnectorReqGen

}