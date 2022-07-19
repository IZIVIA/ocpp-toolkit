plugins {
    kotlin("jvm")
    `maven-publish`
}

coreProject()

dependencies {
    api(project(":ocpp-1-5-core"))
    api(project(":ocpp-json"))

    implementation("com.fasterxml.jackson.core:jackson-databind:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
    implementation("com.networknt:json-schema-validator:_")

    testImplementation(kotlin("test-junit"))
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}
