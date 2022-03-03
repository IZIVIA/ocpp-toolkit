package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.common.MeterValueType
import fr.simatix.cs.simulator.api.model.common.enumeration.ReadingContextEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionReq
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class StartTransactionMapper {

    @Named("convertMeterValueToMeterStart")
    fun convertMeterValueToMeterStart(meterValues: List<MeterValueType>?): Int =
        CommonMapper.filterMeterValues(meterValues, "start", ReadingContextEnumType.TransactionBegin)

    @Mapping(target = "connectorId", source = "evse", qualifiedByName = ["convertEVSEType"])
    @Mapping(target = "idTag", source = "idToken", qualifiedByName = ["convertIdTokenType"])
    @Mapping(target = "meterStart", source = "meterValue", qualifiedByName = ["convertMeterValueToMeterStart"])
    abstract fun genToCoreReq(transactionEventReq: TransactionEventReq?): StartTransactionReq

    fun coreToGenResp(transactionEventResp: StartTransactionResp): TransactionEventResp =
        TransactionEventResp(idTokenInfo = CommonMapper.convertIdTagInfo(transactionEventResp.idTagInfo))

}