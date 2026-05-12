package com.izivia.ocpp.adapter16

interface TransactionRepository {
    fun saveTransactionIds(ids: Ocpp16TransactionIds)
    fun getTransactionIdsByLocalId(id: String): Ocpp16TransactionIds
    fun getLocalIdByTransactionId(transactionId: Int): Ocpp16TransactionIds?
}
