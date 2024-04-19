package com.menna.supporttest.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(articles: List<ArticleLocalDto>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: ArticleLocalDto)

    @Query("SELECT * FROM articles WHERE isFavorite = 1")
    fun getAllFavoritesFlow(): Flow<List<ArticleLocalDto>>

    @Query("SELECT * FROM articles WHERE isFavorite = 1")
    suspend fun getAllFavorites(): List<ArticleLocalDto>

    @Delete
    suspend fun delete(article: ArticleLocalDto)
}