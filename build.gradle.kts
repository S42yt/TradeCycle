import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin

plugins {
    kotlin("jvm").version(libs.versions.jvm)
    alias(libs.plugins.shadow)
    id("java")
}

group = "tr.s42"
version = "1.5.0"

repositories { mavenCentral() }

val stdlib: String = libs.stdlib.get().toString()
subprojects {
    version = rootProject.version
    apply {
        apply(plugin = "org.jetbrains.kotlin.jvm")
        plugin<JavaPlugin>()
        plugin<JavaLibraryPlugin>()
        plugin<ShadowPlugin>()
    }
    repositories {
        mavenCentral()
        maven(uri("https://repo.papermc.io/repository/maven-public/"))
        maven(uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots"))
    }
    dependencies {
        implementation(stdlib)
    }
    kotlin {
        jvmToolchain(21)
    }
}

tasks.jar { enabled = false }