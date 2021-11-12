package com.example.myapplication.di.modules

import com.example.myapplication.INetworkStatus
import com.example.myapplication.api.IDataSource
import com.example.myapplication.cache.IGithubUserCache
import com.example.myapplication.model.IGitHubUsersRepo
import com.example.myapplication.model.RetrofitGitHubUserRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun userRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUserCache
    ): IGitHubUsersRepo = RetrofitGitHubUserRepo(api, networkStatus, cache)
}

