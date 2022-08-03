# SLF4J logging support for libGDX

[![Java CI with Gradle](https://github.com/hider/libgdx-slf4j-logger/actions/workflows/gradle.yml/badge.svg)](https://github.com/hider/libgdx-slf4j-logger/actions/workflows/gradle.yml)

An implementation of `com.badlogic.gdx.ApplicationLogger` which uses SLF4J API to log messages by libGDX application log methods, like `Gdx.app.log`, `Gdx.app.error` and `Gdx.app.debug`.

## Usage
First, add the GitHub Packages repository (`https://maven.pkg.github.com/hider/libgdx-slf4j-logger`) and `io.github.hider:libgdx-slf4j-logger:1.0.0` dependency to your project.
Then, add an SLF4J binding like `org.slf4j:slf4j-simple:1.7.36` as a runtime dependency.
Finally, set `Slf4jApplicationLogger` as logger of the `com.badlogic.gdx.Application` instance.
Requires Java 11 or newer.

### Gradle
```kotlin
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/hider/libgdx-slf4j-logger")
    }
}
dependencies {
    implementation("io.github.hider:libgdx-slf4j-logger:1.0.0")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.36")
}
```

[More info](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#using-a-published-package)

### Maven
```xml
<project>
    <repositories>
        <repository>
            <id>github-hider</id>
            <name>GitHub hider/libgdx-slf4j-logger Apache Maven Packages</name>
            <url>https://maven.pkg.github.com/hider/libgdx-slf4j-logger</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>io.github.hider</groupId>
            <artifactId>libgdx-slf4j-logger</artifactId>
            <version>1.0.0</version>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-simple</artifactId>
            <version>1.7.36</version>
            <scope>runtime</scope>
        </dependency>
    </dependencies>
</project>
```

[More info](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry#installing-a-package)

### Setup ApplicationLogger
As of libGDX 1.11.0, there is only a hacky way to add custom logging to your GDX application.
You have to subclass from `Lwjgl3Application` and override the `setApplicationLogger` method to use `Slf4jApplicationLogger` logger.
`Lwjgl3Application` will call this method in its constructor.

Example:
```kotlin
package my.game

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.ApplicationLogger
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import io.github.hider.gdx.slf4j.Slf4jApplicationLogger
import my.game.MyGame

fun main() {
    Application(MyGame(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("Game title")
    })
}

private class Application(listener: ApplicationListener, config: Lwjgl3ApplicationConfiguration) : Lwjgl3Application(listener, config) {
    override fun setApplicationLogger(applicationLogger: ApplicationLogger) {
        super.setApplicationLogger(Slf4jApplicationLogger())
    }
}
```

## License

All source code released under _MIT License_, see [LICENSE](/LICENSE).
