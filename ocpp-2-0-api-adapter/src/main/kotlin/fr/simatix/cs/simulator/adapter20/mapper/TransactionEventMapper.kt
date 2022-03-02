package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.common.IdTokenInfoType
import fr.simatix.cs.simulator.core20.model.common.MessageContentType
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq as TransactionEventReqGen
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventResp as TransactionEventRespGen
import fr.simatix.cs.simulator.api.model.common.IdTokenInfoType as IdTokenInfoTypeGen
import fr.simatix.cs.simulator.api.model.common.MessageContentType as MessageContentTypeGen
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