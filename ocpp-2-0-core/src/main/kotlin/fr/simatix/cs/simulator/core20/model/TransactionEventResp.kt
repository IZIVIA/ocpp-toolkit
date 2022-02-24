package fr.simatix.cs.simulator.core20.model

data class TransactionEventResp(
    val totalCost: Double? = null,
    val chargingPriority: Int? = 0,
    val idTokenInfo: IdTokenInfoType? = null,
    val updatedPersonalMessage: MessageContentType? = null
)
