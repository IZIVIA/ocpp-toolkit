package com.izivia.ocpp.wamp.server.security

import com.izivia.ocpp.CSOcppId

/**
 * How strictly the credentials of a charging station are checked.
 */
enum class Enforcement {
    /** Checked: a station that fails is refused, and the failure notified. */
    BLOCK,

    /** Checked: a station that fails is still admitted, and the failure notified, for instance during a migration. */
    REPORT,
}

/**
 * The secret a charging station authenticates with, and how strictly it is enforced.
 *
 * The type of the secret, not the negotiated OCPP version, decides how the password it sends is compared.
 * The secret is never shown by [toString].
 */
sealed interface StationSecurity {

    /** A station without credentials, for instance a legacy one: it is admitted without any check nor notification. */
    data object Unsecured : StationSecurity

    /**
     * The OCPP 2.0.1 `BasicAuthPassword` (A00.FR.205): a passwordString of 16 to 40 characters among
     * `a-z A-Z 0-9 * - _ = : + | @ .`, sent as UTF-8.
     */
    class BasicAuthPassword(password: String, val enforcement: Enforcement = Enforcement.BLOCK) :
        StationSecurity {
        init {
            require(PASSWORD_STRING.matches(password)) {
                "a BasicAuthPassword must be 16 to 40 characters among a-z A-Z 0-9 * - _ = : + | @ ."
            }
        }

        internal val accepted: List<ByteArray> = listOf(password.toByteArray(Charsets.UTF_8))

        override fun toString() = "BasicAuthPassword(enforcement=$enforcement)"

        private companion object {
            val PASSWORD_STRING = Regex("[a-zA-Z0-9*\\-_=:+|@.]{16,40}")
        }
    }

    /**
     * The OCPP 1.6 `AuthorizationKey` (Security Whitepaper, A00.FR.205): 16 to 20 bytes, given as 32 to 40
     * hexadecimal digits.
     *
     * The whitepaper does not say whether a station sends the bytes or their hexadecimal form: both are accepted,
     * the hexadecimal form in lower or upper case.
     */
    class AuthorizationKey(hex: String, val enforcement: Enforcement = Enforcement.BLOCK) :
        StationSecurity {
        init {
            require(HEX_KEY.matches(hex)) {
                "an AuthorizationKey must be 16 to 20 bytes, given as 32 to 40 hexadecimal digits"
            }
        }

        internal val accepted: List<ByteArray> = listOf(
            hex.hexToByteArray(),
            hex.lowercase().toByteArray(Charsets.US_ASCII),
            hex.uppercase().toByteArray(Charsets.US_ASCII),
        )

        override fun toString() = "AuthorizationKey(enforcement=$enforcement)"

        private companion object {
            val HEX_KEY = Regex("(?:[0-9a-fA-F]{2}){16,20}")
        }
    }
}

/**
 * A charging station whose credentials failed the check. It carries no credentials.
 */
class AuthenticationFailure(val ocppId: CSOcppId, val reason: Reason, val enforcement: Enforcement) {
    enum class Reason { UNKNOWN_STATION, MISSING_CREDENTIALS, MALFORMED_CREDENTIALS, USERNAME_MISMATCH, WRONG_PASSWORD }

    override fun toString() = "AuthenticationFailure(ocppId=$ocppId, reason=$reason, enforcement=$enforcement)"
}
