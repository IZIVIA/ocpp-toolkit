package com.izivia.ocpp.soap

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonTokenId
import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlFactory
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.izivia.ocpp.utils.KotlinInstantModule
import javax.xml.stream.XMLInputFactory

// enable(MapperFeature) on a built mapper is deprecated in Jackson 2.x. The official
// replacement is the builder, reachable via rebuild() (see jackson-databind#3782), but:
//  - in 2.x rebuild() exists only on JsonMapper, not on XmlMapper; per the Jackson lead a
//    "true, completely functioning rebuild()" only lands in Jackson 3.0;
//  - XmlMapper.builder() does not reproduce the XmlMapper(factory, CustomXmlModule)
//    construction this mapper relies on (the SOAP round-trip tests fail with it);
//  - the ACCEPT_CASE_INSENSITIVE_* features are required (removing them breaks SoapFault
//    mapping).
// So the post-construction form is kept deliberately until a Jackson 3 (tools.jackson)
// migration, which is the same prerequisite as the json-schema-validator 3.x upgrade.
// https://github.com/FasterXML/jackson-databind/issues/3782
@Suppress("DEPRECATION")
class OcppSoapMapper : ObjectMapper(
    XmlMapper(getNewFactory(true), CustomXmlModule)
        .registerModule(
            kotlinModule {
                configure(KotlinFeature.NullIsSameAsDefault, true)
            }
        )
        .registerModule(KotlinInstantModule())
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setDefaultPropertyInclusion(Include.NON_EMPTY)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
        .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
)

fun getNewFactory(nsAware: Boolean): XmlFactory =
    XMLInputFactory
        .newFactory()
        .apply {
            setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, nsAware)
        }
        .let { XmlFactory(it) }

private object CustomXmlModule : JacksonXmlModule() {
    init {
        setDefaultUseWrapper(false)
        setXMLTextElementName("text")
        addDeserializer(Boolean::class.java, CustomBooleanDeserializer())
    }
}

abstract class EnumMixin(
    @JsonValue val value: String
)

abstract class SoapFaultMixin(
    @JacksonXmlProperty(localName = "s:Code")
    val code: FaultCode,
    @JacksonXmlProperty(localName = "s:Reason")
    val reason: FaultReason,
    @JacksonXmlProperty(localName = "s:Value")
    val value: FaultCodeValue
)

abstract class FaultCodeMixin(
    @JacksonXmlProperty(localName = "s:Value")
    val value: FaultCodeValue,
    @JacksonXmlProperty(localName = "o:Subcode")
    val subCode: FaultSubCode
)

abstract class FaultSubCodeMixin(
    @JacksonXmlProperty(localName = "s:Code")
    val code: FaultCode,
    @JacksonXmlProperty(localName = "s:Reason")
    val reason: FaultReason,
    @JacksonXmlProperty(localName = "s:Value")
    val value: FaultSubCodeValue
)

abstract class FaultReasonMixin(
    @JacksonXmlProperty(localName = "s:Text")
    val text: FaultReasonText
)

class CustomBooleanDeserializer : JsonDeserializer<Boolean>() {
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): Boolean =
        when (p?.currentTokenId()) {
            JsonTokenId.ID_STRING -> p.text == "1" || p.text.lowercase() == "true"
            JsonTokenId.ID_TRUE -> true
            JsonTokenId.ID_FALSE -> false
            JsonTokenId.ID_NUMBER_INT -> p.numberValue == 1
            else -> false
        }
}
