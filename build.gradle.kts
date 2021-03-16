val exposedVersion: String by project

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
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-hocon:1.1.0")
    api("io.github.microutils:kotlin-logging:2.0.3")
    implementation("com.zaxxer:HikariCP:4.0.3")
    implementation("dev.kord:kord-core:0.7.0-SNAPSHOT")
    implementation("log4j:log4j:1.2.17")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
}

kotlin {
    sourceSets.all {
        languageSettings.enableLanguageFeature("InlineClasses")
    }
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions {
            freeCompilerArgs = listOf("-Xinline-classes")
        }
    }
    jar {
        manifest {
            attributes(mapOf("Main-Class" to "me.hechfx.project.util.boot.LoadService"))
        }
        from(sourceSets.main.get().output)

        dependsOn(configurations.runtimeClasspath)

        from({
            configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
        })
    }
}