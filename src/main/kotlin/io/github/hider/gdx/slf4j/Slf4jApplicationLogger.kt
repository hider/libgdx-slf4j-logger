package io.github.hider.gdx.slf4j

import com.badlogic.gdx.ApplicationLogger
import org.slf4j.LoggerFactory

class Slf4jApplicationLogger: ApplicationLogger {
    override fun log(tag: String?, message: String?) {
        LoggerFactory.getLogger(tag).info(message)
    }

    override fun log(tag: String?, message: String?, exception: Throwable) {
        LoggerFactory.getLogger(tag).info(message, exception)
    }

    override fun error(tag: String?, message: String?) {
        LoggerFactory.getLogger(tag).error(message)
    }

    override fun error(tag: String?, message: String?, exception: Throwable) {
        LoggerFactory.getLogger(tag).error(message, exception)
    }

    override fun debug(tag: String?, message: String?) {
        LoggerFactory.getLogger(tag).debug(message)
    }

    override fun debug(tag: String?, message: String?, exception: Throwable) {
        LoggerFactory.getLogger(tag).debug(message, exception)
    }
}
