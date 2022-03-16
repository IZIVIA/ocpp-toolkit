package fr.simatix.cs.simulator.adapter16.mapper


import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq as DataTransferReqGen
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferResp as DataTransferRespGen

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class DataTransferMapper {

    @Mapping(target = "data", source = "data", qualifiedByName = ["dataToString"])
    abstract fun genToCoreReq(dataTransferReq: DataTransferReqGen?): DataTransferReq

    @Named("dataToString")
    fun dataToString(data: Any?): String? = data?.toString()

    abstract fun coreToGenResp(dataTransferResp: DataTransferResp?): DataTransferRespGen

}
