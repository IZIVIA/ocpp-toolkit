plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation(project(":operation-information"))
}
