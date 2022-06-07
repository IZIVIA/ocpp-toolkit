package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.core20.model.common.IdTokenInfoType
import com.izivia.ocpp.core20.model.common.MessageContentType
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.transactionevent.TransactionEventReq as TransactionEventReqGen
import com.izivia.ocpp.api.model.transactionevent.TransactionEventResp as TransactionEventRespGen
import com.izivia.ocpp.api.model.common.IdTokenInfoType as IdTokenInfoTypeGen
import com.izivia.ocpp.api.model.common.MessageContentType as MessageContentTypeGen
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class TransactionEventMapper {

    abstract fun mapIdTokenInfo(idTokenInfo : IdTokenInfoType?) : IdTokenInfoTypeGen

    abstract fun mapMessageContent(message: MessageContentType?): MessageContentTypeGen

    @Mapping(target = "meterValue", source = "meterValue", qualifiedByName = ["meterValue"])
    abstract fun genToCoreReq(transactionEventReq: TransactionEventReqGen?): TransactionEventReq

    fun coreToGenResp(transactionEventResp: TransactionEventResp): TransactionEventRespGen =
        TransactionEventRespGen(
            transactionEventResp.totalCost,
            transactionEventResp.chargingPriority,
            mapIdTokenInfo(transactionEventResp.idTokenInfo),
            mapMessageContent(transactionEventResp.updatedPersonalMessage)
        )
}