package com.menna.supporttest.di

import com.menna.supporttest.data.repository.Repository
import com.menna.supporttest.data.repository.RepositoryImp
import com.menna.supporttest.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun bindTriviaRepository(triviaService: ApiService): Repository {
        return RepositoryImp(triviaService)
    }
}