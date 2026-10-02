package com.izivia.ocpp.integration.test

import com.izivia.ocpp.json12.Ocpp12JsonParser
import com.izivia.ocpp.json15.Ocpp15JsonParser
import com.izivia.ocpp.json16.Ocpp16JsonParser
import com.izivia.ocpp.json20.Ocpp20JsonParser
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp as BootNotificationResp12
import com.izivia.ocpp.core15.model.bootnotification.BootNotificationResp as BootNotificationResp15
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp as BootNotificationResp16
import com.izivia.ocpp.core20.model.authorize.AuthorizeReq as AuthorizeReq20

/**
 * Every ocpp-*-json module ships schemas under the same action names, and OcppJsonValidator resolves
 * them off the classpath. The collision is only observable where several versions share a classpath,
 * i.e. here in toolkit. Each payload below is valid in its own version but rejected by the schema of
 * the older version that would shadow it if the schemas sat at the resources root.
 *
 * Only the 2.0 and 1.6 cases reproduce the original collision (#111): 1.5 came first in classpath
 * order anyway, and 1.2 was namespaced from the start. Those two are guards against the classpath
 * order changing, and each case needs its module on the toolkit test classpath to mean anything.
 */
class JsonSchemaIsolationTest {

    @Test
    fun `validates OCPP 2-0 requests against the 2-0 schemas`() {
        // The 1.6 AuthorizeRequest requires idTag and forbids additional properties.
        val request = """[2,"messageId","Authorize",{"idToken":{"idToken":"Tag1","type":"ISO14443"}}]"""

        expectThat(Ocpp20JsonParser().parseAnyFromString(request))
            .and {
                get { action }.isEqualTo("Authorize")
                get { payload }.isA<AuthorizeReq20>().get { idToken.idToken }.isEqualTo("Tag1")
            }
    }

    @Test
    fun `validates OCPP 1-6 responses against the 1-6 schemas`() {
        // The 1.5 BootNotificationResponse requires heartbeatInterval, forbids interval and has no Pending.
        val response = """[3,"messageId",{"status":"Pending","currentTime":"2022-07-21T12:00:00Z","interval":1800}]"""

        expectThat(Ocpp16JsonParser().parseAnyFromJson<BootNotificationResp16>(response))
            .and {
                get { action }.isEqualTo("bootNotification")
                get { payload }.isA<BootNotificationResp16>().get { interval }.isEqualTo(1800)
            }
    }

    @Test
    fun `validates OCPP 1-5 responses against the 1-5 schemas`() {
        // The 1.6 BootNotificationResponse requires interval and forbids heartbeatInterval.
        val response =
            """[3,"messageId",{"status":"Accepted","currentTime":"2022-07-21T12:00:00Z","heartbeatInterval":300}]"""

        expectThat(Ocpp15JsonParser().parseAnyFromJson<BootNotificationResp15>(response))
            .and {
                get { action }.isEqualTo("bootNotification")
                get { payload }.isA<BootNotificationResp15>().get { heartbeatInterval }.isEqualTo(300)
            }
    }

    @Test
    fun `validates OCPP 1-2 responses against the 1-2 schemas`() {
        // currentTime and heartbeatInterval are mandatory in 1.5 but optional in 1.2.
        val response = """[3,"messageId",{"status":"Accepted"}]"""

        expectThat(Ocpp12JsonParser().parseAnyFromJson<BootNotificationResp12>(response))
            .and {
                get { action }.isEqualTo("bootNotification")
                get { payload }.isA<BootNotificationResp12>()
            }
    }
}
