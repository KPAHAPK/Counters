package com.example.myapplication.api

import com.example.myapplication.model.GitHubUser
import com.example.myapplication.model.UserRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {
    @GET("users")
    fun getGitHubUsers(): Single<List<GitHubUser>>

    @GET("users/{username}/repos")
    fun getUserRepositories(@Path("username") username: String): Single<List<UserRepository>>
}