package com.izivia.ocpp.adapter16.impl

import com.izivia.ocpp.adapter16.Ocpp16TransactionIds
import com.izivia.ocpp.adapter16.TransactionRepository

class RealTransactionRepository : TransactionRepository {
    val hashMap: HashMap<String, Int> = HashMap()

    override fun saveTransactionIds(ids: Ocpp16TransactionIds) {
        hashMap.put(ids.localId, ids.csmsId)
    }

    override fun getTransactionIdsByLocalId(id: String): Ocpp16TransactionIds {
        val csmsId = hashMap.get(id)
        if (csmsId != null) {
            return Ocpp16TransactionIds(id, csmsId)
        } else {
            throw IllegalStateException("key ${id} not found in hashmap")
        }
    }
}