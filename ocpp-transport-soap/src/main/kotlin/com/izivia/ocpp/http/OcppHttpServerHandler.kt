package com.izivia.ocpp.http

interface OcppHttpServerHandler {
    /**
     * check for ocpp id existence
     *
     * this is used by server to accept connection for a given ocpp id.
     */
    fun accept(ocppId: String): Boolean
    /**
     * a http action handler
     */
    fun onAction(msg: HttpMessage): HttpMessage?
}