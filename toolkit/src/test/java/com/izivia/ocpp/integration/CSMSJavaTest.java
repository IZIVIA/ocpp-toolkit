package com.izivia.ocpp.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.izivia.ocpp.integration.model.CSMSSettings;
import com.izivia.ocpp.integration.model.ServerSetting;
import com.izivia.ocpp.wamp.server.impl.AdmissionExecution;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

/**
 * The constructors a Java caller of {@link CSMS} and {@link CSMSSettings} compiles against: this source fails to
 * compile when one of them disappears from the Java view of the classes.
 */
class CSMSJavaTest {

    @Test
    void csmsConstructorOfTheReleasesBeforeAdmission() {
        CSMS csms = new CSMS(Map.of(), Set.of(), ocppId -> {
            throw new AssertionError("not called");
        });

        assertNotNull(csms);
    }

    @Test
    void csmsConstructorWithTheAdmission() {
        CSMS csms = new CSMS(Map.of(), Set.of(), ocppId -> {
            throw new AssertionError("not called");
        }, AdmissionExecution.Inline.INSTANCE);

        assertNotNull(csms);
    }

    @Test
    void csmsSettingsConstructorOfTheReleasesBeforeAdmissionRunsInline() {
        List<ServerSetting> servers = List.of();

        CSMSSettings settings = new CSMSSettings(servers);

        assertEquals(servers, settings.getServers());
        assertEquals(AdmissionExecution.Inline.INSTANCE, settings.getAdmission());
    }

    @Test
    void csmsSettingsConstructorWithTheAdmission() {
        CSMSSettings settings = new CSMSSettings(List.of(), AdmissionExecution.Inline.INSTANCE);

        assertEquals(AdmissionExecution.Inline.INSTANCE, settings.getAdmission());
    }
}
