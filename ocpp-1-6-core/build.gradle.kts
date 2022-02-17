plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    implementation(project(":utils"))
    implementation(project(":ocpp-transport"))
    testImplementation(project(":ocpp-websocket"))
    testImplementation(project(":ocpp-wamp"))
}
