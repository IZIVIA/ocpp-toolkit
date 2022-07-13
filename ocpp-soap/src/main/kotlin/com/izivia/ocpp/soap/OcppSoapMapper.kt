package com.izivia.ocpp.soap

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlFactory
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.izivia.ocpp.utils.KotlinxInstantModule
import javax.xml.stream.XMLInputFactory


object OcppSoapMapper : ObjectMapper(
    XmlMapper(get_new_factory(true),  CustomXmlModule)
        .registerModule(kotlinModule())
        .registerModule(KotlinxInstantModule())
        .setSerializationInclusion(Include.NON_NULL)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
)

fun get_new_factory(ns_aware: Boolean) : XmlFactory {
    val CustomXmlFactory: XMLInputFactory = XMLInputFactory.newFactory()
    CustomXmlFactory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, ns_aware)
    return XmlFactory(CustomXmlFactory)
}

object CustomXmlModule : JacksonXmlModule() {
    init {
        setDefaultUseWrapper(false)
    }
}
