package com.izivia.ocpp.soap

import com.fasterxml.jackson.annotation.JsonInclude.Include
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.izivia.ocpp.utils.KotlinxInstantModule

object OcppSoapMapper : ObjectMapper(
    XmlMapper(CustomXmlModule)
        .registerModule(kotlinModule())
        .registerModule(KotlinxInstantModule())
        .setSerializationInclusion(Include.NON_NULL)
)

object CustomXmlModule : JacksonXmlModule() {
    init {
        setDefaultUseWrapper(false)
    }
}