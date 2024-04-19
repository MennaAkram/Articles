package com.menna.supporttest.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.menna.supporttest.domain.models.Article
import com.menna.supporttest.domain.models.Source
import com.menna.supporttest.ui.features.search.toFormattedDate
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "articles")
data class ArticleLocalDto(
    @PrimaryKey
    val id : String,
    val name: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val isFavorite: Boolean,
)

fun ArticleLocalDto.toArticle() = Article(
    source = Source(name = name, id = id),
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = LocalDateTime(
        publishedAt.split("-")[2].toInt(),
        publishedAt.split("-")[0].toInt(),
        publishedAt.split("-")[1].toInt(),
        0,
        0,
        0,
        0
    ),
    isFavorite = isFavorite,
)

fun Article.toLocalDto() = ArticleLocalDto(
    id = source.id ?: "",
    name = source.name,
    author = author,
    title = title,
    description = description,
    url = url,
    urlToImage = urlToImage,
    publishedAt = publishedAt.toFormattedDate(),
    isFavorite = isFavorite,
)