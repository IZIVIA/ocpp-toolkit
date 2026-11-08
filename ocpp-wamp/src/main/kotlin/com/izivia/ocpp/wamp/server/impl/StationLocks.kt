package com.izivia.ocpp.wamp.server.impl

import java.util.concurrent.ConcurrentHashMap

/**
 * One lock per key, kept only while a thread holds or waits on it: the keys come from the requests, so locks kept
 * forever would grow with every distinct key ever seen.
 */
internal class StationLocks {
    private val locks = ConcurrentHashMap<String, StationLock>()

    /** The number of keys whose lock is held or waited on. */
    val size: Int get() = locks.size

    fun <T> withLock(key: String, block: () -> T): T {
        // users is only read and written inside compute, which runs atomically for a key
        val lock = locks.compute(key) { _, lock -> (lock ?: StationLock()).also { it.users++ } }!!
        try {
            return synchronized(lock, block)
        } finally {
            locks.computeIfPresent(key) { _, lock -> lock.takeIf { --it.users > 0 } }
        }
    }

    private class StationLock {
        var users = 0
    }
}
