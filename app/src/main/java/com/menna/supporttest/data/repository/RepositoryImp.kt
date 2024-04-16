package com.menna.supporttest.data.repository

import com.menna.supporttest.data.source.remote.models.Article
import com.menna.supporttest.data.source.remote.network.ApiService
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val apiService: ApiService
) : Repository {
    override suspend fun searchForArticles(query: String, page: Int): List<Article>? {
        return apiService.searchForArticles(query, page)
    }
}