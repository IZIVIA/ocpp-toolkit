package com.izivia.ocpp.soap

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlFactory
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.izivia.ocpp.utils.KotlinxInstantModule
import javax.xml.stream.XMLInputFactory


class OcppSoapMapper : ObjectMapper(
    XmlMapper(getNewFactory(true),  CustomXmlModule)
        .registerModule(kotlinModule())
        .registerModule(KotlinxInstantModule())
        .setSerializationInclusion(Include.NON_NULL)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
)

fun getNewFactory(nsAware: Boolean) : XmlFactory {
    val customXmlFactory: XMLInputFactory = XMLInputFactory.newFactory()
    customXmlFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, nsAware)
    return XmlFactory(customXmlFactory)
}

private object CustomXmlModule : JacksonXmlModule() {
    init {
        setDefaultUseWrapper(false)
    }
}

