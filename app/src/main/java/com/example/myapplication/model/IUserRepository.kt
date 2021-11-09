package com.example.myapplication.model

import io.reactivex.rxjava3.core.Single

interface IUserRepository {
    fun getUserRepos(user: GitHubUser): Single<List<UserRepository>>
}