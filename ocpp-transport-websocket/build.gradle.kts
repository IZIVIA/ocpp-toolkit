plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation("org.http4k:http4k-core:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation(project(":ocpp-wamp"))
    implementation(project(":ocpp-transport"))
    implementation(project(":operation-information"))
    implementation(project(":ocpp-2-0-core"))
    implementation(project(":ocpp-1-6-core"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}