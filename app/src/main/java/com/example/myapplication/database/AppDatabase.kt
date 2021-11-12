package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RoomGitHubUser::class, RoomUserRepository::class], version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun roomGitHubUserDao(): RoomGitHubUserDao
    abstract fun roomUserRepositoryDao(): RoomUserRepositoryDao

    companion object {
        const val DB_NAME = "github_library.db"

    }
}