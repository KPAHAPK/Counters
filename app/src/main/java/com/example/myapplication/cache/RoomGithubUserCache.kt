package com.example.myapplication.cache

import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.RoomGitHubUser
import com.example.myapplication.model.GitHubUser

class RoomGithubUserCache(private val db: AppDatabase) : IGithubUserCache {
    override fun getCache(): List<GitHubUser> {
        return db.roomGitHubUserDao().getAll().map { roomGitHubUser ->
            GitHubUser(roomGitHubUser.uId, roomGitHubUser.login, roomGitHubUser.avatarUrl)
        }
    }

    override fun saveCache(roomGithubUsers: List<RoomGitHubUser>): Boolean {
        db.roomGitHubUserDao().insertAll(roomGithubUsers)
        return true
    }
}