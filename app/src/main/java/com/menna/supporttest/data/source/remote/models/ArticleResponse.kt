package com.menna.supporttest.data.source.remote.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    @SerialName("articles")
    val articles: List<ArticleDto?>?,
    @SerialName("status")
    val status: String?,
    @SerialName("totalResults")
    val totalResults: Int?
)