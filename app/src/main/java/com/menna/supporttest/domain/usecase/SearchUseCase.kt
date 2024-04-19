package com.menna.supporttest.domain.usecase

import androidx.paging.PagingData
import com.menna.supporttest.domain.models.Article
import com.menna.supporttest.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(query: String, page: Int): Flow<PagingData<Article>> {
        return repository.searchForArticles(query, page).flow
    }
}