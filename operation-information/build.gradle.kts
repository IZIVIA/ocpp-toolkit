plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-operation-information"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP Operation Information")
                artifactId = "ocpp-operation-information"
                description.set("OCPP Operation Information")
            }
        }
    }
}
