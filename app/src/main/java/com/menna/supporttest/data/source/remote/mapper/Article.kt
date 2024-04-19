package com.menna.supporttest.data.source.remote.mapper

import com.menna.supporttest.data.source.remote.models.ArticleDto
import com.menna.supporttest.data.source.remote.models.SourceDto
import com.menna.supporttest.domain.models.Article
import com.menna.supporttest.domain.models.Source
import kotlinx.datetime.LocalDateTime

fun ArticleDto.toArticle() = Article(
    source = source?.toSource(id = title+ publishedAt) ?: Source("", ""),
    title = title ?: "",
    description = description ?: "",
    author = author ?: "",
    url = url ?: "",
    urlToImage = urlToImage ?: "",
    publishedAt = publishedAt?.let { LocalDateTime.parse(publishedAt.removeRange(16, 20)) }
        ?: LocalDateTime(2001, 10, 27, 0, 0, 0),
    isFavorite = false
)

fun SourceDto.toSource(id: String): Source {
  return Source(
    id = id,
    name = name ?: ""
  )
}