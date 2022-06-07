plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
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