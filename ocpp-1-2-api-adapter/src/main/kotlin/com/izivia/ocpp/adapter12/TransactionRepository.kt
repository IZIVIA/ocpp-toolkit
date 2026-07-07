package com.izivia.ocpp.adapter12

interface TransactionRepository {
    fun saveTransactionIds(ids: Ocpp12TransactionIds)
    fun getTransactionIdsByLocalId(id: String): Ocpp12TransactionIds
    fun getLocalIdByTransactionId(transactionId: Int): Ocpp12TransactionIds?
}
