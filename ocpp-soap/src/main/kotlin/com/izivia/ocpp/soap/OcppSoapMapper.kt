package com.izivia.ocpp.soap

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.izivia.ocpp.utils.KotlinxInstantModule

object OcppSoapMapper : ObjectMapper(
    XmlMapper(CustomXmlModule)
        .registerModule(kotlinModule())
        .registerModule(KotlinxInstantModule())
)

object CustomXmlModule : JacksonXmlModule() {
    init {
        setDefaultUseWrapper(false)
    }
}