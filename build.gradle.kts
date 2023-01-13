import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "cy.souvlaki"
version = "1.0-SNAPSHOT"

repositories {
    maven {
        url = uri("https://repo.codemc.io/repository/maven-snapshots/")
    }
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    mavenCentral()
}

dependencies {
    implementation("com.github.retrooper.packetevents:spigot:2.0.0-SNAPSHOT")
    compileOnly("org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT")
    annotationProcessor("org.spigotmc:plugin-annotations:1.2.3-SNAPSHOT")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks {
    getByName<Test>("test") {
        useJUnitPlatform()
    }

    getByName<ShadowJar>("shadowJar") {
        relocate("com.github.retrooper.packetevents.", "cy.souvlaki.packetevents.api.")
        relocate("io.github.retrooper.packetevents.", "cy.souvlaki.packetevents.impl.")
        relocate("net.kyori.", "cy.souvlaki.packetevents.kyori.")
    }
}
