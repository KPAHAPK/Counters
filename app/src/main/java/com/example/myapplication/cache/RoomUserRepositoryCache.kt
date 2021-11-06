package com.example.myapplication.cache

import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.RoomUserRepository
import com.example.myapplication.model.UserRepository

class RoomUserRepositoryCache(private val db: AppDatabase) : IUserRepositoryCache {
    override fun getCache(): List<UserRepository> {
        return db.roomUserRepositoryDao().getAll().map { roomUserRepository ->
            UserRepository(
                roomUserRepository.id,
                roomUserRepository.repoName,
                roomUserRepository.forksCount
            )
        }
    }

    override fun saveCache(roomUserRepositories: List<RoomUserRepository>): Boolean {
        db.roomUserRepositoryDao().insertAll(roomUserRepositories)
        return true
    }
}