package com.menna.supporttest.domain.repository

import androidx.paging.Pager
import com.menna.supporttest.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun searchForArticles(query: String, page: Int): Pager<Int, Article>
    suspend fun getFavoriteArticlesFlow(): Flow<List<Article>>
    suspend fun getFavoriteArticles(): List<Article>
    suspend fun updateArticle(article: Article)
    suspend fun deleteArticle(article: Article)

}