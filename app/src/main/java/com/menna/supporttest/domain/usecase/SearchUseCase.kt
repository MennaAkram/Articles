package com.menna.supporttest.domain.usecase

import com.menna.supporttest.domain.models.Article
import com.menna.supporttest.domain.repository.Repository
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(query: String, page: Int): List<Article>? {
        return if (query.isNotEmpty()) {
            repository.searchForArticles(query, page)?.filterNot {
                it.title.isEmpty() || it.description.isEmpty() || it.url.isEmpty()
                        || it.urlToImage.isEmpty() || it.title == "[Removed]"
            }
        } else {
            emptyList()
        }
    }
}