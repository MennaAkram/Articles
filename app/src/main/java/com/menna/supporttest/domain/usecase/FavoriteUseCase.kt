package com.menna.supporttest.domain.usecase

import com.menna.supporttest.domain.models.Article
import com.menna.supporttest.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend fun getAllFavorites() : Flow<List<Article>> {
        return repository.getFavoriteArticlesFlow()
    }

    suspend fun toggleFavorite(article: Article) {
        if (article.isFavorite) {
            repository.deleteArticle(article)
        } else {
            repository.updateArticle(article.copy(isFavorite = true))
        }
    }

}