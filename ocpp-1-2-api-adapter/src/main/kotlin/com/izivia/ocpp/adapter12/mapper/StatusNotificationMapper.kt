package com.izivia.ocpp.adapter12.mapper

import com.izivia.ocpp.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import com.izivia.ocpp.api.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.api.model.transactionevent.TransactionEventResp
import com.izivia.ocpp.api.model.transactionevent.enumeration.ChargingStateEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TransactionEventEnumType
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core12.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.core12.model.statusnotification.enumeration.ChargePointStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationReq as StatusNotificationReqGen
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationResp as StatusNotificationRespGen
import com.izivia.ocpp.api.model.statusnotification.enumeration.ChargePointErrorCode as ChargePointErrorCodeGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class StatusNotificationMapper {
    data class ChargingStateWrapper(
        val chargingState: ChargingStateEnumType?,
        val type: TransactionEventEnumType?
    )

    fun createChargingStateWrapper(
        chargingState: ChargingStateEnumType?,
        type: TransactionEventEnumType?
    ): ChargingStateWrapper = ChargingStateWrapper(chargingState, type)

    @Named("convertConnectorStatus")
    fun convertConnectorStatus(status: ConnectorStatusEnumType): ChargePointStatus =
        when (status) {
            ConnectorStatusEnumType.Occupied -> ChargePointStatus.Occupied
            else -> ChargePointStatus.valueOf(status.name)
        }

    @Named("convertErrorCode")
    fun convertErrorCode(errorCode: ChargePointErrorCodeGen): ChargePointErrorCode =
        ChargePointErrorCode.valueOf(errorCode.name)

    @Named("convertChargingState")
    fun convertChargingState(wrapper: ChargingStateWrapper): ChargePointStatus =
        when (wrapper.chargingState) {
            ChargingStateEnumType.EVConnected -> when (wrapper.type) {
                TransactionEventEnumType.Ended -> ChargePointStatus.Available
                else -> ChargePointStatus.Occupied
            }

            ChargingStateEnumType.Idle -> ChargePointStatus.Available
            null -> throw IllegalArgumentException("Argument transactionInfo.chargingState is required in OCPP 1.2 to update a transaction")
            else -> ChargePointStatus.valueOf(wrapper.chargingState.name)
        }

    @Mapping(target = "connectorId", source = "evse", qualifiedByName = ["convertEVSEType"])
    @Mapping(
        target = "status",
        expression = "java(convertChargingState(createChargingStateWrapper(statusReq.getTransactionInfo().getChargingState(), statusReq.getEventType())))"
    )
    @Mapping(target = "errorCode", source = "transactionInfo.errorCode", qualifiedByName = ["convertErrorCode"])
    abstract fun genToCoreReq(statusReq: TransactionEventReq?): StatusNotificationReq

    abstract fun coreToGenRespTransac(statusResp: StatusNotificationResp): TransactionEventResp

    @Mapping(target = "status", source = "connectorStatus", qualifiedByName = ["convertConnectorStatus"])
    @Mapping(target = "errorCode", source = "errorCode", qualifiedByName = ["convertErrorCode"])
    abstract fun genToCoreReq(statusReq: StatusNotificationReqGen?): StatusNotificationReq

    abstract fun coreToGenResp(statusResp: StatusNotificationResp): StatusNotificationRespGen
}
