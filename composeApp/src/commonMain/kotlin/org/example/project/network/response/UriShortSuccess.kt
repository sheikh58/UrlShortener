package org.example.project.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UriShortSuccess(
    @SerialName("data")
    val `data`: Data?,
    @SerialName("success")
    val success: Boolean?
)