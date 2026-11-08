package com.izivia.ocpp.wamp.server.security

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.Handshake
import com.izivia.ocpp.wamp.basicAuthorization
import com.izivia.ocpp.wamp.getFreePort
import com.izivia.ocpp.wamp.handshake
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageMeta
import com.izivia.ocpp.wamp.server.ConnectionRequest
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.OcppWampServerHandler
import com.izivia.ocpp.wamp.server.asServer
import com.izivia.ocpp.wamp.server.security.AuthenticationFailure.Reason
import com.izivia.ocpp.wamp.server.security.AuthenticationFailure.Reason.MALFORMED_CREDENTIALS
import com.izivia.ocpp.wamp.server.security.AuthenticationFailure.Reason.MISSING_CREDENTIALS
import com.izivia.ocpp.wamp.server.security.AuthenticationFailure.Reason.UNKNOWN_STATION
import com.izivia.ocpp.wamp.server.security.AuthenticationFailure.Reason.USERNAME_MISMATCH
import com.izivia.ocpp.wamp.server.security.AuthenticationFailure.Reason.WRONG_PASSWORD
import com.izivia.ocpp.wamp.server.security.Enforcement.BLOCK
import com.izivia.ocpp.wamp.server.security.Enforcement.REPORT
import com.izivia.ocpp.wamp.server.security.StationSecurity.AuthorizationKey
import com.izivia.ocpp.wamp.server.security.StationSecurity.BasicAuthPassword
import com.izivia.ocpp.wamp.server.security.StationSecurity.Unsecured
import org.junit.jupiter.api.Test
import strikt.api.expect
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.contains
import strikt.assertions.isA
import strikt.assertions.isEmpty
import strikt.assertions.isEqualTo
import strikt.assertions.isFailure
import strikt.assertions.isFalse
import strikt.assertions.isTrue
import strikt.assertions.succeeded
import java.util.Base64
import java.util.concurrent.CopyOnWriteArrayList

class SecurityProfileAuthorizerTest {
    private val failures = CopyOnWriteArrayList<AuthenticationFailure>()

    private val stations = mutableMapOf<CSOcppId, StationSecurity>(
        "CS201" to BasicAuthPassword(PASSWORD),
        "CS16" to AuthorizationKey(AUTHORIZATION_KEY),
    )

    private val authorizer = SecurityProfileAuthorizer(stations::get) { failures += it }

    @Test
    fun `OCPP 2_0_1 station with the right password is admitted`() {
        expect {
            that(authorizer.authorize(request("CS201", basicAuthorization("CS201", PASSWORD)))).isTrue()
            that(failures).isEmpty()
        }
    }

    @Test
    fun `OCPP 2_0_1 station with a wrong password is refused`() {
        expectRefused(request("CS201", basicAuthorization("CS201", "wrong-password-1234")), "CS201", WRONG_PASSWORD)
    }

    @Test
    fun `OCPP 2_0_1 password holding a colon is split at the first one`() {
        val password = "user:pass:word-1234"
        stations["CS201"] = BasicAuthPassword(password)

        expectThat(authorizer.authorize(request("CS201", basicAuthorization("CS201", password)))).isTrue()
    }

    @Test
    fun `OCPP 1_6 station sending the bytes of its authorization key is admitted`() {
        expect {
            that(authorizer.authorize(request("CS16", basicAuthorization("CS16", AUTHORIZATION_KEY.hexToByteArray())))).isTrue()
            that(failures).isEmpty()
        }
    }

    @Test
    fun `OCPP 1_6 station sending the hexadecimal form of its authorization key is admitted`() {
        expect {
            that(authorizer.authorize(request("CS16", basicAuthorization("CS16", AUTHORIZATION_KEY)))).isTrue()
            that(failures).isEmpty()
        }
    }

    @Test
    fun `OCPP 1_6 station sending a wrong authorization key is refused`() {
        val wrong = "00" + AUTHORIZATION_KEY.drop(2)

        expectRefused(request("CS16", basicAuthorization("CS16", wrong.hexToByteArray())), "CS16", WRONG_PASSWORD)
    }

