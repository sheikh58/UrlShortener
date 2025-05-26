package org.example.project.network

import kotlinx.serialization.Serializable

@Serializable
data class ShortenRequest(val url: String)
