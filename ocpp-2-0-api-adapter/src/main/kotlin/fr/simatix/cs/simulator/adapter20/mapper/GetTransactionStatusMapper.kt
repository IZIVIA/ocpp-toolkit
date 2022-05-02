package fr.simatix.cs.simulator.adapter20.mapper

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.gettransactionstatus.GetTransactionStatusReq as GetTransactionStatusReqGen
import fr.simatix.cs.simulator.core20.model.gettransactionstatus.GetTransactionStatusReq

import fr.simatix.cs.simulator.api.model.gettransactionstatus.GetTransactionStatusResp as GetTransactionStatusRespGen
import fr.simatix.cs.simulator.core20.model.gettransactionstatus.GetTransactionStatusResp

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetTransactionStatusMapper
{
    abstract fun genToCoreResp(getTransactionStatusRespGen: GetTransactionStatusRespGen): GetTransactionStatusResp

    fun coreToGenReq(getTransactionStatusRespGen: GetTransactionStatusReq?): GetTransactionStatusReqGen
            = GetTransactionStatusReqGen(getTransactionStatusRespGen?.transactionId)
}