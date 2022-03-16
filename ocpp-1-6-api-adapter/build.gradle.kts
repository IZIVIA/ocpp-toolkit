plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
}

coreProject()

dependencies {
    implementation(project(":csms-api"))
    implementation(project(":ocpp-1-6-core"))
    implementation(project(":ocpp-transport"))
    implementation(project(":operation-information"))
    implementation("org.mapstruct:mapstruct:_")
    kapt("org.mapstruct:mapstruct-processor:_")
    runtimeOnly("ch.qos.logback:logback-classic:_")
}
