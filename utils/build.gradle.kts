plugins {
    kotlin("jvm")
    java
}

coreProject()

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.slf4j:slf4j-simple:1.7.36")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("com.networknt:json-schema-validator:1.0.66")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
}