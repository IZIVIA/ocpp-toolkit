package fr.simatix.cs.simulator.adapter16.impl

import fr.simatix.cs.simulator.adapter16.Ocpp16TransactionIds
import fr.simatix.cs.simulator.adapter16.TransactionRepository

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