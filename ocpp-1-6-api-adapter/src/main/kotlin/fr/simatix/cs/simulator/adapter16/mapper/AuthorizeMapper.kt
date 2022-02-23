package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.IdTokenInfoType
import fr.simatix.cs.simulator.api.model.IdTokenType
import fr.simatix.cs.simulator.api.model.enumeration.AuthorizationStatusEnumType
import fr.simatix.cs.simulator.api.model.enumeration.IdTokenEnumType
import fr.simatix.cs.simulator.core16.model.AuthorizeReq
import fr.simatix.cs.simulator.core16.model.AuthorizeResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.AuthorizeReq as AuthorizeReqGen
import fr.simatix.cs.simulator.api.model.AuthorizeResp as AuthorizeRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class AuthorizeMapper {

    @Mapping(source = "idToken.idToken", target = "idTag")
    abstract fun genToCoreReq(authorizeReq: AuthorizeReqGen?): AuthorizeReq

    fun coreToGenResp(authorizeResp: AuthorizeResp?): AuthorizeRespGen{
        val idTag = authorizeResp?.idTagInfo
        val status = AuthorizationStatusEnumType.valueOf(idTag?.status!!.name)
        val groupIdToken = if (idTag.parentIdTag != null) {
            IdTokenType(idTag.parentIdTag!!, IdTokenEnumType.Central)
        } else {
            null
        }
        val idTokenInfo = IdTokenInfoType(status = status, cacheExpiryDateTime = idTag.expiryDate, groupIdToken = groupIdToken)
        return AuthorizeRespGen(idTokenInfo)
    }

}