package com.menna.supporttest.data.source.remote.network

import com.menna.supporttest.data.source.remote.models.ArticleResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class ApiServiceImp @Inject constructor(
    private val client: HttpClient,
) : ApiService {
    override suspend fun searchForArticles(query: String, page: Int): ArticleResponse? {
        return client.get("https://newsapi.org/v2/everything?q=$query&apiKey=a477c5eda245447b81cb3a7fd41f4d4a").body()
    }
}