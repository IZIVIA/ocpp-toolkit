package com.izivia.ocpp.adapter15.mapper


import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core15.model.datatransfer.DataTransferResp
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq as DataTransferReqGen
import com.izivia.ocpp.api.model.datatransfer.DataTransferResp as DataTransferRespGen

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

    abstract fun coreToGenReq(dataTransferReq: DataTransferReq?): DataTransferReqGen

    @Mapping(target = "data", source = "data", qualifiedByName = ["dataToString"])
    abstract fun genToCoreResp(dataTransferResp: DataTransferRespGen?): DataTransferResp

}
