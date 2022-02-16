plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    testImplementation(project(":ocpp-1-6-websocket"))
}
