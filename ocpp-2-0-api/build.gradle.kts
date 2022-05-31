plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
}

coreProject()

dependencies {
    implementation(project(":ocpp-2-0-core"))
    implementation(project(":operation-information"))
}
