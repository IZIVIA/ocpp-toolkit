package fr.simatix.cs.simulator.websocket

import java.util.*

class Target {
    companion object {
        private fun <T> getProp(key: String): T {
            val props = javaClass.classLoader.getResourceAsStream("application.properties").use {
                Properties().apply { load(it) }
            }
            return (props.getProperty(key) as T) ?: throw RuntimeException("could not find property $key")
        }

        val url = getProp<String>("ws.url")
        val port = getProp<String>("ws.port")
        val path = getProp<String>("ws.path")
        val target = "ws://${url}:${port}/${path}"
    }
}