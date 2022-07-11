package com.izivia.ocpp.utils

import com.fasterxml.jackson.databind.module.SimpleModule
import kotlinx.datetime.Instant

class KotlinxInstantModule : SimpleModule() {
    init {
        addSerializer(Instant::class.java, InstantSerializer())
        addDeserializer(Instant::class.java, InstantDeserializer())
    }
}