plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":operation-information"))
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-generic-api"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP Generic API")
                artifactId = "ocpp-generic-api"
                description.set("OCPP Generic API")
            }
        }
    }
}
