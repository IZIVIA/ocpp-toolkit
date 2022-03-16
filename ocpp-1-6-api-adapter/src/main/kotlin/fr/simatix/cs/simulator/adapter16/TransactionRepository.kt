package fr.simatix.cs.simulator.adapter16

interface TransactionRepository {
    fun saveTransactionIds(ids: Ocpp16TransactionIds)
    fun getTransactionIdsByLocalId(id: String): Ocpp16TransactionIds
}
