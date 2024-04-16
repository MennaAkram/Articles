package com.menna.supporttest.data.repository

import com.menna.supporttest.data.source.remote.models.Article

interface Repository {
    suspend fun searchForArticles(query: String, page: Int): List<Article>?
}