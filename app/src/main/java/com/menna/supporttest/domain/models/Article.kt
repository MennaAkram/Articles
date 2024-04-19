package com.menna.supporttest.domain.models

import kotlinx.datetime.LocalDateTime

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: LocalDateTime,
    val isFavorite: Boolean,
)

data class Source(
    val id: String?,
    val name: String
)
