package org.example.project

import io.ktor.client.HttpClient
import platform.UIKit.UIDevice
import io.ktor.client.engine.darwin.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.LogLevel
import kotlinx.serialization.json.Json
import platform.Foundation.NSBundle
import platform.Foundation.NSDictionary
import platform.Foundation.NSURL
import platform.Foundation.dictionary
import platform.UIKit.UIApplication


class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getHttpClient(): HttpClient {
    return HttpClient(Darwin) {
        followRedirects = true
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
}

actual fun openUrl(url: String, context: Any?) {
    val url1 = NSURL.URLWithString(url) ?: return
    val app = UIApplication.sharedApplication

    app.openURL(
        url1,
        options = NSDictionary.dictionary(),
        completionHandler = { success ->
            if (success) {
                println("URL opened successfully")
            } else {
                println("Failed to open URL")
            }
        }
    )
}

actual class ContextFactory {
    actual fun getContext(): Any = UIApplication.sharedApplication
}

actual fun logMessage(message: String) {
    print(message)
}