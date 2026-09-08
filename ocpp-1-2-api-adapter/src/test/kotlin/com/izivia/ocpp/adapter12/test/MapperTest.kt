package com.izivia.ocpp.adapter12.test

import com.izivia.ocpp.adapter12.Ocpp12Adapter
import com.izivia.ocpp.adapter12.impl.RealTransactionRepository
import com.izivia.ocpp.adapter12.mapper.BootNotificationMapper
import com.izivia.ocpp.adapter12.mapper.CommonMapper
import com.izivia.ocpp.adapter12.mapper.MeterValuesMapper
import com.izivia.ocpp.adapter12.mapper.RemoteStartTransactionMapper
import com.izivia.ocpp.adapter12.mapper.StatusNotificationMapper
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.common.SampledValueType
import com.izivia.ocpp.api.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.api.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.api.model.datatransfer.DataTransferReq
import com.izivia.ocpp.api.model.metervalues.MeterValuesReq
import com.izivia.ocpp.api.model.statusnotification.enumeration.ChargePointErrorCode
import com.izivia.ocpp.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import com.izivia.ocpp.api.model.bootnotification.enumeration.RegistrationStatusEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.ChargingStateEnumType
import com.izivia.ocpp.api.model.transactionevent.enumeration.TransactionEventEnumType
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp as BootNotificationRespCore
import com.izivia.ocpp.core12.model.bootnotification.enumeration.RegistrationStatus as RegistrationStatusCore
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core12.model.statusnotification.enumeration.ChargePointStatus
import com.izivia.ocpp.core12.model.statusnotification.enumeration.ChargePointErrorCode as ChargePointErrorCodeCore
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.transport.ClientTransport
import io.mockk.mockk
import kotlin.time.Instant
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNull

class MapperTest {
    @Test
    fun `meter values are reduced to OCPP 1_2 integer values`() {
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
            get { values!![0].timestamp }.isEqualTo(timestamp)
            get { values!![0].value }.isEqualTo(10)
        }
    }

    @Test
    fun `remote start does not set evse id for OCPP 1_2`() {
        val mapper = Mappers.getMapper(RemoteStartTransactionMapper::class.java)

        val generic = mapper.coreToGenReq(RemoteStartTransactionReq(idTag = "ABC123"), 99)

        expectThat(generic) {
            get { remoteStartId }.isEqualTo(99)
            get { evseId }.isNull()
            get { idToken.idToken }.isEqualTo("ABC123")
        }
    }

    @Test
    fun `ongoing transaction charging states map to OCPP 1_2 occupied status`() {
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
    fun `a boot notification rejected without a current time stays usable`() {
        val mapper = Mappers.getMapper(BootNotificationMapper::class.java)

        // OCPP 1.2 declares currentTime and heartbeatInterval with minOccurs="0": a bare Rejected
        // response is legal, and is the common shape of a refusal.
        val response = mapper.coreToGenResp(BootNotificationRespCore(status = RegistrationStatusCore.Rejected))

        expectThat(response) {
            get { status }.isEqualTo(RegistrationStatusEnumType.Rejected)
            get { interval }.isEqualTo(0)
        }
    }

    @Test
    fun `an accepted boot notification keeps the values sent by the central system`() {
        val mapper = Mappers.getMapper(BootNotificationMapper::class.java)
        val currentTime = Instant.parse("2026-01-01T00:00:00Z")

        val response = mapper.coreToGenResp(
            BootNotificationRespCore(currentTime, 300, RegistrationStatusCore.Accepted)
        )

        expectThat(response) {
            get { status }.isEqualTo(RegistrationStatusEnumType.Accepted)
            get { this.currentTime }.isEqualTo(currentTime)
            get { interval }.isEqualTo(300)
        }
    }

    @Test
    fun `a transaction ended with the EV still connected keeps the connector occupied`() {
        val mapper = Mappers.getMapper(StatusNotificationMapper::class.java)

        // OCPP 1.2 has no Finishing: the cable is still plugged in, so the connector is not free.
        val status = mapper.convertChargingState(
            mapper.createChargingStateWrapper(ChargingStateEnumType.EVConnected, TransactionEventEnumType.Ended)
        )

        expectThat(status).isEqualTo(ChargePointStatus.Occupied)
    }

    @Test
    fun `singleEnergyRegister selects the EnergyActiveImportRegister reading with the same rule on both paths`() {
        val energy = SampledValueType(10.0, measurand = MeasurandEnumType.EnergyActiveImportRegister)
        val voltage = SampledValueType(230.0, measurand = MeasurandEnumType.Voltage)

        // picks the energy register among other measurands, context ignored by default
        expectThat(CommonMapper.singleEnergyRegister(listOf(voltage, energy))).isEqualTo(10)
        // none present -> null
        expectThat(CommonMapper.singleEnergyRegister(listOf(voltage))).isNull()

        val begin = SampledValueType(1.0, ReadingContextEnumType.TransactionBegin, MeasurandEnumType.EnergyActiveImportRegister)
        val end = SampledValueType(2.0, ReadingContextEnumType.TransactionEnd, MeasurandEnumType.EnergyActiveImportRegister)
        // a context restricts the selection (Start/Stop path)
        expectThat(CommonMapper.singleEnergyRegister(listOf(begin, end), ReadingContextEnumType.TransactionEnd)).isEqualTo(2)
        // ambiguous (>1 match, no context) is rejected
        assertThrows(IllegalArgumentException::class.java) {
            CommonMapper.singleEnergyRegister(listOf(begin, end))
        }
    }

    @Test
    fun `error codes without OCPP 1_2 equivalent fall back to Mode3Error`() {
        val mapper = Mappers.getMapper(StatusNotificationMapper::class.java)

        listOf(
            ChargePointErrorCode.GroundFailure,
            ChargePointErrorCode.InternalError,
            ChargePointErrorCode.OtherError,
            ChargePointErrorCode.OverVoltage
        ).forEach { code ->
            expectThat(mapper.convertErrorCode(code)).isEqualTo(ChargePointErrorCodeCore.Mode3Error)
        }
        // A code that exists in 1.2 is preserved.
        expectThat(mapper.convertErrorCode(ChargePointErrorCode.PowerMeterFailure))
            .isEqualTo(ChargePointErrorCodeCore.PowerMeterFailure)
    }

    @Test
    fun `reserved connector status maps to unavailable in OCPP 1_2`() {
        val mapper = Mappers.getMapper(StatusNotificationMapper::class.java)

        expectThat(mapper.convertConnectorStatus(ConnectorStatusEnumType.Reserved))
            .isEqualTo(ChargePointStatus.Unavailable)
    }

    @Test
    fun `data transfer is explicitly unsupported in OCPP 1_2`() {
        val adapter = Ocpp12Adapter(
            chargingStationId = "CP001",
            transport = mockk<ClientTransport>(relaxed = true),
            csApi = mockk<CSApi>(relaxed = true),
            transactionIds = RealTransactionRepository()
        )

        assertThrows(IllegalStateException::class.java) {
            adapter.dataTransfer(RequestMetadata("CP001"), DataTransferReq(vendorId = "vendor"))
        }
    }
}