    @Test
    fun `the rule comes from the secret, so an authorization key is not accepted as a 2_0_1 password`() {
        stations["CS201"] = BasicAuthPassword(AUTHORIZATION_KEY)

        expectRefused(
            request("CS201", basicAuthorization("CS201", AUTHORIZATION_KEY.hexToByteArray())),
            "CS201",
            WRONG_PASSWORD
        )
    }

    @Test
    fun `station authenticating with another station id is refused`() {
        expectRefused(request("CS201", basicAuthorization("CS16", PASSWORD)), "CS201", USERNAME_MISMATCH)
    }

    @Test
    fun `station without an authorization header is refused`() {
        expectRefused(request("CS201"), "CS201", MISSING_CREDENTIALS)
    }

    @Test
    fun `station using another scheme than basic is refused`() {
        expectRefused(request("CS201", "Authorization" to "Bearer $PASSWORD"), "CS201", MISSING_CREDENTIALS)
    }

    @Test
    fun `station sending credentials that are not base64 is refused`() {
        expectRefused(request("CS201", "Authorization" to "Basic not*base64"), "CS201", MALFORMED_CREDENTIALS)
    }

    @Test
    fun `station sending credentials without a colon is refused`() {
        val credentials = Base64.getEncoder().encodeToString("CS201$PASSWORD".toByteArray())

        expectRefused(request("CS201", "Authorization" to "Basic $credentials"), "CS201", MALFORMED_CREDENTIALS)
    }

    @Test
    fun `unknown station is refused`() {
        expectRefused(request("UNKNOWN", basicAuthorization("UNKNOWN", PASSWORD)), "UNKNOWN", UNKNOWN_STATION)
    }

    @Test
    fun `unsecured station is admitted without credentials nor notification`() {
        stations["LEGACY"] = Unsecured

        expect {
            that(authorizer.authorize(request("LEGACY"))).isTrue()
            that(authorizer.authorize(request("LEGACY", basicAuthorization("LEGACY", "wrong-password-1234")))).isTrue()
            that(failures).isEmpty()
        }
    }

    @Test
    fun `station with enforcement report is admitted despite a wrong password, and the failure notified`() {
        stations["MIGRATING"] = AuthorizationKey(AUTHORIZATION_KEY, REPORT)

        expect {
            that(authorizer.authorize(request("MIGRATING", basicAuthorization("MIGRATING", "wrong")))).isTrue()
            that(failures.map { it.describe() }).isEqualTo(listOf("MIGRATING WRONG_PASSWORD REPORT"))
        }
    }

    @Test
    fun `station with enforcement report and the right password is admitted without notification`() {
        stations["MIGRATING"] = BasicAuthPassword(PASSWORD, REPORT)

        expect {
            that(authorizer.authorize(request("MIGRATING", basicAuthorization("MIGRATING", PASSWORD)))).isTrue()
            that(failures).isEmpty()
        }
    }

    @Test
    fun `station with enforcement block is refused on a wrong password, and the failure notified`() {
        stations["STRICT"] = BasicAuthPassword(PASSWORD, BLOCK)

        expectRefused(request("STRICT", basicAuthorization("STRICT", "wrong-password-1234")), "STRICT", WRONG_PASSWORD)
    }

    @Test
    fun `failure listener that throws keeps the decision`() {
        stations["MIGRATING"] = BasicAuthPassword(PASSWORD, REPORT)
        val throwing = SecurityProfileAuthorizer(stations::get) { error("listener down") }

        expect {
            that(throwing.authorize(request("CS201", basicAuthorization("CS201", "wrong-password-1234")))).isFalse()
            that(throwing.authorize(request("MIGRATING", basicAuthorization("MIGRATING", "wrong")))).isTrue()
            that(throwing.authorize(request("UNKNOWN"))).isFalse()
        }
    }

