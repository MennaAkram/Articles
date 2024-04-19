package com.menna.supporttest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.menna.supporttest.data.repository.paging.SearchPagingSource
import com.menna.supporttest.data.source.local.ArticleDao
import com.menna.supporttest.data.source.local.toArticle
import com.menna.supporttest.data.source.local.toLocalDto
import com.menna.supporttest.domain.models.Article
import com.menna.supporttest.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val searchPagingSource: SearchPagingSource,
    private val articleDao: ArticleDao
) : Repository {
    override fun searchForArticles(query: String, page: Int): Pager<Int, Article> {
        searchPagingSource.query = query
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { searchPagingSource },
        )
    }

    override suspend fun getFavoriteArticlesFlow(): Flow<List<Article>> {
        return articleDao.getAllFavoritesFlow().map { it.map { it.toArticle() } }
    }

    override suspend fun getFavoriteArticles(): List<Article> {
        return articleDao.getAllFavorites().map { it.toArticle() }
    }

    override suspend fun updateArticle(article: Article) {
        articleDao.insert(article.toLocalDto())
    }

    override suspend fun deleteArticle(article: Article) {
        articleDao.delete(article.toLocalDto())
    }
}