package com.izivia.ocpp.soap

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.DatabindContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase

class BaseResolver: TypeIdResolverBase() {
    override fun init(baseType: JavaType?) {
    }

    override fun idFromValue(value: Any?): String {
        var name : String = "o:" + value!!::class.simpleName!!.replaceFirstChar { it.lowercase() }
        if ( name.endsWith("Resp") ) {
            name += "onse"
        } else if (name.endsWith("Req") ) {
            name += "uest"
        }
        return name
    }

    override fun idFromValueAndType(value: Any?, suggestedType: Class<*>?): String {
        return idFromValue(value = value)
    }

    override open fun typeFromId(context: DatabindContext?, id: String?): JavaType? {
        TODO("Get context " + context + " id " + id)
    }
    override fun getMechanism(): JsonTypeInfo.Id {
        TODO("Not yet implemented")
    }
}
