package com.menna.supporttest.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
//    @Singleton
//    @Provides
//    fun provideDataStorePref(@ApplicationContext context: Context): DataStorePref {
//        return DataStorePrefImp(context)
//    }
}