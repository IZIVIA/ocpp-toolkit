package fr.simatix.cs.simulator.integration.test

import fr.simatix.cs.simulator.api20.impl.OcppCSCallbacks
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core20.model.common.IdTokenType
import fr.simatix.cs.simulator.core20.model.common.enumeration.AuthorizationStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.IdTokenEnumType
import fr.simatix.cs.simulator.core20.model.common.enumeration.RequestStartStopStatusEnumType
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core20.model.statusnotification.enumeration.ConnectorStatusEnumType
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionType
import fr.simatix.cs.simulator.core20.model.transactionevent.enumeration.ChargingStateEnumType
import fr.simatix.cs.simulator.core20.model.transactionevent.enumeration.TransactionEventEnumType
import fr.simatix.cs.simulator.core20.model.transactionevent.enumeration.TriggerReasonEnumType
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointErrorCode as ChargePointErrorCode16
import fr.simatix.cs.simulator.core16.model.statusnotification.enumeration.ChargePointStatus as ChargePointStatus16
import fr.simatix.cs.simulator.api16.impl.OcppCSCallbacks as OcppCSCallbacks16
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeReq as AuthorizeReq16
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeResp as AuthorizeResp16
import fr.simatix.cs.simulator.core16.model.common.enumeration.AuthorizationStatus as AuthorizationStatus16
import fr.simatix.cs.simulator.core16.model.common.enumeration.RemoteStartStopStatus as RemoteStartStopStatus16
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionReq as RemoteStartTransactionReq16
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionResp as RemoteStartTransactionResp16
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionReq as StartTransactionReq16
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionResp as StartTransactionResp16
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationReq as StatusNotificationReq16

