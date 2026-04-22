import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin

plugins {
    alias(libs.plugins.shadow)
    id("java")
}

group = "de.s42"
version = "2.0.0"

repositories { mavenCentral() }

private val lombok = libs.lombok.get()

subprojects {
    version = rootProject.version
    apply {
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
        compileOnly(lombok)
        annotationProcessor(lombok)
    }
}

tasks.jar { enabled = false }