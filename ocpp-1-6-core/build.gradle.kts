plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation(project(":ocpp-1-6-websocket"))
}
