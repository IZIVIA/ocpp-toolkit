plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation(project(":ocpp-1-6-websocket"))
}
