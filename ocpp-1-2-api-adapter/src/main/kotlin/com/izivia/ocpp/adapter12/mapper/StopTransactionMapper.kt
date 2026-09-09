package com.izivia.ocpp.adapter12.mapper

import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.api.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.api.model.transactionevent.TransactionEventResp
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class StopTransactionMapper {

    @Named("convertMeterValueToMeterStop")
    fun convertMeterValueToMeterStop(meterValues: List<MeterValueType>?): Int =
        CommonMapper.filterMeterValues(meterValues, "stop", ReadingContextEnumType.TransactionEnd)

    @Named("convertIdTokenTypeOrNull")
    fun convertIdTokenTypeOrNull(idToken: IdTokenType?): String? =
        idToken?.idToken

    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "idTag", source = "transactionReq.idToken", qualifiedByName = ["convertIdTokenTypeOrNull"])
    @Mapping(
        target = "meterStop",
        source = "transactionReq.meterValue",
        qualifiedByName = ["convertMeterValueToMeterStop"]
    )
    abstract fun genToCoreReq(transactionReq: TransactionEventReq?, transactionId: Int): StopTransactionReq

    fun coreToGenResp(transactionResp: StopTransactionResp): TransactionEventResp {
        val idTokenInfo = transactionResp.idTagInfo?.let { CommonMapper.convertIdTagInfo(it) }
        return TransactionEventResp(idTokenInfo = idTokenInfo)

    }

}
