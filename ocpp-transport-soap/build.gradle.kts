plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

dependencies {}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-transport-soap"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP Transport SOAP")
                artifactId = "ocpp-transport-soap"
                description.set("OCPP Transport SOAP")
            }
        }
    }
}
