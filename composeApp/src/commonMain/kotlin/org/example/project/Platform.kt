package org.example.project

import io.ktor.client.HttpClient

interface Platform {
    val name: String
}

expect class ContextFactory {
    fun getContext(): Any
}

expect fun getHttpClient(): HttpClient

expect fun getPlatform(): Platform

expect fun openUrl(url:String,context:Any?)

expect fun logMessage(message:String) : Unit