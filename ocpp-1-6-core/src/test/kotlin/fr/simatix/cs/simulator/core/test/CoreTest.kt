package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.core.ChargePointOperations
import org.junit.jupiter.api.Test

class CoreTest {
    @Test
    fun `heartbeat request`() {
        val operations = ChargePointOperations.newChargePointOperations(
            "chargePoint2"
        )
        operations.heartbeat()
    }
}