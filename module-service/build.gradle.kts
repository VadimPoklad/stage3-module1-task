plugins {
    id("java")
}

group = "com.mjs.school"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {

    implementation(project(mapOf("path" to ":module-repository")))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}


