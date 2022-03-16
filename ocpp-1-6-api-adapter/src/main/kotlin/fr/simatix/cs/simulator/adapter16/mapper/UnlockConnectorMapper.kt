package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.core16.model.unlockconnector.enumeration.UnlockStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorReq as UnlockConnectorReqGen
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp as UnlockConnectorRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class UnlockConnectorMapper {

    @Named("convertUnlockStatus")
    fun convertUnlockStatus(status: UnlockStatusEnumType): UnlockStatus =
        when(status){
            UnlockStatusEnumType.UnknownConnector,
            UnlockStatusEnumType.OngoingAuthorizedTransaction -> UnlockStatus.NotSupported
            else -> UnlockStatus.valueOf(status.name)
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertUnlockStatus"])
    abstract fun genToCoreResp(unlockConnectorResp: UnlockConnectorRespGen): UnlockConnectorResp

    fun coreToGenReq(unlockConnectorReq: UnlockConnectorReq): UnlockConnectorReqGen{
        return UnlockConnectorReqGen(unlockConnectorReq.connectorId,unlockConnectorReq.connectorId)
    }

}