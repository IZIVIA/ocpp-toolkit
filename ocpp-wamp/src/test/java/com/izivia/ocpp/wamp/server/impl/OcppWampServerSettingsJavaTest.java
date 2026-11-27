package com.izivia.ocpp.wamp.server.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * The constructors a Java caller of {@link OcppWampServerSettings} compiles against: this source fails to compile when
 * one of them disappears from the Java view of the class.
 */
class OcppWampServerSettingsJavaTest {

    private static final int DEFAULT_MAX_CONCURRENT_CALLS = 32 * Runtime.getRuntime().availableProcessors();
    private static final int DEFAULT_MAX_QUEUED_CALLS = 1024;

    @Test
    void noArgConstructorKeepsEveryDefault() {
        OcppWampServerSettings settings = new OcppWampServerSettings();

        assertEquals(30_000L, settings.getTimeoutInMs());
        assertEquals(DEFAULT_MAX_CONCURRENT_CALLS, settings.getMaxConcurrentCalls());
        assertEquals(DEFAULT_MAX_QUEUED_CALLS, settings.getMaxQueuedCalls());
    }

    @Test
    void timeoutOnlyConstructorKeepsTheDefaultCallBounds() {
        OcppWampServerSettings settings = new OcppWampServerSettings(5_000L);

        assertEquals(5_000L, settings.getTimeoutInMs());
        assertEquals(DEFAULT_MAX_CONCURRENT_CALLS, settings.getMaxConcurrentCalls());
        assertEquals(DEFAULT_MAX_QUEUED_CALLS, settings.getMaxQueuedCalls());
    }

    @Test
    void fullConstructorSetsEveryValue() {
        OcppWampServerSettings settings = new OcppWampServerSettings(5_000L, 4, 0);

        assertEquals(5_000L, settings.getTimeoutInMs());
        assertEquals(4, settings.getMaxConcurrentCalls());
        assertEquals(0, settings.getMaxQueuedCalls());
    }
}
