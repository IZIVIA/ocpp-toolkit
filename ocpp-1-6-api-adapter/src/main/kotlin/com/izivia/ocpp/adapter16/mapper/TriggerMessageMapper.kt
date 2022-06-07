package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.api.model.triggermessage.enumeration.MessageTriggerEnumType
import com.izivia.ocpp.api.model.triggermessage.enumeration.TriggerMessageStatusEnumType
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core16.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core16.model.triggermessage.enumeration.MessageTrigger
import com.izivia.ocpp.core16.model.triggermessage.enumeration.TriggerMessageStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageReq as TriggerMessageReqGen
import com.izivia.ocpp.api.model.triggermessage.TriggerMessageResp as TriggerMessageRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class TriggerMessageMapper {

    @Named("convertTriggerMessageStatus")
    fun convertTriggerMessageStatus(status: TriggerMessageStatusEnumType): TriggerMessageStatus =
        TriggerMessageStatus.valueOf(status.name)

    @Named("convertConnectorId")
    fun convertConnectorId(connectorId: Int): EVSEType =
        EVSEType(connectorId, connectorId)

    @Named("convertMessageTrigger")
    fun convertMessageTrigger(status: MessageTrigger): MessageTriggerEnumType =
        when(status){
            MessageTrigger.DiagnosticsStatusNotification -> MessageTriggerEnumType.LogStatusNotification
            else -> MessageTriggerEnumType.valueOf(status.name)
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertTriggerMessageStatus"])
    abstract fun genToCoreResp(triggerMessageResp: TriggerMessageRespGen?): TriggerMessageResp


    @Mapping(target = "requestedMessage", source = "requestedMessage", qualifiedByName = ["convertMessageTrigger"])
    @Mapping(target= "evse", source = "connectorId", qualifiedByName = ["convertConnectorId"])
    abstract fun coreToGenReq(triggerMessageReq: TriggerMessageReq?): TriggerMessageReqGen
}
