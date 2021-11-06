package com.example.myapplication.cache

import com.example.myapplication.database.RoomGitHubUser
import com.example.myapplication.database.RoomUserRepository
import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.UserRepository

interface IUserRepositoryCache {
    fun getCache(): List<UserRepository>
    fun saveCache(roomUserRepositories: List<RoomUserRepository>): Boolean
}