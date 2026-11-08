package com.izivia.ocpp.wamp.server.impl

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isFalse
import strikt.assertions.isTrue
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

class StationLocksTest {
    private val locks = StationLocks()

    @Test
    fun `should drop the lock of a key once released`() {
        val result = locks.withLock("cs1") { locks.size }

        expectThat(result).isEqualTo(1)
        expectThat(locks.size).isEqualTo(0)
    }

    @Test
    fun `should drop the lock of a key when its block throws`() {
        runCatching { locks.withLock("cs1") { error("boom") } }

        expectThat(locks.size).isEqualTo(0)
    }

    @Test
    fun `should keep the lock of a key while a thread waits on it`() {
        val held = CountDownLatch(1)
        val released = CountDownLatch(1)
        val holding = thread { locks.withLock("cs1") { held.countDown(); released.await(5, TimeUnit.SECONDS) } }
        expectThat(held.await(5, TimeUnit.SECONDS)).isTrue()

        val waited = CountDownLatch(1)
        val waiting = thread { locks.withLock("cs1") { waited.countDown() } }
        expectThat(waited.await(300, TimeUnit.MILLISECONDS)).isFalse()

        released.countDown()
        holding.join(5_000)
        expectThat(waited.await(5, TimeUnit.SECONDS)).isTrue()
        waiting.join(5_000)
        expectThat(locks.size).isEqualTo(0)
    }

    @Test
    fun `should not block a key on the lock of another`() {
        val released = CountDownLatch(1)
        val holding = thread { locks.withLock("cs1") { released.await(5, TimeUnit.SECONDS) } }

        val other = CountDownLatch(1)
        thread { locks.withLock("cs2") { other.countDown() } }
        expectThat(other.await(5, TimeUnit.SECONDS)).isTrue()

        released.countDown()
        holding.join(5_000)
    }

    @Test
    fun `should run one block at a time for a key, even across drops of its lock`() {
        val inside = AtomicInteger()
        val overlaps = AtomicInteger()
        val start = CountDownLatch(1)
        val threads = List(8) {
            thread {
                start.await()
                repeat(2_000) {
                    locks.withLock("cs1") {
                        if (inside.incrementAndGet() > 1) overlaps.incrementAndGet()
                        inside.decrementAndGet()
                    }
                }
            }
        }
        start.countDown()
        threads.forEach { it.join(30_000) }

        expectThat(overlaps.get()).isEqualTo(0)
        expectThat(locks.size).isEqualTo(0)
    }
}
