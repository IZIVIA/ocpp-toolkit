plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":ocpp-1-6-core"))
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
            artifactId = "ocpp-1-6-api"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 1.6 API")
                artifactId = "ocpp-1-6-api"
                description.set("OCPP 1.6 API")
            }
        }
    }
}
