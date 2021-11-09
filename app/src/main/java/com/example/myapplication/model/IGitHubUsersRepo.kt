package com.example.myapplication.model

import io.reactivex.rxjava3.core.Single

interface IGitHubUsersRepo {
    fun getGitHubUsers(): Single<List<GitHubUser>>
}