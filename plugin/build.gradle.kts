dependencies {
    compileOnly(libs.spigot)

    implementation(project(":core"))
    implementation(project(":paper"))
    implementation(project(":spigot"))
}

tasks {
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to project.version)
        }
    }
    jar { enabled = false }
    build { dependsOn(shadowJar) }
    shadowJar {
        archiveBaseName.set(rootProject.name)
        archiveVersion.set(project.version.toString())
        archiveClassifier.set(null as String?)
        destinationDirectory.set(rootProject.layout.buildDirectory.dir("libs"))
        relocate("kotlin", "tr.s42.libs.kotlin")
        minimize()
    }
}
