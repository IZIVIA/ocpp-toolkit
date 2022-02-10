plugins {
    kotlin("jvm")
}

dependencies {
    implementation("org.http4k:http4k-bom:_")
    implementation("org.http4k:http4k-core:_")
    implementation("org.http4k:http4k-client-websocket:_")
    implementation("org.http4k:http4k-server-undertow:_")
    implementation("org.http4k:http4k-client-apache:_")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:_")

    implementation("org.slf4j:slf4j-api:1.7.36")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.10")
}
