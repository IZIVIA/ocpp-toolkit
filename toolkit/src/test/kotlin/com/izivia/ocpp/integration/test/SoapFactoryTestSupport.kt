package com.izivia.ocpp.integration.test

import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.soap.OcppSoapParser
import com.izivia.ocpp.soap.ResponseSoapMessage
import com.izivia.ocpp.transport.OcppVersion
import io.mockk.mockk
import kotlinx.datetime.Instant
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

internal const val TEST_MESSAGE_ID = "a727d144-82bb-497a-a0c7-4ef2295910d4"
internal val TEST_CURRENT_TIME = Instant.parse("2022-02-15T00:00:00.000Z")

internal fun soapApi(ocppVersion: OcppVersion, port: Int) =
    ApiFactory.getCSMSApi(
        settings = Settings(
            ocppVersion = ocppVersion,
            transportType = TransportEnum.SOAP,
            target = "http://localhost:$port/ocpp",
            clientPath = "/cp",
            clientPort = 0,
            newMessageId = { TEST_MESSAGE_ID }
        ),
        ocppId = "CP001",
        csApi = mockk<CSApi>(relaxed = true)
    )

internal fun soapServer(
    parser: OcppSoapParser,
    payload: Any,
    receivedActions: MutableList<String>
) = routes(
    "/ocpp/" bind Method.POST to { request ->
        val soapRequest = parser.parseAnyRequestFromSoap(request.bodyString())
        receivedActions += soapRequest.action
        Response(Status.OK).body(
            parser.mapResponseToSoap(
                ResponseSoapMessage(
                    action = soapRequest.action,
                    messageId = "response-$TEST_MESSAGE_ID",
                    relatesTo = soapRequest.messageId,
                    to = soapRequest.from,
                    from = soapRequest.to,
                    chargeBoxIdentity = soapRequest.chargingStationId,
                    payload = payload
                )
            )
        )
    }
).asServer(Undertow(0)).start()
