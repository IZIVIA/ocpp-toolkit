package com.izivia.ocpp.wamp.server.security

import com.izivia.ocpp.CSOcppId
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
 * The username must be the station id (A00.FR.204). How the password is compared depends on the type of the
 * [StationSecurity], never on the negotiated OCPP version.
 *
 * @param securityOf the security expected for a station, or `null` for an unknown station, which is refused.
 *   If it throws, the exception propagates and the server refuses the station.
 * @param onFailure notified of each failed check, whether the station is refused ([Enforcement.BLOCK], unknown
 *   station) or admitted anyway ([Enforcement.REPORT]). If it throws, the decision is kept.
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
        val security = securityOf(ocppId)
            ?: return fail(AuthenticationFailure(ocppId, Reason.UNKNOWN_STATION, Enforcement.BLOCK))
        val (accepted, enforcement) = when (security) {
            StationSecurity.Unsecured -> return true
            is StationSecurity.BasicAuthPassword -> security.accepted to security.enforcement
            is StationSecurity.AuthorizationKey -> security.accepted to security.enforcement
        }
        val reason = check(request, accepted) ?: return true
        return fail(AuthenticationFailure(ocppId, reason, enforcement))
    }

    private fun check(request: ConnectionRequest, accepted: List<ByteArray>): Reason? {
        val encoded = request.headers
            .firstOrNull { (name, _) -> name.equals("Authorization", ignoreCase = true) }?.second
            ?.takeIf { it.startsWith("Basic ", ignoreCase = true) }
            ?.substring(6)?.trim()
            ?: return Reason.MISSING_CREDENTIALS
        val credentials = try {
            Base64.getDecoder().decode(encoded)
        } catch (_: IllegalArgumentException) {
            return Reason.MALFORMED_CREDENTIALS
        }
        // split as bytes: the username cannot hold a ':', but an OCPP 1.6 binary password can, and is not UTF-8
        val colon = credentials.indexOf(':'.code.toByte())
        if (colon < 0) return Reason.MALFORMED_CREDENTIALS
        if (String(credentials, 0, colon, Charsets.UTF_8) != request.ocppId) return Reason.USERNAME_MISMATCH
        val password = credentials.copyOfRange(colon + 1, credentials.size)
        return if (accepted.any { MessageDigest.isEqual(password, it) }) null else Reason.WRONG_PASSWORD
    }

    private fun fail(failure: AuthenticationFailure): Boolean {
        try {
            onFailure(failure)
        } catch (e: Exception) {
            logger.warn("[${failure.ocppId}] authentication failure listener failed with ${e::class.qualifiedName}")
        }
        return failure.enforcement == Enforcement.REPORT
    }

    private companion object {
        val logger = LoggerFactory.getLogger(SecurityProfileAuthorizer::class.java)
    }
}
