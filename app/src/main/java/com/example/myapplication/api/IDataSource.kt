package com.example.myapplication.api

import com.example.myapplication.model.GitHubUser
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface IDataSource{
    @GET("users")
    fun getGitHubUsers(): Single<GitHubUser>
}