plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":generic-api"))
    implementation(project(":ocpp-2-0-core"))
    implementation(project(":ocpp-transport"))
    implementation(project(":operation-information"))
    implementation("org.mapstruct:mapstruct:_")
    kapt("org.mapstruct:mapstruct-processor:_")
    runtimeOnly("ch.qos.logback:logback-classic:_")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-2-0-api-adapter"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 2.0 API Adapter")
                artifactId = "ocpp-2-0-api-adapter"
                description.set("OCPP 2.0 API Adapter")
            }
        }
    }
}
