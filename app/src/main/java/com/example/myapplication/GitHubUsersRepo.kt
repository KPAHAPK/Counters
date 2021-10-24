package com.example.myapplication

class GitHubUsersRepo {
    private val repositories = (0..1000).map { GitHubUser(it, "GitHubUser") }

    fun getUsers(): List<GitHubUser> {
        return repositories
    }
}