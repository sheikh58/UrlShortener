package org.example.project

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.core.net.toUri
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getHttpClient(): HttpClient {
    return HttpClient(OkHttp) {
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

actual class ContextFactory(private val activity:ComponentActivity) {
    actual fun getContext(): Any = activity.applicationContext
}

actual fun openUrl(url: String, context: Any?) {

    (context as Context).let {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        it.startActivity(intent)
    }
}

actual fun logMessage(message: String) {
    Log.e("TAG", "logMessage: result is $message" )
}