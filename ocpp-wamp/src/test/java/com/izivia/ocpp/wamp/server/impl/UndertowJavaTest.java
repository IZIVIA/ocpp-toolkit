package com.izivia.ocpp.wamp.server.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.izivia.ocpp.wamp.server.WebSocketUpgradeDecision;
import java.time.Duration;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * The constructors a Java caller of {@link Undertow} compiles against: this source fails to compile when one of them
 * disappears from the Java view of the class.
 */
class UndertowJavaTest {

    @Test
    void constructorOfTheReleasesBeforeAdmissionRunsInline() {
        Undertow undertow = new Undertow(8000, true, exchange -> WebSocketUpgradeDecision.Accept.INSTANCE, Set.of("ocpp1.6"));

        assertEquals(8000, undertow.getPort());
        assertTrue(undertow.getEnableHttp2());
        assertEquals(Set.of("ocpp1.6"), undertow.getWsSubprotocols());
        assertEquals(AdmissionExecution.Inline.INSTANCE, undertow.getAdmission());
    }

    @Test
    void fullConstructorSetsTheAdmission() {
        AdmissionExecution admission = new AdmissionExecution.Dispatched(4, Duration.ofSeconds(1), null);

        Undertow undertow = new Undertow(8000, true, exchange -> WebSocketUpgradeDecision.Accept.INSTANCE, Set.of(), admission);

        assertEquals(admission, undertow.getAdmission());
    }
}