    @Test
    fun `station security lookup that throws propagates`() {
        val throwing = SecurityProfileAuthorizer({ BasicAuthPassword("short") })

        expectThrows<IllegalArgumentException> { throwing.authorize(request("CS201", basicAuthorization("CS201", PASSWORD))) }
    }

    @Test
    fun `basic auth password must be 16 to 40 characters of the OCPP 2_0_1 charset`() {
        expect {
            catching { BasicAuthPassword("a".repeat(15)) }.isFailure().isA<IllegalArgumentException>()
            catching { BasicAuthPassword("a".repeat(41)) }.isFailure().isA<IllegalArgumentException>()
            catching { BasicAuthPassword("password with space") }.isFailure().isA<IllegalArgumentException>()
            catching { BasicAuthPassword("pässwordString-1234") }.isFailure().isA<IllegalArgumentException>()
            catching { BasicAuthPassword("a".repeat(16)) }.succeeded()
            catching { BasicAuthPassword("a".repeat(40)) }.succeeded()
            catching { BasicAuthPassword("aZ09*-_=:+|@.aaaa") }.succeeded()
        }
    }

    @Test
    fun `authorization key must be 16 to 20 bytes in hexadecimal`() {
        expect {
            catching { AuthorizationKey("ab".repeat(15)) }.isFailure().isA<IllegalArgumentException>()
            catching { AuthorizationKey("ab".repeat(21)) }.isFailure().isA<IllegalArgumentException>()
            catching { AuthorizationKey("zz".repeat(16)) }.isFailure().isA<IllegalArgumentException>()
            catching { AuthorizationKey("a".repeat(33)) }.isFailure().isA<IllegalArgumentException>()
            catching { AuthorizationKey("ab".repeat(16)) }.succeeded()
            catching { AuthorizationKey("AB".repeat(20)) }.succeeded()
        }
    }

    @Test
    fun `station securities do not show their secret when printed`() {
        expect {
            that(BasicAuthPassword(PASSWORD).toString())
                .isEqualTo("BasicAuthPassword(enforcement=BLOCK)")
            that(AuthorizationKey(AUTHORIZATION_KEY, REPORT).toString())
                .isEqualTo("AuthorizationKey(enforcement=REPORT)")
        }
    }

    @Test
    fun `server answers 401 to a wrong password and connects the right one`() {
        val port = getFreePort()
        val server = OcppWampServer.newServer(setOf(OCPP_1_6), authorizer = authorizer).let { wamp ->
            wamp.register(object : OcppWampServerHandler {
                override fun accept(ocppId: CSOcppId) = true
                override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage? = null
            })
            wamp.asServer(port).start()
        }

        try {
            expect {
                that(handshake(port, "CS16", basicAuthorization("CS16", "wrong")))
                    .isEqualTo(Handshake(401, """Basic realm="OCPP""""))
                that(handshake(port, "CS16", basicAuthorization("CS16", AUTHORIZATION_KEY.hexToByteArray())).status)
                    .isEqualTo(101)
                that(failures.map { it.describe() }).contains("CS16 WRONG_PASSWORD BLOCK")
            }
        } finally {
            server.stop()
        }
    }

    private fun expectRefused(request: ConnectionRequest, ocppId: CSOcppId, reason: Reason) {
        expect {
            that(authorizer.authorize(request)).isFalse()
            that(failures.map { it.describe() }).isEqualTo(listOf("$ocppId $reason BLOCK"))
        }
    }

    private fun AuthenticationFailure.describe() = "$ocppId $reason $enforcement"

    private fun request(ocppId: CSOcppId, vararg headers: Pair<String, String>) =
        ConnectionRequest(ocppId, headers.toList())

    companion object {
        private const val PASSWORD = "passwordString-1234"

        // 16 bytes holding a ':' (3a) and a sequence that is not valid UTF-8 (c3 28)
        private const val AUTHORIZATION_KEY = "ff3ac3280102030405060708090a0b0c"
    }
}
