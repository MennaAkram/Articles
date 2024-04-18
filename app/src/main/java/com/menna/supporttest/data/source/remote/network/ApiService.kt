package com.menna.supporttest.data.source.remote.network

import com.menna.supporttest.data.source.remote.models.ArticleResponse

interface ApiService {
    suspend fun searchForArticles(query: String, page: Int): ArticleResponse?
}