package com.menna.supporttest.data.source.remote.network

import android.util.Log
import com.menna.supporttest.data.source.remote.models.Article
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class ApiServiceImp @Inject constructor(
    private val client: HttpClient,
) : ApiService {
    override suspend fun searchForArticles(query: String, page: Int): List<Article>? {
        Log.e("TAG", "searchForArticles: $query")
        return client.get("https://newsapi.org/v2/everything?q=apple&apiKey=a477c5eda245447b81cb3a7fd41f4d4a").body()
    }
}