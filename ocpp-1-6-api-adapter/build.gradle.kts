plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation(project(":csms-api"))
    implementation(project(":ocpp-1-6-core"))
    implementation(project(":ocpp-transport"))
    testImplementation(project(":ocpp-websocket"))
    testImplementation(project(":ocpp-wamp"))
}
