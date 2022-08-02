# SLF4J logging support for libGDX

## Usage

As of libGDX 1.11.0, there is only a hacky way to add custom logging to your GDX application.
You have to subclass from `Lwjgl3Application` and override the `setApplicationLogger` method to use `Slf4jApplicationLogger` logger.

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
