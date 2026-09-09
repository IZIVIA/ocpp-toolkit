package com.izivia.ocpp.adapter15.test

import com.izivia.ocpp.adapter15.mapper.MeterValuesMapper
import com.izivia.ocpp.adapter15.mapper.RemoteStartTransactionMapper
import com.izivia.ocpp.adapter15.mapper.StatusNotificationMapper
import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.common.SampledValueType
import com.izivia.ocpp.api.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.api.model.metervalues.MeterValuesReq
import com.izivia.ocpp.api.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.api.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.ChargingStateEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TransactionEventEnumType
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core15.model.statusnotification.enumeration.ChargePointErrorCode as ChargePointErrorCodeCore
import kotlin.time.Instant
import com.izivia.ocpp.adapter15.mapper.ResetMapper
import com.izivia.ocpp.api.model.reset.enumeration.ResetStatusEnumType
import com.izivia.ocpp.core15.model.reset.enumeration.ResetStatus
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class MapperTest {
    @Test
    fun `meter values are mapped to OCPP 1_5 sampled values`() {
        val mapper = Mappers.getMapper(MeterValuesMapper::class.java)
        val timestamp = Instant.parse("2026-01-01T00:00:00Z")

        val request = MeterValuesReq(
            evseId = 1,
            connectorId = 2,
            transactionId = "42",
            meterValue = listOf(
                MeterValueType(
                    timestamp = timestamp,
                    sampledValue = listOf(
                        SampledValueType(10.0, measurand = MeasurandEnumType.EnergyActiveImportRegister)
                    )
                )
            )
        )

        val core = mapper.genToCoreReq(request)

        expectThat(core) {
            get { connectorId }.isEqualTo(2)
            get { transactionId }.isEqualTo(42)
            get { values!![0].timestamp }.isEqualTo(timestamp)
            get { values!![0].value[0].value }.isEqualTo("10.0")
        }
    }

    @Test
    fun `occupied status is mapped to OCPP 1_5 occupied status`() {
        val mapper = Mappers.getMapper(StatusNotificationMapper::class.java)
        val request = StatusNotificationReq(
            connectorId = 1,
            evseId = 1,
            connectorStatus = ConnectorStatusEnumType.Occupied,
            errorCode = ChargePointErrorCode.NoError,
            timestamp = Instant.parse("2026-01-01T00:00:00Z")
        )

        val core = mapper.genToCoreReq(request)

        expectThat(core.status).isEqualTo(ChargePointStatus.Occupied)
    }

    @Test
    fun `ongoing transaction charging states map to OCPP 1_5 occupied status`() {
        val mapper = Mappers.getMapper(StatusNotificationMapper::class.java)

        listOf(
            ChargingStateEnumType.Charging,
            ChargingStateEnumType.SuspendedEV,
            ChargingStateEnumType.SuspendedEVSE
        ).forEach { state ->
            val status = mapper.convertChargingState(mapper.createChargingStateWrapper(state, null))
            expectThat(status).isEqualTo(ChargePointStatus.Occupied)
        }
    }

    @Test
    fun `error codes without OCPP 1_5 equivalent fall back to OtherError`() {
        val mapper = Mappers.getMapper(StatusNotificationMapper::class.java)

        listOf(
            ChargePointErrorCode.EVCommunicationError,
            ChargePointErrorCode.InternalError,
            ChargePointErrorCode.LocalListConflict,
            ChargePointErrorCode.OverVoltage
        ).forEach { code ->
            expectThat(mapper.convertErrorCode(code)).isEqualTo(ChargePointErrorCodeCore.OtherError)
        }
        // A code that exists in 1.5 is preserved.
        expectThat(mapper.convertErrorCode(ChargePointErrorCode.GroundFailure))
            .isEqualTo(ChargePointErrorCodeCore.GroundFailure)
    }

    @Test
    fun `a transaction ended with the EV still connected keeps the connector occupied`() {
        val mapper = Mappers.getMapper(StatusNotificationMapper::class.java)

        // OCPP 1.5 has no Finishing: the cable is still plugged in, so the connector is not free.
        val status = mapper.convertChargingState(
            mapper.createChargingStateWrapper(ChargingStateEnumType.EVConnected, TransactionEventEnumType.Ended)
        )

        expectThat(status).isEqualTo(ChargePointStatus.Occupied)
    }

    @Test
    fun `every generic reset status maps to an OCPP 1_5 status`() {
        val mapper = Mappers.getMapper(ResetMapper::class.java)

        // Scheduled has no OCPP 1.5 equivalent: the reset is acknowledged, so Accepted is the honest
        // downgrade. Pinned here because nothing else exercises it.
        mapOf(
            ResetStatusEnumType.Accepted to ResetStatus.Accepted,
            ResetStatusEnumType.Rejected to ResetStatus.Rejected,
            ResetStatusEnumType.Scheduled to ResetStatus.Accepted
        ).forEach { (generic, expected) ->
            expectThat(mapper.convertResetStatus(generic)).isEqualTo(expected)
        }
    }

    @Test
    fun `remote start keeps connector id for OCPP 1_5`() {
        val mapper = Mappers.getMapper(RemoteStartTransactionMapper::class.java)

        val generic = mapper.coreToGenReq(RemoteStartTransactionReq(connectorId = 3, idTag = "ABC123"), 99)

        expectThat(generic) {
            get { remoteStartId }.isEqualTo(99)
            get { evseId }.isEqualTo(3)
            get { idToken.idToken }.isEqualTo("ABC123")
        }
    }
}
