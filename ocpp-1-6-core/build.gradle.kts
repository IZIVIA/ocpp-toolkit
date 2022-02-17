plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    implementation(project(":utils"))
    testImplementation(project(":ocpp-1-6-websocket"))
}
