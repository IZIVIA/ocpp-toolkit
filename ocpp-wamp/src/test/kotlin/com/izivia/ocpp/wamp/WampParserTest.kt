package fr.izivia.websocketproxy.wamp

import com.izivia.ocpp.utils.MessageErrorCode
import com.izivia.ocpp.wamp.messages.WampMessage
import com.izivia.ocpp.wamp.messages.WampMessageParser
import com.izivia.ocpp.wamp.messages.WampMessageType
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull
import java.io.File

class WampParserTest {

    @Test
    fun `should parse wrong typed Msg and return null`() {
        val callTypeMsg =
            """[25,"37e834e6-0698-46da-b6d0-f2b3b3de11d9","StatusNotification",{"connectorId":1,"errorCode":"NoError","info":"","status":"Available","timestamp":"2023-03-03T11:38:43.522Z"}]"""
        var wampMessage = WampMessageParser.parse(callTypeMsg)
        expectThat(wampMessage).isNull()
    }

    @Test
    fun `should parse wrong Msg and return null`() {
        var callTypeMsg = """["2", "*****"]"""
        var wampMessage = WampMessageParser.parse(callTypeMsg)
        expectThat(wampMessage).isNull()

        callTypeMsg = ""
        wampMessage = WampMessageParser.parse(callTypeMsg)
        expectThat(wampMessage).isNull()
    }

    @Test
    fun `should parse call Msg`() {
        val callTypeMsg =
            """[2,"37e834e6-0698-46da-b6d0-f2b3b3de11d9","StatusNotification",{"connectorId":1,"errorCode":"NoError","info":"","status":"Available","timestamp":"2023-03-03T11:38:43.522Z"}]"""
        val callTypeMsgWithoutQuote =
            """[2,37e834e6-0698-46da-b6d0-f2b3b3de11d9,StatusNotification,{"connectorId":1,"errorCode":"NoError","info":"","status":"Available","timestamp":"2023-03-03T11:38:43.522Z"}]"""

        val callTypeMsgWithoutQuoteAndBracet =
            """2,37e834e6-0698-46da-b6d0-f2b3b3de11d9,StatusNotification,{"connectorId":1,"errorCode":"NoError","info":"","status":"Available","timestamp":"2023-03-03T11:38:43.522Z"}"""

        // not sure this really happens in real world - the format is invalid, with a single " in the middle of the HB payload
        val callTypeMsgWithCariageReturn =
            "[2,\n\"2840\",\n\"Heartbeat\",\n{\"}\n]"

        var wampMessage = WampMessageParser.parse(callTypeMsg)
        expectThat(wampMessage).isNotNull().and {
            get { msgId }.isEqualTo("37e834e6-0698-46da-b6d0-f2b3b3de11d9")
            get { action }.isEqualTo("StatusNotification")
            get {
                payload
            }.isEqualTo(
                """{"connectorId":1,"errorCode":"NoError","info":"","status":"Available","timestamp":"2023-03-03T11:38:43.522Z"}"""
            )
        }

        wampMessage = WampMessageParser.parse(callTypeMsgWithoutQuote)
        expectThat(wampMessage).isNotNull().and {
            get { msgId }.isEqualTo("37e834e6-0698-46da-b6d0-f2b3b3de11d9")
            get { action }.isEqualTo("StatusNotification")
            get {
                payload
            }.isEqualTo(
                """{"connectorId":1,"errorCode":"NoError","info":"","status":"Available","timestamp":"2023-03-03T11:38:43.522Z"}"""
            )
        }

        wampMessage = WampMessageParser.parse(callTypeMsgWithoutQuoteAndBracet)
        expectThat(wampMessage).isNotNull().and {
            get { msgId }.isEqualTo("37e834e6-0698-46da-b6d0-f2b3b3de11d9")
            get { action }.isEqualTo("StatusNotification")
            get {
                payload
            }.isEqualTo(
                """{"connectorId":1,"errorCode":"NoError","info":"","status":"Available","timestamp":"2023-03-03T11:38:43.522Z"}"""
            )
        }

        wampMessage = WampMessageParser.parse(callTypeMsgWithCariageReturn)
        expectThat(wampMessage).isNotNull().and {
            get { msgId }.isEqualTo("2840")
            get { action }.isEqualTo("Heartbeat")
            get { payload }.isEqualTo("{}")
        }

        // real world messages with carriage returns
        wampMessage = WampMessageParser.parse(
            """
            [2,"391031245","MeterValues",
            {
            "meterValue": [
            {
            "timestamp": "2024-03-04T08:30:03Z",
            "sampledValue": [
            {
            "format": "Raw",
            "location": "Outlet",
            "context": "Sample.Clock",
            "measurand": "Energy.Active.Import.Register",
            "unit": "Wh",
            "value": "285520"
            }
            ]
            }
            ],
            "connectorId": 1
            }]
            """.trimIndent()
        )
        expectThat(wampMessage).isNotNull().and {
            get { msgId }.isEqualTo("391031245")
            get { action }.isEqualTo("MeterValues")
            get {
                payload
            }.isEqualTo(
                """
                {
                "meterValue": [
                {
                "timestamp": "2024-03-04T08:30:03Z",
                "sampledValue": [
                {
                "format": "Raw",
                "location": "Outlet",
                "context": "Sample.Clock",
                "measurand": "Energy.Active.Import.Register",
                "unit": "Wh",
                "value": "285520"
                }
                ]
                }
                ],
                "connectorId": 1
                }
                """.trimIndent()
            )
        }

        wampMessage = WampMessageParser.parse(
            """
            [2,"774911893","Heartbeat",
            {
            }]
            """.trimIndent()
        )
        expectThat(wampMessage).isNotNull().and {
            get { msgId }.isEqualTo("774911893")
            get { action }.isEqualTo("Heartbeat")
            get { payload }.isEqualTo("{\n}")
        }
    }

