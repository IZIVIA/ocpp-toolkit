package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.common.MeterValueType
import fr.simatix.cs.simulator.api.model.common.enumeration.ReadingContextEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.EVSEType
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

    @Named("convertEVSEType")
    fun convertEVSEType(evse: EVSEType?): Int =
        if (evse != null) {
            if (evse.connectorId != null && evse.connectorId != evse.id) {
                throw IllegalArgumentException("Argument evse can't have different id and connectorId : ${evse.id} != ${evse.connectorId}")
            } else {
                evse.id
            }
        } else {
            throw IllegalArgumentException("Argument evse is required in OCPP 1.6 to start a transaction")
        }

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