package fr.simatix.cs.simulator.core20.model.notifycustomerinformation

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fr.simatix.cs.simulator.utils.InstantDeserializer
import fr.simatix.cs.simulator.utils.InstantSerializer
import kotlinx.datetime.Instant

data class NotifyCustomerInformationReq(
    val data: String,
    val tbc: Boolean = false,
    val seqNo: Int,
    @JsonSerialize(using = InstantSerializer::class)
    @JsonDeserialize(using = InstantDeserializer::class)
    val generatedAt: Instant,
    val requestId: Int
)