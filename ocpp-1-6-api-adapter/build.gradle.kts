plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation(project(":generic-api"))
    implementation(project(":ocpp-1-6-core"))
    implementation(project(":ocpp-transport"))
    testImplementation(project(":ocpp-websocket"))
    testImplementation(project(":ocpp-wamp"))
}
