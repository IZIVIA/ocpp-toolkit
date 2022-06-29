plugins {
    kotlin("jvm")
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":ocpp-transport-soap"))
    implementation(project(":ocpp-1-6-core"))

    testImplementation(kotlin("test-junit"))
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
