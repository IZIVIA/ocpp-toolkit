package com.izivia.ocpp.operation.information

data class OperationExecution<T, R>(
    val executionMeta: ExecutionMetadata,
    val request: T,
    val response: R
)
