plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:_")
    implementation("org.apache.commons:commons-lang3:_")
    implementation("com.networknt:json-schema-validator:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
}