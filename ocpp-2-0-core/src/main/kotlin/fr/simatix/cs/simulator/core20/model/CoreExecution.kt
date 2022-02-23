package fr.simatix.cs.simulator.core20.model

import fr.simatix.cs.simulator.api.model.ExecutionMetadata

data class CoreExecution <T> (
    val executionMeta: ExecutionMetadata,
    val response: T
)
