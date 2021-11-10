package com.example.myapplication.di.modules

import com.example.myapplication.INetworkStatus
import com.example.myapplication.api.IDataSource
import com.example.myapplication.cache.IUserRepositoryCache
import com.example.myapplication.model.IUserRepository
import com.example.myapplication.model.RetrofitUserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource,
        networkStatus: INetworkStatus,
        cache: IUserRepositoryCache
    ): IUserRepository = RetrofitUserRepository(api, networkStatus, cache)

}