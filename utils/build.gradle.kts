plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:_")
    implementation("org.apache.commons:commons-lang3:_")
    implementation("com.networknt:json-schema-validator:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-utils"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP Utils")
                artifactId = "ocpp-utils"
                description.set("OCPP Utils")
            }
        }
    }
}
