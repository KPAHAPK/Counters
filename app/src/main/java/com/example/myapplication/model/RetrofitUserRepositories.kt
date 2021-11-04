package com.example.myapplication.model

import com.example.myapplication.api.IDataSource
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitUserRepositories(private val api: IDataSource): IUserRepositories {
    override fun getUserRepos(userLogin: String): Single<List<UserRepository>> {
        return api.getUserRepositories(userLogin).subscribeOn(Schedulers.io())
    }
}