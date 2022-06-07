package com.izivia.ocpp.adapter20.mapper

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.gettransactionstatus.GetTransactionStatusReq as GetTransactionStatusReqGen
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusReq

import com.izivia.ocpp.api.model.gettransactionstatus.GetTransactionStatusResp as GetTransactionStatusRespGen
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusResp

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetTransactionStatusMapper
{
    abstract fun genToCoreResp(getTransactionStatusRespGen: GetTransactionStatusRespGen): GetTransactionStatusResp

    fun coreToGenReq(getTransactionStatusRespGen: GetTransactionStatusReq?): GetTransactionStatusReqGen
            = GetTransactionStatusReqGen(getTransactionStatusRespGen?.transactionId)
}