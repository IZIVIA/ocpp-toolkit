package com.izivia.ocpp.json15

import com.izivia.ocpp.core15.model.common.enumeration.Actions
import com.izivia.ocpp.utils.MessageErrorCode
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import strikt.api.expectThat
import strikt.assertions.isNotEqualTo

/**
 * Pins the parser's schema folder to the shipped resources: every action must find its request and
 * response schema. A missing schema surfaces as an INTERNAL_ERROR, a mismatching payload as a
 * PROTOCOL_ERROR, so an empty payload is enough to tell the two apart.
 */
class SchemaResolutionTest {

    private val parser = Ocpp15JsonParser()

    @ParameterizedTest
    @EnumSource(Actions::class)
    fun `resolves the request schema`(action: Actions) {
        expectThat(parser.parseAnyFromString("""[2,"messageId","${action.value}",{}]"""))
            .get { errorCode }.isNotEqualTo(MessageErrorCode.INTERNAL_ERROR)
    }

    @ParameterizedTest
    @EnumSource(Actions::class)
    fun `resolves the response schema`(action: Actions) {
        expectThat(parser.parseAnyFromString("""[3,"messageId",{}]""", action.classResponse))
            .get { errorCode }.isNotEqualTo(MessageErrorCode.INTERNAL_ERROR)
    }
}
