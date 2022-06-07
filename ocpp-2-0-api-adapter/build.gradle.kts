plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":csms-api"))
    implementation(project(":ocpp-2-0-core"))
    implementation(project(":ocpp-transport"))
    implementation(project(":operation-information"))
    implementation("org.mapstruct:mapstruct:_")
    kapt("org.mapstruct:mapstruct-processor:_")
    runtimeOnly("ch.qos.logback:logback-classic:_")
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