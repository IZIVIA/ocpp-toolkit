package com.izivia.ocpp.core20.model.bootnotification

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.izivia.ocpp.core20.model.bootnotification.enumeration.RegistrationStatusEnumType
import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.utils.InstantDeserializer
import com.izivia.ocpp.utils.InstantSerializer
import kotlinx.datetime.Instant

data class BootNotificationResp(
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val currentTime: Instant,
    val interval: Int,
    val status: RegistrationStatusEnumType,
    val statusInfo: StatusInfoType? = null
)