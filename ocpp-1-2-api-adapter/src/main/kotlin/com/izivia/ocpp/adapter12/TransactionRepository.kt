package com.izivia.ocpp.adapter12

interface TransactionRepository {
    fun saveTransactionIds(ids: Ocpp12TransactionIds)
    fun getTransactionIdsByLocalId(id: String): Ocpp12TransactionIds
    fun getLocalIdByTransactionId(transactionId: Int): Ocpp12TransactionIds?

    /**
     * Drops the mapping for [localId]. Called once a transaction is over so that a long-running
     * process does not accumulate one entry per transaction.
     */
    fun deleteTransactionIds(localId: String)
}
