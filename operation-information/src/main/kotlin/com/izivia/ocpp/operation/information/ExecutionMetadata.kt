package com.izivia.ocpp.operation.information

import kotlinx.datetime.Instant

data class ExecutionMetadata(
    val reqMeta: RequestMetadata,
    val status: RequestStatus,
    val requestTime: Instant = Instant.parse("0000-01-01T00:00:00.000Z"),
    val responseTime: Instant = Instant.parse("0000-01-01T00:00:00.000Z")
){
    fun combine(executionMetadata: ExecutionMetadata): ExecutionMetadata =
        ExecutionMetadata(
            reqMeta = reqMeta,
            status = status.combine(executionMetadata.status),
            requestTime = requestTime,
            responseTime = executionMetadata.responseTime
        )
}
