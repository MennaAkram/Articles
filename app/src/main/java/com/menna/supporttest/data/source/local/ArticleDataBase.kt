package com.menna.supporttest.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticleLocalDto::class], version = 1)
abstract class ArticleDataBase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}