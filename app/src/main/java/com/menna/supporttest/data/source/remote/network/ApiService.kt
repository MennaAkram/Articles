package com.menna.supporttest.data.source.remote.network

import com.menna.supporttest.data.source.remote.models.Article

interface ApiService {
    suspend fun searchForArticles(query: String, page: Int): List<Article>?
}