package com.example.myapplication.model

import io.reactivex.rxjava3.core.Single

interface IUserRepositories {
    fun getUserRepos(userLogin: String): Single<List<UserRepositories>>
}