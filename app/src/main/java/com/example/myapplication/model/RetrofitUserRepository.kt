package com.example.myapplication.model

import com.example.myapplication.INetworkStatus
import com.example.myapplication.api.IDataSource
import com.example.myapplication.cache.IUserRepositoryCache
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.RoomUserRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitUserRepository(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IUserRepositoryCache
) :
    IUserRepository {
    override fun getUserRepos(user: GitHubUser): Single<List<UserRepository>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getUserRepositories(user.login).flatMap { userRepositories ->
                    Single.fromCallable {
                        val roomUserRepositories = userRepositories.map { userRepository ->
                            RoomUserRepository(
                                userRepository.id ?: "",
                                userRepository.name ?: "",
                                userRepository.forksCount ?: "",
                                user.id ?: ""
                            )
                        }
                        cache.saveCache(roomUserRepositories)
                        userRepositories
                    }
                }
            } else {
                Single.fromCallable {
                    cache.getCache()
                }
            }
        }.subscribeOn(Schedulers.io())
}