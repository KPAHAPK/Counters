package com.example.myapplication.model

import com.example.myapplication.INetworkStatus
import com.example.myapplication.api.IDataSource
import com.example.myapplication.cache.IGithubUserCache
import com.example.myapplication.database.RoomGitHubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUserRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val cache: IGithubUserCache
) : IGitHubUsersRepo {

    override fun getGitHubUsers(): Single<List<GitHubUser>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getGitHubUsers().flatMap { githubUsers ->
                    Single.fromCallable {
                        val roomGithubUsers = githubUsers.map { user ->
                            RoomGitHubUser(user.id ?: "", user.login ?: "", user.avatarUrl ?: "")
                        }
                        cache.saveCache(roomGithubUsers)
                        githubUsers
                    }
                }
            } else {
                Single.fromCallable {
                    cache.getCache()
                }
            }
        }.subscribeOn(Schedulers.io())
}