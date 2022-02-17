plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation(project(":generic-api"))
    implementation(project(":ocpp-2-0-core"))
}
