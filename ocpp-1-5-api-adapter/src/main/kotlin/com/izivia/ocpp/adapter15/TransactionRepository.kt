package com.izivia.ocpp.adapter15

interface TransactionRepository {
    fun saveTransactionIds(ids: Ocpp15TransactionIds)
    fun getTransactionIdsByLocalId(id: String): Ocpp15TransactionIds
    fun getLocalIdByTransactionId(transactionId: Int): Ocpp15TransactionIds?

    /**
     * Drops the mapping for [localId]. Called once a transaction is over so that a long-running
     * process does not accumulate one entry per transaction.
     */
    fun deleteTransactionIds(localId: String)
}
