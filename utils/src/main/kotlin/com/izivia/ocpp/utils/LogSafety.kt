package com.izivia.ocpp.utils

import java.util.Collections
import java.util.IdentityHashMap

private const val MAX_REDACTION_DEPTH = 32

/**
 * Stands in a log for a throwable whose message is left out: its own message is the class name of the original, and
 * its stack trace is the one of the original.
 *
 * Built by [redactedForLog].
 */
class RedactedThrowable internal constructor(originalClassName: String) : Throwable(originalClassName)

/**
 * A copy of this throwable to pass to a logger, without any message: an exception message may quote a frame, a
 * payload or a credential.
 *
 * Every throwable of the cause chain and of the suppressed lists is replaced by a [RedactedThrowable] whose message
 * is the class name of the original and whose stack trace is the original one. A throwable met twice, as in a cause
 * cycle, is replaced the second time by a copy without cause nor suppressed, and the copy stops past 32 levels.
 *
 * The toolkit's own logs use it. Applications can use it too, for instance to report the [OcppErrorEvent.cause] an
 * [OcppErrorListener] receives to a logger or an error tracker without its messages, nor those of its causes.
 */
fun Throwable.redactedForLog(): Throwable =
    redact(this, Collections.newSetFromMap(IdentityHashMap()), 0)

private fun redact(original: Throwable, seen: MutableSet<Throwable>, depth: Int): Throwable {
    val copy = RedactedThrowable(original.javaClass.name)
    copy.stackTrace = original.stackTrace
    if (!seen.add(original) || depth >= MAX_REDACTION_DEPTH) {
        return copy
    }
    original.cause?.let { copy.initCause(redact(it, seen, depth + 1)) }
    original.suppressed.forEach { copy.addSuppressed(redact(it, seen, depth + 1)) }
    return copy
}

/**
 * This string, to be put in a log line when its peer controls it (a station id, a message id, a close reason):
 * every ISO control character and U+2028/U+2029 is rendered as a `\uXXXX` escape, so that it cannot forge a log line,
 * and past [max] characters it is truncated, with a marker giving its original length.
 */
fun String.escapedForLog(max: Int = 64): String {
    var end = minOf(length, max)
    if (end in 1 until length && this[end - 1].isHighSurrogate()) end--
    val escaped = StringBuilder(end + 16)
    for (i in 0 until end) {
        val c = this[i]
        if (Character.isISOControl(c) || c == '\u2028' || c == '\u2029') {
            escaped.append("\\u").append(String.format("%04x", c.code))
        } else {
            escaped.append(c)
        }
    }
    if (end < length) {
        escaped.append("...(").append(length).append(" chars)")
    }
    return escaped.toString()
}
