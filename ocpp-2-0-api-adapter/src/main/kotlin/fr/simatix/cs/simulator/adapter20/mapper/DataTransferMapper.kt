package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq as DataTransferReqGen
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferResp as DataTransferRespGen

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface DataTransferMapper {

    fun genToCoreReq(dataTransferReq: DataTransferReqGen?): DataTransferReq

    fun coreToGenResp(dataTransferResp: DataTransferResp?): DataTransferRespGen

}
