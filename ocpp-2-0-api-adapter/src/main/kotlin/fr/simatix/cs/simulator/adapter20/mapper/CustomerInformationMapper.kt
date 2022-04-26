package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.customerinformation.CustomerInformationResp
import fr.simatix.cs.simulator.api.model.customerinformation.CustomerInformationResp as CustomerInformationRespGen
import fr.simatix.cs.simulator.core20.model.customerinformation.CustomerInformationReq
import fr.simatix.cs.simulator.api.model.customerinformation.CustomerInformationReq as CustomerInformationReqGen
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CustomerInformationMapper {
    fun genToCoreResp(customerInformationResp: CustomerInformationRespGen?): CustomerInformationResp

    fun coreToGenReq(customerInformationReq: CustomerInformationReq?): CustomerInformationReqGen
}
