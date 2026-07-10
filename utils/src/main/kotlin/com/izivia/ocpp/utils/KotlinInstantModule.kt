package com.izivia.ocpp.utils

import com.fasterxml.jackson.databind.module.SimpleModule
import kotlin.time.Instant

open class KotlinInstantModule : SimpleModule() {
    init {
        addSerializer(Instant::class.java, InstantSerializer())
        addDeserializer(Instant::class.java, InstantDeserializer())
    }
}
