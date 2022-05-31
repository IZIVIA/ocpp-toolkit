plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
}

coreProject()

dependencies {
    implementation(project(":ocpp-1-6-core"))
    implementation(project(":operation-information"))

}
