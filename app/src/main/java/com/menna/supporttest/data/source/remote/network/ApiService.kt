package com.menna.supporttest.data.source.remote.network

import com.menna.supporttest.data.source.remote.models.ArticleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("everything/")
    suspend fun searchForArticles(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20,
        @Query("apiKey") apiKey: String = "e090b46ce7d14397bfba265459c4b735",
    ): Response<ArticleResponse>?
}