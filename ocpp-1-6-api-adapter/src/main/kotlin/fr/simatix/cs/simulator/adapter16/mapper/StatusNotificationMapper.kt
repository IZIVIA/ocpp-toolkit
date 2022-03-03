package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.ChargingStateEnumType
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointErrorCode
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq as StatusNotificationReqGen
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationResp as StatusNotificationRespGen
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ChargePointErrorCode as ChargePointErrorCodeGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class StatusNotificationMapper {

    @Named("convertConnectorStatus")
    fun convertConnectorStatus(status : ConnectorStatusEnumType): ChargePointStatus =
        when(status){
            ConnectorStatusEnumType.Occupied -> ChargePointStatus.Preparing
            else -> ChargePointStatus.valueOf(status.name)
        }

    @Named("convertErrorCode")
    fun convertErrorCode(errorCode : ChargePointErrorCodeGen): ChargePointErrorCode = ChargePointErrorCode.valueOf(errorCode.name)

    @Named("convertChargingState")
    fun convertChargingState(chargingState: ChargingStateEnumType?): ChargePointStatus =
        when(chargingState){
            ChargingStateEnumType.EVConnected -> ChargePointStatus.Preparing
            ChargingStateEnumType.Idle -> ChargePointStatus.Available
            null -> throw IllegalArgumentException("Argument transactionInfo.chargingState is required in OCPP 1.6 to update a transaction")
            else -> ChargePointStatus.valueOf(chargingState.name)
        }

    @Mapping(target = "connectorId", source = "evse", qualifiedByName = ["convertEVSEType"])
    @Mapping(target = "status", source = "transactionInfo.chargingState", qualifiedByName = ["convertChargingState"])
    @Mapping(target = "errorCode", source = "transactionInfo.errorCode", qualifiedByName = ["convertErrorCode"])
    abstract fun genToCoreReq(statusReq: TransactionEventReq?): StatusNotificationReq

    abstract fun coreToGenRespTransac(statusResp: StatusNotificationResp): TransactionEventResp

    @Mapping(target = "status", source = "connectorStatus", qualifiedByName = ["convertConnectorStatus"])
    @Mapping(target = "errorCode", source = "errorCode", qualifiedByName = ["convertErrorCode"])
    abstract fun genToCoreReq(statusReq: StatusNotificationReqGen?): StatusNotificationReq

    abstract fun coreToGenResp(statusResp: StatusNotificationResp): StatusNotificationRespGen
}