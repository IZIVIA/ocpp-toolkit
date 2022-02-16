package fr.simatix.cs.simulator.core20.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class HeartbeatResponse(
    @JsonFormat
        (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
    val currentTime: Date
)
