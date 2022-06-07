package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesResp
import org.mapstruct.Mapper
import com.izivia.ocpp.api.model.reportchargingprofiles.ReportChargingProfilesReq as ReportChargingProfilesReqGen
import com.izivia.ocpp.api.model.reportchargingprofiles.ReportChargingProfilesResp as ReportChargingProfilesRespGen
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ReportChargingProfilesMapper {
    fun genToCoreReq(reportChargingProfileReq: ReportChargingProfilesReqGen?): ReportChargingProfilesReq

    fun coreToGenResp(reportChargingProfileResp: ReportChargingProfilesResp?): ReportChargingProfilesRespGen
}