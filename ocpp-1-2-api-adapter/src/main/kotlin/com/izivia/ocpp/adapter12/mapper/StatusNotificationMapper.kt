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
import org.slf4j.LoggerFactory
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationReq as StatusNotificationReqGen
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationResp as StatusNotificationRespGen
import com.izivia.ocpp.api.model.statusnotification.enumeration.ChargePointErrorCode as ChargePointErrorCodeGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class StatusNotificationMapper {
    private val logger = LoggerFactory.getLogger(StatusNotificationMapper::class.java)

    data class ChargingStateWrapper(
        val chargingState: ChargingStateEnumType?,
        /**
         * Not read by [convertChargingState] in OCPP 1.2: unlike 1.6, which splits EVConnected into
         * Preparing and Finishing on the event type, 1.x maps it to Occupied whatever the event. The
         * field is kept so this wrapper stays shaped like the 1.6 one, and so tests can pin the
         * combination that used to yield Available on Ended.
         */
        val type: TransactionEventEnumType?
    )

    fun createChargingStateWrapper(
        chargingState: ChargingStateEnumType?,
        type: TransactionEventEnumType?
    ): ChargingStateWrapper = ChargingStateWrapper(chargingState, type)

    @Named("convertConnectorStatus")
    fun convertConnectorStatus(status: ConnectorStatusEnumType): ChargePointStatus =
        when (status) {
            ConnectorStatusEnumType.Available -> ChargePointStatus.Available
            ConnectorStatusEnumType.Occupied -> ChargePointStatus.Occupied
            ConnectorStatusEnumType.Faulted -> ChargePointStatus.Faulted
            ConnectorStatusEnumType.Unavailable -> ChargePointStatus.Unavailable
            // OCPP 1.2 has no Reserved status: a reserved connector is not usable by the public.
            ConnectorStatusEnumType.Reserved -> ChargePointStatus.Unavailable
        }

    @Named("convertErrorCode")
    fun convertErrorCode(errorCode: ChargePointErrorCodeGen): ChargePointErrorCode =
        when (errorCode) {
            ChargePointErrorCodeGen.ConnectorLockFailure -> ChargePointErrorCode.ConnectorLockFailure
            ChargePointErrorCodeGen.HighTemperature -> ChargePointErrorCode.HighTemperature
            ChargePointErrorCodeGen.NoError -> ChargePointErrorCode.NoError
            ChargePointErrorCodeGen.PowerMeterFailure -> ChargePointErrorCode.PowerMeterFailure
            ChargePointErrorCodeGen.PowerSwitchFailure -> ChargePointErrorCode.PowerSwitchFailure
            ChargePointErrorCodeGen.ReaderFailure -> ChargePointErrorCode.ReaderFailure
            ChargePointErrorCodeGen.ResetFailure -> ChargePointErrorCode.ResetFailure

            // OCPP 1.2 has no OtherError catch-all: fall back to Mode3Error and warn about the loss.
            ChargePointErrorCodeGen.EVCommunicationError,
            ChargePointErrorCodeGen.GroundFailure,
            ChargePointErrorCodeGen.InternalError,
            ChargePointErrorCodeGen.LocalListConflict,
            ChargePointErrorCodeGen.OtherError,
            ChargePointErrorCodeGen.OverCurrentFailure,
            ChargePointErrorCodeGen.UnderVoltage,
            ChargePointErrorCodeGen.OverVoltage,
            ChargePointErrorCodeGen.WeakSignal -> {
                logger.warn("ChargePointErrorCode ${errorCode.name} has no OCPP 1.2 equivalent, mapped to Mode3Error")
                ChargePointErrorCode.Mode3Error
            }
        }

    @Named("convertChargingState")
    fun convertChargingState(wrapper: ChargingStateWrapper): ChargePointStatus =
        when (wrapper.chargingState) {
            // OCPP 1.2 has no Finishing: with the EV still connected the connector is not reusable,
            // so it stays Occupied. Available comes with the Idle that follows unplugging.
            ChargingStateEnumType.EVConnected -> ChargePointStatus.Occupied

            ChargingStateEnumType.Charging,
            ChargingStateEnumType.SuspendedEV,
            ChargingStateEnumType.SuspendedEVSE -> ChargePointStatus.Occupied

            ChargingStateEnumType.Idle -> ChargePointStatus.Available
            null -> throw IllegalArgumentException("Argument transactionInfo.chargingState is required in OCPP 1.2 to update a transaction")
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
