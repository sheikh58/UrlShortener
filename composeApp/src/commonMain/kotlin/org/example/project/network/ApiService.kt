package org.example.project.network

import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.example.project.getHttpClient
import org.example.project.network.response.ErrorResponse
import org.example.project.network.response.UriShortSuccess

class ApiService {

    private val baseUrl = "https://ulvis.net/API/write/get"

    private val client = getHttpClient()

    suspend fun makeShortUrl(url: String): String {
        val response = client.post(baseUrl) {
            url {
                parameters.append("url", url)
            }
            contentType(ContentType.Application.Json)
        }

        val json = Json { ignoreUnknownKeys = true }
        val jsonElement = json.parseToJsonElement(response.bodyAsText())

        val jsonObject = jsonElement.jsonObject
        val isSuccess = jsonObject["success"]?.jsonPrimitive?.booleanOrNull

        return if (isSuccess == true) {
            response.body<UriShortSuccess>().data?.url ?: ""
        } else {
            response.body<ErrorResponse>().error?.msg ?: "Invalid url"
        }
    }
}