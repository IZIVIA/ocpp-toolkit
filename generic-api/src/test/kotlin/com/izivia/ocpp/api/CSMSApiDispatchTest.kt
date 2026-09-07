package com.izivia.ocpp.api

import com.izivia.ocpp.api.impl.DefaultCSMSApi
import com.izivia.ocpp.api.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.api.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.api.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatusEnumType
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo

class CSMSApiDispatchTest {

    private val meta = RequestMetadata("CP001")

    /**
     * [send] dispatches on the request type with a catch-all `else`, so a missing branch does not fail
     * the build: it degrades to an opaque IllegalStateException at runtime. Hence an explicit test.
     */
    @Test
    fun `send routes a diagnostics status notification to its own operation`() {
        val csmsApi = mockk<CSMSApi>(relaxed = true)
        val request = DiagnosticsStatusNotificationReq(DiagnosticsStatusEnumType.Uploaded)

        csmsApi.send(meta, request)

        verify(exactly = 1) { csmsApi.diagnosticsStatusNotification(meta, request) }
        verify(exactly = 0) { csmsApi.logStatusNotification(any(), any()) }
    }

    @Test
    fun `send keeps routing the other operations`() {
        val csmsApi = mockk<CSMSApi>(relaxed = true)
        val request = HeartbeatReq()

        csmsApi.send(meta, request)

        verify(exactly = 1) { csmsApi.heartbeat(meta, request) }
        verify(exactly = 0) { csmsApi.diagnosticsStatusNotification(any(), any()) }
    }

    @Test
    fun `the default implementation delegates a diagnostics status notification to its callback`() {
        val callbacks = mockk<CSMSApiCallbacks>()
        val expected = DiagnosticsStatusNotificationResp()
        every { callbacks.diagnosticsStatusNotification(any()) } returns expected

        val request = DiagnosticsStatusNotificationReq(DiagnosticsStatusEnumType.UploadFailed)
        val execution = DefaultCSMSApi(callbacks).diagnosticsStatusNotification(meta, request)

        expectThat(execution) {
            get { this.request }.isEqualTo(request)
            get { this.response }.isEqualTo(expected)
            get { this.executionMeta.status }.isEqualTo(RequestStatus.SUCCESS)
        }
        verify(exactly = 1) { callbacks.diagnosticsStatusNotification(request) }
    }

    @Test
    fun `the diagnostics status notification callback is optional`() {
        val csmsApi = DefaultCSMSApi(object : CSMSApiCallbacks {})

        expectThat(
            runCatching {
                csmsApi.diagnosticsStatusNotification(
                    meta,
                    DiagnosticsStatusNotificationReq(DiagnosticsStatusEnumType.Idle)
                )
            }.exceptionOrNull()
        ).isA<NotImplementedError>()
    }
}
