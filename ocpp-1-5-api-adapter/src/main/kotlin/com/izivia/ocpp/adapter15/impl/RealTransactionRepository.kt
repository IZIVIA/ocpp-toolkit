package com.izivia.ocpp.adapter15.impl

import com.izivia.ocpp.adapter15.Ocpp15TransactionIds
import com.izivia.ocpp.adapter15.TransactionRepository
import java.util.concurrent.ConcurrentHashMap

/**
 * In-memory, non-persistent [TransactionRepository]: the local-id/csms-id mappings are lost on restart.
 * A StopTransaction/MeterValues whose StartTransaction was not recorded (e.g. after a restart) will fail
 * the local-id lookup. Inject a persistent [TransactionRepository] if durability across restarts is required.
 */
class RealTransactionRepository : TransactionRepository {
    private val hashMap: ConcurrentHashMap<String, Int> = ConcurrentHashMap()

    override fun saveTransactionIds(ids: Ocpp15TransactionIds) {
        hashMap.put(ids.localId, ids.csmsId)
    }

    override fun getTransactionIdsByLocalId(id: String): Ocpp15TransactionIds {
        val csmsId = hashMap[id] ?: throw IllegalStateException("key ${id} not found in hashmap")
        return Ocpp15TransactionIds(id, csmsId)
    }

    override fun getLocalIdByTransactionId(transactionId: Int): Ocpp15TransactionIds? {
        val localId = hashMap.toList().find { it.second == transactionId }?.first
        return localId?.let { Ocpp15TransactionIds(it, transactionId) }
    }
}
