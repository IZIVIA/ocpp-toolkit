package com.izivia.ocpp.adapter15.mapper

import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.api.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.api.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.api.model.transactionevent.TransactionEventResp
import com.izivia.ocpp.core15.model.common.MeterValue
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core15.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.core15.model.stoptransaction.TransactionData
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class StopTransactionMapper {

    @Named("convertMeterValueToMeterStop")
    fun convertMeterValueToMeterStop(meterValues: List<MeterValueType>?): Int =
        CommonMapper.filterMeterValues(meterValues, "stop", ReadingContextEnumType.TransactionEnd)

    @Named("convertToTransactionData")
    fun convertToTransactionData(meterValues: List<MeterValueType>?): List<TransactionData>? =
        meterValues?.map { (s, t) ->
            MeterValue(
                timestamp = t,
                value = s.filter {
                    it.context != ReadingContextEnumType.TransactionEnd ||
                            it.measurand != MeasurandEnumType.EnergyActiveImportRegister
                }.map { CommonMapper.convertSampledValue(it) },
            )
        }?.filter { it.value.isNotEmpty() }
            ?.map { TransactionData(listOf(it)) }

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
    @Mapping(
        target = "transactionData",
        source = "transactionReq.meterValue",
        qualifiedByName = ["convertToTransactionData"]
    )
    abstract fun genToCoreReq(transactionReq: TransactionEventReq?, transactionId: Int): StopTransactionReq

    fun coreToGenResp(transactionResp: StopTransactionResp): TransactionEventResp {
        val idTokenInfo = transactionResp.idTagInfo?.let { CommonMapper.convertIdTagInfo(it) }
        return TransactionEventResp(idTokenInfo = idTokenInfo)

    }

}
