package com.izivia.ocpp.utils

/**
 * This host as it appears in a URI authority: an IPv6 literal is bracketed, anything else is
 * returned untouched, so that `"$scheme://${host.toUriHost()}:$port"` is a valid URL for any host.
 */
fun String.toUriHost(): String = if (':' in this && !startsWith('[')) "[$this]" else this
