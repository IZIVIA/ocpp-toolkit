package com.izivia.ocpp.wamp.server;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.izivia.ocpp.OcppVersion;
import com.izivia.ocpp.wamp.server.impl.AdmissionExecution;
import com.izivia.ocpp.wamp.server.impl.EventsListeners;
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings;
import java.util.EnumSet;
import org.http4k.server.Http4kServer;
import org.junit.jupiter.api.Test;

/**
 * The {@code asServer} functions a Java caller of {@link OcppWampServer} compiles against: this source fails to compile
 * when one of them disappears from the Java view of the file facade.
 */
class OcppWampServerJavaTest {

    private final OcppWampServer server = OcppWampServer.Companion.newServer(
        EnumSet.allOf(OcppVersion.class),
        "ws",
        new OcppWampServerSettings(),
        new EventsListeners(),
        null,
        ConnectionAuthorizer.Companion.getALLOW_ALL()
    );

    @Test
    void asServerOfTheReleasesBeforeAdmission() {
        Http4kServer http4kServer = OcppWampServerKt.asServer(server, 8000);

        assertNotNull(http4kServer);
    }

    @Test
    void asServerWithTheAdmission() {
        Http4kServer http4kServer = OcppWampServerKt.asServer(server, 8000, AdmissionExecution.Inline.INSTANCE);

        assertNotNull(http4kServer);
    }
}
