plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation(project(":csms-api"))
    implementation(project(":ocpp-2-0-core"))
    implementation(project(":ocpp-transport"))
}
