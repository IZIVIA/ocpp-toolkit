package com.izivia.ocpp.adapter12.mapper

import com.izivia.ocpp.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core12.model.unlockconnector.enumeration.UnlockStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorReq as UnlockConnectorReqGen
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorResp as UnlockConnectorRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class UnlockConnectorMapper {

    @Named("convertUnlockStatus")
    fun convertUnlockStatus(status: UnlockStatusEnumType): UnlockStatus =
        when (status) {
            UnlockStatusEnumType.Unlocked -> UnlockStatus.Accepted
            UnlockStatusEnumType.UnlockFailed,
            UnlockStatusEnumType.UnknownConnector,
            UnlockStatusEnumType.OngoingAuthorizedTransaction -> UnlockStatus.Rejected
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertUnlockStatus"])
    abstract fun genToCoreResp(unlockConnectorResp: UnlockConnectorRespGen): UnlockConnectorResp

    fun coreToGenReq(unlockConnectorReq: UnlockConnectorReq): UnlockConnectorReqGen{
        return UnlockConnectorReqGen(unlockConnectorReq.connectorId,unlockConnectorReq.connectorId)
    }

}
