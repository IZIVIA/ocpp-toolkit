package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationReq as NotifyCustomerInformationReqGen
import com.izivia.ocpp.api.model.notifycustomerinformation.NotifyCustomerInformationResp as NotifyCustomerInformationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyCustomerInformationMapper {

    fun genToCoreReq(notifyCustomerInformationReq: NotifyCustomerInformationReqGen?): NotifyCustomerInformationReq

    fun coreToGenResp(notifyCustomerInformationResp: NotifyCustomerInformationResp?): NotifyCustomerInformationRespGen
}