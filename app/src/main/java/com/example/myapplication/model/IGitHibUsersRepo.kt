package com.example.myapplication.model

import io.reactivex.rxjava3.core.Single

interface IGitHibUsersRepo {
    fun getGitHubUsers(): Single<List<GitHubUser>>
}