    @Test
    fun `should parse call result Msg`() {
        val callResultTypeMsg = """[3,"ffd94ceeA8c5bA4b9dA9a1bA21c64276d254",{"status":"Accepted"}]"""
        val callResultTypeMsgWithoutQuote = """[3,ffd94ceeA8c5bA4b9dA9a1bA21c64276d254,{"status":"Accepted"}]"""

        var wampMessage = WampMessageParser.parse(callResultTypeMsg)
        expectThat(wampMessage).isNotNull()
        expectThat(wampMessage!!) {
            get { msgId }.isEqualTo("ffd94ceeA8c5bA4b9dA9a1bA21c64276d254")
            get { msgType }.isEqualTo(WampMessageType.CALL_RESULT)
            get { payload }.isEqualTo("""{"status":"Accepted"}""")
        }

        wampMessage = WampMessageParser.parse(callResultTypeMsgWithoutQuote)
        expectThat(wampMessage).isNotNull()
        expectThat(wampMessage!!) {
            get { msgId }.isEqualTo("ffd94ceeA8c5bA4b9dA9a1bA21c64276d254")
            get { msgType }.isEqualTo(WampMessageType.CALL_RESULT)
            get { payload }.isEqualTo("""{"status":"Accepted"}""")
        }
    }

    @Test
    fun `should parse call error Msg`() {
        val callErrorTypeMsg =
            """[4,"11d95d88-5228-4a95-832a-b9e9ff6841c5","GenericError","Generic description",{"status", "ERROR"}]"""
        val callErrorTypeMsgWithoutQuote =
            """[4,11d95d88-5228-4a95-832a-b9e9ff6841c5,GenericError,Generic description,{"status", "ERROR"}]"""

        var wampMessage = WampMessageParser.parse(callErrorTypeMsg)
        expectThat(wampMessage).isNotNull()
        expectThat(wampMessage!!) {
            get { msgId }.isEqualTo("11d95d88-5228-4a95-832a-b9e9ff6841c5")
            get { msgType }.isEqualTo(WampMessageType.CALL_ERROR)
            get { errorCode }.isEqualTo(MessageErrorCode.GENERIC_ERROR)
            get { errorDescription }.isEqualTo("Generic description")
            get { payload }.isEqualTo("""{"status", "ERROR"}""")
        }

        wampMessage = WampMessageParser.parse(callErrorTypeMsgWithoutQuote)
        expectThat(wampMessage).isNotNull()
        expectThat(wampMessage!!) {
            get { msgId }.isEqualTo("11d95d88-5228-4a95-832a-b9e9ff6841c5")
            get { msgType }.isEqualTo(WampMessageType.CALL_ERROR)
            get { errorCode }.isEqualTo(MessageErrorCode.GENERIC_ERROR)
            get { errorDescription }.isEqualTo("Generic description")
            get { payload }.isEqualTo("""{"status", "ERROR"}""")
        }

        wampMessage = WampMessageParser.parse(
            """[4,"11d95d88-5228-4a95-832a-b9e9ff6841c5","InternalError","a problem, with a comma",{"status", "ERROR"}]"""
        )
        expectThat(wampMessage).isNotNull().and {
            get { msgId }.isEqualTo("11d95d88-5228-4a95-832a-b9e9ff6841c5")
            get { msgType }.isEqualTo(WampMessageType.CALL_ERROR)
            get { errorCode }.isEqualTo(MessageErrorCode.INTERNAL_ERROR)
            get { errorDescription }.isEqualTo("a problem, with a comma")
            get { payload }.isEqualTo("""{"status", "ERROR"}""")
        }
    }

