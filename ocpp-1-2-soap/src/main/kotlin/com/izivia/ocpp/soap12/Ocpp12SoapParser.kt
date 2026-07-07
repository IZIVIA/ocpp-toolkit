package com.izivia.ocpp.soap12

import com.fasterxml.jackson.core.type.TypeReference
import com.izivia.ocpp.core12.Ocpp12ForcedFieldType
import com.izivia.ocpp.core12.Ocpp12IgnoredNullRestriction
import com.izivia.ocpp.core12.model.common.enumeration.Actions
import com.izivia.ocpp.soap.*
import com.izivia.ocpp.utils.*
import kotlin.reflect.full.memberProperties

class Ocpp12SoapParser(
    override val ignoredNullRestrictions: List<Ocpp12IgnoredNullRestriction>? = null,
    override val forcedFieldTypes: List<Ocpp12ForcedFieldType>? = null,
) :
    OcppSoapParserImpl(
        ocppNs = OcppNs(
            ocppCpNs = "urn://Ocpp/Cp/2010/08/",
            ocppCsNs = "urn://Ocpp/Cs/2010/08/"
        ),
        soapMapperInput = Ocpp12SoapMapperIn,
        soapMapperOutput = Ocpp12SoapMapper
    ) {

    override fun readToEnvelop(
        soap: String,
        warningHandler: (warnings: List<ErrorDetail>) -> Unit
    ): SoapEnvelope<*> =
        try {
            soapMapperInput
                .readTree(soap)
                .apply {
                    applyDeserializerOptions(this, warningHandler)
                }?.let {
                    soapMapperInput
                        .readerFor(object : TypeReference<SoapEnvelope<Ocpp12SoapBody>>() {})
                        .readValue(it)
                }
        } catch (e: Exception) {
            parseSoapFaulted(soap, e) {
                Ocpp12SoapBody(fault = it)
            }
        } as SoapEnvelope<Ocpp12SoapBody>

    private fun getRealBodyContent(envelope: SoapEnvelope<Ocpp12SoapBody>): Any {
        for (prop in Ocpp12SoapBody::class.memberProperties) {
            prop.get(envelope.body)?.let { return it }
        }
        throw IllegalArgumentException("Unknown message operation. enveloppe = $envelope")
    }

    override fun getRequestBodyContent(envelope: SoapEnvelope<*>): Any =
        getRealBodyContent(envelope as SoapEnvelope<Ocpp12SoapBody>)
            .isA<SoapFault> { it.toFaultReq() }

    override fun getResponseBodyContent(envelope: SoapEnvelope<*>): Any =
        getRealBodyContent(envelope as SoapEnvelope<Ocpp12SoapBody>)

    override fun getOcppInitiator(action: String): OcppInitiator =
        Actions.valueOf(action.uppercase()).initiatedBy
}
