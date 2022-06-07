package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.common.IdTokenInfoType
import com.izivia.ocpp.api.model.sendlocallist.enumeration.SendLocalListStatusEnumType
import com.izivia.ocpp.api.model.sendlocallist.enumeration.UpdateEnumType
import com.izivia.ocpp.core16.model.common.IdTagInfo
import com.izivia.ocpp.core16.model.sendlocallist.AuthorizationData
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core16.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core16.model.sendlocallist.enumeration.UpdateStatus
import com.izivia.ocpp.core16.model.sendlocallist.enumeration.UpdateType
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.sendlocallist.AuthorizationData as AuthorizationDataGen
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListReq as SendLocalListReqGen
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListResp as SendLocalListRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class SendLocalListMapper {

    @Named("convertSendStatus")
    fun convertSendStatus(status: SendLocalListStatusEnumType): UpdateStatus =
        UpdateStatus.valueOf(status.value)

    @Named("convertUpdateTypeStatus")
    fun convertUpdateTypeStatus(updateType: UpdateType): UpdateEnumType =
        UpdateEnumType.valueOf(updateType.value)

    @Named("convertIdTagInfo")
    fun convertIdTagInfo(info: IdTagInfo?): IdTokenInfoType? =
        if (info == null) null else CommonMapper.convertIdTagInfo(info)

    @Named("convertAuthorizationData")
    @Mapping(target = "idToken", source = "idTag", qualifiedByName = ["convertIdTag"])
    @Mapping(target = "idTokenInfo", source = "idTagInfo", qualifiedByName = ["convertIdTagInfo"])
    abstract fun convertAuthorizationData(data: AuthorizationData): AuthorizationDataGen

    @Named("convertAuthorizationListStatus")
    fun convertAuthorizationListStatus(list: List<AuthorizationData>?): List<AuthorizationDataGen>? =
        list?.map { convertAuthorizationData(it) }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertSendStatus"])
    abstract fun genToCoreResp(sendLocalListResp: SendLocalListRespGen?): SendLocalListResp

    @Mapping(target = "versionNumber", source = "listVersion")
    @Mapping(target = "updateType", source = "updateType", qualifiedByName = ["convertUpdateTypeStatus"])
    @Mapping(
        target = "localAuthorizationList",
        source = "localAuthorizationList",
        qualifiedByName = ["convertAuthorizationListStatus"]
    )
    abstract fun coreToGenReq(sendLocalListReq: SendLocalListReq): SendLocalListReqGen

}