    @Test
    fun `should parse call error without status`() {
        val callErrorTypeMsg =
            """[4,"52370","NotImplemented","",{}]"""

        var wampMessage = WampMessageParser.parse(callErrorTypeMsg)
        expectThat(wampMessage).isNotNull()
        expectThat(wampMessage!!) {
            get { msgId }.isEqualTo("52370")
            get { msgType }.isEqualTo(WampMessageType.CALL_ERROR)
            get { errorCode }.isEqualTo(MessageErrorCode.NOT_IMPLEMENTED)
            get { errorDescription }.isEqualTo("")
            get { payload }.isEqualTo("""{}""")
        }
    }

    @Test
    fun `should parse call error with a brace in the description`() {
        mapOf(
            """[4,"1","InternalError","bad { thing",{}]""" to """bad { thing""",
            """[4,"1","InternalError","{",{}]""" to """{""",
            """[4,"1","InternalError","say \"{",{}]""" to """say "{""",
            """[4,"1","InternalError","C:\\{",{}]""" to """C:\{""",
            """[4,"1","InternalError","bad { thing",{"}]""" to """bad { thing""",
        ).forEach { (message, description) ->
            expectThat(WampMessageParser.parse(message)).isNotNull().and {
                get { errorDescription }.isEqualTo(description)
                get { payload }.isEqualTo("{}")
            }
        }
    }

