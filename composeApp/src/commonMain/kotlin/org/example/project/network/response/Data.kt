package org.example.project.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("full")
    val full: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("url")
    val url: String?
)