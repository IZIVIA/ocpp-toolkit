plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":ocpp-2-0-core"))
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
            artifactId = "ocpp-2-0-api"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 2.0 API")
                artifactId = "ocpp-2-0-api"
                description.set("OCPP 2.0 API")
            }
        }
    }
}