    @Test
    fun `should remove a single double quote in the payload after a lone backslash`() {
        expectThat(WampMessageParser.parse("""[4,"1","InternalError","C:\",{"}]""")).isNotNull().and {
            get { errorDescription }.isEqualTo("""C:\""")
            get { payload }.isEqualTo("{}")
        }
    }

    @Test
    fun `should parse all without error`() {
        val path = "src/test/resources/listeWampMsgTest.txt"
        val file = File(path)
        file.readLines().forEach {
            val wampMessage = WampMessageParser.parse(it)
            expectThat(wampMessage).isNotNull()
        }
    }

    @Test
    fun `should parse message with line return without removing them`() {
        val message = """
            [2,"380fa189-6db6-45b9-95d8-598d591ecb21","DataTransfer",{"data":"{\"certificate\":\"-----BEGIN CERTIFICATE-----\\nMIIC7zCCApagAwIBAgIIUyQLgoSlqLQwCgYIKoZIzj0EAwIwbjElMCMGA1UEAwwc\\nU3ViQ0EyLUEtZU1TUF9Gb3JfQ0MtR2VuZXJpYzEMMAoGA1UECwwDU1RHMQ8wDQYD\\nVQQKDAZHSVJFVkUxGTAXBgoJkiaJk/IsZAEZFglTdWJDQTItQ0MxCzAJBgNVBAYT\\nAkZSMB4XDTI0MDIyODE0NDI1N1oXDTI2MDIyNzE0NDI1NlowODEXMBUGA1UEAwwO\\nQkVBQkMwMDAwMENDQzExDDAKBgNVBAsMA1NURzEPMA0GA1UECgwGR0lSRVZFMFkw\\nEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEQsNHpYMV+MU5gmv6UbBpVh2Zov2k5UAx\\n+lw0rmbgUrhAXr28jCcP0NswPyA/oL793pPy4AlyjNudPLHVSJK5vKOCAVIwggFO\\nMAwGA1UdEwEB/wQCMAAwHwYDVR0jBBgwFoAUyACMOxfU7JbjRgfP8JPIRDHyIasw\\nUQYIKwYBBQUHAQEERTBDMEEGCCsGAQUFBzABhjVodHRwOi8vb2NzcC50a21naXJ2\\nLmV3MS5haXMtc3RnMDMuYWNsb3VkLmdlbWFsdG8uY29tLzCBmgYDVR0fBIGSMIGP\\nMIGMoIGJoIGGhoGDaHR0cDovL2NybC50a21naXJ2LmV3MS5haXMtc3RnMDMuYWNs\\nb3VkLmdlbWFsdG8uY29tOjgwL2NybC9pc3N1ZXIvQ049U3ViQ0EyLUEtZU1TUF9G\\nb3JfQ0MtR2VuZXJpYyxPVT1TVEcsTz1HSVJFVkUsREM9U3ViQ0EyLUNDLEM9RlIw\\nHQYDVR0OBBYEFAsw6L5q8N7Ef5f5zQmerdOwch5RMA4GA1UdDwEB/wQEAwID6DAK\\nBggqhkjOPQQDAgNHADBEAiAToXdNPxbCSMwze+2aJUptIneJvqxIrenYcWpXhKY7\\ndQIgUG5WyVVI8LcMVR+dh37HWDGI9kzcHdwjkHSOZvt0TJA=\\n-----END CERTIFICATE-----\\n-----BEGIN CERTIFICATE-----\\nMIICxTCCAmqgAwIBAgIIIOJZj4RouCkwCgYIKoZIzj0EAwIwZjEdMBsGA1UEAwwU\\nU3ViQ0ExLUEtZU1TUF9Gb3JfQ0MxDDAKBgNVBAsMA1NURzEPMA0GA1UECgwGR0lS\\nRVZFMRkwFwYKCZImiZPyLGQBGRYJU3ViQ0ExLUNDMQswCQYDVQQGEwJGUjAeFw0y\\nMzA2MTUxMTU1NTBaFw0zMzA2MTIxMTU1NDlaMG4xJTAjBgNVBAMMHFN1YkNBMi1B\\nLWVNU1BfRm9yX0NDLUdlbmVyaWMxDDAKBgNVBAsMA1NURzEPMA0GA1UECgwGR0lS\\nRVZFMRkwFwYKCZImiZPyLGQBGRYJU3ViQ0EyLUNDMQswCQYDVQQGEwJGUjBZMBMG\\nByqGSM49AgEGCCqGSM49AwEHA0IABAfX13fmGCQ+TpaweOkDbet99F7wIopNu9uH\\nKK2LK7aYHC3pNLrVUa592tQ1uFNlHC672au3xWxaPTrueJhNuM6jgfkwgfYwEgYD\\nVR0TAQH/BAgwBgEB/wIBADAfBgNVHSMEGDAWgBRhDL/25BwXsZ27LijaEpOsYz19\\n6jCBjwYDVR0fBIGHMIGEMIGBoH+gfYZ7aHR0cDovL2NybC50a21naXJ2LmV3MS5h\\naXMtc3RnMDMuYWNsb3VkLmdlbWFsdG8uY29tOjgwL2NybC9pc3N1ZXIvQ049U3Vi\\nQ0ExLUEtZU1TUF9Gb3JfQ0MsT1U9U1RHLE89R0lSRVZFLERDPVN1YkNBMS1DQyxD\\nPUZSMB0GA1UdDgQWBBTIAIw7F9TsluNGB8/wk8hEMfIhqzAOBgNVHQ8BAf8EBAMC\\nAcYwCgYIKoZIzj0EAwIDSQAwRgIhAO6KCchNhiflsrW2JAL1lXNj9bU9pVD1Wkn0\\n2jBcONnZAiEAuIL9ATGShvBjkhClQVhraOGaIJa9D6gtVYIAb1zQLI4=\\n-----END CERTIFICATE-----\\n-----BEGIN CERTIFICATE-----\\nMIICGDCCAb+gAwIBAgIIRV513sWcKIswCgYIKoZIzj0EAwIwVzEUMBIGA1UEAwwL\\nVjJHUm9vdENBLUExDDAKBgNVBAsMA1NURzEPMA0GA1UECgwGR0lSRVZFMRMwEQYK\\nCZImiZPyLGQBGRYDVjJHMQswCQYDVQQGEwJGUjAeFw0yMzA2MTUxMTU0NTlaFw00\\nMzA2MTAxMTU0NThaMGYxHTAbBgNVBAMMFFN1YkNBMS1BLWVNU1BfRm9yX0NDMQww\\nCgYDVQQLDANTVEcxDzANBgNVBAoMBkdJUkVWRTEZMBcGCgmSJomT8ixkARkWCVN1\\nYkNBMS1DQzELMAkGA1UEBhMCRlIwWTATBgcqhkjOPQIBBggqhkjOPQMBBwNCAAT7\\nHN8Cp+NvVfApdwA+zUMSg9ntdHq/byoS1jmH9ao9ooPoLnj8KrVOK4hdoypUhJMz\\nLY0kn6a+V3vvll3wXqfUo2YwZDASBgNVHRMBAf8ECDAGAQH/AgEBMB8GA1UdIwQY\\nMBaAFApEo3a+pMNKnAyd3qiSiw6+HLqGMB0GA1UdDgQWBBRhDL/25BwXsZ27Lija\\nEpOsYz196jAOBgNVHQ8BAf8EBAMCAQYwCgYIKoZIzj0EAwIDRwAwRAIgB/P5S1oi\\n8c7aKLQrmdCrru9SdAq31do2LxaTLUDiSn4CIA+F/GuiHSBpQorLcuLWCA+kLcmQ\\ntl5ha4yFKqOaN62a\\n-----END CERTIFICATE-----\\n\",\"idToken\":\"BEABC00000CCC1\",\"iso15118CertificateHashData\":[{\"hashAlgorithm\":\"SHA256\",\"issuerKeyHash\":\"8b5ad29f85a30a5367c651eb3af37505748f238765526fe1b34600bf07952bd7\",\"issuerNameHash\":\"975b9b968234125eb87cd6f0e7fafa45df628f5265cf164bae0c67e15b8a4d8d\",\"responderURL\":\"http://ocsp.tkmgirv.ew1.ais-stg03.acloud.gemalto.com/\",\"serialNumber\":\"53240b8284a5a8b4\"}]}","messageId":"Authorize","vendorId":"org.openchargealliance.iso15118pnc"}]
        """.trimIndent()

        val wampMessage = WampMessage.parse(message)

        expectThat(wampMessage)
            .isNotNull()
            .and {
                get { msgType }.isEqualTo(WampMessageType.CALL)
                get { msgId }.isEqualTo("380fa189-6db6-45b9-95d8-598d591ecb21")
                get { action }.isEqualTo("DataTransfer")
                get { errorCode }.isNull()
                get { errorDescription }.isNull()
                get { payload }.isEqualTo(
                    """{"data":"{\"certificate\":\"-----BEGIN CERTIFICATE-----\\nMIIC7zCCApagAwIBAgIIUyQLgoSlqLQwCgYIKoZIzj0EAwIwbjElMCMGA1UEAwwc\\nU3ViQ0EyLUEtZU1TUF9Gb3JfQ0MtR2VuZXJpYzEMMAoGA1UECwwDU1RHMQ8wDQYD\\nVQQKDAZHSVJFVkUxGTAXBgoJkiaJk/IsZAEZFglTdWJDQTItQ0MxCzAJBgNVBAYT\\nAkZSMB4XDTI0MDIyODE0NDI1N1oXDTI2MDIyNzE0NDI1NlowODEXMBUGA1UEAwwO\\nQkVBQkMwMDAwMENDQzExDDAKBgNVBAsMA1NURzEPMA0GA1UECgwGR0lSRVZFMFkw\\nEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEQsNHpYMV+MU5gmv6UbBpVh2Zov2k5UAx\\n+lw0rmbgUrhAXr28jCcP0NswPyA/oL793pPy4AlyjNudPLHVSJK5vKOCAVIwggFO\\nMAwGA1UdEwEB/wQCMAAwHwYDVR0jBBgwFoAUyACMOxfU7JbjRgfP8JPIRDHyIasw\\nUQYIKwYBBQUHAQEERTBDMEEGCCsGAQUFBzABhjVodHRwOi8vb2NzcC50a21naXJ2\\nLmV3MS5haXMtc3RnMDMuYWNsb3VkLmdlbWFsdG8uY29tLzCBmgYDVR0fBIGSMIGP\\nMIGMoIGJoIGGhoGDaHR0cDovL2NybC50a21naXJ2LmV3MS5haXMtc3RnMDMuYWNs\\nb3VkLmdlbWFsdG8uY29tOjgwL2NybC9pc3N1ZXIvQ049U3ViQ0EyLUEtZU1TUF9G\\nb3JfQ0MtR2VuZXJpYyxPVT1TVEcsTz1HSVJFVkUsREM9U3ViQ0EyLUNDLEM9RlIw\\nHQYDVR0OBBYEFAsw6L5q8N7Ef5f5zQmerdOwch5RMA4GA1UdDwEB/wQEAwID6DAK\\nBggqhkjOPQQDAgNHADBEAiAToXdNPxbCSMwze+2aJUptIneJvqxIrenYcWpXhKY7\\ndQIgUG5WyVVI8LcMVR+dh37HWDGI9kzcHdwjkHSOZvt0TJA=\\n-----END CERTIFICATE-----\\n-----BEGIN CERTIFICATE-----\\nMIICxTCCAmqgAwIBAgIIIOJZj4RouCkwCgYIKoZIzj0EAwIwZjEdMBsGA1UEAwwU\\nU3ViQ0ExLUEtZU1TUF9Gb3JfQ0MxDDAKBgNVBAsMA1NURzEPMA0GA1UECgwGR0lS\\nRVZFMRkwFwYKCZImiZPyLGQBGRYJU3ViQ0ExLUNDMQswCQYDVQQGEwJGUjAeFw0y\\nMzA2MTUxMTU1NTBaFw0zMzA2MTIxMTU1NDlaMG4xJTAjBgNVBAMMHFN1YkNBMi1B\\nLWVNU1BfRm9yX0NDLUdlbmVyaWMxDDAKBgNVBAsMA1NURzEPMA0GA1UECgwGR0lS\\nRVZFMRkwFwYKCZImiZPyLGQBGRYJU3ViQ0EyLUNDMQswCQYDVQQGEwJGUjBZMBMG\\nByqGSM49AgEGCCqGSM49AwEHA0IABAfX13fmGCQ+TpaweOkDbet99F7wIopNu9uH\\nKK2LK7aYHC3pNLrVUa592tQ1uFNlHC672au3xWxaPTrueJhNuM6jgfkwgfYwEgYD\\nVR0TAQH/BAgwBgEB/wIBADAfBgNVHSMEGDAWgBRhDL/25BwXsZ27LijaEpOsYz19\\n6jCBjwYDVR0fBIGHMIGEMIGBoH+gfYZ7aHR0cDovL2NybC50a21naXJ2LmV3MS5h\\naXMtc3RnMDMuYWNsb3VkLmdlbWFsdG8uY29tOjgwL2NybC9pc3N1ZXIvQ049U3Vi\\nQ0ExLUEtZU1TUF9Gb3JfQ0MsT1U9U1RHLE89R0lSRVZFLERDPVN1YkNBMS1DQyxD\\nPUZSMB0GA1UdDgQWBBTIAIw7F9TsluNGB8/wk8hEMfIhqzAOBgNVHQ8BAf8EBAMC\\nAcYwCgYIKoZIzj0EAwIDSQAwRgIhAO6KCchNhiflsrW2JAL1lXNj9bU9pVD1Wkn0\\n2jBcONnZAiEAuIL9ATGShvBjkhClQVhraOGaIJa9D6gtVYIAb1zQLI4=\\n-----END CERTIFICATE-----\\n-----BEGIN CERTIFICATE-----\\nMIICGDCCAb+gAwIBAgIIRV513sWcKIswCgYIKoZIzj0EAwIwVzEUMBIGA1UEAwwL\\nVjJHUm9vdENBLUExDDAKBgNVBAsMA1NURzEPMA0GA1UECgwGR0lSRVZFMRMwEQYK\\nCZImiZPyLGQBGRYDVjJHMQswCQYDVQQGEwJGUjAeFw0yMzA2MTUxMTU0NTlaFw00\\nMzA2MTAxMTU0NThaMGYxHTAbBgNVBAMMFFN1YkNBMS1BLWVNU1BfRm9yX0NDMQww\\nCgYDVQQLDANTVEcxDzANBgNVBAoMBkdJUkVWRTEZMBcGCgmSJomT8ixkARkWCVN1\\nYkNBMS1DQzELMAkGA1UEBhMCRlIwWTATBgcqhkjOPQIBBggqhkjOPQMBBwNCAAT7\\nHN8Cp+NvVfApdwA+zUMSg9ntdHq/byoS1jmH9ao9ooPoLnj8KrVOK4hdoypUhJMz\\nLY0kn6a+V3vvll3wXqfUo2YwZDASBgNVHRMBAf8ECDAGAQH/AgEBMB8GA1UdIwQY\\nMBaAFApEo3a+pMNKnAyd3qiSiw6+HLqGMB0GA1UdDgQWBBRhDL/25BwXsZ27Lija\\nEpOsYz196jAOBgNVHQ8BAf8EBAMCAQYwCgYIKoZIzj0EAwIDRwAwRAIgB/P5S1oi\\n8c7aKLQrmdCrru9SdAq31do2LxaTLUDiSn4CIA+F/GuiHSBpQorLcuLWCA+kLcmQ\\ntl5ha4yFKqOaN62a\\n-----END CERTIFICATE-----\\n\",\"idToken\":\"BEABC00000CCC1\",\"iso15118CertificateHashData\":[{\"hashAlgorithm\":\"SHA256\",\"issuerKeyHash\":\"8b5ad29f85a30a5367c651eb3af37505748f238765526fe1b34600bf07952bd7\",\"issuerNameHash\":\"975b9b968234125eb87cd6f0e7fafa45df628f5265cf164bae0c67e15b8a4d8d\",\"responderURL\":\"http://ocsp.tkmgirv.ew1.ais-stg03.acloud.gemalto.com/\",\"serialNumber\":\"53240b8284a5a8b4\"}]}","messageId":"Authorize","vendorId":"org.openchargealliance.iso15118pnc"}"""
                )
            }
        println(wampMessage)
    }
}
