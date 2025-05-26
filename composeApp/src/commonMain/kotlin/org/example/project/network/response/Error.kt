package org.example.project.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Error(
    @SerialName("code")
    val code: Int?,
    @SerialName("msg")
    val msg: String?
)