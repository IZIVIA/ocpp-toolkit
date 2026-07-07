package com.izivia.ocpp.adapter15

interface TransactionRepository {
    fun saveTransactionIds(ids: Ocpp15TransactionIds)
    fun getTransactionIdsByLocalId(id: String): Ocpp15TransactionIds
    fun getLocalIdByTransactionId(transactionId: Int): Ocpp15TransactionIds?
}
