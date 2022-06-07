package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core20.model.datatransfer.DataTransferResp
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq as DataTransferReqGen
import com.izivia.ocpp.api.model.datatransfer.DataTransferResp as DataTransferRespGen

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface DataTransferMapper {

    fun genToCoreReq(dataTransferReq: DataTransferReqGen?): DataTransferReq

    fun coreToGenResp(dataTransferResp: DataTransferResp?): DataTransferRespGen

    fun coreToGenReq(dataTransferReq: DataTransferReq?): DataTransferReqGen

    fun genToCoreResp(dataTransferResp: DataTransferRespGen?): DataTransferResp
}
