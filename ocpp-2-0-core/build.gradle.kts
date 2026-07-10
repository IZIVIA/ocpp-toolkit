plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
    api(project(":ocpp-transport"))
    api(project(":operation-information"))
    api(project(":utils"))
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-2-0-core"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 2.0 Core")
                artifactId = "ocpp-2-0-core"
                description.set("OCPP 2.0 Core")
            }
        }
    }
}
