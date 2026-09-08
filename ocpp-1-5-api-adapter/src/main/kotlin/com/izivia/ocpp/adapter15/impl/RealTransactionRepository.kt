package com.izivia.ocpp.adapter15.impl

import com.izivia.ocpp.adapter15.Ocpp15TransactionIds
import com.izivia.ocpp.adapter15.TransactionRepository
import java.util.concurrent.ConcurrentHashMap

/**
 * In-memory, non-persistent [TransactionRepository]: the local-id/csms-id mappings are lost on restart.
 * A StopTransaction whose StartTransaction was not recorded (e.g. after a restart) fails the local-id
 * lookup; the same case on MeterValues is degraded to a not-sent request instead. Entries are dropped
 * when the transaction stops, so the map does not grow with the process lifetime.
 * Inject a persistent [TransactionRepository] if durability across restarts is required.
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
        val localId = hashMap.entries.firstOrNull { it.value == transactionId }?.key
        return localId?.let { Ocpp15TransactionIds(it, transactionId) }
    }

    override fun deleteTransactionIds(localId: String) {
        hashMap.remove(localId)
    }
}
