package com.izivia.ocpp.adapter15.mapper

import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionReq as GetLocalListVersionReqGen
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionResp as GetLocalListVersionRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetLocalListVersionMapper {

    @Mapping(target = "listVersion", source = "versionNumber")
    abstract fun genToCoreResp(getLocalListVersionResp: GetLocalListVersionRespGen?): GetLocalListVersionResp

    abstract fun coreToGenReq(getLocalListVersionReq: GetLocalListVersionReq): GetLocalListVersionReqGen

}
