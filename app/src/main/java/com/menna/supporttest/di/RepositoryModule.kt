package com.menna.supporttest.di

import com.menna.supporttest.data.repository.RepositoryImp
import com.menna.supporttest.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repositoryImp: RepositoryImp): Repository
}