plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation("org.http4k:http4k-core:_")
    implementation("org.http4k:http4k-client-websocket:_")
    implementation("org.http4k:http4k-server-undertow:_")
    implementation("org.http4k:http4k-client-apache:_")

    implementation(project(":csms-api"))
    implementation(project(":ocpp-wamp"))
    implementation(project(":ocpp-websocket"))
    implementation(project(":ocpp-1-6-api-adapter"))
    implementation(project(":ocpp-1-6-core"))
    implementation(project(":ocpp-1-6-soap"))
    implementation(project(":ocpp-2-0-api-adapter"))
    implementation(project(":ocpp-2-0-core"))
    implementation(project(":ocpp-transport"))
}