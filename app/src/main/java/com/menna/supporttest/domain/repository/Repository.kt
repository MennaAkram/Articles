package com.menna.supporttest.domain.repository

import com.menna.supporttest.domain.models.Article

interface Repository {
    suspend fun searchForArticles(query: String, page: Int): List<Article>?
}