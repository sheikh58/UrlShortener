package org.example.project.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    @SerialName("error")
    val error: Error?,
    @SerialName("success")
    val success: Boolean?
)