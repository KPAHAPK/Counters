package com.example.myapplication.di.modules

import com.example.myapplication.App
import com.example.myapplication.INetworkStatus
import com.example.myapplication.api.IDataSource
import com.example.myapplication.cache.IUserRepositoryCache
import com.example.myapplication.cache.RoomUserRepositoryCache
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.di.scope.IRepositoryScopeContainer
import com.example.myapplication.di.scope.RepositoryScope
import com.example.myapplication.model.IUserRepository
import com.example.myapplication.model.RetrofitUserRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun repoCache(database: AppDatabase): IUserRepositoryCache = RoomUserRepositoryCache(database)

    @RepositoryScope
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserRepositoryCache
    ): IUserRepository = RetrofitUserRepository(api, networkStatus, cache)

    @RepositoryScope
    @Provides
    fun scopeContainer(app: App): IRepositoryScopeContainer = app
}