plugins {
    kotlin("jvm")
}

coreProject()

dependencies {
    implementation("org.http4k:http4k-core:_")
    implementation("org.http4k:http4k-client-websocket:_")
    implementation("org.http4k:http4k-server-undertow:_")
    implementation("org.http4k:http4k-client-apache:_")

    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:_")

    runtimeOnly("ch.qos.logback:logback-classic:_")
}
