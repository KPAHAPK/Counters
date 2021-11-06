package com.example.myapplication.cache

import com.example.myapplication.database.RoomGitHubUser
import com.example.myapplication.model.GitHubUser
import io.reactivex.rxjava3.core.Single

interface IGithubUserCache {
    fun getCache(): List<GitHubUser>
    fun saveCache(roomGithubUsers: List<RoomGitHubUser>): Boolean
}