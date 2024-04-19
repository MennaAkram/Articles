package com.menna.supporttest.data.repository.paging

import com.menna.supporttest.data.source.remote.mapper.toArticle
import com.menna.supporttest.data.source.remote.network.ApiService
import com.menna.supporttest.domain.models.Article
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val apiService: ApiService,
) : BasePagingSource<Article>() {

    lateinit var query: String
    override suspend fun fetchData(page: Int): List<Article> {
        val response = apiService.searchForArticles(query, page)
        return if (response?.code() == 200){
            response.body()?.articles?.filterNotNull()?.map { it.toArticle() } ?: emptyList()
        } else {
            emptyList()
        }
    }

}