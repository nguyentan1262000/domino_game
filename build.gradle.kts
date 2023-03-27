plugins {
    id("java")
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

group = "vn.vietdefi.logic-domino"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
    implementation("com.google.code.gson:gson:2.10.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

task("copyDependencies", Copy::class) {
    configurations.compileClasspath.get()
        .filter { it.extension == "jar" }
        .forEach { from(it.absolutePath).into("$buildDir/lib") }
}