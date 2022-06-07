package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileReq as SetChargingProfileReqGen
import com.izivia.ocpp.api.model.setchargingprofile.SetChargingProfileResp as SetChargingProfileRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface SetChargingProfileMapper {

    fun genToCoreResp(setChargingProfileResp: SetChargingProfileRespGen?): SetChargingProfileResp

    fun coreToGenReq(setChargingProfileReq: SetChargingProfileReq): SetChargingProfileReqGen

}