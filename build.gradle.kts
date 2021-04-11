val exposedVersion: String by project
val kordVersion = "0.7.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.30"
}

group = "me.hechfx.project"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://schlaubi.jfrog.io/artifactory/lavakord")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-hocon:1.1.0")
    api("io.github.microutils:kotlin-logging:2.0.3")
    implementation("com.zaxxer:HikariCP:4.0.3")
    implementation("dev.kord:kord-core:$kordVersion")
    implementation("dev.schlaubi.lavakord:kord:1.0.0-SNAPSHOT")
    implementation("log4j:log4j:1.2.17")
    implementation("org.slf4j:slf4j-simple:1.7.30")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
}

kotlin {
    sourceSets.all {
        languageSettings.enableLanguageFeature("InlineClasses")
    }
}

val fatJar = task("fatJar", type = Jar::class) {
    println("Building fat jar for senichi-kord...")
    baseName = "${project.name}-fat"
    manifest {
        attributes["Main-Class"] = "me.hechfx.project.util.boot.LoadService"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks["jar"] as CopySpec)
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions {
            freeCompilerArgs = listOf("-Xinline-classes")
        }
    }
    "build" {
        dependsOn(fatJar)
    }
}