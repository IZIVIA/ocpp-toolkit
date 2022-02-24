plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
}

coreProject()

dependencies {
    implementation(project(":csms-api"))
    implementation(project(":ocpp-2-0-core"))
    implementation(project(":ocpp-transport"))
    implementation("org.mapstruct:mapstruct:_")
    kapt("org.mapstruct:mapstruct-processor:_")
}
