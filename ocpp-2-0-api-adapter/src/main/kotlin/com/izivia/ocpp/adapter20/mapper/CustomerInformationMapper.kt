package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationResp
import com.izivia.ocpp.api.model.customerinformation.CustomerInformationResp as CustomerInformationRespGen
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationReq
import com.izivia.ocpp.api.model.customerinformation.CustomerInformationReq as CustomerInformationReqGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CustomerInformationMapper {
    fun genToCoreResp(customerInformationResp: CustomerInformationRespGen?): CustomerInformationResp

    fun coreToGenReq(customerInformationReq: CustomerInformationReq?): CustomerInformationReqGen
}
