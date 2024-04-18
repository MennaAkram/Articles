package com.menna.supporttest.data.repository

import com.menna.supporttest.data.source.remote.mapper.toArticle
import com.menna.supporttest.data.source.remote.network.ApiService
import com.menna.supporttest.domain.models.Article
import com.menna.supporttest.domain.repository.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val apiService: ApiService
) : Repository {
    override suspend fun searchForArticles(query: String, page: Int): List<Article>? {
        return apiService.searchForArticles(query, page)?.articles?.filterNotNull()?.map { it.toArticle() }
    }
}