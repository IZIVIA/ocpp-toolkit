package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.notifycustomerinformation.NotifyCustomerInformationReq
import fr.simatix.cs.simulator.core20.model.notifycustomerinformation.NotifyCustomerInformationResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationReq as NotifyCustomerInformationReqGen
import fr.simatix.cs.simulator.api.model.notifycustomerinformation.NotifyCustomerInformationResp as NotifyCustomerInformationRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface NotifyCustomerInformationMapper {

    fun genToCoreReq(notifyCustomerInformationReq: NotifyCustomerInformationReqGen?): NotifyCustomerInformationReq

    fun coreToGenResp(notifyCustomerInformationResp: NotifyCustomerInformationResp?): NotifyCustomerInformationRespGen
}