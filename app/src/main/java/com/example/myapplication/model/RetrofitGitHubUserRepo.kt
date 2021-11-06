package com.example.myapplication.model

import com.example.myapplication.INetworkStatus
import com.example.myapplication.api.IDataSource
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.RoomGitHubUser
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGitHubUserRepo(
    private val api: IDataSource,
    private val networkStatus: INetworkStatus,
    private val db: AppDatabase
) : IGitHubUsersRepo {
    override fun getGitHubUsers(): Single<List<GitHubUser>> = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getGitHubUsers().flatMap { githubUsers ->
                Single.fromCallable {
                    val roomGithubUsers = githubUsers.map { user ->
                        RoomGitHubUser(user.id ?: "", user.login ?: "", user.avatarUrl ?: "")
                    }
                    db.roomGitHubUserDao().insertAll(roomGithubUsers)
                    githubUsers
                }
            }
        } else {
            Single.fromCallable {
                db.roomGitHubUserDao().getAll().map { roomGitHubUser ->
                    GitHubUser(roomGitHubUser.uId, roomGitHubUser.login, roomGitHubUser.avatarUrl)
                }
            }
        }
    }.subscribeOn(Schedulers.io())
}