import fr.simatix.cs.simulator.integration.ApiFactory.Companion.Ocpp16ConnectionToCSMS
import fr.simatix.cs.simulator.integration.ApiFactory.Companion.Ocpp20ConnectionToCSMS
import fr.simatix.cs.simulator.integration.model.TransportEnum
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import kotlinx.datetime.Clock.System.now
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class ExampleTest {

    private val chargPointId = "my-chargepoint1"
    private val csmsUrl = "ws://127.0.0.1:8080/steve/websocket/CentralSystemService/"
    private val transport = TransportEnum.WEBSOCKET
    @Test
    fun ocpp16Charge() {

        //establish a connection to the CSMS
        val connection = Ocpp16ConnectionToCSMS(
                chargePointId = chargPointId,
                csmsUrl = csmsUrl,
                transportType = transport,
                ocppCSCallbacks = OcppCSCallbacks16()
        )
        connection.connect()

        //the idTag variable is used to make unique transaction, so, we can use it to stare at who started a transaction for example
        val idTag = "Tag2"

        //send an authorize request to the CSMS. We are retreiving the response from the CSMS throught the response variable.
        val response: AuthorizeResp16 = connection.authorize(RequestMetadata(chargPointId),AuthorizeReq16(idTag = idTag )).response

        //We're checking if the Authorization request has been accepted by the CSMS.
        if (response.idTagInfo.status == AuthorizationStatus16.Accepted) {

            println("Authorization Accepted")

            //As the Authorization has been accepted, we can start a transaction, but before,
            //we need to change the Status to Preparing
            connection.statusNotification(
                meta = RequestMetadata(chargPointId),
                request = StatusNotificationReq16(
                    connectorId = 1,
                    errorCode = ChargePointErrorCode16.NoError,
                    status = ChargePointStatus16.Preparing
                )
            )

            //We can now start a transaction by sending a StartTransaction request to the CSMS.
            //We can identify this transaction thanks to the idTag parameter
            //This function returns a response generated by the CSMS
            val response: StartTransactionResp16 =
                connection.startTransaction(
                    meta = RequestMetadata(chargPointId),
                    request = StartTransactionReq16(
                        connectorId = 4,
                        idTag = idTag,
                        meterStart = 0,
                        now()
                    )
                ).response

            //we are retrieving the transactionId from the reponse of our previous transaction request.
            val transactionId = response.transactionId

            println("TransactionId : $transactionId")

            // As the authorization has been accepted and that we have sent and receive a response for our startTransaction,
            // we can now set the charge status to Charging.
            // In order to do this, we send a statusNotification to the CSMS notifying that the status has changed to Charging
            connection.statusNotification(
                meta = RequestMetadata(chargPointId),
                request = StatusNotificationReq16(
                    connectorId = 1,
                    errorCode = ChargePointErrorCode16.NoError,
                    status = ChargePointStatus16.Charging
                )
            )
        }

        connection.close()
    }

    /**
     * Remote charge is when the CSMS send itself a message to start a transaction to an  EVSE.
     * It be done in multiple cases, for example :
     * - To enable a CSO to help an EV Driver remotly
     * - To remotly start a transaction
     * - To permit to mobile apps to control charging transactions
     * (from the specifications p 167)
     */
    @Test
    fun ocpp16RemoteCharge() {


        var remoteStartTransactionReq: RemoteStartTransactionReq16? = null

        //As previously said, the idTag variable is used to make unique transaction, so, we can use it to stare at who started a transaction for example
        val idTag = "Tag2"

        //define the callback for the remoteTransactionRequest
        //It returns a RemoteTransactionResponse
        val ocppCSCallbacks = object : OcppCSCallbacks16() {
            override fun remoteStartTransaction(req: RemoteStartTransactionReq16): RemoteStartTransactionResp16 {
                remoteStartTransactionReq = req
                return RemoteStartTransactionResp16(status = RemoteStartStopStatus16.Accepted)
            }
        }

        //establish a connection to the CSMS
        val connection = Ocpp16ConnectionToCSMS(
                chargePointId = chargPointId,
                csmsUrl = csmsUrl,
                transportType = transport,
                ocppCSCallbacks = ocppCSCallbacks
        )
        connection.connect()

        //defining the timeout delay for receiving a remoteTransactionRequest
        val waitUntil = now() + 1.toDuration(DurationUnit.MINUTES)

        //We are waiting for the remote start request from the CSMS
        while (remoteStartTransactionReq == null && now() < waitUntil) {
            sleep(1000)
        }

        //We are checking if there was a remoteTransactionRequest sent
        if (remoteStartTransactionReq != null) {
            println("${remoteStartTransactionReq?.idTag}")

            //As the Authorization has been accepted, we can start a transaction, but before,
            //we need to change the Status to Preparing
            connection.statusNotification(
                meta = RequestMetadata(chargPointId),
                request = StatusNotificationReq16(
                    connectorId = 1,
                    errorCode = ChargePointErrorCode16.NoError,
                    status = ChargePointStatus16.Preparing
                )
            )

            //We can now start a transaction by sending a StartTransaction request to the CSMS.
            //We can identify this transaction thanks to the idTag parameter
            //This function returns a response generated by the CSMS
            val response: StartTransactionResp16 =
                connection.startTransaction(
                    meta = RequestMetadata(chargPointId),
                    StartTransactionReq16(
                        connectorId = 1,
                        idTag = idTag,
                        meterStart = 0,
                        timestamp = now()
                    )
                ).response

            //we are retrieving the transactionId from the reponse of our previous transaction request.
            val transactionId = response.transactionId

            // As the authorization has been accepted and that we have sent and receive a response for our startTransaction,
            // we can now set the charge status to Charging.
            // In order to do this, we send a statusNotification to the CSMS notifying that the status has changed to Charging
            connection.statusNotification(
                meta = RequestMetadata(chargPointId),
                StatusNotificationReq16(
                    connectorId = 1,
                    errorCode = ChargePointErrorCode16.NoError,
                    status = ChargePointStatus16.Charging
                )
            )
        }
        connection.close()
    }



    @Test
    fun ocpp20Charge() {
        val connection = Ocpp20ConnectionToCSMS(
                chargePointId = chargPointId,
                csmsUrl = csmsUrl,
                transportType = transport,
                ocppCSCallbacks = OcppCSCallbacks()
        )
        connection.connect()

        val response: AuthorizeResp = connection.authorize(RequestMetadata(chargPointId), AuthorizeReq(IdTokenType(
                idToken = "2233223",
                type = IdTokenEnumType.Central,
        ))).response

        if (response.idTokenInfo.status == AuthorizationStatusEnumType.Accepted) {
            connection.statusNotification(
                    meta = RequestMetadata(chargPointId),
                    request = StatusNotificationReq(
                            connectorId = 1,
                            connectorStatus = ConnectorStatusEnumType.Occupied,
                            evseId = 1,
                            timestamp = now()
                    )
            )

            val response: TransactionEventResp =
                    connection.transactionEvent(
                            meta = RequestMetadata(chargPointId),
                            request = TransactionEventReq(
                                    eventType = TransactionEventEnumType.Started,
                                    timestamp = now(),
                                    triggerReason = TriggerReasonEnumType.Authorized,
                                    seqNo = 1,
                                    transactionInfo = TransactionType(
                                            "1",
                                            ChargingStateEnumType.Charging
                                    )
                            )
                    ).response

            connection.statusNotification(
                meta = RequestMetadata(chargPointId),
                request = StatusNotificationReq(
                        connectorId = 1,
                        connectorStatus = ConnectorStatusEnumType.Occupied,
                        evseId = 1,
                        timestamp = now()
                )
            )
        }
        connection.close()

    }

    @Test
    fun ocpp20RemoteCharge() {

        var remoteStartTransactionReq: RequestStartTransactionReq? = null

        val ocppCSCallbacks = object : OcppCSCallbacks() {
            override fun requestStartTransaction(req: RequestStartTransactionReq): RequestStartTransactionResp {
                remoteStartTransactionReq = req
                return RequestStartTransactionResp(RequestStartStopStatusEnumType.Accepted)
            }
        }

        val connection = Ocpp20ConnectionToCSMS(
                chargePointId = chargPointId,
                csmsUrl = csmsUrl,
                transportType = transport,
                ocppCSCallbacks = ocppCSCallbacks
        )
        connection.connect()

        val waitUntil = now() + 1.toDuration(DurationUnit.MINUTES)

        while (remoteStartTransactionReq == null && now() < waitUntil) {
            sleep(100)
        }

        if (remoteStartTransactionReq != null) {
            println("${remoteStartTransactionReq?.idToken?.idToken}")
            connection.statusNotification(
                    meta = RequestMetadata(chargPointId),
                    request = StatusNotificationReq(
                            connectorId = 1,
                            connectorStatus = ConnectorStatusEnumType.Occupied,
                            evseId = 1,
                            timestamp = now()
                    )
            )

            val response: TransactionEventResp =
                    connection.transactionEvent(
                            meta = RequestMetadata(chargPointId),
                            request = TransactionEventReq(
                                    eventType = TransactionEventEnumType.Started,
                                    timestamp = now(),
                                    triggerReason = TriggerReasonEnumType.Authorized,
                                    seqNo = 1,
                                    transactionInfo = TransactionType(
                                            "1",
                                            ChargingStateEnumType.Charging
                                    )
                            )
                    ).response

            connection.statusNotification(
                    meta = RequestMetadata(chargPointId),
                    request = StatusNotificationReq(
                            connectorId = 1,
                            connectorStatus = ConnectorStatusEnumType.Occupied,
                            evseId = 1,
                            timestamp = now()
                    )
            )
        }
        connection.close()
    }
}