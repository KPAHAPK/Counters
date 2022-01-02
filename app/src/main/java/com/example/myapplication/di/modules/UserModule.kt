package com.example.myapplication.di.modules

import com.example.myapplication.App
import com.example.myapplication.INetworkStatus
import com.example.myapplication.api.IDataSource
import com.example.myapplication.cache.IGithubUserCache
import com.example.myapplication.cache.RoomGithubUserCache
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.di.scope.IUserScopeContainer
import com.example.myapplication.di.scope.UserScope
import com.example.myapplication.model.IGitHubUsersRepo
import com.example.myapplication.model.RetrofitGitHubUserRepo
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    fun userCache(database: AppDatabase): IGithubUserCache = RoomGithubUserCache(database)

    @UserScope
    @Provides
    fun userRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IGithubUserCache
    ): IGitHubUsersRepo =
        RetrofitGitHubUserRepo(api, networkStatus, cache)

    @UserScope
    @Provides
    fun scopeContainer(app: App): IUserScopeContainer = app
}