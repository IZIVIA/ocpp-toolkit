package com.izivia.ocpp.integration.test

import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.api.CSMSApiCallbacks
import com.izivia.ocpp.api.model.authorize.AuthorizeReq
import com.izivia.ocpp.api.model.authorize.AuthorizeResp
import com.izivia.ocpp.api.model.common.IdTokenInfoType
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.enumeration.AuthorizationStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.IdTokenEnumType
import com.izivia.ocpp.api.model.common.enumeration.RequestStartStopStatusEnumType
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.integration.ApiFactory.Companion.CSMSOcppServer
import com.izivia.ocpp.integration.ApiFactory.Companion.Ocpp16ConnectionToCSMS
import com.izivia.ocpp.integration.CSMS
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.RequestMetadata
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.EnabledIfSystemProperty
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import com.izivia.ocpp.api16.OcppCSCallbacks as OcppCSCallbacks16
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq as AuthorizeReq16
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp as AuthorizeResp16
import com.izivia.ocpp.core16.model.common.enumeration.AuthorizationStatus as AuthorizationStatus16
import com.izivia.ocpp.core16.model.common.enumeration.RemoteStartStopStatus as RemoteStartStopStatus16
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionReq as RemoteStartTransactionReq16
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionResp as RemoteStartTransactionResp16

@EnabledIfSystemProperty(named = "has.local.steve", matches = "true")
class ExampleCSApiTest {

    @Test
    fun `csApi of CSMS test`() {
        val port = 12345
        val idTag = "Tag2"

        // Create and start the CSMS server
        val csmsApi: CSMSApiCallbacks = object : CSMSApiCallbacks {
            override fun authorize(request: AuthorizeReq):  AuthorizeResp =
                AuthorizeResp(
                    IdTokenInfoType(
                        status = if (request.idToken.idToken == idTag) AuthorizationStatusEnumType.Accepted
                        else AuthorizationStatusEnumType.Invalid)
                )
        }

        val path = "ws"
        val server: CSMS = CSMSOcppServer(
            CSMSSettings(
                port = port,
                servers = listOf(
                    ServerSetting(
                        path = path,
                        ocppVersion = setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0),
                        transportType = TransportEnum.WEBSOCKET
                    )
                )
            ),
            csmsApiCallbacks = csmsApi,
            fn = { ocppId: String -> ChargingStationConfig(acceptWSConnection = true, soapUrl = null) }
        )

        server.start()


        //Create a CS in ocpp16 and connect to the CSMS
        val ocppCSCallbacks = object : OcppCSCallbacks16{
            override fun remoteStartTransaction(req: RemoteStartTransactionReq16): RemoteStartTransactionResp16 =
                RemoteStartTransactionResp16(
                    status = if (req.idTag == idTag) RemoteStartStopStatus16.Accepted
                    else RemoteStartStopStatus16.Rejected
                )
        }


        val chargPointId = "my-chargepoint1"

        //establish a connection to the CSMS
        val connection = Ocpp16ConnectionToCSMS(
            chargePointId = chargPointId,
            csmsUrl =  "ws://localhost:$port/$path",
            transportType = TransportEnum.WEBSOCKET,
            ocppCSCallbacks = ocppCSCallbacks
        )
        connection.connect()

        //send an authorize request to the CSMS
        val responseAuthorize: AuthorizeResp16 =
            connection.authorize(RequestMetadata(chargPointId), AuthorizeReq16(idTag = idTag)).response

        //We're checking if the Authorization request has been accepted by the CSMS.
        expectThat(responseAuthorize.idTagInfo.status).isEqualTo(AuthorizationStatus16.Accepted)

        val genericApi = server.getCSApiGeneric()
        val responseRemote: RequestStartTransactionResp = genericApi.requestStartTransaction(
            RequestMetadata(chargPointId),
            RequestStartTransactionReq(
                remoteStartId = 1,
                idToken = IdTokenType(idTag, IdTokenEnumType.Central)
            )
        ).response

        expectThat(responseRemote.status).isEqualTo(RequestStartStopStatusEnumType.Rejected)

        connection.close()
        server.stop()
    }
}





