package com.izivia.ocpp.wamp.server.security

import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.utils.escapedForLog
import com.izivia.ocpp.wamp.server.ConnectionAuthorizer
import com.izivia.ocpp.wamp.server.ConnectionRequest
import com.izivia.ocpp.wamp.server.security.AuthenticationFailure.Reason
import org.slf4j.LoggerFactory
import java.security.MessageDigest
import java.util.Base64

/**
 * Checks the HTTP Basic authentication of OCPP security profiles 1 and 2 against the secret expected for each
 * charging station.
 *
 * The username must be the station id (A00.FR.204), as decoded from the URL: a station connecting to
 * `/ocppj/RDAM%20123` sends the username `RDAM 123`. An id holding a `:` cannot authenticate, since the username
 * ends at the first `:` (RFC 7617), which is why OCPP-J 2.0.1 keeps it out of the identity.
 *
 * How the password is compared depends on the type of the [StationSecurity], never on the negotiated OCPP version.
 *
 * @param securityOf the security expected for a station, or `null` for an unknown station, which is refused.
 *   If it throws, the exception propagates and the server refuses the station.
 * @param onFailure notified of each failed check, whether the station is refused ([Enforcement.BLOCK], unknown
 *   station) or admitted anyway ([Enforcement.REPORT]). If it throws, the decision is kept, be it an exception or
 *   an error such as a `NoClassDefFoundError`: only a `VirtualMachineError` propagates.
 *
 * Both run on the WebSocket IO thread and must not block: keep the secrets in memory rather than querying a
 * database on each connection.
 */
class SecurityProfileAuthorizer(
    private val securityOf: (CSOcppId) -> StationSecurity?,
    private val onFailure: (AuthenticationFailure) -> Unit = {},
) : ConnectionAuthorizer {

    override fun authorize(request: ConnectionRequest): Boolean {
        val ocppId = request.ocppId
        // parsed before the lookup, so that an unknown station is not answered sooner than a known one
        val credentials = parse(request)
        val security = securityOf(ocppId)
            ?: return fail(AuthenticationFailure(ocppId, Reason.UNKNOWN_STATION, Enforcement.BLOCK))
        val (accepted, enforcement) = when (security) {
            StationSecurity.Unsecured -> return true
            is StationSecurity.BasicAuthPassword -> security.accepted to security.enforcement
            is StationSecurity.AuthorizationKey -> security.accepted to security.enforcement
        }
        val reason = when (credentials) {
            is Credentials.Invalid -> credentials.reason
            is Credentials.Password -> Reason.WRONG_PASSWORD.takeUnless {
                accepted.any { MessageDigest.isEqual(credentials.bytes, it) }
            }
        } ?: return true
        return fail(AuthenticationFailure(ocppId, reason, enforcement))
    }

    private fun parse(request: ConnectionRequest): Credentials {
        val encoded = request.headers
            .firstOrNull { (name, _) -> name.equals("Authorization", ignoreCase = true) }?.second
            ?.takeIf { it.startsWith("Basic ", ignoreCase = true) }
            ?.substring(6)?.trim()
            ?: return Credentials.Invalid(Reason.MISSING_CREDENTIALS)
        val credentials = try {
            Base64.getDecoder().decode(encoded)
        } catch (_: IllegalArgumentException) {
            return Credentials.Invalid(Reason.MALFORMED_CREDENTIALS)
        }
        // split as bytes: the username cannot hold a ':', but an OCPP 1.6 binary password can, and is not UTF-8
        val colon = credentials.indexOf(':'.code.toByte())
        if (colon < 0) return Credentials.Invalid(Reason.MALFORMED_CREDENTIALS)
        if (String(credentials, 0, colon, Charsets.UTF_8) != request.ocppId) {
            return Credentials.Invalid(Reason.USERNAME_MISMATCH)
        }
        return Credentials.Password(credentials.copyOfRange(colon + 1, credentials.size))
    }

    private sealed interface Credentials {
        class Password(val bytes: ByteArray) : Credentials
        class Invalid(val reason: Reason) : Credentials
    }

    private fun fail(failure: AuthenticationFailure): Boolean {
        try {
            onFailure(failure)
        } catch (e: VirtualMachineError) {
            throw e
        } catch (e: Throwable) {
            // Throwable on purpose, as in notifySafely: a library missing at runtime fails with a NoClassDefFoundError
            logger.warn("[${failure.ocppId.escapedForLog()}] authentication failure listener failed with ${e::class.qualifiedName}")
        }
        return failure.enforcement == Enforcement.REPORT
    }

    private companion object {
        val logger = LoggerFactory.getLogger(SecurityProfileAuthorizer::class.java)
    }
}
