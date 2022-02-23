package fr.simatix.cs.simulator.api.model

data class OperationExecution<T, R>(
    val executionMeta: ExecutionMetadata,
    val request: T,
    val response: R
)
