plugins {
    `java-library`
    kotlin("jvm") version "1.6.21"
    `maven-publish`
}

group = "io.github.hider"
version = "1.0.1"
description = "SLF4J logging support for libGDX"
java {
    sourceCompatibility = JavaVersion.VERSION_11
    withJavadocJar()
    withSourcesJar()
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.badlogicgames.gdx:gdx:1.11.0")
    api("org.slf4j:slf4j-api:1.7.36")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn")
            jvmTarget = "11"
        }
    }
}

val githubUsername: String? = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/$githubUsername/${project.name}")
            credentials {
                username = githubUsername
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
            pom {
                name.set("${project.group}:${project.name}")
                description.set(project.description)
                url.set("https://github.com/hider/libgdx-slf4j-logger")
                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }
            }
        }
    }
}
