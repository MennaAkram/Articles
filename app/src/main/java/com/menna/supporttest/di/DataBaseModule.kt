package com.menna.supporttest.di

import android.content.Context
import androidx.room.Room
import com.menna.supporttest.data.source.local.ArticleDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): ArticleDataBase {
        return Room.databaseBuilder(
            context,
            ArticleDataBase::class.java,
            "article_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(ArticleDataBase: ArticleDataBase) = ArticleDataBase.articleDao()
}