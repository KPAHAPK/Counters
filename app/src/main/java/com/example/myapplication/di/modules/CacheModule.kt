package com.example.myapplication.di.modules

import androidx.room.Room
import com.example.myapplication.App
import com.example.myapplication.cache.IGithubUserCache
import com.example.myapplication.cache.IUserRepositoryCache
import com.example.myapplication.cache.RoomGithubUserCache
import com.example.myapplication.cache.RoomUserRepositoryCache
import com.example.myapplication.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun database(app: App): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DB_NAME).build()

    @Singleton
    @Provides
    fun userCache(database: AppDatabase): IGithubUserCache = RoomGithubUserCache(database)

    @Singleton
    @Provides
    fun repoCache(database: AppDatabase): IUserRepositoryCache = RoomUserRepositoryCache(database)

}