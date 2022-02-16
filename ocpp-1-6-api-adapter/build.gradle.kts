plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation(project(":generic-api"))
    implementation(project(":ocpp-1-6-core"))
    testImplementation(project(":ocpp-1-6-websocket"))
}
