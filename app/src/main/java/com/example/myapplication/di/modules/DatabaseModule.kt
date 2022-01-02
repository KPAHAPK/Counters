package com.example.myapplication.di.modules

import androidx.room.Room
import com.example.myapplication.App
import com.example.myapplication.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun database(app: App): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DB_NAME).build()
}