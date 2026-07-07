package com.izivia.ocpp.adapter12.impl

import com.izivia.ocpp.adapter12.Ocpp12TransactionIds
import com.izivia.ocpp.adapter12.TransactionRepository
import java.util.concurrent.ConcurrentHashMap

class RealTransactionRepository : TransactionRepository {
    val hashMap: ConcurrentHashMap<String, Int> = ConcurrentHashMap()

    override fun saveTransactionIds(ids: Ocpp12TransactionIds) {
        hashMap.put(ids.localId, ids.csmsId)
    }

    override fun getTransactionIdsByLocalId(id: String): Ocpp12TransactionIds {
        val csmsId = hashMap[id] ?: throw IllegalStateException("key ${id} not found in hashmap")
        return Ocpp12TransactionIds(id, csmsId)
    }

    override fun getLocalIdByTransactionId(transactionId: Int): Ocpp12TransactionIds? {
        val localId = hashMap.toList().find { it.second == transactionId }?.first
        return localId?.let { Ocpp12TransactionIds(it, transactionId) }
    }
}
