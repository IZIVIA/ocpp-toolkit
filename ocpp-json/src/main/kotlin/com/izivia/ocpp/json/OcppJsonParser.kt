package com.izivia.ocpp.json

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.izivia.ocpp.json.JsonMessageType.*
import com.izivia.ocpp.utils.*
import com.izivia.ocpp.utils.fault.FAULT
import com.izivia.ocpp.utils.fault.Fault
import com.networknt.schema.ValidationMessage
import com.networknt.schema.ValidatorTypeCode
import kotlin.reflect.KClass

abstract class OcppJsonParser(
    private val mapper: ObjectMapper,
    protected val ocppJsonValidator: OcppJsonValidator?,
    open val ignoredNullRestrictions: List<AbstractIgnoredNullRestriction>? = null,
    open val ignoredValidationCodes: List<ValidatorTypeCode>? = null,
    open val forcedFieldTypes: List<AbstractForcedFieldType>? = null
) {
    val classActionRegex = "(Resp|Req)$".toRegex()

    protected fun getActionFromClassName(className: String) =
        className.replace(classActionRegex, "").uppercase()

    abstract fun getRequestPayloadClass(
        action: String,
        errorHandler: (e: Exception) -> Throwable
    ): Class<out Any>

    abstract fun getResponsePayloadClass(
        action: String,
        errorHandler: (e: Exception) -> Throwable
    ): Class<out Any>

    protected abstract fun getActionFromClass(className: String): String

    protected abstract fun validateJson(
        jsonMessage: JsonMessage<JsonNode>,
        errorsHandler: (errors: List<ValidationMessage>) -> Unit
    )

    /**
     * Parses a JSON message from a string.
     *
     * @param messageStr The string representation of the message.
     * @param useClazz In the case of a CALL_RESULT type message, useClazz is the class used to parse the message
     *                 (as it cannot always be deduced from the message itself)
     *
     * @return A JsonMessage object containing the parsed data.
     */
    fun parseAnyFromString(messageStr: String, useClazz: Class<out Any>? = null): JsonMessage<Any> {
        try {
            val warnings = mutableListOf<ErrorDetail>()

            var parsed = parseNodePayload(parseStringToJsonNode(messageStr))

            var clazz = useClazz
            when (parsed.msgType) {
                CALL -> clazz = getRequestPayloadClass(parsed.action!!) {
                    ActionRequestNullOrUnknownException(
                        message = it.message!!,
                        messageId = parsed.msgId,
                        errorDetails = listOf(
                            ErrorDetail(
                                code = ErrorDetailCode.PAYLOAD.value,
                                detail = messageStr
                            ),
                            ErrorDetail(
                                code = ErrorDetailCode.ACTION.value,
                                detail = parsed.action ?: FAULT
                            )
                        )
                    )
                }

                CALL_RESULT -> useClazz?.let {
                    parsed = parsed.copy(action = getActionFromClass(it.simpleName))
                    clazz = useClazz
                } ?: throw ActionResponseNotSpecifiedException(
                    message = "Action class not defined",
                    messageId = parsed.msgId,
                    errorDetails = listOf(
                        ErrorDetail(
                            code = ErrorDetailCode.ACTION.value,
                            detail = "Cannot parse message, class used to retrieve the response action is not defined"
                        ),
                        ErrorDetail(
                            code = ErrorDetailCode.PAYLOAD.value,
                            detail = messageStr
                        )
                    )
                )

                else -> {
                    parsed = parsed.copy(
                        action = getActionFromClass(useClazz?.simpleName ?: "clazzUndefinedAction")
                    )
                    clazz = Fault::class.java
                }
            }

            val isNodeAction: (node: JsonNode, rule: InterfaceFieldOption) -> JsonNode? = {
                    node, rule ->
                node.takeIf { parsed.action?.lowercase() == rule.action.value.lowercase() }
            }

            ignoredNullRestrictions?.parseNullField(parsed.payload, isNodeAction = isNodeAction)
                ?.also { warnings.addAll(it) }
            forcedFieldTypes?.parseFieldToConvert(parsed.payload, isNodeAction = isNodeAction)
                ?.also { warnings.addAll(it) }

            validateJson(jsonMessage = parsed) { lvm ->
                lvm.mapNotNull { vm ->
                    vm.takeUnless { msg ->
                        ignoredValidationCodes?.contains(ValidatorTypeCode.fromValue(vm.type))
                            .also {
                                warnings.add(
                                    ErrorDetail(
                                        code = msg.code,
                                        detail = "Validations error : message=${msg.message}, details=${msg.details}"
                                    )
                                )
                            } ?: false
                    }
                }.map { vm ->
                    ErrorDetail(
                        code = vm.code,
                        detail = "Validations error : message=${vm.message}, details=${vm.details}"
                    )
                }.takeIf { it.isNotEmpty() }
                    ?.let {
                        throw ValidationException(
                            message = "Validation error",
                            messageId = parsed.msgId,
                            errorDetails = listOf(
                                ErrorDetail(
                                    code = ErrorDetailCode.ACTION.value,
                                    detail = parsed.action ?: FAULT
                                ),
                                ErrorDetail(
                                    code = ErrorDetailCode.PAYLOAD.value,
                                    detail = messageStr
                                )
                            ).plus(it)
                        )
                    }
            }
            val jsonMessage = (
                    warnings.takeIf { it.size > 0 }
                        ?.let {
                            parsed.copy(warnings = it.toList())
                        } ?: parsed) as JsonMessage<Any>

            return clazz.takeIf { it != Fault::class.java }?.let {
                jsonMessage.copy(
                    payload = mapJsonNodeToObject(parsed, clazz!!)
                )
            } ?: jsonMessage.copy(
                action = FAULT,
                payload =
                Fault(
                    errorCode = parsed.errorCode?.errorCode ?: MessageErrorCode.INTERNAL_ERROR.errorCode,
                    errorDescription = parsed.errorDescription ?: MessageErrorCode.INTERNAL_ERROR.description,
                    errorDetails = listOf(
                        ErrorDetail(
                            code = ErrorDetailCode.PAYLOAD.value,
                            detail = parsed.payload.toString()
                        ),
                        ErrorDetail(
                            code = ErrorDetailCode.ACTION.value,
                            detail = parsed.action ?: FAULT
                        )
                    )
                )
            )
        } catch (e: OcppParserException) {
            return jsonMessage(
                msgId = e.messageId,
                msgType = CALL_ERROR,
                error = e.errorCode,
                detail = e.stackTraceToString(),
                action = FAULT,
                payload = e.errorDetails?.let {
                    Fault(
                        errorCode = e.errorCode.errorCode,
                        errorDescription = e.errorCode.description,
                        errorDetails = it
                    )
                }
            )
        } catch (e: Exception) {
            return jsonMessage(
                msgId = null,
                msgType = CALL_ERROR,
                action = FAULT,
                error = MessageErrorCode.INTERNAL_ERROR,
                detail = e.stackTraceToString(),
                payload = Fault(
                    errorCode = MessageErrorCode.INTERNAL_ERROR.errorCode,
                    errorDescription = MessageErrorCode.INTERNAL_ERROR.description,
                    errorDetails = listOf(
                        ErrorDetail(code = ErrorDetailCode.STACKTRACE.value, detail = e.stackTraceToString()),
                        ErrorDetail(code = ErrorDetailCode.PAYLOAD.value, detail = messageStr)
                    )
                )
            )
        }
    }

    private fun jsonMessage(
        msgType: JsonMessageType,
        msgId: String?,
        action: String,
        error: MessageErrorCode,
        payload: Any?,
        detail: String?
    ) = JsonMessage(
        msgType = msgType,
        msgId = msgId ?: "Unknown",
        action = action,
        errorCode = error,
        errorDescription = error.description,
        payload = payload ?: Fault(
            errorCode = error.errorCode,
            errorDescription = error.description,
            errorDetails = listOf(
                ErrorDetail(
                    code = error.errorCode,
                    detail = detail!!
                )
            )
        )
    )

    private fun mapJsonNodeToObject(jsonMessage: JsonMessage<JsonNode>, clazz: Class<out Any>): Any =
        try {
            mapper.treeToValue(jsonMessage.payload, clazz)
        } catch (e: Exception) {
            throw ActionRequestNullOrUnknownException(
                message = "Cannot parse jsonMessage=$jsonMessage to class $clazz",
                messageId = jsonMessage.msgId,
                errorDetails = listOf(
                    ErrorDetail(
                        code = "request",
                        detail = "${jsonMessage.payload}"
                    )
                )
            )
        }

    fun parseStringToJsonNode(mesageString: String): JsonNode =
        try {
            mapper.readTree(mesageString)
        } catch (e: Exception) {
            throw MalformedOcppMessageException(
                message = "Cannot parse OCPP message to JsonNode",
                messageId = null,
                errorDetails = listOf(ErrorDetail(code = ErrorDetailCode.PAYLOAD.value, detail = mesageString))
            )
        }

    inline fun <reified T : Any> parseAnyFromJson(messageStr: String): JsonMessage<Any> =
        parseAnyFromString(messageStr = messageStr, useClazz = T::class.java)

    fun parseNodePayload(jsonNode: JsonNode): JsonMessage<JsonNode> {
        var msgId: String? = null
        try {
            msgId = jsonNode[1].asText()
            jsonNode[0].asInt()
        } catch (e: Exception) {
            throw FormatViolationException(
                message = "Malformed messageType in json=$jsonNode",
                messageId = msgId,
                errorDetails = listOf(ErrorDetail(code = ErrorDetailCode.PAYLOAD.value, detail = jsonNode.toString()))
            )
        }.let {
            return when (it) {
                CALL.id -> JsonMessage.Call(
                    msgId = jsonNode[1].asText(),
                    action = jsonNode[2].asText(),
                    payload = jsonNode[3]
                )

                CALL_RESULT.id ->
                    JsonMessage.CallResult(msgId = jsonNode[1].asText(), payload = jsonNode[2])

                CALL_ERROR.id -> JsonMessage.CallError(
                    msgId = jsonNode[1].asText(),
                    errorCode = MessageErrorCode.fromValue(jsonNode[2].asText()),
                    errorDescription = jsonNode[3].asText(),
                    payload = jsonNode[4]
                )

                else -> throw MessageTypeException(
                    message = "Unknown messageType in json=$jsonNode",
                    messageId = msgId,
                    errorDetails = listOf(
                        ErrorDetail(
                            code = ErrorDetailCode.PAYLOAD.value,
                            detail = jsonNode.toString()
                        )
                    )
                )
            }
        }
    }

    fun <T : Any> parsePayloadFromJson(payload: String, clazz: KClass<T>): T =
        mapper.readValue(payload, clazz.java)

    /**
     * Same as [parsePayloadFromJson], reading the timestamps without timezone as [timestampZone] says
     * instead of as UTC.
     */
    fun <T : Any> parsePayloadFromJson(payload: String, clazz: KClass<T>, timestampZone: TimestampZoneContext): T =
        mapper.readerFor(clazz.java).withTimestampZone(timestampZone).readValue(payload)

    fun <T> mapPayloadToString(payload: T): String =
        mapper.writeValueAsString(payload)

    fun <T> mapToJson(message: JsonMessage<T>): String =
        message
            .let {
                message
                    .payload
                    .takeUnless { it == null || (it is String && it.isBlank()) }
                    ?: JsonMessageEmptyPayload()
            }
            .let { payload ->
                when (message.msgType) {
                    CALL -> listOf(
                        message.msgType.id,
                        message.msgId,
                        message.action,
                        payload
                    )

                    CALL_RESULT -> listOf(
                        message.msgType.id,
                        message.msgId,
                        payload
                    )

                    CALL_ERROR -> listOf(
                        message.msgType.id,
                        message.msgId,
                        message.errorCode!!.errorCode,
                        message.errorDescription,
                        payload
                    )
                }
            }
            .let { mapper.writeValueAsString(it) }
}
