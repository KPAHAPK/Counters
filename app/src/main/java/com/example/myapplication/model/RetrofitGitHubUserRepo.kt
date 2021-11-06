package com.example.myapplication.model

import com.example.myapplication.api.IDataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUserRepo(private val api: IDataSource) : IGitHubUsersRepo {
    override fun getGitHubUsers(): Single<List<GitHubUser>> {
        return api.getGitHubUsers().subscribeOn(Schedulers.io())
    }
}