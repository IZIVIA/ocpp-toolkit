package com.izivia.ocpp.adapter15.impl

import com.izivia.ocpp.adapter15.Ocpp15TransactionIds
import com.izivia.ocpp.adapter15.TransactionRepository

class RealTransactionRepository : TransactionRepository {
    val hashMap: HashMap<String, Int> = HashMap()

    override fun saveTransactionIds(ids: Ocpp15TransactionIds) {
        hashMap.put(ids.localId, ids.csmsId)
    }

    override fun getTransactionIdsByLocalId(id: String): Ocpp15TransactionIds {
        val csmsId = hashMap.get(id)
        if (csmsId != null) {
            return Ocpp15TransactionIds(id, csmsId)
        } else {
            throw IllegalStateException("key ${id} not found in hashmap")
        }
    }
}
