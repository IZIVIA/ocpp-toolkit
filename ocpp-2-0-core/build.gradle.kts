plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:_")
    implementation(project(":utils"))
    implementation(project(":ocpp-transport"))
    implementation(project(":csms-api"))
}
