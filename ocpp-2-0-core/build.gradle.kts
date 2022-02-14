plugins {
    kotlin("jvm")
    java
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation(project(":ocpp-2-0-websocket"))
}
