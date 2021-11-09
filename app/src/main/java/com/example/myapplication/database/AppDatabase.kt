package com.example.myapplication.database

import android.content.Context
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
//        private var instance: AppDatabase? = null
//        fun getInstance() = instance
//            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
//            if (instance == null) {
//                instance = Room.databaseBuilder(context!!, AppDatabase::class.java, DB_NAME).build()
//            }
        }
    }

}